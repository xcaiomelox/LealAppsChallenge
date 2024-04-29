package com.lealapps.bodygrowth.core.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lealapps.bodygrowth.core.domain.model.Exercise
import java.sql.Timestamp

class Converters {

    @TypeConverter
    fun saveTimestamp(date: Timestamp?): String? = date?.toString()

    @TypeConverter
    fun getTimestamp(value: String?): Timestamp? = value?.let { Timestamp.valueOf(it) }

    @TypeConverter
    fun saveExercises(exercises: List<Exercise>?): String? = exercises.let { Gson().toJson(it) }

    @TypeConverter
    fun getExercises(exercises: String?): List<Exercise>? = exercises.let {
        Gson().fromJson(it, Array<Exercise>::class.java).asList()
    }


}