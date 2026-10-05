package com.javiviz.sh01_aplicaciondeutileria.polymerCalculator.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PolymerCalculatorViewModel : ViewModel() {
    private var _waterIntake = MutableStateFlow("")
    private var _turbidity = MutableStateFlow("")
    private var _factor = MutableStateFlow("")
    private var _liters = MutableStateFlow("")
    private var _polymer = MutableStateFlow(0.0)

    val waterIntake : StateFlow<String> = _waterIntake.asStateFlow()
    val turbidity : StateFlow<String> = _turbidity.asStateFlow()
    val factor : StateFlow<String> = _factor.asStateFlow()
    val liters : StateFlow<String> = _liters.asStateFlow()
    val polymer : StateFlow<Double> = _polymer.asStateFlow()

    fun setWaterIntake(newValue : String) {
        _waterIntake.value = newValue
    }
    fun setTurbidity(newValue : String) {
        _turbidity.value = newValue
    }

    fun setFactor(newValue : String) {
        _factor.value = newValue
    }
    fun setLiters(newValue : String) {
        _liters.value = newValue
    }

    fun calculatePolymer() {
        val gasto = _waterIntake.value.toDoubleOrNull() ?: 0.0
        val turbiedad = _turbidity.value.toDoubleOrNull() ?: 0.0
        val factor = _factor.value.toDoubleOrNull() ?: 0.0
        val litros = _liters.value.toDoubleOrNull() ?: 0.0

        _polymer.value = if (litros != 0.0) {
            (gasto * turbiedad * factor * 2.852) / litros
        } else {
            0.0
        }
    }
}