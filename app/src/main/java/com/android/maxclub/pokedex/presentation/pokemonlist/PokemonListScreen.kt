package com.android.maxclub.pokedex.presentation.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.maxclub.pokedex.R
import com.android.maxclub.pokedex.presentation.Screen
import com.android.maxclub.pokedex.presentation.pokemonlist.components.PokemonListItem

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
            Image(
                painter = painterResource(id = R.drawable.ic_pokemon_logo),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                contentPadding = PaddingValues(8.dp),
                content = {
                    items(
                        count = pokemonList.itemCount,
                        key = { pokemonList[it]?.id ?: 0 }
                    ) { index ->
                        pokemonList[index]?.let { pokemonListItem ->
                            PokemonListItem(
                                pokemonListItem = pokemonListItem,
                                onClick = { pokemonName, dominantColor ->
                                    navController.navigate("${Screen.PokemonDetail.route}/$pokemonName/$dominantColor")
                                },
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                })
        }
    }
}