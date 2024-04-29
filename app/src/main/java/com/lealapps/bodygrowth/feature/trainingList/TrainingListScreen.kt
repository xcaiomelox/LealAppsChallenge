package com.lealapps.bodygrowth.feature.trainingList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lealapps.bodygrowth.R
import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.model.Training
import com.lealapps.bodygrowth.feature.trainingList.components.TrainingCard
import java.sql.Timestamp

@Composable
fun TrainingListScreen(
    state: TrainingListState,
    actions: TrainingListActions,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                actions.onClickFab.invoke()
            }) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(colorResource(id = R.color.gray_itemCard))
        ) {
            itemsIndexed(state.itemList) { index, training ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(4.dp))
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                TrainingCard(
                    training = training,
                    onClickTraining = { trainingId -> actions.onClickTraining.invoke(trainingId) },
                    onClickExercise = { clickedExercise ->
                        actions.onClickExercise.invoke(training.id, clickedExercise)
                    },
                )
            }
        }
    }
}


@Composable
@Preview(name = "TrainingList")
private fun TrainingListScreenPreview() {
    TrainingListScreen(
        state = TrainingListState(
            itemList = listOf(
                Training(
                    id = null,
                    title = "Peito e tríceps",
                    description = "Peito e bíceps",
                    date = Timestamp.valueOf("2000-05-10 12:00:00"),
                    isDone = false,
                    exercises = listOf(
                        Exercise(
                            title = "Cardio Respiratório",
                            image = "eum",
                            description = "esteira"
                        ),
                        Exercise(title = "verterem", image = "eum", description = "postulant"),
                        Exercise(title = "verterem", image = "eum", description = "postulant"),
                        Exercise(title = "verterem", image = "eum", description = "postulant"),
                    )
                )
            )
        ),
        actions = TrainingListActions(
            onClickFab = {},
            onClickExercise = { _,_ -> },
            onClickTraining = {}
        ),
    )
}


