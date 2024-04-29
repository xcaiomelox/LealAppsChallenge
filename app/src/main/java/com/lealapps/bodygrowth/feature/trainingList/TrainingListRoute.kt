package com.lealapps.bodygrowth.feature.trainingList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.lealapps.bodygrowth.core.presentation.navigation.Routes

@Composable
fun TrainingListRoute(
    navController: NavController,
    viewModel: TrainingListViewModel,
) {
    val uiState by viewModel.stateFlow.collectAsState(TrainingListState())

    val actions = rememberTrainingListActions(viewModel, navController)

    TrainingListScreen(uiState, actions)
}


@Composable
fun rememberTrainingListActions(
    viewModel: TrainingListViewModel,
    navController: NavController
): TrainingListActions {
    return remember(viewModel) {
        TrainingListActions(
            onClickFab = {
                navController.navigate(Routes.AddEditTraining.linkWithTrainingId())
            },
            onClickTraining = {
                navController.navigate(Routes.AddEditTraining.linkWithTrainingId(it))
            },
            onClickExercise = { _, _ ->

            }
        )
    }
}