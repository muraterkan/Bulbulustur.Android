package com.bulbulustur.android.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AccountControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface AccountControllerEvent {
    data object Refresh : AccountControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : AccountControllerEvent
    data class Submit(val body: Any? = null) : AccountControllerEvent
}

class AccountController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(AccountControllerState())
    val State: StateFlow<AccountControllerState> = _state.asStateFlow()


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
                cacheKey = "App.Account.Index." + parameters.toString()
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
    fun Security(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Security"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Account.Security." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Security",
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
    fun AddressList(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "AddressList"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Account.AddressList." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "AddressList",
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
    fun AddressForm(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "AddressForm"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Account.AddressForm." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "AddressForm",
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
    fun CommunicationPreference(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "CommunicationPreference"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Account.CommunicationPreference." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "CommunicationPreference",
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
    fun ProductHistory(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "ProductHistory"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Account.ProductHistory." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "ProductHistory",
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
    fun RequestList(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "RequestList"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Account.RequestList." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "RequestList",
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
    fun RequestDetail(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "RequestDetail"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Account.RequestDetail." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "RequestDetail",
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
    fun ReviewList(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "ReviewList"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Account.ReviewList." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "ReviewList",
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