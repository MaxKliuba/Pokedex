package com.android.maxclub.pokedex.domain.model

import com.android.maxclub.pokedex.data.remote.dto.PokemonTypeDto

data class PokemonType(
    val name: String,
)

fun PokemonTypeDto.toPokemonType(): PokemonType =
    PokemonType(name = type.name)