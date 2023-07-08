package com.android.maxclub.pokedex.data.mappers

import com.android.maxclub.pokedex.data.remote.dto.PokemonDto
import com.android.maxclub.pokedex.data.remote.dto.PokemonTypeDto
import com.android.maxclub.pokedex.data.remote.dto.StatDto
import com.android.maxclub.pokedex.domain.model.Pokemon
import com.android.maxclub.pokedex.domain.model.PokemonStat
import com.android.maxclub.pokedex.domain.model.PokemonType
import com.android.maxclub.pokedex.util.POKEMON_IMAGE_BASE_URL
import com.android.maxclub.pokedex.util.POKEMON_IMAGE_FORMAT

fun PokemonDto.toPokemon(): Pokemon =
    Pokemon(
        id = id,
        name = name,
        imageUrl = "$POKEMON_IMAGE_BASE_URL$id$POKEMON_IMAGE_FORMAT",
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