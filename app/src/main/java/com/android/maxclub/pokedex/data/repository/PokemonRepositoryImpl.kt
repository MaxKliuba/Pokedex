package com.android.maxclub.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.android.maxclub.pokedex.data.mappers.toPokemon
import com.android.maxclub.pokedex.data.mappers.toPokemonListItem
import com.android.maxclub.pokedex.data.remote.PokeApi
import com.android.maxclub.pokedex.data.remote.PokemonPagingSource
import com.android.maxclub.pokedex.domain.model.Pokemon
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import com.android.maxclub.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi
) : PokemonRepository {

    override fun getPagingPokemonList(pageSize: Int): Flow<PagingData<PokemonListItem>> =
        Pager(
            config = PagingConfig(
                pageSize = pageSize,
            ),
            pagingSourceFactory = {
                PokemonPagingSource(pokeApi)
            }
        )
            .flow
            .map { pagingData ->
                pagingData.map { it.toPokemonListItem() }
            }

    override fun getPokemon(pokemonName: String): Flow<Pokemon> = flow {
        emit(
            pokeApi.getPokemon(pokemonName).toPokemon()
        )
    }.flowOn(Dispatchers.IO)
}