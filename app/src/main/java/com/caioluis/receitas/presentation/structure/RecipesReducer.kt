package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.presentation.base.Reducer

interface RecipesReducer : Reducer<RecipesState, RecipesEffect> {
    override fun invoke(state: RecipesState, effect: RecipesEffect): RecipesState
}