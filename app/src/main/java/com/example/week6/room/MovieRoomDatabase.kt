package com.example.week6.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.week6.configure.Constant
import com.example.week6.models.Movie

@Database(entities = [Movie::class], version = 3, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieFavDao

    companion object {
        @Volatile
        private var instance: MovieRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, MovieRoomDatabase::class.java, Constant.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}