package com.caioluis.receitas.domain

import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("\$oid")
    val id: String
)