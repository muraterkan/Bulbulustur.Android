package com.bulbulustur.android.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SplashControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface SplashControllerEvent {
    data object Refresh : SplashControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : SplashControllerEvent
    data class Submit(val body: Any? = null) : SplashControllerEvent
}

class SplashController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(SplashControllerState())
    val State: StateFlow<SplashControllerState> = _state.asStateFlow()


    fun Landing(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Landing"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Splash.Landing." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Landing",
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
    fun ModeSelection(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "ModeSelection"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Splash.ModeSelection." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "ModeSelection",
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