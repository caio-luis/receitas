package com.caioluis.receitas.domain.base

typealias Reducer<State, Effect> = (state: State, effect: Effect) -> State