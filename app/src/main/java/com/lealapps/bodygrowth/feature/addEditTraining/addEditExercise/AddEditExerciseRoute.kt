package com.lealapps.bodygrowth.feature.addEditTraining.addEditExercise

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.lealapps.bodygrowth.feature.addEditTraining.AddEditTrainingState
import com.lealapps.bodygrowth.feature.addEditTraining.AddEditTrainingViewModel

@Composable
fun AddEditExerciseRoute(
    navController: NavController,
    viewModel: AddEditTrainingViewModel
) {
    val uiState by viewModel.stateFlow.collectAsState(AddEditTrainingState())

    val actions = rememberAddEditExerciseActions(viewModel, navController)

    AddEditExerciseScreen(uiState, actions)
}

@Composable
fun rememberAddEditExerciseActions(
    viewModel: AddEditTrainingViewModel,
    navController: NavController
): AddEditExerciseActions {
    return remember(viewModel) {
        AddEditExerciseActions(
            onTitleChanged = {
                val newTitle = it
                viewModel.updateSelectedExerciseTitle(newTitle)
            },
            onDescriptionChanged = {
                val newDescription = it
                viewModel.updateSelectedExerciseDescription(newDescription)
            },
            onImageChanged = {
                val newImage = it
                viewModel.updateSelectedExerciseImage(newImage)
            },
            onSaveChanges = {
                if (viewModel.stateFlow.value.training.id != null) {
                    viewModel.saveTraining()
                }
                navController.navigateUp()
            }
        )
    }
}

data class AddEditExerciseActions(
    val onTitleChanged: (String) -> Unit,
    val onDescriptionChanged: (String) -> Unit,
    val onImageChanged: (String) -> Unit,
    val onSaveChanges: () -> Unit,
)