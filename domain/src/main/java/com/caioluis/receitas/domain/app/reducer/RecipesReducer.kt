package com.caioluis.receitas.domain.app.reducer

import com.caioluis.receitas.domain.base.Effect
import com.caioluis.receitas.domain.base.Reducer
import com.caioluis.receitas.domain.base.State

interface RecipesReducer : Reducer<State, Effect> {
    override fun invoke(state: State, effect: Effect): State
}