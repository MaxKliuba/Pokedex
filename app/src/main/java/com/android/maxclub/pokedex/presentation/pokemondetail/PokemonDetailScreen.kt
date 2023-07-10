package com.android.maxclub.pokedex.presentation.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.maxclub.pokedex.presentation.pokemondetail.components.PokemonDetailErrorSection
import com.android.maxclub.pokedex.presentation.pokemondetail.components.PokemonDetailSection
import com.android.maxclub.pokedex.presentation.pokemondetail.components.PokemonDetailTopSection
import com.android.maxclub.pokedex.presentation.pokemondetail.components.PokemonImageSection

@Composable
fun PokemonDetailScreen(
    navController: NavController,
    viewModel: PokemonDetailViewModel = hiltViewModel(),
) {
    val state: PokemonDetailUiState by viewModel.uiState

    val pokemonImageSize: Dp = 200.dp

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = pokemonImageSize / 2f,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                    .shadow(10.dp, RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .align(Alignment.BottomCenter)
            ) {
                when (state) {
                    PokemonDetailUiState.Loading -> CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    is PokemonDetailUiState.Success -> PokemonDetailSection(
                        pokemon = state.pokemon,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = pokemonImageSize / 2f)
                    )

                    is PokemonDetailUiState.Error -> PokemonDetailErrorSection(
                        errorMessage = state.errorMessage.asString(),
                        onRetry = viewModel::fetchPokemon,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            if (state is PokemonDetailUiState.Success) {
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    PokemonImageSection(
                        pokemonImageUrl = state.pokemon.imageUrl,
                        modifier = Modifier
                            .size(pokemonImageSize)
                            .padding(top = 20.dp)
                    )
                }
            }
        }
    }
}