package com.lealapps.bodygrowth.feature.addEditTraining

import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.model.Training
import java.sql.Timestamp

data class AddEditTrainingState(
    val training: Training = Training(),
    val editedExercise: Exercise = Exercise(),
)

data class AddEditTrainingActions(
    val onTitleChanged: (String) -> Unit,
    val onDescriptionChanged: (String) -> Unit,
    val onDateChanged: (Timestamp) -> Unit,
    val onAddNewExercise: () -> Unit = {},
    val onEditExercise: (exercise: Exercise) -> Unit = {},
    val onSaveTraining: () -> Unit = {},
)