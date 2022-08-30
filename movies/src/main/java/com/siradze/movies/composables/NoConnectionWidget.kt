package com.siradze.movies.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.siradze.movies.R

@Composable
fun NoConnectionWidget(message : String, modifier: Modifier = Modifier, callback : () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message, style = MaterialTheme.typography.caption, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))
            FloatingActionButton(onClick = { callback() }, backgroundColor = MaterialTheme.colors.background ) {
                Icon(imageVector =
                ImageVector.vectorResource(id = R.drawable.refresh_icon),
                    contentDescription = "refresh_icon")
            }
        }

    }
}