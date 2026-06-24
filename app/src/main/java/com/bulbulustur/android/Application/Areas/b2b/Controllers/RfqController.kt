package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RfqControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface RfqControllerEvent {
    data object Refresh : RfqControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : RfqControllerEvent
    data class Submit(val body: Any? = null) : RfqControllerEvent
}

class RfqController(
    private val executeService: IExecuteService,
    private val defaultRepository: IB2BDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(RfqControllerState())
    val State: StateFlow<RfqControllerState> = _state.asStateFlow()


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
                cacheKey = "b2b.Rfq.Index." + parameters.toString()
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
                cacheKey = "b2b.Rfq.List." + parameters.toString()
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
    fun Create(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Create"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2b.Rfq.Create." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Create",
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
    fun CreatePost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "CreatePost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2b.Rfq.CreatePost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "CreatePost",
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
                cacheKey = "b2b.Rfq.Detail." + parameters.toString()
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
    fun OfferDetail(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "OfferDetail"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2b.Rfq.OfferDetail." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "OfferDetail",
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
    fun CancelPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "CancelPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2b.Rfq.CancelPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "CancelPost",
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

