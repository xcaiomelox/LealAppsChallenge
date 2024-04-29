package com.lealapps.bodygrowth.core.domain.repository

import com.lealapps.bodygrowth.core.domain.model.Training
import kotlinx.coroutines.flow.Flow

interface TrainingRepository {

    suspend fun upsertTraining(training: Training)

    suspend fun deleteTraining(training: Training)

    suspend fun getTrainingById(id: Int): Training?

    fun getTrainings(): Flow<List<Training>>

}