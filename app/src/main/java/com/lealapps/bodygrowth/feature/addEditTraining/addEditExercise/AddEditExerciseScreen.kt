package com.lealapps.bodygrowth.feature.addEditTraining.addEditExercise

import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lealapps.bodygrowth.R
import com.lealapps.bodygrowth.feature.addEditTraining.AddEditTrainingState
import kotlinx.coroutines.launch

@Composable
fun AddEditExerciseScreen(
    state: AddEditTrainingState,
    actions: AddEditExerciseActions,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.gray_itemCard))
            .padding(top = 20.dp)
    ) {
        ExerciseImage(imageURL = state.editedExercise.image, actions.onImageChanged)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            TextField(
                value = state.editedExercise.title,
                onValueChange = { newText ->
                    actions.onTitleChanged.invoke(newText)
                },
                label = { Text(text = "Exercício") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                value = state.editedExercise.description,
                onValueChange = { newText ->
                    actions.onDescriptionChanged.invoke(newText)
                },
                label = { Text(text = "Descrição") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            Button(
                onClick = { actions.onSaveChanges.invoke() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)

            ) {
                Text(text = "Salvar")
            }
        }
    }
}

@Composable
fun ExerciseImage(
    imageURL: String,
    onPhotographURIChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(300.dp)
            .padding(20.dp)
            .clip(RoundedCornerShape(12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val contentResolver = LocalContext.current.contentResolver
        val scope = rememberCoroutineScope()
        val launcher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.OpenDocument()
            ) {
                it?.run {
                    contentResolver.takePersistableUriPermission(
                        this,
                        FLAG_GRANT_READ_URI_PERMISSION
                    )
                    onPhotographURIChanged.invoke(this.toString())
                }
            }
        AsyncImage(
            model = imageURL,
            contentDescription = "",
            error = painterResource(id = R.drawable.clickable_image),
            onError = {
                it.result.throwable
            },
            contentScale = ContentScale.Crop,
            clipToBounds = true,
            modifier = Modifier
                .fillMaxSize()
                .size(50.dp)
                .clickable {
                    launcher.launch(arrayOf("image/*"))
                }
        )
    }
}

@Composable
@Preview(name = "AddEditExercise")
private fun AddEditExerciseScreenPreview() {
    AddEditExerciseScreen(
        state = AddEditTrainingState(),
        actions = AddEditExerciseActions(
            onTitleChanged = {},
            onDescriptionChanged = {},
            onImageChanged = {},
            onSaveChanges = {},
        ),
    )
}

