package com.gallery.iosstyle.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gallery.iosstyle.data.Photo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class PhotoGroup(
    val date: String,
    val displayDate: String,
    val photos: List<Photo>
)

@Composable
fun IOSTimelineGrid(
    photos: List<Photo>,
    onPhotoClick: (Photo, Int) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    if (photos.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No photos to display",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    } else {
        val groupedPhotos = groupPhotosByDate(photos)
        
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(groupedPhotos) { group ->
                PhotoGroupSection(
                    group = group,
                    onPhotoClick = onPhotoClick,
                    allPhotos = photos
                )
            }
        }
    }
}

@Composable
private fun PhotoGroupSection(
    group: PhotoGroup,
    onPhotoClick: (Photo, Int) -> Unit,
    allPhotos: List<Photo>
) {
    Column {
        // Date header
        Text(
            text = group.displayDate,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            ),
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Featured large photo + grid of smaller photos
        if (group.photos.isNotEmpty()) {
            val featuredPhoto = group.photos.first()
            val gridPhotos = group.photos.drop(1)
            
            Column {
                // Large featured photo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .padding(horizontal = 1.dp)
                        .clickable { 
                            val index = allPhotos.indexOf(featuredPhoto)
                            onPhotoClick(featuredPhoto, index) 
                        }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(featuredPhoto.uri)
                            .crossfade(true)
                            .build(),
                        contentDescription = featuredPhoto.displayName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                
                // Grid of smaller photos if more than 1 photo
                if (gridPhotos.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(1.dp))
                    
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(horizontal = 1.dp),
                        horizontalArrangement = Arrangement.spacedBy(1.dp),
                        verticalArrangement = Arrangement.spacedBy(1.dp),
                        modifier = Modifier.height(calculateGridHeight(gridPhotos.size))
                    ) {
                        items(gridPhotos) { photo ->
                            val index = allPhotos.indexOf(photo)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clickable { onPhotoClick(photo, index) }
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(photo.uri)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = photo.displayName,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun calculateGridHeight(photoCount: Int): androidx.compose.ui.unit.Dp {
    val rows = kotlin.math.ceil(photoCount / 3.0).toInt()
    val itemHeight = 120.dp
    val spacingTotal = (rows - 1).dp
    return itemHeight * rows + spacingTotal
}

private fun groupPhotosByDate(photos: List<Photo>): List<PhotoGroup> {
    val calendar = Calendar.getInstance()
    val today = calendar.time
    calendar.add(Calendar.DAY_OF_YEAR, -1)
    val yesterday = calendar.time
    
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val displayFormatter = SimpleDateFormat("EEEE, MMMM d", Locale.getDefault())
    
    return photos
        .sortedByDescending { it.dateTaken ?: it.dateAdded }
        .groupBy { photo ->
            val date = photo.dateTaken ?: photo.dateAdded
            dateFormatter.format(date)
        }
        .map { (dateKey, groupPhotos) ->
            val date = groupPhotos.first().dateTaken ?: groupPhotos.first().dateAdded
            val displayDate = when {
                dateFormatter.format(date) == dateFormatter.format(today) -> "Today"
                dateFormatter.format(date) == dateFormatter.format(yesterday) -> "Yesterday" 
                else -> displayFormatter.format(date)
            }
            
            PhotoGroup(
                date = dateKey,
                displayDate = displayDate,
                photos = groupPhotos.sortedByDescending { it.dateTaken ?: it.dateAdded }
            )
        }
}