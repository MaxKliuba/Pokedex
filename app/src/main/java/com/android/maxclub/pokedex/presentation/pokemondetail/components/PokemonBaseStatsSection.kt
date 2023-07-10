package com.android.maxclub.pokedex.presentation.pokemondetail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.maxclub.pokedex.R
import com.android.maxclub.pokedex.domain.model.PokemonStat
import com.android.maxclub.pokedex.ui.theme.Grey
import com.android.maxclub.pokedex.ui.theme.LightGrey
import com.android.maxclub.pokedex.util.toColor
import com.android.maxclub.pokedex.util.toPokemonStatAbbr

@Composable
fun PokemonBaseStatsSection(
    pokemonStats: List<PokemonStat>,
    modifier: Modifier = Modifier,
    animDelayPerItem: Int = 100,
) {
    val maxBaseStat = remember {
        pokemonStats.maxOf { it.value }
    }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.base_stats_title),
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))

        for (i in pokemonStats.indices) {
            val stat = pokemonStats[i]
            PokemonStatItem(
                statName = stat.name.toPokemonStatAbbr(),
                statValue = stat.value,
                statMaxValue = maxBaseStat,
                statColor = stat.toColor(),
                animDelay = i * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun PokemonStatItem(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    modifier: Modifier = Modifier,
    animDuration: Int = 1000,
    animDelay: Int = 0,
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) statValue / statMaxValue.toFloat() else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(28.dp)
            .clip(CircleShape)
            .background(if (isSystemInDarkTheme()) Grey else LightGrey)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(currentPercent.value)
                .clip(CircleShape)
                .background(statColor)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = statName,
                fontWeight = FontWeight.Bold,
                color = if (isSystemInDarkTheme()) Color.Black else Color.White,
            )
            Text(
                text = (currentPercent.value * statMaxValue).toInt().toString(),
                fontWeight = FontWeight.Bold,
                color = if (isSystemInDarkTheme()) Color.Black else Color.White,
            )
        }
    }
}