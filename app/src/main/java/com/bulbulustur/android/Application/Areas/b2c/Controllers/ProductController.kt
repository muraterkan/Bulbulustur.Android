package com.bulbulustur.android.Application.Areas.b2c.Controllers

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
    private val defaultRepository: IB2CDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(ProductControllerState())
    val State: StateFlow<ProductControllerState> = _state.asStateFlow()


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
                cacheKey = "b2c.Product.Index." + parameters.toString()
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
                cacheKey = "b2c.Product.List." + parameters.toString()
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
                cacheKey = "b2c.Product.Detail." + parameters.toString()
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
    fun OtherSellerList(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "OtherSellerList"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2c.Product.OtherSellerList." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "OtherSellerList",
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
    fun Question(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Question"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2c.Product.Question." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Question",
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
    fun QuestionPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "QuestionPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2c.Product.QuestionPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "QuestionPost",
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
    fun Review(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Review"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2c.Product.Review." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Review",
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
    fun ReviewPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "ReviewPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "b2c.Product.ReviewPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "ReviewPost",
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
