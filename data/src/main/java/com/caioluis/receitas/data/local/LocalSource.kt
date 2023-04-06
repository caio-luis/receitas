package com.caioluis.receitas.data.local

import com.caioluis.receitas.domain.model.DomainRecipe
import io.reactivex.Observable

interface LocalSource {
    fun getRecipes(ingredients: List<String>): Observable<List<DomainRecipe>>
}
