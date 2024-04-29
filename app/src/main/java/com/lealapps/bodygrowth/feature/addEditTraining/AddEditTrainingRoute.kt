package com.lealapps.bodygrowth.feature.addEditTraining

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.lealapps.bodygrowth.core.presentation.navigation.Routes

@Composable
fun AddEditTrainingRoute(
    trainingId: Int?,
    viewModel: AddEditTrainingViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        trainingId?.let {
            viewModel.loadTraining(it)
        }
    }

    val uiState by viewModel.stateFlow.collectAsState(AddEditTrainingState())

    val actions = rememberAddEditTrainingActions(viewModel, navController)

    AddEditTrainingScreen(uiState, actions)
}

@Composable
fun rememberAddEditTrainingActions(
    viewModel: AddEditTrainingViewModel,
    navController: NavHostController,
): AddEditTrainingActions {
    return remember(viewModel) {
        AddEditTrainingActions(
            onTitleChanged = {
                val newTitle = it
                viewModel.updateTrainingTitle(newTitle)
            },
            onDescriptionChanged = {
                val newDescription = it
                viewModel.updateTrainingDescription(newDescription)
            },
            onDateChanged = {
                val newDate = it
                viewModel.updateTrainingDate(newDate)
            },
            onAddNewExercise = {
                viewModel.addNewExercise()
                navController.navigate(Routes.AddEditExercise.link)
            },
            onEditExercise = { exercise ->
                viewModel.updateSelectedExercise(exercise)
                navController.navigate(
                    Routes.AddEditExercise.link
                )
            },
            onSaveTraining = {
                viewModel.saveTraining()
                navController.navigateUp()
            }
        )
    }
}