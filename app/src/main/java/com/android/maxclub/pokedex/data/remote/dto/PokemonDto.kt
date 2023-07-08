package com.android.maxclub.pokedex.data.remote.dto

data class PokemonDto(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeDto>,
    val weight: Int,
    val height: Int,
    val stats: List<StatDto>,
)