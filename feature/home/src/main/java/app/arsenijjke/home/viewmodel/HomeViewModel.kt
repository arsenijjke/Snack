package app.arsenijjke.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.arsenijjke.domain.usecase.GetCategoriesUseCase
import app.arsenijjke.domain.usecase.GetRandomMealUseCase
import app.arsenijjke.domain.usecase.GetTimeOfDayUseCase
import app.arsenijjke.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomMealUseCase: GetRandomMealUseCase,
    private val getTimeOfDayUseCase: GetTimeOfDayUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    val state: StateFlow<MealUiState> = mealUiState()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            MealUiState.Loading
        )

    private fun mealUiState(): Flow<MealUiState> =
        combine(
            flow { emit(getRandomMealUseCase()) },
            flow { emit(getCategoriesUseCase()) },
        ) { mealResult, categoriesResult ->
            val greeting = getTimeOfDayUseCase()

            val categories = when (categoriesResult) {
                is Result.Success -> categoriesResult.value.map { it.toUiModel() }
                is Result.Failure -> emptyList()
                is Result.Loading -> emptyList()
            }

            when (mealResult) {
                is Result.Success -> MealUiState.Success(
                    mealResult.value.toUiModel(
                        greeting = greeting,
                        categories = categories,
                    )
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
