package com.javiviz.sh01_aplicaciondeutileria.pureSulfateCalculator.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PureSulfateCalculatorViewModel : ViewModel() {
    private var _waterIntake = MutableStateFlow("")
    private var _turbidity = MutableStateFlow("")
    private var _pureSulfate = MutableStateFlow(0.0)

    val waterIntake : StateFlow<String> = _waterIntake.asStateFlow()
    val turbidity : StateFlow<String> = _turbidity.asStateFlow()
    val pureSulfate : StateFlow<Double> = _pureSulfate.asStateFlow()

    fun setWaterIntake(newValue : String) {
        _waterIntake.value = newValue
    }
    fun setTurbidity(newValue : String) {
        _turbidity.value = newValue
    }

    fun calculatePureSulfate () {
        val gasto = _waterIntake.value.toDoubleOrNull() ?: 0.0
        val turbiedad = _turbidity.value.toDoubleOrNull() ?: 0.0
        _pureSulfate.value = (gasto * turbiedad) / 69.947
    }
}