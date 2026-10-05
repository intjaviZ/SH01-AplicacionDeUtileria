package com.javiviz.sh01_aplicaciondeutileria.polymerCalculator.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.sh01_aplicaciondeutileria.polymerCalculator.viewModel.PolymerCalculatorViewModel
import com.javiviz.sh01_aplicaciondeutileria.ui.components.CustomTextField
import com.javiviz.sh01_aplicaciondeutileria.ui.components.ResultCard

@Composable
fun PolymerCalculatorScreen(viewModel : PolymerCalculatorViewModel = viewModel()) {
    val waterIntake by viewModel.waterIntake.collectAsStateWithLifecycle()
    val turbidity by viewModel.turbidity.collectAsStateWithLifecycle()
    val factor by viewModel.factor.collectAsStateWithLifecycle()
    val liters by viewModel.liters.collectAsStateWithLifecycle()
    val polymer by viewModel.polymer.collectAsStateWithLifecycle()


    ResultCard(polymer,"Polimero")

    CustomTextField(
        value = waterIntake,
        onValueChanged = { viewModel.setWaterIntake(it) },
        label = "Gasto promedio de agua"
    )

    CustomTextField(
        value = turbidity,
        onValueChanged = { viewModel.setTurbidity(it) },
        label = "Turbiedad del agua"
    )

    CustomTextField(
        value = factor,
        onValueChanged = { viewModel.setFactor(it) },
        label = "Factor de la formula"
    )

    CustomTextField(
        value = liters,
        onValueChanged = { viewModel.setLiters(it) },
        label = "Litros de prueba",
        nextAction = ImeAction.Go
    )

    Button(
        modifier = Modifier.padding(32.dp).fillMaxWidth(),
        onClick = {viewModel.calculatePolymer()}
    ) {
        Text("Calcular")
    }
}