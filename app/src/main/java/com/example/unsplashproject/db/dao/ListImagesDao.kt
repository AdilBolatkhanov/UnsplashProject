package com.example.unsplashproject.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.unsplashproject.db.entity.Image
import com.example.unsplashproject.db.entity.ListImagesTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ListImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg images: Image)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(images: List<Image>)

    @Query("SELECT * FROM $ListImagesTable")
    fun getAllImages(): Flow<List<Image>>

    @Query("SELECT * FROM $ListImagesTable")
    fun getAllImagesByPage(): DataSource.Factory<Int, Image>

    @Query("SELECT count(*) FROM $ListImagesTable")
    fun getImagesCount(): Int

    @Query("DELETE FROM $ListImagesTable")
    fun deleteAll()



}