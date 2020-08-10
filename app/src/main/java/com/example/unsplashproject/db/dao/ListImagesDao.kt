package com.example.unsplashproject.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.unsplashproject.feature.domain.entity.Image
import com.example.unsplashproject.feature.domain.entity.ListImagesTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ListImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg images: Image)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(images: List<Image>)

    @Query("SELECT * FROM $ListImagesTable")
    fun getAllImages(): Flow<List<Image>>

    @Query("SELECT * FROM $ListImagesTable")
    fun getAllImagesByPage(): DataSource.Factory<Int, Image>

    @Query("SELECT count(*) FROM $ListImagesTable")
    suspend fun getImagesCount(): Int

    @Query("DELETE FROM $ListImagesTable")
   suspend  fun deleteAll()


}