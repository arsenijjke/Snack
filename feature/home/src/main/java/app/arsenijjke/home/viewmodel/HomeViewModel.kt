package app.arsenijjke.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.arsenijjke.domain.usecase.GetRandomMealUseCase
import app.arsenijjke.domain.usecase.GetTimeOfDayUseCase
import app.arsenijjke.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val getTimeOfDayUseCase: GetTimeOfDayUseCase
) : ViewModel() {

    val state: StateFlow<MealUiState> = mealUiState()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            MealUiState.Loading
        )

    private fun mealUiState(): Flow<MealUiState> =
        flow { emit(getRandomMealUseCase()) }
            .map { result ->
                val greeting = getTimeOfDayUseCase()
                when (result) {
                    is Result.Success -> MealUiState.Success(
                        result.value.toUiModel(greeting = greeting)
                    )
                    is Result.Failure -> MealUiState.Error
                    is Result.Loading -> MealUiState.Loading
                }
            }

    sealed interface MealUiState {
        data class Success(val meal: MealUiModel) : MealUiState
        data object Error : MealUiState
        data object Loading : MealUiState
    }

}
