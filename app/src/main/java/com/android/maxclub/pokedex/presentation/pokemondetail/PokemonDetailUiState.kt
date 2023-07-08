package com.android.maxclub.pokedex.presentation.pokemondetail

import com.android.maxclub.pokedex.domain.model.Pokemon
import com.android.maxclub.pokedex.util.LocalString

sealed class PokemonDetailUiState {
    object Loading : PokemonDetailUiState()
    data class Success(val pokemon: Pokemon) : PokemonDetailUiState()
    data class Error(val errorMessage: LocalString) : PokemonDetailUiState()
}