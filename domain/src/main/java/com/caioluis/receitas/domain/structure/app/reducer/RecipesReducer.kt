package com.caioluis.receitas.domain.structure.app.reducer

import com.caioluis.receitas.domain.structure.base.Reducer
import com.caioluis.receitas.domain.structure.base.Effect
import com.caioluis.receitas.domain.structure.base.State

interface RecipesReducer : Reducer<State, Effect> {
    override fun invoke(state: State, effect: Effect): State
}