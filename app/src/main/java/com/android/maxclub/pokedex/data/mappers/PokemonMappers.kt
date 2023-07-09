package com.android.maxclub.pokedex.data.mappers

import com.android.maxclub.pokedex.data.remote.dto.PokemonDto
import com.android.maxclub.pokedex.data.remote.dto.PokemonTypeDto
import com.android.maxclub.pokedex.data.remote.dto.StatDto
import com.android.maxclub.pokedex.domain.model.Pokemon
import com.android.maxclub.pokedex.domain.model.PokemonStat
import com.android.maxclub.pokedex.domain.model.PokemonType

fun PokemonDto.toPokemon(): Pokemon =
    Pokemon(
        id = id,
        name = name,
        imageUrl = sprites.other.dreamWorld.frontDefault
            ?: sprites.other.officialArtwork.frontDefault
            ?: sprites.other.home.frontDefault
            ?: sprites.frontDefault
            ?: "",
        types = types.map { it.toPokemonType() },
        weight = weight,
        height = height,
        stats = stats.map { it.toPokemonStat() },
    )

fun StatDto.toPokemonStat(): PokemonStat =
    PokemonStat(
        name = stat.name,
        value = baseStat,
    )

fun PokemonTypeDto.toPokemonType(): PokemonType =
    PokemonType(name = type.name)