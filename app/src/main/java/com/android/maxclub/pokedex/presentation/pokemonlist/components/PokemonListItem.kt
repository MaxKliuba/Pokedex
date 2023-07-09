package com.android.maxclub.pokedex.presentation.pokemonlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.android.maxclub.pokedex.R
import com.android.maxclub.pokedex.domain.model.PokemonListItem
import com.android.maxclub.pokedex.util.calculateDominantColor
import java.util.Locale

@Composable
fun PokemonListItem(
    pokemonListItem: PokemonListItem,
    onClick: (String, Int) -> Unit,
    modifier: Modifier
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor,
                        defaultDominantColor,
                    )
                )
            )
            .clickable {
                onClick(pokemonListItem.pokemonName, dominantColor.toArgb())
            }
    ) {
        Column {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemonListItem.imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .crossfade(true)
                    .build(),
                loading = {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 12.dp,
                        modifier = Modifier.scale(0.35f)
                    )
                },
                onSuccess = {
                    calculateDominantColor(it.result.drawable) { color ->
                        dominantColor = color
                    }
                },
                error = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_unknown_pokemon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                    )
                },
                contentDescription = pokemonListItem.pokemonName,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp)
            )
            Text(
                text = "#${pokemonListItem.id} ${pokemonListItem.pokemonName.capitalize(Locale.ROOT)}",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}