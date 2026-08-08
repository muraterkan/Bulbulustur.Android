package com.bulbulustur.android.Application.Controllers

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SearchControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface SearchControllerEvent {
    data object Refresh : SearchControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : SearchControllerEvent
    data class Submit(val body: Any? = null) : SearchControllerEvent
}

class SearchController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(SearchControllerState())
    val State: StateFlow<SearchControllerState> = _state.asStateFlow()


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
                cacheKey = "App.Search.Index." + parameters.toString()
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
    fun Result(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = BBLocalization.Current.Get(key = "acdeca0e-10dc-40c3-879d-9834eb11644f", fallback = "Result")
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Search.Result." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = BBLocalization.Current.Get(key = "acdeca0e-10dc-40c3-879d-9834eb11644f", fallback = "Result"),
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

