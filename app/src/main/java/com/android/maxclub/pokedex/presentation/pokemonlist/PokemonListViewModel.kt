package com.android.maxclub.pokedex.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import com.android.maxclub.pokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

private const val DEFAULT_PAGE_SIZE = 50
private const val SEARCH_PAGE_SIZE = 100
private const val SEARCH_QUERY_MAX_LENGTH = 50

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val pagingPokemonList: Flow<PagingData<PokemonListItem>> =
        _searchQuery
            .debounce(500.milliseconds)
            .flatMapLatest { query ->
                val isSearching = query.isNotBlank()

                pokemonRepository.getPagingPokemonList(
                    pageSize = if (isSearching) SEARCH_PAGE_SIZE else DEFAULT_PAGE_SIZE
                ).map { pagingData ->
                    if (isSearching) {
                        pagingData.filter {
                            it.pokemonName.startsWith(query.trim(), ignoreCase = true)
                        }
                    } else {
                        pagingData
                    }
                }
            }.cachedIn(viewModelScope)

    fun onSearch(query: String) {
        if (query.length <= SEARCH_QUERY_MAX_LENGTH) {
            _searchQuery.value = query
        }
    }
}