package com.gallery.iosstyle.data

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PhotoRepository(private val context: Context) {
    
    suspend fun getAllPhotos(): List<Photo> = withContext(Dispatchers.IO) {
        val photos = mutableListOf<Photo>()
        val contentResolver: ContentResolver = context.contentResolver
        
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT
        )
        
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        
        val cursor: Cursor? = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )
        
        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateAddedColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            val dateTakenColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            val mimeTypeColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)
            val widthColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
            val heightColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)
            
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn)
                val dateAdded = Date(it.getLong(dateAddedColumn) * 1000)
                val dateTaken = it.getLong(dateTakenColumn).let { timestamp ->
                    if (timestamp > 0) Date(timestamp) else null
                }
                val size = it.getLong(sizeColumn)
                val mimeType = it.getString(mimeTypeColumn)
                val width = it.getInt(widthColumn)
                val height = it.getInt(heightColumn)
                
                val contentUri: Uri = Uri.withAppendedPath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )
                
                photos.add(
                    Photo(
                        id = id,
                        uri = contentUri,
                        displayName = name,
                        dateAdded = dateAdded,
                        dateTaken = dateTaken,
                        size = size,
                        mimeType = mimeType,
                        width = width,
                        height = height
                    )
                )
            }
        }
        
        photos
    }
    
    suspend fun getRecentDays(): List<RecentDay> = withContext(Dispatchers.IO) {
        val photos = getAllPhotos()
        val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())
        
        // Group photos by date taken
        val groupedPhotos = photos
            .filter { it.dateTaken != null }
            .groupBy { photo ->
                dateFormat.format(photo.dateTaken!!)
            }
        
        groupedPhotos.map { (date, photoList) ->
            RecentDay(
                date = date,
                photos = photoList.take(3) // Show only first 3 photos per day
            )
        }.take(10) // Show only last 10 days
    }
    
    suspend fun getPeopleGroups(): List<PeopleGroup> = withContext(Dispatchers.IO) {
        val photos = getAllPhotos()
        
        // Mock people groups for demo - in real app this would use ML face detection
        val mockGroups = listOf(
            "Family",
            "Friends", 
            "Pets",
            "Work"
        )
        
        mockGroups.mapIndexedNotNull { index, name ->
            val groupPhotos = photos.drop(index * 3).take(5)
            if (groupPhotos.isNotEmpty()) {
                PeopleGroup(
                    name = name,
                    coverPhoto = groupPhotos.first(),
                    photoCount = groupPhotos.size
                )
            } else null
        }
    }
}