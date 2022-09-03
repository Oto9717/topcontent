package com.siradze.movies.moviesList.ui.composables
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.siradze.movies.R
import com.siradze.movies.moviesList.ui.mvi.SearchState

@Composable
internal fun SearchBar(state : SearchState, modifier: Modifier, onQueryChange : (String, Boolean)->Unit)
{

    val focused = remember {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    BackHandler(enabled = focused.value) {
        focusManager.clearFocus()
    }

    Card(modifier = modifier, shape = RoundedCornerShape(30.dp)) {
        OutlinedTextField(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxHeight()
                .fillMaxWidth()
                .onFocusEvent {
                    focused.value = it.isFocused
                },
            textStyle = MaterialTheme.typography.subtitle1,
            value = state.value,
            trailingIcon = {
                IconButton(onClick = {
                    onQueryChange("", true)
                }) {
                    val icon = if(state.value.isNotEmpty()){
                        Icons.Filled.Close
                    }else{
                        Icons.Filled.Search
                    }
                    Icon(icon, "icon button", tint = MaterialTheme.colors.onSurface)
                }
            },
            placeholder = { Text(text = stringResource(id = R.string.search), style = MaterialTheme.typography.subtitle1) },
            onValueChange = {
                onQueryChange(it, false)
            },

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),

            shape = RoundedCornerShape(30.dp),


            )
    }


    /*

    }*/
}