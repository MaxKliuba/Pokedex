package com.android.maxclub.pokedex.domain.repository

import androidx.paging.PagingData
import com.android.maxclub.pokedex.domain.model.Pokemon
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import com.android.maxclub.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonList(pageSize: Int): Flow<PagingData<PokemonListItem>>

    fun getPokemon(pokemonName: String): Flow<Resource<Pokemon>>
}