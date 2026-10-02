package com.javiviz.sh01_aplicaciondeutileria.pureSulfateCalculator.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.sh01_aplicaciondeutileria.pureSulfateCalculator.viewModel.PureSulfateCalculatorViewModel
import com.javiviz.sh01_aplicaciondeutileria.ui.components.CustomTextField
import com.javiviz.sh01_aplicaciondeutileria.ui.components.ResultCard

@Composable
fun PureSulfateCalculatorScreen(viewModel : PureSulfateCalculatorViewModel = viewModel()) {
    val waterIntake by viewModel.waterIntake.collectAsStateWithLifecycle()
    val turbidity by viewModel.turbidity.collectAsStateWithLifecycle()
    val pureSulfate by viewModel.pureSulfate.collectAsStateWithLifecycle()

    ResultCard(pureSulfate,"Cantidad del sulfato puro a dosificar ml/min")

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

    Button(
        modifier = Modifier.padding(32.dp).fillMaxWidth(),
        onClick = {viewModel.calculatePureSulfate()}
    ) {
        Text("Calcular")
    }
}