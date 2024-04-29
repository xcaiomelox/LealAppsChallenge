package com.lealapps.bodygrowth.core.presentation.navigation

sealed class Routes(val link: String) {
    data object Trainings : Routes("trainings")
    data object AddEditTraining : Routes("add_edit_training/{${Arguments.TRAINING_ID_KEY}}") {
        fun linkWithTrainingId(trainingId: Int? = null) =
            link.replace("{${Arguments.TRAINING_ID_KEY}}", trainingId?.toString() ?: "null")
    }

    data object AddEditExercise : Routes("add_edit_exercise")
}