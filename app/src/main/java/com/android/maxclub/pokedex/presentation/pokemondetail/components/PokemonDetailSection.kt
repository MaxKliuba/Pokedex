package com.android.maxclub.pokedex.presentation.pokemondetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.android.maxclub.pokedex.domain.model.Pokemon
import java.util.Locale

@Composable
fun PokemonDetailSection(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(scrollState)
    ) {
        Text(
            text = "#${pokemon.id} ${pokemon.name.capitalize(Locale.ROOT)}",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        PokemonTypeSection(types = pokemon.types)

        PokemonDataSection(
            pokemonWeight = pokemon.weight,
            pokemonHeight = pokemon.height
        )

        PokemonBaseStatsSection(pokemonStats = pokemon.stats)
    }
}