package com.siradze.movies.movieDetails.ui.composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.siradze.movies.domain.model.Movie
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MoviePreviewCard(movie : Movie?, modifier: Modifier = Modifier, onClick : (Movie) -> Unit) {
    val shape = remember { RoundedCornerShape(size = 10.dp) }
    val placeHolderColor = MaterialTheme.colors.secondaryVariant
    val placeholderModifier = remember {
        Modifier.placeholder(
            visible = movie == null,
            color = placeHolderColor,
            shape = RoundedCornerShape(4.dp),
            highlight = PlaceholderHighlight.shimmer(
                highlightColor = Color.White,
            ),
        )
    }
    Card(modifier = modifier, onClick = { movie?.let{onClick(it)} }, elevation = 5.dp, shape = shape) {
        Box(modifier = Modifier
            .width(120.dp)
            .fillMaxHeight()) {
            GlideImage(
                modifier = placeholderModifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(shape),
                imageModel = movie?.posterPath,
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 250),
            )
        }
    }
}