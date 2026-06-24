package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ReportControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface ReportControllerEvent {
    data object Refresh : ReportControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : ReportControllerEvent
    data class Submit(val body: Any? = null) : ReportControllerEvent
}

class ReportController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(ReportControllerState())
    val State: StateFlow<ReportControllerState> = _state.asStateFlow()


    fun Abuse(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Abuse"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Report.Abuse." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Abuse",
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
    fun AbusePost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "AbusePost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "App.Report.AbusePost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "AbusePost",
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

