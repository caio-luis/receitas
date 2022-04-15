package com.caioluis.receitas.bridge.app.interactor

import com.caioluis.receitas.bridge.base.Command
import com.caioluis.receitas.bridge.base.Effect
import com.caioluis.receitas.bridge.base.Interactor
import com.caioluis.receitas.bridge.base.State
import io.reactivex.Observable

interface RecipesInteractor : Interactor<State, Command, Effect> {
    override fun invoke(state: State, command: Command): Observable<Effect>
}