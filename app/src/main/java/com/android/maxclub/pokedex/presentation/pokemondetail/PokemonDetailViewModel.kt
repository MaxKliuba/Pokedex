package com.android.maxclub.pokedex.presentation.pokemondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.maxclub.pokedex.domain.repository.PokemonRepository
import com.android.maxclub.pokedex.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    val dominantColor: Int? = savedStateHandle.get<Int>(Screen.PokemonDetail.ARG_DOMINANT_COLOR)

    init {
        savedStateHandle.get<String>(Screen.PokemonDetail.ARG_POKEMON_NAME)?.let { pokemonName ->
            pokemonRepository.getPokemon(pokemonName)
                .onEach {
                    // TODO
                }
                .launchIn(viewModelScope)
        }
    }
}