package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BasketControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface BasketControllerEvent {
    data object Refresh : BasketControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : BasketControllerEvent
    data class Submit(val body: Any? = null) : BasketControllerEvent
}

class BasketController(
    private val executeService: IExecuteService,
    private val defaultRepository: IB2CDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(BasketControllerState())
    val State: StateFlow<BasketControllerState> = _state.asStateFlow()


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
                cacheKey = "b2c.Basket.Index." + parameters.toString()
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
    fun AddPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "AddPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2c.Basket.AddPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "AddPost",
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
    fun UpdatePost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "UpdatePost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2c.Basket.UpdatePost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "UpdatePost",
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
    fun RemovePost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "RemovePost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2c.Basket.RemovePost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "RemovePost",
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

