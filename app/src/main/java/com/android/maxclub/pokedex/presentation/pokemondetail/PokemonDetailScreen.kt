package com.android.maxclub.pokedex.presentation.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.maxclub.pokedex.R
import com.android.maxclub.pokedex.domain.model.Pokemon
import com.android.maxclub.pokedex.presentation.pokemondetail.components.PokemonDetailTopSection
import java.util.Locale

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val state: PokemonDetailUiState by viewModel.uiState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(viewModel.dominantColor)
            .padding(bottom = 16.dp)
    ) {
        PokemonDetailTopSection(
            onBackClick = navController::navigateUp,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter)
        )

        state.let { state ->
            when (state) {
                PokemonDetailUiState.Loading -> CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )

                is PokemonDetailUiState.Success -> PokemonDetailSection(
                    pokemon = state.pokemon,
                    modifier = Modifier.align(Alignment.Center)
                )

                is PokemonDetailUiState.Error -> PokemonDetailError(
                    errorMessage = state.errorMessage.asString(),
                    onRetry = viewModel::fetchPokemon,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun PokemonDetailSection(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Text(
        text = pokemon.name.capitalize(Locale.ROOT),
        modifier = modifier
    )
}

@Composable
fun PokemonDetailError(
    errorMessage: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = errorMessage)

        Button(onClick = onRetry) {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.retry_text))
        }
    }
}