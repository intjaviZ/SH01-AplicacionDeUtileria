package com.javiviz.sh01_aplicaciondeutileria.waterIntakeCalculator.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.sh01_aplicaciondeutileria.ui.components.CustomTextField
import com.javiviz.sh01_aplicaciondeutileria.ui.components.ResultCard
import com.javiviz.sh01_aplicaciondeutileria.waterIntakeCalculator.viewModel.WaterIntakeCalculatorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterIntakeCalculatorScreen(viewModel : WaterIntakeCalculatorViewModel = viewModel()) {
    val literPerSecond by viewModel.literPerSecond.collectAsStateWithLifecycle()
    val minutesOfWork by viewModel.minutesOfWork.collectAsStateWithLifecycle()
    val waterIntake by viewModel.waterIntake.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ResultCard(waterIntake,"Resultado en litros por segundo")

        CustomTextField(
            value = literPerSecond,
            onValueChanged = { viewModel.setLiterPerSecond(it) },
            label = "Litros por segundo"
        )

        CustomTextField(
            value = minutesOfWork,
            onValueChanged = { viewModel.setMinutesOfWork(it) },
            label = "Minutos de trabajo"
        )


        Button(
            modifier = Modifier.padding(32.dp).fillMaxWidth(),
            onClick = {viewModel.calculateWaterIntake()}
        ) {
            Text("Calcular")
        }
    }
}