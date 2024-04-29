package com.lealapps.bodygrowth.feature.trainingList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lealapps.bodygrowth.core.domain.repository.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TrainingListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<TrainingListState> =
        MutableStateFlow(TrainingListState())

    val stateFlow: StateFlow<TrainingListState> = _stateFlow.asStateFlow()

    init {
        loadTrainings()
    }

    private fun loadTrainings() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                trainingRepository.getTrainings().collect { trainingList ->
                    _stateFlow.update {
                        it.copy(itemList = trainingList)
                    }
                }
            }
        }
    }
}