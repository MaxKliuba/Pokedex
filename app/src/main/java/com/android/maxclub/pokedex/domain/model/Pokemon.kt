package com.android.maxclub.pokedex.domain.model

import com.android.maxclub.pokedex.data.remote.dto.PokemonDto

data class Pokemon(
    val id: Int,
    val name: String,
)

fun PokemonDto.toPokemon(): Pokemon =
    TODO("Not yet implemented")