package com.caioluis.receitas.domain.model

data class DomainSection(
    val sectionName: String? = "",
    val content: List<String>? = listOf()
)
