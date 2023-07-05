package com.android.maxclub.pokedex.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.maxclub.pokedex.data.remote.PokeApi.Companion.STARTING_PAGE_INDEX
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import com.android.maxclub.pokedex.domain.model.toPokemonListItem

class PokemonPagingSource(
    private val pokeApi: PokeApi
) : PagingSource<Int, PokemonListItem>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonListItem>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListItem> =
        try {
            val page = params.key ?: STARTING_PAGE_INDEX

            // loadSize = 3 * pageSize
            val response = pokeApi.getPokemonList(
                limit = params.loadSize,
                offset = page * params.loadSize,
            )

            LoadResult.Page(
                data = response.results.map { it.toPokemonListItem() },
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (response.next == null) null else page + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}