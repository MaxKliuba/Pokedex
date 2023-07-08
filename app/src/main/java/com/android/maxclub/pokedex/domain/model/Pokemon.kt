package com.android.maxclub.pokedex.domain.model

import com.android.maxclub.pokedex.data.remote.dto.PokemonDto
import com.android.maxclub.pokedex.util.POKEMON_IMAGE_BASE_URL
import com.android.maxclub.pokedex.util.POKEMON_IMAGE_FORMAT
import java.util.Locale

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>,
    val weight: Int,
    val height: Int,
    val stats: List<PokemonStat>,
)

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