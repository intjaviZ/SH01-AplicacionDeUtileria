package com.javiviz.sh01_aplicaciondeutileria.main.presentation

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.sh01_aplicaciondeutileria.dilutedSulfateCalculator.presentation.DilutedSulfateCalculatorScreen
import com.javiviz.sh01_aplicaciondeutileria.main.viewModel.MainViewModel
import com.javiviz.sh01_aplicaciondeutileria.main.viewModel.Sections
import com.javiviz.sh01_aplicaciondeutileria.polymerCalculator.presentation.PolymerCalculatorScreen
import com.javiviz.sh01_aplicaciondeutileria.pureSulfateCalculator.presentation.PureSulfateCalculatorScreen
import com.javiviz.sh01_aplicaciondeutileria.ui.components.Profile
import com.javiviz.sh01_aplicaciondeutileria.waterIntakeCalculator.presentation.WaterIntakeCalculatorScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel : MainViewModel = viewModel()) {
    val currentSection by viewModel.currentSection.collectAsStateWithLifecycle()
    val isDark by viewModel.isDarkTheme.collectAsStateWithLifecycle()
    val nombre by viewModel.nombre.collectAsStateWithLifecycle()
    val matricula by viewModel.matricula.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Text(
                    text = "Calculadora de dosificación",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                Sections.entries.forEach { section ->
                    NavigationDrawerItem(
                        label = { Text(section.label) },
                        selected = currentSection == section,
                        onClick = {
                            viewModel.onSectionSelected(section)
                            coroutineScope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Modo oscuro", style = MaterialTheme.typography.bodyLarge)

                    Switch(
                        checked = isDark,
                        onCheckedChange = { viewModel.setDarkTheme(it) }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentSection.label) },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menú"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .imePadding()
                    .verticalScroll(scrollState),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                when(currentSection) {
                    Sections.WATER_INTAKE -> WaterIntakeCalculatorScreen()
                    Sections.PURE_SULFATE -> PureSulfateCalculatorScreen()
                    Sections.DILUTED_SULFATE -> DilutedSulfateCalculatorScreen()
                    Sections.POLYMER -> PolymerCalculatorScreen()
                }

                ElevatedButton(onClick = { viewModel.setProfile() }) {
                    Text("Actualizar perfil")
                }
                Profile(nombre = nombre, matricula = matricula)

            }

        }
    }

}