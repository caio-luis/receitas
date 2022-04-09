package com.caioluis.receitas.domain.structure.app.interactor

import com.caioluis.receitas.domain.structure.base.Interactor
import com.caioluis.receitas.domain.structure.base.Effect
import com.caioluis.receitas.domain.structure.base.State
import com.caioluis.receitas.domain.structure.base.Command
import io.reactivex.Observable

interface RecipesInteractor : Interactor<State, Command, Effect> {
    override fun invoke(state: State, command: Command): Observable<Effect>
}