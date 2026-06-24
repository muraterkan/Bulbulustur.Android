package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AboutControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface AboutControllerEvent {
    data object Refresh : AboutControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : AboutControllerEvent
    data class Submit(val body: Any? = null) : AboutControllerEvent
}

class AboutController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(AboutControllerState())
    val State: StateFlow<AboutControllerState> = _state.asStateFlow()


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
                cacheKey = "App.About.Index." + parameters.toString()
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
    fun BankAccounts(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "BankAccounts"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.About.BankAccounts." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "BankAccounts",
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

