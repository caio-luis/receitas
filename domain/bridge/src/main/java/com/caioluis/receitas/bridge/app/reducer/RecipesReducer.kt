package com.caioluis.receitas.bridge.app.reducer

import com.caioluis.receitas.bridge.base.Effect
import com.caioluis.receitas.bridge.base.Reducer
import com.caioluis.receitas.bridge.base.State

interface RecipesReducer : Reducer<State, Effect> {
    override fun invoke(state: State, effect: Effect): State
}