package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.app.interactor.RecipesInteractor
import com.caioluis.receitas.domain.app.reducer.RecipesReducer
import com.caioluis.receitas.presentation.ui.RecipeUiEvent
import com.caioluis.receitas.presentation.ui.RecipesUiEventTransformer
import com.caioluis.receitas.util.BaseSchedulerProvider
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class RecipesPresenter(
    private val recipesInteractor: RecipesInteractor,
    private val recipesReducer: RecipesReducer,
    private val initialState: RecipesState = RecipesState(),
    schedulerProvider: BaseSchedulerProvider
) {
    private val uiEventTransformer: RecipesUiEventTransformer = RecipesUiEventTransformer()
    private val commandSubject = PublishSubject.create<RecipesCommand>()
    private val disposables: MutableList<Disposable> = mutableListOf()

    val stateSubject = BehaviorSubject.createDefault(initialState)

    init {
        disposables += commandSubject
            .distinctUntilChanged()
            .flatMap { command ->
                recipesInteractor(
                    state = stateSubject.value ?: initialState,
                    command = command
                )
            }.map { effect ->
                recipesReducer(
                    state = stateSubject.value ?: initialState,
                    effect = effect
                )
            }.subscribeOn(schedulerProvider.ui())
            .observeOn(schedulerProvider.ui())
            .subscribe { stateSubject.onNext(it as RecipesState) }
    }

    fun dispatchCommand(recipesEvent: RecipeUiEvent) {
        commandSubject.onNext(uiEventTransformer(recipesEvent))
    }

    fun clear() {
        disposables.map {
            if (!it.isDisposed)
                it.dispose()
        }
    }
}