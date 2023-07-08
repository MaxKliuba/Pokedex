package com.android.maxclub.pokedex.presentation.pokemonlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.maxclub.pokedex.R

@Composable
fun PokemonListError(
    errorMessage: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = errorMessage,
                fontSize = 16.sp,

                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

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
}