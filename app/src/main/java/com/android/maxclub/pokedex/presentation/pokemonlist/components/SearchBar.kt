package com.android.maxclub.pokedex.presentation.pokemonlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    hint: String,
    text: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val isHintDisplayed = hint.isNotBlank() && text.isEmpty()

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onSearch,
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface, fontSize = 20.sp),
            keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(MaterialTheme.colorScheme.surface, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}