package com.caioluis.receitas.domain.structure.base

typealias Reducer<State, Effect> = (state: State, effect: Effect) -> State