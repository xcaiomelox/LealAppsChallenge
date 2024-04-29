package com.lealapps.bodygrowth.core.data


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lealapps.bodygrowth.core.domain.model.Training

@Database(entities = [Training::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TrainingDatabase: RoomDatabase() {

    abstract fun dao(): TrainingDao

}