package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.presentation.view.RecipeUiEvent
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class RecipesPresenter(
        initialState: RecipesState,
        recipesInteractor: RecipesInteractor,
        recipesReducer: RecipesReducer
) {
    private val commandSubject = PublishSubject.create<RecipesCommand>()
    private val uiEventSubject = PublishSubject.create<RecipeUiEvent>()
    private val stateSubject = BehaviorSubject.createDefault(initialState)
    private val disposables: MutableList<Disposable> = mutableListOf()

    init {
        disposables += commandSubject.subscribe {
            disposables += recipesInteractor(stateSubject.value
                    ?: initialState, it).doOnNext { effect ->
                recipesReducer(stateSubject.value ?: initialState, effect)
            }.subscribe()
        }
    }
}