package com.android.maxclub.pokedex.presentation.pokemondetail.components

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.android.maxclub.pokedex.R

@Composable
fun PokemonImageSection(
    pokemonImageUrl: String,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(pokemonImageUrl)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(true)
            .build(),
        loading = {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 16.dp,
                modifier = Modifier.scale(0.25f)
            )
        },
        error = {
            Image(
                painter = painterResource(id = R.drawable.ic_unknown_pokemon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
            )
        },
        contentDescription = null,
        modifier = modifier
    )
}