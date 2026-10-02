package com.javiviz.sh01_aplicaciondeutileria.waterIntakeCalculator.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WaterIntakeCalculatorViewModel : ViewModel() {
    private var _literPerSecond = MutableStateFlow("")
    private var _minutesOfWork = MutableStateFlow("")
    private var _waterIntake = MutableStateFlow(0.0)

    val literPerSecond : StateFlow<String> = _literPerSecond.asStateFlow()
    val minutesOfWork : StateFlow<String> = _minutesOfWork.asStateFlow()
    val waterIntake : StateFlow<Double> = _waterIntake.asStateFlow()

    fun setLiterPerSecond(newValue : String) {

        _literPerSecond.value = newValue
    }
    fun setMinutesOfWork(newValue : String) {

        _minutesOfWork.value = newValue
    }

    fun calculateWaterIntake() {
        val parsedLiterPerSecond = _literPerSecond.value.toDoubleOrNull() ?: 0.0
        val parsedMinutesOfWork = _minutesOfWork.value.toDoubleOrNull() ?: 0.0
        _waterIntake.value = (parsedLiterPerSecond * parsedMinutesOfWork) / 60
    }

}