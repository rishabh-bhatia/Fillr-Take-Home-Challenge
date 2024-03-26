package com.rishabh.fillrtask.presentation.image_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.rishabh.fillrtask.R
import com.rishabh.fillrtask.presentation.image_detail.components.DetailListItem

/**
 * Displays the detail screen for a photo in the app.
 * This composable function constructs a screen that shows an image followed by its details.
 * It utilizes LazyColumn for efficient loading and display.
 * The image is loaded using the Coil library with placeholders for loading and errors.
 * Details about the photo are shown using the DetailListItem composable.
 *
 * @param index The index of the photo to display details for. Can be null.
 * @param viewModel The ViewModel that provides photo details. By default, it's injected using Hilt.
 */
@Composable
fun ImageDetailScreen(
    viewModel: ImageDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.photo?.let { photo ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(photo.imageUrl)
                                .placeholder(drawableResId = R.drawable.placeholder)
                                .error(drawableResId = R.drawable.placeholder)
                                .scale(Scale.FILL)
                                .build()
                        ),
                        contentDescription = photo.title,
                        modifier = Modifier.fillMaxWidth(), // Ensures the image fills the width.
                        contentScale = ContentScale.FillWidth // Scales the image to fill the width.
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    DetailListItem(
                        photoItem = photo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
