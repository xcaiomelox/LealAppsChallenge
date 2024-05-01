package com.lealapps.bodygrowth.feature.addEditTraining.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lealapps.bodygrowth.R
import com.lealapps.bodygrowth.feature.addEditTraining.AddEditTrainingActions
import com.lealapps.bodygrowth.feature.addEditTraining.AddEditTrainingState

@Composable
fun TrainingForms(
    state: AddEditTrainingState,
    actions: AddEditTrainingActions,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .size(300.dp)
            .background(colorResource(id = R.color.gray_itemCard)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                label = { Text(text = "Treino", fontSize = 14.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
            TextField(
                value = state.training.description ?: "",
                onValueChange = { newText ->
                    actions.onDescriptionChanged(newText)
                },
                label = { Text(text = "Descrição", fontSize = 14.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
            DateCard(date = state.training.date, onSelectNewDate = actions.onDateChanged)
        }

    }
}