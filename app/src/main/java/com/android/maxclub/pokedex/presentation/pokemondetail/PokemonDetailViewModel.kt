package com.android.maxclub.pokedex.presentation.pokemondetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.maxclub.pokedex.R
import com.android.maxclub.pokedex.domain.repository.PokemonRepository
import com.android.maxclub.pokedex.util.Screen
import com.android.maxclub.pokedex.util.LocalString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val pokemonName: String =
        savedStateHandle.get<String>(Screen.PokemonDetail.ARG_POKEMON_NAME) ?: ""
    val dominantColor: Color = savedStateHandle.get<Int>(Screen.PokemonDetail.ARG_DOMINANT_COLOR)
        ?.let { Color(it) } ?: Color.White

    private val _uiState = mutableStateOf<PokemonDetailUiState>(PokemonDetailUiState.Loading)
    val uiState: State<PokemonDetailUiState> = _uiState

    private var fetchPokemonJob: Job? = null

    init {
        fetchPokemon()
    }

    fun fetchPokemon() {
        fetchPokemonJob?.cancel()
        fetchPokemonJob = pokemonRepository.getPokemon(pokemonName)
            .onStart {
                _uiState.value = PokemonDetailUiState.Loading
            }
            .onEach { pokemon ->
                _uiState.value = PokemonDetailUiState.Success(pokemon)
            }
            .catch { e ->
                _uiState.value = PokemonDetailUiState.Error(
                    e.message?.let { LocalString.Dynamic(it) }
                        ?: LocalString.Resource(R.string.some_error_message)
                )
                e.printStackTrace()
            }
            .launchIn(viewModelScope)
    }
}