package com.caioluis.receitas.data.remote

import com.caioluis.receitas.data.remote.mapper.RecipesRemoteMapper
import com.caioluis.receitas.domain.model.DomainRecipe
import io.reactivex.Observable

class RemoteImpl(
    private val service: RecipesService,
    private val mapper: RecipesRemoteMapper,
) : RemoteSource {
    override fun getRecipes(ingredients: List<String>): Observable<List<DomainRecipe>> {
        return service.getRecipes(ingredients)
            .map { mapper.mapRecipes(it) }
    }
}
