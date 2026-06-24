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

data class CategoryQuickAccessControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
)

sealed interface CategoryQuickAccessControllerEvent {

    data object Refresh : CategoryQuickAccessControllerEvent

    data class Load(
        val Parameters: Map<String, Any?> = emptyMap()
    ) : CategoryQuickAccessControllerEvent
}

class CategoryQuickAccessController(
    private val executeService: IExecuteService,
    private val defaultRepository: IAppDefaultRepository
) : BaseController() {

    private val _state = MutableStateFlow(
        CategoryQuickAccessControllerState()
    )

    val State: StateFlow<CategoryQuickAccessControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: CategoryQuickAccessControllerEvent
    ) {
        when (event) {
            CategoryQuickAccessControllerEvent.Refresh -> {
                Index()
            }

            is CategoryQuickAccessControllerEvent.Load -> {
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
                cacheKey = "App.ViewComponents.CategoryQuickAccess.Index." + parameters.toString()
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
