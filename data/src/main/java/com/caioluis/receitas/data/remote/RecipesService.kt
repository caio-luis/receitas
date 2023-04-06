package com.caioluis.receitas.data.remote

import com.caioluis.receitas.data.remote.model.RecipeRemote
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryName

interface RecipesService {
    @GET("/recipes")
    fun getRecipes(@QueryName ingredients: List<String>): Observable<List<RecipeRemote>>
}
