package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PreferenceControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface PreferenceControllerEvent {
    data object Refresh : PreferenceControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : PreferenceControllerEvent
    data class Submit(val body: Any? = null) : PreferenceControllerEvent
}

class PreferenceController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(PreferenceControllerState())
    val State: StateFlow<PreferenceControllerState> = _state.asStateFlow()


    fun UsagePurpose(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "UsagePurpose"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Preference.UsagePurpose." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "UsagePurpose",
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
    fun UsagePurposePost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "UsagePurposePost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "App.Preference.UsagePurposePost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "UsagePurposePost",
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

