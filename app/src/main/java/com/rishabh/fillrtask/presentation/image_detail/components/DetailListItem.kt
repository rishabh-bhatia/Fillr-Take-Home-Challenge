package com.rishabh.fillrtask.presentation.image_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rishabh.fillrtask.domain.model.Photo

/**
 * Displays detailed information about a photo as a composable item.
 * This composable is designed to present photo details in a readable and visually appealing format.
 *
 * @param photoItem The photo item containing the details to be displayed.
 * @param modifier Modifier to be applied to the Column layout. Default is Modifier.
 */
@Composable
fun DetailListItem(
    photoItem: Photo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp), // Added padding for better spacing and readability.
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start // Ensures content is aligned to the start.
    ) {
        // Each text component represents a detail of the photo.
        Text(
            text = "Index: ${photoItem.index}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "ID: ${photoItem.id}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Title: ${photoItem.title}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Farm: ${photoItem.farm}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "IsFamily: ${photoItem.isfamily}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "IsFriend: ${photoItem.isfriend}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "IsPublic: ${photoItem.ispublic}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Owner: ${photoItem.owner}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Secret: ${photoItem.secret}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Server: ${photoItem.server}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "ImageUrl: ${photoItem.imageUrl}",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
