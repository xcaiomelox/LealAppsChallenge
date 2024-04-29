package com.lealapps.bodygrowth.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.lealapps.bodygrowth.feature.addEditTraining.addEditExercise.AddEditExerciseRoute
import com.lealapps.bodygrowth.feature.addEditTraining.AddEditTrainingRoute
import com.lealapps.bodygrowth.feature.addEditTraining.AddEditTrainingViewModel
import com.lealapps.bodygrowth.feature.trainingList.TrainingListRoute

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Trainings.link
    ) {
        composable(Routes.Trainings.link) {
            TrainingListRoute(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
        navigation(
            startDestination = Routes.AddEditTraining.link,
            route = "Edit"
        ) {
            composable(
                route = Routes.AddEditTraining.link,
                arguments = listOf(navArgument(Arguments.TRAINING_ID_KEY) { nullable = true })
            ) {
                val trainingId = it.arguments?.getString(Arguments.TRAINING_ID_KEY)
                AddEditTrainingRoute(
                    navController = navController,
                    trainingId = trainingId?.toIntOrNull(),
                    viewModel = it.sharedViewModel<AddEditTrainingViewModel>(navController)
                )
            }
            composable(
                route = Routes.AddEditExercise.link,
            ) {
                AddEditExerciseRoute(
                    navController = navController,
                    viewModel = it.sharedViewModel<AddEditTrainingViewModel>(navController),
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}