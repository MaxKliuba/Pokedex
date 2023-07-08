package com.android.maxclub.pokedex.presentation.pokemonlist.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.android.maxclub.pokedex.domain.model.PokemonListItem

@Composable
fun PokemonList(
    pokemonList: LazyPagingItems<PokemonListItem>,
    onItemClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 8.dp),
        modifier = modifier
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            PokemonListLoadState(
                state = pokemonList.loadState.prepend,
                onRetry = pokemonList::retry
            )

            PokemonListLoadState(
                state = pokemonList.loadState.refresh,
                onRetry = pokemonList::retry
            )
        }

        items(
            count = pokemonList.itemCount,
            key = { pokemonList[it]?.id ?: 0 }
        ) { index ->
            pokemonList[index]?.let { pokemonListItem ->
                PokemonListItem(
                    pokemonListItem = pokemonListItem,
                    onClick = onItemClick,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            PokemonListLoadState(
                state = pokemonList.loadState.append,
                onRetry = pokemonList::retry
            )
        }
    }
}