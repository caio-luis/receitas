package com.caioluis.receitas.presentation.base

typealias Reducer<State, Effect> = (state: State, effect: Effect) -> State