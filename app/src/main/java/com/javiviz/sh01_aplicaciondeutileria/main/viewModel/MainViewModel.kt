package com.javiviz.sh01_aplicaciondeutileria.main.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private var _currentSection = MutableStateFlow(Sections.WATER_INTAKE)
    val currentSection : StateFlow<Sections> = _currentSection.asStateFlow()

    fun onSectionSelected(section: Sections) {
        _currentSection.value = section
    }
}