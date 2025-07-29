package com.gallery.iosstyle

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gallery.iosstyle.data.Photo
import com.gallery.iosstyle.data.PhotoRepository
import com.gallery.iosstyle.ui.screens.PhotoDetailScreen
import com.gallery.iosstyle.ui.screens.PhotosScreen
import com.gallery.iosstyle.ui.theme.IOSGalleryTheme
import com.gallery.iosstyle.viewmodel.PhotoViewModel

class MainActivity : ComponentActivity() {
    
    private var hasReadPermission by mutableStateOf(false)
    
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasReadPermission = permissions.values.all { it }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Check initial permissions
        hasReadPermission = hasReadMediaPermission()
        
        setContent {
            IOSGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryApp(
                        hasPermission = hasReadPermission,
                        onRequestPermission = { requestStoragePermission() }
                    )
                }
            }
        }
    }
    
    private fun hasReadMediaPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == 
                    android.content.pm.PackageManager.PERMISSION_GRANTED
        } else {
            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == 
                    android.content.pm.PackageManager.PERMISSION_GRANTED
        }
    }
    
    private fun requestStoragePermission() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            )
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        
        requestPermissionLauncher.launch(permissions)
    }
}

@Composable
fun GalleryApp(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    // Create repository and viewmodel
    val context = androidx.compose.ui.platform.LocalContext.current
    val repository = remember { PhotoRepository(context) }
    val viewModel: PhotoViewModel = androidx.lifecycle.viewmodel.compose.viewModel { PhotoViewModel(repository) }
    
    // Remember photos for navigation between screens
    var currentPhotos by remember { mutableStateOf<List<Photo>>(emptyList()) }
    var currentPhotoIndex by remember { mutableStateOf(0) }
    
    NavHost(
        navController = navController,
        startDestination = "photos"
    ) {
        composable("photos") {
            PhotosScreen(
                viewModel = viewModel,
                hasPermission = hasPermission,
                onRequestPermission = onRequestPermission,
                onPhotoClick = { photo, index ->
                    // Get current photos from viewmodel
                    currentPhotos = viewModel.photos.value
                    currentPhotoIndex = index
                    navController.navigate("photo_detail")
                }
            )
        }
        
        composable("photo_detail") {
            if (currentPhotos.isNotEmpty()) {
                PhotoDetailScreen(
                    photos = currentPhotos,
                    initialIndex = currentPhotoIndex,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}