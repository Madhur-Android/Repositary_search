package com.example.repositary_search.db

import android.content.Context
import androidx.room.*
import com.example.repositary_search.Retrofit.models.Item

@Database(
    entities = [Item::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RepositoryDatabase: RoomDatabase() {

    abstract fun getRepositoryDao(): RepositoryDAO

    companion object{
        @Volatile
        private var instance: RepositoryDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it}
        }
        private  fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RepositoryDatabase::class.java,
                "repository_db.db"
            ).build()
    }
}