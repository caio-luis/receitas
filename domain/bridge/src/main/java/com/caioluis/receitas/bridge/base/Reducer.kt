package com.caioluis.receitas.bridge.base

typealias Reducer<State, Effect> = (state: State, effect: Effect) -> State