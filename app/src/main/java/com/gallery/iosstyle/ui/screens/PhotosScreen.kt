package com.gallery.iosstyle.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gallery.iosstyle.data.Photo
import com.gallery.iosstyle.ui.components.EmptyPhotosMessage
import com.gallery.iosstyle.ui.components.LoadingIndicator
import com.gallery.iosstyle.ui.components.PeoplePetsSection
import com.gallery.iosstyle.ui.components.PhotoGrid
import com.gallery.iosstyle.ui.components.RecentDaysSection
import com.gallery.iosstyle.ui.components.IOSTimelineGrid
import com.gallery.iosstyle.ui.components.SimplePhotoGrid
import com.gallery.iosstyle.ui.components.IOSBottomNavigation
import com.gallery.iosstyle.ui.components.IOSTimePeriodFilter
import com.gallery.iosstyle.ui.components.TimePeriod
import com.gallery.iosstyle.ui.theme.IOSBlue
import com.gallery.iosstyle.ui.theme.IOSGray
import com.gallery.iosstyle.viewmodel.PhotoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(
    viewModel: PhotoViewModel,
    onPhotoClick: (Photo, Int) -> Unit,
    onRequestPermission: () -> Unit,
    hasPermission: Boolean,
    modifier: Modifier = Modifier
) {
    val photos by viewModel.photos.collectAsState()
    val recentDays by viewModel.recentDays.collectAsState()
    val peopleGroups by viewModel.peopleGroups.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    
    var selectedTab by remember { mutableStateOf("photos") }
    var selectedTimePeriod by remember { mutableStateOf(TimePeriod.DAYS) }

    LaunchedEffect(hasPermission) {
        if (hasPermission) {
            viewModel.loadPhotos()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Photos",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 34.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    OutlinedButton(
                        onClick = { /* Select functionality */ },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = IOSBlue
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Select")
                    }
                    IconButton(onClick = { /* More options */ }) {
                        Text("•••", style = MaterialTheme.typography.titleLarge, color = IOSBlue)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            IOSBottomNavigation(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        
        when {
            !hasPermission -> {
                PermissionRequestContent(
                    onRequestPermission = onRequestPermission,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            
            isLoading -> {
                LoadingIndicator(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            
            error != null -> {
                val errorMessage = error ?: "Unknown error"
                ErrorContent(
                    error = errorMessage,
                    onRetry = { viewModel.loadPhotos() },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            
            photos.isEmpty() -> {
                EmptyPhotosMessage(
                    modifier = Modifier.padding(paddingValues)
                )
            }
            
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    // Time period filter
                    IOSTimePeriodFilter(
                        selectedPeriod = selectedTimePeriod,
                        onPeriodSelected = { selectedTimePeriod = it }
                    )
                    
                    // Simple photo grid for better performance
                    SimplePhotoGrid(
                        photos = photos,
                        onPhotoClick = onPhotoClick
                    )
                }
            }
        }
    }
}

@Composable
fun PermissionRequestContent(
    onRequestPermission: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Permission Required",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "This app needs access to your photos to display them in a beautiful gallery.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = onRequestPermission,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = IOSBlue
                    )
                ) {
                    Text("Grant Permission")
                }
            }
        }
    }
}

@Composable
fun ErrorContent(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Error",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = IOSBlue
                )
            ) {
                Text("Retry")
            }
        }
    }
}