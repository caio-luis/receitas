package com.caioluis.receitas.bridge.base

import io.reactivex.Observable

typealias Interactor<State, Command, Effect> =
            (state: State, command: Command) -> Observable<Effect>