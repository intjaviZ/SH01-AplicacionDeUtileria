package com.javiviz.sh01_aplicaciondeutileria.dilutedSulfateCalculator.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DilutedSulfateCalculatorViewModel : ViewModel() {
    private var _waterIntake = MutableStateFlow("")
    private var _turbidity = MutableStateFlow("")
    private var _concentration = MutableStateFlow("")
    private var _dilutedSulfate = MutableStateFlow(0.0)

    val waterIntake : StateFlow<String> = _waterIntake.asStateFlow()
    val turbidity : StateFlow<String> = _turbidity.asStateFlow()
    val concentration : StateFlow<String> = _concentration.asStateFlow()
    val dilutedSulfate : StateFlow<Double> = _dilutedSulfate.asStateFlow()

    fun setWaterIntake(newValue : String) {
        _waterIntake.value = newValue
    }
    fun setTurbidity(newValue : String) {
        _turbidity.value = newValue
    }
    fun setConcentration(newValue : String) {
        _concentration.value = newValue
    }

    fun calculateDilutedSulfate () {
        val gasto = _waterIntake.value.toDoubleOrNull() ?: 0.0
        val turbiedad = _turbidity.value.toDoubleOrNull() ?: 0.0
        val concentrado = _concentration.value.toDoubleOrNull() ?: 0.0
        val factorTurbiedad = calculateFactor(turbiedad)
        _dilutedSulfate.value = if (concentrado != 0.0) {
            (gasto*turbiedad*factorTurbiedad)/((concentrado) * 5)
        } else {
            0.0
        }
    }

    fun calculateFactor (turbidity : Double) : Double{
        return when {
            turbidity > 0 && turbidity <= 80 -> 0.13
            turbidity > 80 && turbidity <= 149 -> 0.18
            turbidity > 149 && turbidity <= 300 -> 0.23
            else -> 0.0
        }
    }

}