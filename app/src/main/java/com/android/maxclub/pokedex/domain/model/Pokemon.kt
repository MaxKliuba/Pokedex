package com.android.maxclub.pokedex.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>,
    val weight: Int,
    val height: Int,
    val stats: List<PokemonStat>,
)