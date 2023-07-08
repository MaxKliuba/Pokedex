package com.android.maxclub.pokedex.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StatDto(
    @SerializedName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: PokemonItemDto,
)