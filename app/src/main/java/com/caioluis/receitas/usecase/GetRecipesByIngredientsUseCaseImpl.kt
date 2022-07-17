package com.caioluis.receitas.usecase

import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.domain.base.Effect
import com.caioluis.receitas.domain.model.DomainRecipe
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.presentation.mapper.toViewModel
import com.caioluis.receitas.presentation.structure.RecipesEffect
import com.caioluis.receitas.util.BaseSchedulerProvider
import io.reactivex.Observable

class GetRecipesByIngredientsUseCaseImpl(
    private val dataSource: RecipesDataSource,
    private val scheduler: BaseSchedulerProvider
) : GetRecipesByIngredientsUseCase {

    override fun invoke(ingredients: List<String>): Observable<Effect> {
        return getRecipesEffect(dataSource.getRecipes(ingredients)) as Observable<Effect>
    }

    private fun getRecipesEffect(observable: Observable<List<DomainRecipe>>): Observable<RecipesEffect> {
        return observable
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .map(::handleResult)
            .startWith(RecipesEffect.Loading)
            .onErrorReturn(RecipesEffect::Error)
    }

    private fun handleResult(recipes: List<DomainRecipe>): RecipesEffect =
        RecipesEffect.ShowRecipes(recipes.map { it.toViewModel() })
}
