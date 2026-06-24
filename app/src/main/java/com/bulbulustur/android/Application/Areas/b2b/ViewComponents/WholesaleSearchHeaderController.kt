package com.bulbulustur.android.Application.Areas.b2b.ViewComponents

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Areas.b2b.Controllers.BaseController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.IB2BDefaultRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WholesaleSearchHeaderControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface WholesaleSearchHeaderControllerEvent {

    data object Refresh : WholesaleSearchHeaderControllerEvent

    data class Load(
        val Parameters: Map<String, Any?> = emptyMap()
    ) : WholesaleSearchHeaderControllerEvent
}

class WholesaleSearchHeaderController(
    private val executeService: IExecuteService,
    private val defaultRepository: IB2BDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(
        WholesaleSearchHeaderControllerState()
    )

    val State: StateFlow<WholesaleSearchHeaderControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: WholesaleSearchHeaderControllerEvent
    ) {
        when (event) {
            WholesaleSearchHeaderControllerEvent.Refresh -> {
                Index()
            }

            is WholesaleSearchHeaderControllerEvent.Load -> {
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
                cacheKey = "App.Areas.b2b.ViewComponents.WholesaleSearchHeader.Index." + parameters.toString()
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
