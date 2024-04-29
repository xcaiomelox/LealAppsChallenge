package com.lealapps.bodygrowth.feature.addEditTraining

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lealapps.bodygrowth.R
import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.model.Training
import com.lealapps.bodygrowth.feature.trainingList.components.ExerciseList
import java.sql.Timestamp
import java.util.Calendar

@Composable
fun AddEditTrainingScreen(
    state: AddEditTrainingState,
    actions: AddEditTrainingActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.gray_itemCard))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Novo treino",
            fontSize = 40.sp,
            style = TextStyle(Color.White),
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = state.training.title,
            onValueChange = { newTitle ->
                actions.onTitleChanged(newTitle)
            },
            label = { Text(text = "Treino") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = state.training.description ?: "",
            onValueChange = { newText ->
                actions.onDescriptionChanged(newText)
            },
            label = { Text(text = "Descrição") },
            modifier = Modifier.fillMaxWidth()
        )
        DatePicker(date = state.training.date, onSelectNewDate = actions.onDateChanged)
        Spacer(modifier = Modifier.height(12.dp))
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
            Text(text = "Criar Treino")
        }
    }
}

@Composable
private fun DatePicker(
    date: Timestamp?,
    onSelectNewDate: (Timestamp) -> Unit
) {
    val context = LocalContext.current
    val formattedDate = date.toFormattedDate()
    TextField(
        value = formattedDate,
        onValueChange = {},
        label = @Composable { Text("Selecionar Data") },
        enabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(id = R.color.background_buttomDate))
            .clickable {
                val instanceCalendar = Calendar.getInstance()
                val year = instanceCalendar.get(Calendar.YEAR)
                val month = instanceCalendar.get(Calendar.MONTH)
                val day = instanceCalendar.get(Calendar.DAY_OF_MONTH)
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        onSelectNewDate.invoke(Timestamp.valueOf("$year-${month + 1}-$day 12:00:00"))
                    },
                    year,
                    month,
                    day
                ).show()
            },
    )
}

private fun Timestamp?.toFormattedDate(): String = if (this == null) "Insira a data" else
    toString().take(10)


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
