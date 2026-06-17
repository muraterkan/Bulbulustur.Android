package com.bulbulustur.android.Application.Areas.b2c.ViewComponents

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Areas.b2c.Controllers.BaseController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.IB2CDefaultRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RetailSearchHeaderControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface RetailSearchHeaderControllerEvent {

    data object Refresh : RetailSearchHeaderControllerEvent

    data class Load(
        val Parameters: Map<String, Any?> = emptyMap()
    ) : RetailSearchHeaderControllerEvent
}

class RetailSearchHeaderController(
    private val executeService: IExecuteService,
    private val defaultRepository: IB2CDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(
        RetailSearchHeaderControllerState()
    )

    val State: StateFlow<RetailSearchHeaderControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: RetailSearchHeaderControllerEvent
    ) {
        when (event) {
            RetailSearchHeaderControllerEvent.Refresh -> {
                Index()
            }

            is RetailSearchHeaderControllerEvent.Load -> {
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
                cacheKey = "App.Areas.b2c.ViewComponents.RetailSearchHeader.Index." + parameters.toString()
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