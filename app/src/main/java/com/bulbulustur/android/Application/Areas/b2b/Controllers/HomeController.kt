package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface HomeControllerEvent {
    data object Refresh : HomeControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : HomeControllerEvent
    data class Submit(val body: Any? = null) : HomeControllerEvent
}

class HomeController(
    private val executeService: IExecuteService,
    private val defaultRepository: IB2BDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(HomeControllerState())
    val State: StateFlow<HomeControllerState> = _state.asStateFlow()


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
                cacheKey = "b2b.Home.Index." + parameters.toString()
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
}

