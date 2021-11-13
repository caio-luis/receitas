package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.presentation.view.RecipeUiEvent
import com.caioluis.receitas.presentation.view.RecipesUiEventTransformer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class RecipesPresenter(
    private val recipesInteractor: RecipesInteractor,
    private val recipesReducer: RecipesReducer,
    private val initialState: RecipesState = RecipesState(),
) {
    val commandSubject = PublishSubject.create<RecipesCommand>()
    val stateSubject = BehaviorSubject.createDefault(initialState)
    private val disposables: MutableList<Disposable> = mutableListOf()
    private val uiEventTransformer: RecipesUiEventTransformer = RecipesUiEventTransformer()

    init {
        disposables += commandSubject.doOnNext {
            disposables += subscribeInteractor(it)
        }.subscribe()
    }

    private fun subscribeInteractor(command: RecipesCommand): Disposable {
        return recipesInteractor(
            state = stateSubject.value ?: initialState,
            command = command
        ).doOnNext { effect ->
            val newState = recipesReducer(
                state = stateSubject.value ?: initialState,
                effect = effect
            )
            stateSubject.onNext(newState)
            stateSubject.subscribe()
        }.subscribe()
    }

    fun dispatchCommand(recipesEvent: RecipeUiEvent) {
        commandSubject.onNext(uiEventTransformer(recipesEvent))
    }

    fun clear() {
        disposables.map {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}