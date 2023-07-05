package com.android.maxclub.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.maxclub.pokedex.data.remote.PokeApi
import com.android.maxclub.pokedex.data.remote.PokemonPagingSource
import com.android.maxclub.pokedex.domain.model.Pokemon
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import com.android.maxclub.pokedex.domain.model.toPokemon
import com.android.maxclub.pokedex.domain.repository.PokemonRepository
import com.android.maxclub.pokedex.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi
) : PokemonRepository {

    override fun getPokemonList(pageSize: Int): Flow<PagingData<PokemonListItem>> =
        Pager(
            config = PagingConfig(
                pageSize = pageSize,
            ),
            pagingSourceFactory = {
                PokemonPagingSource(pokeApi)
            }
        ).flow

    override fun getPokemon(pokemonName: String): Flow<Resource<Pokemon>> = flow {
        emit(Resource.Loading())

        try {
            val response = pokeApi.getPokemon(pokemonName)

            emit(Resource.Success(response.toPokemon()))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)
}