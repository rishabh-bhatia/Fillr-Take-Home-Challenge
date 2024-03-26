package com.rishabh.fillrtask.presentation.image_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.rishabh.fillrtask.domain.model.Photo

/**
 * Composable function that displays an item for an image in a list.
 *
 * This composable creates a row layout that includes an image thumbnail on the left and the title
 * of the image on the right. The image is loaded and displayed using the Coil library with a
 * placeholder for loading and error states. The whole row is clickable, and executing a specific
 * action when clicked, which is defined by the passed `onItemClick` lambda function.
 *
 * @param photo The photo data to be displayed.
 * @param onItemClick A lambda function that is called when the item is clicked.
 */
@Composable
fun ImageListItem(
    photo: Photo,
    onItemClick: (Photo) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(photo) }
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(RectangleShape)
                .padding(4.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(photo.imageUrl)
                        .placeholder(drawableResId = com.rishabh.fillrtask.R.drawable.placeholder)
                        .error(drawableResId = com.rishabh.fillrtask.R.drawable.placeholder)
                        .scale(Scale.FILL)
                        .build()
                ),
                contentDescription = photo.title,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = photo.title,
            color = Color.DarkGray,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(CenterVertically)
                .fillMaxSize()
                .padding(4.dp)
        )
    }
}