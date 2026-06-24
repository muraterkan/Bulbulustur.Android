package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SettingsControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface SettingsControllerEvent {
    data object Refresh : SettingsControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : SettingsControllerEvent
    data class Submit(val body: Any? = null) : SettingsControllerEvent
}

class SettingsController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(SettingsControllerState())
    val State: StateFlow<SettingsControllerState> = _state.asStateFlow()


    fun Index(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Index"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Settings.Index." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Index",
                    parameters = parameters
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    LastResult = response
                )
            }
        }
    }
    fun LanguageSelection(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "LanguageSelection"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Settings.LanguageSelection." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "LanguageSelection",
                    parameters = parameters
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    LastResult = response
                )
            }
        }
    }
    fun ThemeSelection(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "ThemeSelection"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Settings.ThemeSelection." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "ThemeSelection",
                    parameters = parameters
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    LastResult = response
                )
            }
        }
    }
    fun Appearance(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Appearance"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Settings.Appearance." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Appearance",
                    parameters = parameters
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    LastResult = response
                )
            }
        }
    }
}

