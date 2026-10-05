package com.javiviz.sh01_aplicaciondeutileria.main.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private var _currentSection = MutableStateFlow(Sections.WATER_INTAKE)
    private val _isDarkTheme = MutableStateFlow(true)
    private val _nombre = MutableStateFlow("")
    private val _matricula = MutableStateFlow("")
    val currentSection : StateFlow<Sections> = _currentSection.asStateFlow()
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()
    val nombre: StateFlow<String> = _nombre.asStateFlow()
    val matricula: StateFlow<String> = _matricula.asStateFlow()

    fun setDarkTheme(enabled: Boolean) {
        _isDarkTheme.value = enabled
    }
    fun onSectionSelected(section: Sections) {
        _currentSection.value = section
    }

    fun setProfile() {
        _nombre.value = "Javier A. Zárate Gómez"
        _matricula.value = "Matricula: 253441"
    }
}