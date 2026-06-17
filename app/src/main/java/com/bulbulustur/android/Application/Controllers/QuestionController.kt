package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class QuestionControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface QuestionControllerEvent {
    data object Refresh : QuestionControllerEvent
    data class Load(val parameters: Map<String, Any?> = emptyMap()) : QuestionControllerEvent
    data class Submit(val body: Any? = null) : QuestionControllerEvent
}

class QuestionController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(QuestionControllerState())
    val State: StateFlow<QuestionControllerState> = _state.asStateFlow()


    fun Answer(parameters: Map<String, Any?> = emptyMap()) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "Answer"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "App.Question.Answer." + parameters.toString()
            ) {
                defaultRepository.GetAsync(
                    actionName = "Answer",
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
    fun AnswerPost(body: Any? = null) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "AnswerPost"
                )
            }

            val response = executeService.PostAsync(
                operationType = "App.Question.AnswerPost"
            ) {
                defaultRepository.PostAsync(
                    actionName = "AnswerPost",
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
