package com.gallery.iosstyle.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gallery.iosstyle.ui.components.IOSBottomNavigation
import com.gallery.iosstyle.ui.components.SimplePhotoGrid
import com.gallery.iosstyle.ui.theme.IOSBlue
import com.gallery.iosstyle.viewmodel.PhotoViewModel

@Composable
fun MainScreen(
    viewModel: PhotoViewModel? = null
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
    viewModel: PhotoViewModel?
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
        
        // Simple Filter Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Years", "Months", "Days", "All Photos").forEachIndexed { index, title ->
                val isSelected = index == 3 // Default to "All Photos"
                
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            color = if (isSelected) Color.White else Color.Transparent
                        )
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isSelected) Color.Black else Color.Gray
                    )
                }
            }
        }
        
        // Simple Photo Grid
        SimplePhotoGrid(
            photos = emptyList(), // Placeholder for now
            onPhotoClick = { _, _ -> },
            modifier = Modifier.fillMaxSize()
        )
    }
}