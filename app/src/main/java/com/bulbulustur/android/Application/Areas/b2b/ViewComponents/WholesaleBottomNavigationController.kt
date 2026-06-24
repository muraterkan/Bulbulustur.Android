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

data class WholesaleBottomNavigationControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface WholesaleBottomNavigationControllerEvent {

    data object Refresh : WholesaleBottomNavigationControllerEvent

    data class Load(
        val Parameters: Map<String, Any?> = emptyMap()
    ) : WholesaleBottomNavigationControllerEvent
}

class WholesaleBottomNavigationController(
    private val executeService: IExecuteService,
    private val defaultRepository: IB2BDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(
        WholesaleBottomNavigationControllerState()
    )

    val State: StateFlow<WholesaleBottomNavigationControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: WholesaleBottomNavigationControllerEvent
    ) {
        when (event) {
            WholesaleBottomNavigationControllerEvent.Refresh -> {
                Index()
            }

            is WholesaleBottomNavigationControllerEvent.Load -> {
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
                cacheKey = "App.Areas.b2b.ViewComponents.WholesaleBottomNavigation.Index." + parameters.toString()
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
