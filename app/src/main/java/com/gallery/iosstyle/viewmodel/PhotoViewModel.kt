package com.gallery.iosstyle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallery.iosstyle.data.Photo
import com.gallery.iosstyle.data.PhotoRepository
import com.gallery.iosstyle.data.PeopleGroup
import com.gallery.iosstyle.data.RecentDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoViewModel(private val repository: PhotoRepository) : ViewModel() {
    
    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos.asStateFlow()
    
    private val _recentDays = MutableStateFlow<List<RecentDay>>(emptyList())
    val recentDays: StateFlow<List<RecentDay>> = _recentDays.asStateFlow()
    
    private val _peopleGroups = MutableStateFlow<List<PeopleGroup>>(emptyList())
    val peopleGroups: StateFlow<List<PeopleGroup>> = _peopleGroups.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    fun loadPhotos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                val photoList = repository.getAllPhotos()
                _photos.value = photoList
                
                // Load additional data
                val recentDaysList = repository.getRecentDays()
                _recentDays.value = recentDaysList
                
                val peopleGroupsList = repository.getPeopleGroups()
                _peopleGroups.value = peopleGroupsList
                
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun clearError() {
        _error.value = null
    }
}