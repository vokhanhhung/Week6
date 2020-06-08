package com.example.week6.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromGenres(genres: List<Int>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenres(str: String): List<Int> {
        val type = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(str, type)
    }
}