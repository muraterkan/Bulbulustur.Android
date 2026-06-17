package com.bulbulustur.android.Application.ViewComponents

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Controllers.BaseController
import com.bulbulustur.android.Application.Controllers.IAppDefaultRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StoreCardControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface StoreCardControllerEvent {

    data object Refresh : StoreCardControllerEvent

    data class Load(
        val Parameters: Map<String, Any?> = emptyMap()
    ) : StoreCardControllerEvent
}

class StoreCardController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(
        StoreCardControllerState()
    )

    val State: StateFlow<StoreCardControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: StoreCardControllerEvent
    ) {
        when (event) {
            StoreCardControllerEvent.Refresh -> {
                Index()
            }

            is StoreCardControllerEvent.Load -> {
                Index(
                    parameters = event.Parameters
                )
            }
        }
    }

    fun Index(
        parameters: Map<String, Any?> = emptyMap()
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Index"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.ViewComponents.StoreCard.Index." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Index",
                    parameters = parameters
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    LastResult = response,
                    ErrorMessage = null
                )
            }
        }
    }
}