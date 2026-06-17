package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface ProductControllerEvent {
    data object Refresh : ProductControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : ProductControllerEvent
    data class Submit(val body: Any? = null) : ProductControllerEvent
}

class ProductController(
    private val executeService: IExecuteService,
    private val defaultRepository: com.bulbulustur.android.Application.Areas.b2b.Controllers.IB2BDefaultRepository
) : com.bulbulustur.android.Application.Areas.b2b.Controllers.BaseController() {

    private val _state = MutableStateFlow(_root_ide_package_.com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductControllerState())
    val State: StateFlow<com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductControllerState> = _state.asStateFlow()


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
                cacheKey = "b2b.Product.Index." + parameters.toString()
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
                cacheKey = "b2b.Product.List." + parameters.toString()
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
                cacheKey = "b2b.Product.Detail." + parameters.toString()
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
    fun SampleRequest(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "SampleRequest"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2b.Product.SampleRequest." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "SampleRequest",
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
    fun SampleRequestPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "SampleRequestPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2b.Product.SampleRequestPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "SampleRequestPost",
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
    fun LastPriceRequest(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "LastPriceRequest"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2b.Product.LastPriceRequest." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "LastPriceRequest",
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
    fun LastPriceRequestPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "LastPriceRequestPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2b.Product.LastPriceRequestPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "LastPriceRequestPost",
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
    fun CustomizationRequest(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "CustomizationRequest"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2b.Product.CustomizationRequest." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "CustomizationRequest",
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
    fun CustomizationRequestPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "CustomizationRequestPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2b.Product.CustomizationRequestPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "CustomizationRequestPost",
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
}
