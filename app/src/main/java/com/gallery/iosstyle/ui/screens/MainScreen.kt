package com.gallery.iosstyle.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gallery.iosstyle.ui.components.IOSBottomNavigation
import com.gallery.iosstyle.ui.components.IOSTimePeriodFilter
import com.gallery.iosstyle.ui.components.SimplePhotoGrid
import com.gallery.iosstyle.ui.theme.IOSBlue
import com.gallery.iosstyle.viewmodel.PhotoViewModel

@Composable
fun MainScreen(
    viewModel: PhotoViewModel = hiltViewModel()
) {
    var selectedTab by remember { mutableStateOf("photos") }
    
    Scaffold(
        bottomBar = {
            IOSBottomNavigation(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                "photos" -> PhotosScreenSimple(viewModel = viewModel)
                "for_you" -> ForYouScreen()
                "albums" -> AlbumsScreen()
                "search" -> SearchScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreenSimple(
    viewModel: PhotoViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // iOS-style Header
        TopAppBar(
            title = {
                Text(
                    text = "Photos",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },
            actions = {
                TextButton(
                    onClick = { },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = IOSBlue
                    )
                ) {
                    Text(
                        text = "Select",
                        fontSize = 16.sp
                    )
                }
                
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = IOSBlue
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )
        
        // Time Period Filter
        IOSTimePeriodFilter(
            selectedFilter = "All Photos",
            onFilterSelected = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        // Simple Photo Grid
        val photos by viewModel.photos.collectAsState()
        SimplePhotoGrid(
            photos = photos,
            onPhotoClick = { _, _ -> },
            modifier = Modifier.fillMaxSize()
        )
    }
}