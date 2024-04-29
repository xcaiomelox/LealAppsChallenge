package com.lealapps.bodygrowth.feature.trainingList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.lealapps.bodygrowth.R
import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.feature.designSystem.theme.BodyGrowthTheme
import java.sql.Timestamp

@Composable
fun ExerciseCard(
    exercise: Exercise,
    timestamp: Timestamp?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(id = R.color.gray_itemCard))
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
        ) {

            if (timestamp != null) {
                Text(text = "${timestamp.date}", style = TextStyle(Color.White))
                Text(
                    text = timestamp.month.toExtendedMonth().take(3),
                    style = TextStyle(Color.White)
                )
            }
        }
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (title, image, description) = createRefs()
            Text(
                text = exercise.title,
                fontSize = 20.sp,
                style = TextStyle(Color.White),
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end, 16.dp)
                }
            )
            Text(
                text = exercise.description,
                fontSize = 16.sp,
                style = TextStyle(Color.Gray),
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(title.start)
                }
            )
            AsyncImage(
                model = exercise.image,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.treino_cardio),
                contentDescription = null,
                error = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

private fun Int.toExtendedMonth(): String = when (this) {
    0 -> "Janeiro"
    1 -> "Fevereiro"
    2 -> "Março"
    3 -> "Abril"
    4 -> "Maio"
    else -> "Dezembro"
}


@Preview
@Composable
private fun ExerciseCardPreview() {
    BodyGrowthTheme {
        ExerciseCard(
            exercise = Exercise(
                title = "Cardio Respiratório",
                image = "eum",
                description = "esteira"
            ),
            timestamp = Timestamp.valueOf("2000-05-10 12:00:00"),
        )
    }
}