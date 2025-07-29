package com.gallery.iosstyle.data

import android.net.Uri
import java.util.Date

data class Photo(
    val id: Long,
    val uri: Uri,
    val displayName: String,
    val dateAdded: Date,
    val dateTaken: Date?,
    val size: Long,
    val mimeType: String,
    val width: Int,
    val height: Int
)

data class PhotoSection(
    val title: String,
    val photos: List<Photo>
)

data class RecentDay(
    val date: String,
    val photos: List<Photo>
)

data class PeopleGroup(
    val name: String,
    val coverPhoto: Photo,
    val photoCount: Int
)