package com.lealapps.bodygrowth.feature.trainingList

import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.model.Training

data class TrainingListState(
    val itemList: List<Training> = emptyList()
)

data class TrainingListActions(
    val onClickFab: () -> Unit,
    val onClickTraining: (trainingId: Int?) -> Unit,
    val onClickExercise: (trainingId: Int?, exercise: Exercise) -> Unit,
)