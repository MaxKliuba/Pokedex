package com.android.maxclub.pokedex.domain.repository

import androidx.paging.PagingData
import com.android.maxclub.pokedex.domain.model.Pokemon
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPagingPokemonList(pageSize: Int): Flow<PagingData<PokemonListItem>>

    fun getPokemon(pokemonName: String): Flow<Pokemon>
}