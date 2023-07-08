package com.android.maxclub.pokedex.domain.model

import com.android.maxclub.pokedex.data.remote.dto.StatDto

data class PokemonStat(
    val name: String,
    val value: Int,
)

fun StatDto.toPokemonStat(): PokemonStat =
    PokemonStat(
        name = stat.name,
        value = baseStat,
    )