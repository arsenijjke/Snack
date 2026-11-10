package app.arsenijjke.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.arsenijjke.domain.usecase.GetRandomMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomMealUseCase: GetRandomMealUseCase
) : ViewModel() {

    private val _state = MutableStateFlow("")
    val state = _state
        .onStart{ getRandomMeal() }
        .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5L),
        ""
    )

    fun getRandomMeal() {
        viewModelScope.launch {
            _state.value = getRandomMealUseCase().toString()
        }

    }
}
