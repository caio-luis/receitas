package com.caioluis.receitas.data.remote

import com.caioluis.receitas.domain.model.DomainRecipe
import io.reactivex.Observable

interface RemoteSource {
    fun getRecipes(ingredients: List<String>): Observable<List<DomainRecipe>>
}
