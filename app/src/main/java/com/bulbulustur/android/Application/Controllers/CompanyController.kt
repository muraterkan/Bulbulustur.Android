package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CompanyControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface CompanyControllerEvent {
    data object Refresh : CompanyControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : CompanyControllerEvent
    data class Submit(val body: Any? = null) : CompanyControllerEvent
}

class CompanyController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(CompanyControllerState())
    val State: StateFlow<CompanyControllerState> = _state.asStateFlow()


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
                cacheKey = "App.Company.Index." + parameters.toString()
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
    fun List(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "List"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Company.List." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "List",
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
    fun Detail(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Detail"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Company.Detail." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Detail",
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
    fun Edit(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Edit"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Company.Edit." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Edit",
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
    fun Products(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Products"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Company.Products." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Products",
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
    fun Contact(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Contact"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Company.Contact." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Contact",
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
    fun Activate(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Activate"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Company.Activate." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Activate",
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

