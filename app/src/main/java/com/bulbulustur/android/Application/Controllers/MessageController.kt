package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MessageControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface MessageControllerEvent {
    data object Refresh : MessageControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : MessageControllerEvent
    data class Submit(val body: Any? = null) : MessageControllerEvent
}

class MessageController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(MessageControllerState())
    val State: StateFlow<MessageControllerState> = _state.asStateFlow()


    fun Inbox(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Inbox"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Message.Inbox." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Inbox",
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
                cacheKey = "App.Message.Detail." + parameters.toString()
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
    fun SendPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "SendPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "App.Message.SendPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "SendPost",
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

