package com.android.maxclub.pokedex.presentation.pokemonlist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.android.maxclub.pokedex.R

@Composable
fun PokemonListLoadState(
    state: LoadState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when (state) {
            is LoadState.Loading -> PokemonListLoading(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            is LoadState.Error -> PokemonListError(
                errorMessage = state.error.message ?: stringResource(R.string.some_error_message),
                onRetry = onRetry,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            is LoadState.NotLoading -> Unit
        }
    }
}