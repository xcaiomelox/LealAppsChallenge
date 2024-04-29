package com.lealapps.bodygrowth.core.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Upsert
import com.lealapps.bodygrowth.core.domain.model.Training
import kotlinx.coroutines.flow.Flow

@Dao
@TypeConverters(Converters::class)
interface TrainingDao {

    @Upsert
    suspend fun upsertTraining(training: Training)

    @Delete
    suspend fun deleteTraining(training: Training)

    @Query("SELECT * FROM training WHERE id = :id")
    suspend fun getTrainingById(id: Int): List<Training>

    @Query("SELECT * FROM training")
    fun getTrainings(): Flow<List<Training>>

}