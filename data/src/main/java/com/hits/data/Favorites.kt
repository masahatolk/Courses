package com.hits.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "favorite_courses")
data class FavoriteCourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    val startDate: String,
    val publishDate: String
)

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(course: FavoriteCourseEntity)

    @Query("DELETE FROM favorite_courses WHERE id = :courseId")
    suspend fun removeFavorite(courseId: Int)

    @Query("SELECT * FROM favorite_courses")
    fun getFavoriteCourses(): Flow<List<FavoriteCourseEntity>>

    @Query("SELECT id FROM favorite_courses")
    fun getFavoriteIds(): Flow<List<Int>>
}