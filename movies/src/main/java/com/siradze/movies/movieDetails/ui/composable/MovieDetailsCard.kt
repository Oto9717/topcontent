package com.siradze.movies.movieDetails.ui.composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.siradze.movies.R
import com.siradze.movies.movieDetails.data.model.MovieDetails
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailsCard(movieDetails: MovieDetails?, modifier : Modifier = Modifier) {


    val placeHolderColor = MaterialTheme.colors.secondaryVariant
    val placeholderModifier =
        Modifier.placeholder(
            visible = movieDetails == null,
            color = placeHolderColor,
            // optional, defaults to RectangleShape
            shape = RoundedCornerShape(4.dp),
            highlight = PlaceholderHighlight.shimmer(
                highlightColor = Color.White,
            ),
        )


    Box(modifier = modifier){
        Column() {

            Card(Modifier.height(300.dp),elevation = 5.dp, shape =  RoundedCornerShape(size = 10.dp) ) {
                GlideImage(
                    modifier = placeholderModifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable(enabled = true, onClick = { })
                        .background(placeHolderColor),
                    imageModel = movieDetails?.backdropPath,
                    contentScale = ContentScale.Crop,
                    circularReveal = CircularReveal(duration = 250),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Row(modifier = placeholderModifier){
                    Text(text = "${stringResource(id = R.string.rating)}: ", style = MaterialTheme.typography.subtitle1,)
                    Text(text = movieDetails?.vote_average.toString(), style = MaterialTheme.typography.subtitle1,)
                    Icon(modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.star_rate_icon),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "star_icon")
                }
                Row(modifier = placeholderModifier){
                    Text(text = "${stringResource(id = R.string.vote_count)}: ", style = MaterialTheme.typography.subtitle1,)
                    Text(text = movieDetails?.vote_count.toString(), style = MaterialTheme.typography.subtitle1,)
                    Icon(modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.people_icon),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = "star_icon")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row() {
                Card(
                    Modifier
                        .height(150.dp)
                        .width(110.dp), elevation = 5.dp, shape =  RoundedCornerShape(size = 10.dp) ) {
                    GlideImage(
                        modifier = placeholderModifier
                            .fillMaxHeight()
                            .clickable(enabled = true, onClick = { })
                            .background(placeHolderColor),
                        imageModel = movieDetails?.posterPath,
                        contentScale = ContentScale.Crop,
                        circularReveal = CircularReveal(duration = 250),
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(text = movieDetails?.name ?: "Movie Title placeholder", placeholderModifier,
                        style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = movieDetails?.overview ?: "", placeholderModifier.fillMaxWidth(),
                        style = MaterialTheme.typography.caption)
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }
    }
}