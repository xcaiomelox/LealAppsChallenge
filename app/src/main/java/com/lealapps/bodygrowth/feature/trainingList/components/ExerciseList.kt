package com.lealapps.bodygrowth.feature.trainingList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.model.Training

@Composable
fun ExerciseList(
    training: Training,
    modifier: Modifier = Modifier,
    onClick: (Exercise) -> Unit
) {
    training.exercises.forEachIndexed { index, exercise ->
        Column(
            modifier = modifier
                .padding(horizontal = 4.dp)
        ) {
            if (index > 0) {
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            ExerciseCard(
                exercise = exercise,
                timestamp = if (index == 0) training.date else null,
                modifier = Modifier.clickable { onClick.invoke(exercise) }
            )
        }
    }
}
