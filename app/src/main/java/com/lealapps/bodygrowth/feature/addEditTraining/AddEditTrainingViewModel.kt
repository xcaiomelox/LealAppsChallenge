package com.lealapps.bodygrowth.feature.addEditTraining

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lealapps.bodygrowth.core.domain.model.Exercise
import com.lealapps.bodygrowth.core.domain.repository.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class AddEditTrainingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AddEditTrainingState> =
        MutableStateFlow(AddEditTrainingState())

    val stateFlow: StateFlow<AddEditTrainingState> = _stateFlow.asStateFlow()
    private val selectedExerciseIndex = mutableIntStateOf(0)

    fun updateTrainingTitle(newTitle: String) {
        _stateFlow.update {
            it.copy(training = it.training.copy(title = newTitle))
        }
    }

    fun updateTrainingDescription(newDescription: String) {
        _stateFlow.update {
            it.copy(training = it.training.copy(description = newDescription))
        }
    }

    fun updateTrainingDate(newDate: Timestamp) {
        _stateFlow.update {
            it.copy(training = it.training.copy(date = newDate))
        }
    }

    fun updateSelectedExerciseTitle(newTitle: String) {
        _stateFlow.update {
            it.copy(editedExercise = it.editedExercise.copy(title = newTitle))
        }
        updateExerciseInExerciseList()
    }

    fun updateSelectedExerciseDescription(newDescription: String) {
        _stateFlow.update {
            it.copy(editedExercise = it.editedExercise.copy(description = newDescription))
        }
        updateExerciseInExerciseList()
    }

    fun updateSelectedExerciseImage(newImage: String) {
        _stateFlow.update {
            it.copy(editedExercise = it.editedExercise.copy(image = newImage))
        }
        updateExerciseInExerciseList()
    }

    private fun updateExerciseInExerciseList() {
        _stateFlow.update {
            val newExerciseList =
                if (selectedExerciseIndex.intValue > it.training.exercises.lastIndex) {
                    it.training.exercises + it.editedExercise
                } else {
                    it.training.exercises.mapIndexed { index, exercise ->
                        if (index == selectedExerciseIndex.intValue) it.editedExercise else exercise
                    }
                }
            it.copy(training = it.training.copy(exercises = newExerciseList))
        }
    }

    fun loadTraining(id: Int) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                trainingRepository.getTrainingById(id)?.let { training ->
                    _stateFlow.update {
                        it.copy(training = training)
                    }
                }
            }
        }
    }

    fun saveTraining() {
        CoroutineScope(Dispatchers.IO).launch {
            trainingRepository.upsertTraining(_stateFlow.value.training)
        }
    }

    fun addNewExercise() {
        _stateFlow.update {
            selectedExerciseIndex.intValue = it.training.exercises.lastIndex + 1
            it.copy(editedExercise = Exercise())
        }
    }

    fun updateSelectedExercise(editedExercise: Exercise) {
        _stateFlow.update {
            selectedExerciseIndex.intValue = it.training.exercises.indexOf(editedExercise)
            it.copy(editedExercise = editedExercise)
        }
    }
}