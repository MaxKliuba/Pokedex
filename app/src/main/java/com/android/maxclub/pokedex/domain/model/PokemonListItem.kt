package com.android.maxclub.pokedex.domain.model

import com.android.maxclub.pokedex.data.remote.dto.PokemonListItemDto
import com.android.maxclub.pokedex.util.POKEMON_IMAGE_URL
import java.util.Locale

data class PokemonListItem(
    val id: Int,
    val pokemonName: String,
    val imageUrl: String,
)

fun PokemonListItemDto.toPokemonListItem(): PokemonListItem {
    val id = (if (url.endsWith("/")) url.dropLast(1) else url)
        .takeLastWhile { it.isDigit() }
        .toInt()

    return PokemonListItem(
        id = id,
        pokemonName = name.capitalize(Locale.ROOT),
        imageUrl = "$POKEMON_IMAGE_URL$id.png",
    )
}
