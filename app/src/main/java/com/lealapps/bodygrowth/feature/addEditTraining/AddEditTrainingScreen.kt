package com.lealapps.bodygrowth.feature.addEditTraining

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lealapps.bodygrowth.R
import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.model.Training
import com.lealapps.bodygrowth.feature.addEditTraining.components.TrainingForms
import com.lealapps.bodygrowth.feature.trainingList.components.ExerciseList


@Composable
fun AddEditTrainingScreen(
    state: AddEditTrainingState,
    actions: AddEditTrainingActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.gray_backgroundScreen))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TrainingForms(
            state = state,
            actions = actions
        )
        Spacer(modifier = Modifier.height(12.dp))
        ExerciseForms(state = state, actions = actions)
    }
}

@Composable
fun ExerciseForms(
    state: AddEditTrainingState,
    actions: AddEditTrainingActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(id = R.color.gray_itemCard))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Exercícios",
            fontSize = 40.sp,
            style = TextStyle(Color.White),
        )
        ExerciseList(
            training = state.training,
            onClick = {
                actions.onEditExercise.invoke(it)
            },
        )
        Button(
            onClick = { actions.onAddNewExercise.invoke() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Criar Exercício")
        }
        Button(
            onClick = { actions.onSaveTraining.invoke() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }
    }
}


@Composable
@Preview(name = "AddEditTraining")
private fun AddEditTrainingScreenPreview() {
    AddEditTrainingScreen(
        state = AddEditTrainingState(
            training = Training(
                exercises = listOf(
                    Exercise(title = "Peito e triceps")
                )
            )
        ),
        actions = AddEditTrainingActions(
            onTitleChanged = {},
            onDescriptionChanged = {},
            onDateChanged = {},
            onAddNewExercise = {},
            onEditExercise = {},
            onSaveTraining = {},
        )
    )
}

