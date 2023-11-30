package com.example.repositary_search.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.repositary_search.Retrofit.models.Item
import retrofit2.http.DELETE

@Dao
interface RepositoryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(repository: Item): Long

    @Query("SELECT * FROM repository")
    fun getAllRepository(): LiveData<List<Item>>

    @DELETE
    suspend fun deleteRepository(repository: Item)
}