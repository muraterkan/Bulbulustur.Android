package com.bulbulustur.android.Application.Areas.b2b.Controllers

import android.util.Log

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerCustomizeRequestRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerLastPriceRequestRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerSampleRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerCustomizeRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerLastPriceRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerSampleRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WholesaleBuyerRequestControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val ErrorMessage: String? = null,
    val LastPriceInsertResult: Result<Unit>? = null,
    val SampleInsertResult: Result<Unit>? = null,
    val CustomizeInsertResult: Result<Unit>? = null
)

class WholesaleBuyerRequestController(
    private val executeService: IExecuteService,
    private val lastPriceRequestRepository: IWholesaleBuyerLastPriceRequestRepository,
    private val sampleRequestRepository: IWholesaleBuyerSampleRequestRepository,
    private val customizeRequestRepository: IWholesaleBuyerCustomizeRequestRepository
) : BaseController() {

    private val _state = MutableStateFlow(WholesaleBuyerRequestControllerState())
    val State: StateFlow<WholesaleBuyerRequestControllerState> = _state.asStateFlow()

    fun InsertLastPriceRequest(languageId: Int, model: WholesaleBuyerLastPriceRequestInsertModel, onSuccess: () -> Unit = {}) {
        if (languageId <= 0 || model.InsertedBy <= 0 || model.WholesaleProductId <= 0 || model.CompanyId <= 0) {
            SetError(BBLocalization.Current.Get(key = "eedef71d-7220-41fd-8082-d153a7ccf794", fallback = "Son fiyat talebi için gerekli bilgiler eksik."))
            return
        }

        viewModelScope.launch {
            Start("InsertLastPriceRequest")

            val response = executeService.PostAsync(operationType = "b2b.WholesaleBuyerRequest.InsertLastPriceRequest") {
                lastPriceRequestRepository.InsertAsync(languageId = languageId, model = model)
            }

            Complete {
                copy(
                    LastPriceInsertResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }

            if (response.Success) onSuccess()
        }
    }

    fun InsertSampleRequest(languageId: Int, model: WholesaleBuyerSampleRequestInsertModel, onSuccess: () -> Unit = {}) {
        if (languageId <= 0 || model.InsertedBy <= 0 || model.WholesaleProductId <= 0 || model.CompanyId <= 0) {
            SetError(BBLocalization.Current.Get(key = "034899e5-cfa2-46b8-86dd-db08e61e0ad8", fallback = "Numune talebi için gerekli bilgiler eksik."))
            return
        }

        viewModelScope.launch {
            Start("InsertSampleRequest")

            val response = executeService.PostAsync(operationType = "b2b.WholesaleBuyerRequest.InsertSampleRequest") {
                sampleRequestRepository.InsertAsync(languageId = languageId, model = model)
            }

            Complete {
                copy(
                    SampleInsertResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }

            if (response.Success) onSuccess()
        }
    }

    fun InsertCustomizeRequest(languageId: Int, model: WholesaleBuyerCustomizeRequestInsertModel, onSuccess: () -> Unit = {}) {
        if (languageId <= 0 || model.InsertedBy <= 0 || model.WholesaleProductId <= 0 || model.CompanyId <= 0) {
            SetError(BBLocalization.Current.Get(key = "2e862aa8-6e88-449e-a5d8-c9b3e7c91da9", fallback = "Özelleştirme talebi için gerekli bilgiler eksik."))
            return
        }

        viewModelScope.launch {
            Start("InsertCustomizeRequest")

            val response = executeService.PostAsync(operationType = "b2b.WholesaleBuyerRequest.InsertCustomizeRequest") {
                customizeRequestRepository.InsertAsync(languageId = languageId, model = model)
            }

            Complete {
                copy(
                    CustomizeInsertResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }

            if (response.Success) onSuccess()
        }
    }

    fun ClearInsertResults() {
        _state.update {
            it.copy(
                ErrorMessage = null,
                LastPriceInsertResult = null,
                SampleInsertResult = null,
                CustomizeInsertResult = null
            )
        }
    }

    private fun Start(action: String) {
        _state.update {
            it.copy(
                IsLoading = true,
                CurrentAction = action,
                ErrorMessage = null
            )
        }
    }

    private fun Complete(update: WholesaleBuyerRequestControllerState.() -> WholesaleBuyerRequestControllerState) {
        _state.update {
            it.update().copy(
                IsLoading = false,
                CurrentAction = null
            )
        }
    }

    private fun SetError(message: String) {
        _state.update {
            it.copy(
                IsLoading = false,
                CurrentAction = null,
                ErrorMessage = message
            )
        }
    }
}
