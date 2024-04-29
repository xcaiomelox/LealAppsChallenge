package com.lealapps.bodygrowth.feature.trainingList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.model.Training
import com.lealapps.bodygrowth.feature.designSystem.theme.BodyGrowthTheme
import java.sql.Timestamp

@Composable
fun TrainingCard(
    training: Training,
    onClickTraining: (Int?) -> Unit,
    onClickExercise: (Exercise) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickTraining.invoke(training.id)
            }
    ) {
        Text(
            text = training.title,
            fontSize = 24.sp,
            style = TextStyle(Color.White),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 20.dp)
        )
        ExerciseList(training = training, onClick = onClickExercise)
    }
}

@Composable
@Preview(name = "TrainingList")
private fun ItemListPreview() {
    BodyGrowthTheme {
        TrainingCard(
            training = Training(
                id = null,
                title = "Peito",
                description = null,
                date = Timestamp.valueOf("2000-05-10 12:00:00"),
                isDone = false,
                exercises = listOf(
                    Exercise(title = "Cardio Respiratório", image = "eum", description = "esteira"),
                    Exercise(title = "verterem", image = "eum", description = "postulant"),
                    Exercise(title = "verterem", image = "eum", description = "postulant"),
                    Exercise(title = "verterem", image = "eum", description = "postulant"),
                )
            ),
            onClickTraining = {},
            onClickExercise = {},
        )
    }
}