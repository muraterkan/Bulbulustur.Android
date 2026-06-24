package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LogonControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface LogonControllerEvent {
    data object Refresh : LogonControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : LogonControllerEvent
    data class Submit(val body: Any? = null) : LogonControllerEvent
}

class LogonController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(LogonControllerState())
    val State: StateFlow<LogonControllerState> = _state.asStateFlow()


    fun Login(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Login"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Logon.Login." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Login",
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
    fun LoginPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "LoginPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "App.Logon.LoginPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "LoginPost",
                    body = body
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
    fun RegisterStart(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "RegisterStart"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Logon.RegisterStart." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "RegisterStart",
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
    fun RegisterStartPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "RegisterStartPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "App.Logon.RegisterStartPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "RegisterStartPost",
                    body = body
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
    fun RegisterFinal(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "RegisterFinal"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Logon.RegisterFinal." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "RegisterFinal",
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
    fun RegisterFinalPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "RegisterFinalPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "App.Logon.RegisterFinalPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "RegisterFinalPost",
                    body = body
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
    fun ForgotPassword(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "ForgotPassword"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Logon.ForgotPassword." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "ForgotPassword",
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
    fun ForgotPasswordPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "ForgotPasswordPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "App.Logon.ForgotPasswordPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "ForgotPasswordPost",
                    body = body
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
    fun Expired(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Expired"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Logon.Expired." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Expired",
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
    fun FirstDoor(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "FirstDoor"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Logon.FirstDoor." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "FirstDoor",
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

