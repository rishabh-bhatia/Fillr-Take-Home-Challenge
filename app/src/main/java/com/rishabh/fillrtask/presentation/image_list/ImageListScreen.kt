package com.rishabh.fillrtask.presentation.image_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishabh.fillrtask.presentation.image_list.components.ImageListItem
import com.rishabh.fillrtask.presentation.ui.Screen
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Displays an image list with a dynamic quick return header and a static title text at the bottom.
 * The header is visible at the initial scroll position and hides upon scrolling down, reappearing
 * when scrolling up to the top of the list. This enhances user experience by providing more screen
 * space for content while keeping the header accessible.
 *
 * @param navController A [NavController] for navigating between screens.
 * @param viewModel A [ImageListViewModel] instance for accessing the list of images and UI state.
 * It is provided by Hilt.
 *
 * The UI consists of a column with three main components:
 * 1. A quick return header that shows "Quick Return Header" text. Its visibility is controlled by
 * the user's scroll action.
 * 2. A LazyColumn that displays a scrollable list of images. Each item navigates to a detailed view
 * on click.
 * 3. A static title text displayed at the bottom, which remains visible irrespective of the list's
 * scroll position.
 *
 * This function uses `AnimatedVisibility` to show or hide the header based on the scroll direction
 * and position.
 */
@Composable
fun ImageListScreen(
    navController: NavController,
    viewModel: ImageListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val title by viewModel.title.collectAsState()
    val listState = rememberLazyListState()

    // Remember the scroll position to determine the direction of scroll
    val lastScrollPosition = remember { mutableStateOf(0) }
    val isHeaderVisible = remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        // Use snapshotFlow to observe the scroll position and determine the visibility of the header
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .distinctUntilChanged()
            .collect { scrollOffset ->
                val directionDown = scrollOffset > lastScrollPosition.value
                if (directionDown && listState.firstVisibleItemIndex > 0) {
                    isHeaderVisible.value = false
                } else if (!directionDown && listState.isScrollInProgress) {
                    isHeaderVisible.value = true
                }
                lastScrollPosition.value = scrollOffset
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = isHeaderVisible.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Quick Return Header",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                items(state.photos) { photo ->
                    ImageListItem(photo = photo, onItemClick = {
                        navController.navigate(
                            Screen.ImageDetailScreen.route + "/${photo.index}"
                        )
                    })
                }
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        Text(
            text = title,
            color = Color.DarkGray,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
