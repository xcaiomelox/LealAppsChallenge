package com.lealapps.bodygrowth.feature.trainingList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.model.Training
import java.sql.Timestamp

@Composable
fun ExerciseList(
    training: Training,
    modifier: Modifier = Modifier,
    onClick: (Exercise) -> Unit
) {
    training.exercises.forEachIndexed { index, exercise ->
        Column(
            modifier = modifier
                .padding(horizontal = 4.dp, vertical = 0.dp)
        ) {
            if (index > 0) {
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            ExerciseCard(
                exercise = exercise,
                timestamp = if (index == 0) training.date else null,
                modifier = Modifier
                    .clickable { onClick.invoke(exercise) }

            )
        }
    }
}

@Preview
@Composable
private fun ExerciseListPreview() {
    ExerciseList(
        training = Training(
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
            )
        )
    ) {

    }
}