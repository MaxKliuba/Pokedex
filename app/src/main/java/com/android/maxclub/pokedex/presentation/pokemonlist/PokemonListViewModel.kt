package com.android.maxclub.pokedex.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import com.android.maxclub.pokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 50

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val pagingPokemonList: Flow<PagingData<PokemonListItem>> =
        pokemonRepository.getPagingPokemonList(PAGE_SIZE)
            .cachedIn(viewModelScope)
}