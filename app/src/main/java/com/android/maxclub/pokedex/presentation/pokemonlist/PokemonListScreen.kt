package com.android.maxclub.pokedex.presentation.pokemonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.maxclub.pokedex.presentation.Screen
import com.android.maxclub.pokedex.presentation.pokemonlist.components.PokemonList
import com.android.maxclub.pokedex.presentation.pokemonlist.components.PokemonListTopSection

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val pokemonList = viewModel.pagingPokemonList.collectAsLazyPagingItems()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            PokemonListTopSection(modifier = Modifier.fillMaxWidth())

            PokemonList(
                pokemonList = pokemonList,
                onItemClick = { pokemonName, dominantColor ->
                    navController.navigate("${Screen.PokemonDetail.route}/$pokemonName/$dominantColor")
                }
            )
        }
    }
}