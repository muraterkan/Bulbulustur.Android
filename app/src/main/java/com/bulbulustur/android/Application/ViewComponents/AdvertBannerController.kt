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

data class AdvertBannerControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface AdvertBannerControllerEvent {

    data object Refresh : AdvertBannerControllerEvent

    data class Load(
        val Parameters: Map<String, Any?> = emptyMap()
    ) : AdvertBannerControllerEvent
}

class AdvertBannerController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(
        AdvertBannerControllerState()
    )

    val State: StateFlow<AdvertBannerControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: AdvertBannerControllerEvent
    ) {
        when (event) {
            AdvertBannerControllerEvent.Refresh -> {
                Index()
            }

            is AdvertBannerControllerEvent.Load -> {
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
                cacheKey = "App.ViewComponents.AdvertBanner.Index." + parameters.toString()
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
