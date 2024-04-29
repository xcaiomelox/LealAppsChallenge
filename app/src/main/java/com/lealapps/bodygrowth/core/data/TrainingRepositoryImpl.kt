package com.lealapps.bodygrowth.core.data

import com.lealapps.bodygrowth.core.domain.repository.TrainingRepository
import com.lealapps.bodygrowth.core.domain.model.Training
import kotlinx.coroutines.flow.Flow

class TrainingRepositoryImpl(
    private val dao: TrainingDao
): TrainingRepository {

    override suspend fun upsertTraining(training: Training) {
        dao.upsertTraining(training)
    }

    override suspend fun deleteTraining(training: Training) {
        dao.deleteTraining(training)
    }

    override suspend fun getTrainingById(id: Int): Training? {
        return dao.getTrainingById(id).firstOrNull()
    }

    override fun getTrainings(): Flow<List<Training>> {
        return dao.getTrainings()
    }
}