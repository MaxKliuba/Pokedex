package com.android.maxclub.pokedex.data.mappers

import com.android.maxclub.pokedex.data.remote.dto.PokemonListItemDto
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import com.android.maxclub.pokedex.util.POKEMON_IMAGE_BASE_URL
import com.android.maxclub.pokedex.util.POKEMON_IMAGE_FORMAT

fun PokemonListItemDto.toPokemonListItem(): PokemonListItem {
    val id = (if (url.endsWith("/")) url.dropLast(1) else url)
        .takeLastWhile { it.isDigit() }
        .toInt()

    return PokemonListItem(
        id = id,
        pokemonName = name,
        imageUrl = "$POKEMON_IMAGE_BASE_URL$id$POKEMON_IMAGE_FORMAT",
    )
}