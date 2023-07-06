package com.android.maxclub.pokedex.presentation.pokemonlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.maxclub.pokedex.R

@Composable
fun PokemonListTopSection(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Black,
                        Color.Transparent,
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pokemon_logo),
            contentDescription = "Pokemon",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}