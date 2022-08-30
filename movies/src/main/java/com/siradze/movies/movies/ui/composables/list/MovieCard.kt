package com.siradze.movies.movies.ui.composables.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.siradze.movies.R
import com.siradze.movies.data.model.Movie
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(movie : Movie?, modifier: Modifier = Modifier, onClick : (Movie) -> Unit){

    val shape = remember { RoundedCornerShape(size = 10.dp) }
    val placeHolderColor = MaterialTheme.colors.secondaryVariant
    val placeholderModifier = remember {
        Modifier.placeholder(
            visible = movie == null,
            color = placeHolderColor,
            // optional, defaults to RectangleShape
            shape = RoundedCornerShape(4.dp),
            highlight = PlaceholderHighlight.shimmer(
                highlightColor = Color.White,
            ),
        )
    }
    Card(modifier = modifier, onClick = { movie?.let{onClick(it)} }, elevation = 5.dp, shape = shape) {
        Row() {
            Box(modifier = Modifier
                .padding(10.dp)
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
            Column(modifier = Modifier.padding(10.dp)) {
                Row() {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(modifier = Modifier
                            .width(10.dp)
                            .height(10.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.star_rate_icon),
                            tint = MaterialTheme.colors.primary,
                            contentDescription = "favorite_icon")
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = movie?.voteAverage.toString(), style = MaterialTheme.typography.caption,
                            modifier = placeholderModifier,
                        )
                    }
                }
                Text(text = movie?.name ?: "Anime title", style = MaterialTheme.typography.subtitle1,
                    modifier = placeholderModifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = movie?.overview.toString(),
                    style = MaterialTheme.typography.overline,
                    overflow = TextOverflow.Ellipsis,
                    modifier = placeholderModifier.fillMaxSize()
                )
            }

        }

    }
}