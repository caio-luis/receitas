package com.caioluis.receitas.domain.app.interactor

import com.caioluis.receitas.domain.base.Command
import com.caioluis.receitas.domain.base.Effect
import com.caioluis.receitas.domain.base.Interactor
import com.caioluis.receitas.domain.base.State
import io.reactivex.Observable

interface RecipesInteractor : Interactor<State, Command, Effect> {
    override fun invoke(state: State, command: Command): Observable<Effect>
}