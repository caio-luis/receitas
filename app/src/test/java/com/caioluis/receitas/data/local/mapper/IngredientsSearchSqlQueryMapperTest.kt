package com.caioluis.receitas.data.local.mapper

import org.junit.Assert.assertEquals
import org.junit.Test

class IngredientsSearchSqlQueryMapperTest {

    private val ingredientsSearchSqlQueryMapper: IngredientsSearchSqlQueryMapper =
        IngredientsSearchSqlQueryMapper.Impl()

    @Test
    fun `assert that query is correct with 1 parameter`() {
        val parameters = listOf("teste")

        val query = ingredientsSearchSqlQueryMapper(parameters).sql

        assertEquals("SELECT * FROM receitas WHERE ingredients LIKE '%teste%'", query)
    }

    @Test
    fun `assert that query is correct with more than 1 parameter`() {
        val parameters = listOf("teste", "teste2")

        val query = ingredientsSearchSqlQueryMapper(parameters).sql

        assertEquals(
            "SELECT * FROM receitas WHERE ingredients LIKE '%teste%' " +
                    "OR ingredients LIKE '%teste2%'", query
        )
    }

    @Test
    fun `assert that query is correct with more than 1 paramete`() {
        val parameters = listOf<String>()

        val query = ingredientsSearchSqlQueryMapper(parameters).sql

        assertEquals("", query)
    }
}