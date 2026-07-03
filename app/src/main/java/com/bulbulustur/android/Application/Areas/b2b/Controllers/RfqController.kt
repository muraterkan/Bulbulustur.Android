package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SendedOfferDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescColorDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaterialTypeDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescTradeTermDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescUnitDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBuyerRequestRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISendedOfferRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescColorRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCurrencyRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMaterialTypeRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPaymentTermRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescTradeTermRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescUnitRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BuyerRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RfqControllerState(
    val IsLoading: Boolean = false,
    val IsCreateOptionsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val ErrorMessage: String? = null,

    val BuyerRequestListResult: Result<List<BuyerRequestDTO>>? = null,
    val BuyerRequestDetailResult: Result<BuyerRequestDTO?>? = null,
    val BuyerRequestUpdateDetailResult: Result<BuyerRequestUpdateModel?>? = null,
    val BuyerRequestInsertResult: Result<Unit>? = null,
    val BuyerRequestUpdateResult: Result<Unit>? = null,
    val BuyerRequestDeleteResult: Result<Unit>? = null,

    val SendedOfferListResult: Result<List<SendedOfferDTO>>? = null,
    val SendedOfferDetailResult: Result<SendedOfferDTO?>? = null,

    val ProductCategoryListResult: Result<List<ProductCategoryDTO>>? = null,
    val UnitListResult: Result<List<SystemDescUnitDTO>>? = null,
    val CurrencyListResult: Result<List<SystemDescCurrencyDTO>>? = null,
    val ColorListResult: Result<List<SystemDescColorDTO>>? = null,
    val MaterialTypeListResult: Result<List<SystemDescMaterialTypeDTO>>? = null,
    val PaymentTermListResult: Result<List<SystemDescPaymentTermDTO>>? = null,
    val TradeTermListResult: Result<List<SystemDescTradeTermDTO>>? = null
) {
    val BuyerRequests: List<BuyerRequestDTO>
        get() = BuyerRequestListResult?.Data.orEmpty()

    val BuyerRequest: BuyerRequestDTO?
        get() = BuyerRequestDetailResult?.Data

    val SendedOffers: List<SendedOfferDTO>
        get() = SendedOfferListResult?.Data.orEmpty()

    val SendedOffer: SendedOfferDTO?
        get() = SendedOfferDetailResult?.Data

    val ProductCategories: List<ProductCategoryDTO>
        get() = ProductCategoryListResult?.Data.orEmpty()

    val Units: List<SystemDescUnitDTO>
        get() = UnitListResult?.Data.orEmpty()

    val Currencies: List<SystemDescCurrencyDTO>
        get() = CurrencyListResult?.Data.orEmpty()

    val Colors: List<SystemDescColorDTO>
        get() = ColorListResult?.Data.orEmpty()

    val MaterialTypes: List<SystemDescMaterialTypeDTO>
        get() = MaterialTypeListResult?.Data.orEmpty()

    val PaymentTerms: List<SystemDescPaymentTermDTO>
        get() = PaymentTermListResult?.Data.orEmpty()

    val TradeTerms: List<SystemDescTradeTermDTO>
        get() = TradeTermListResult?.Data.orEmpty()

    val CreateOptionsErrorMessage: String?
        get() = listOfNotNull(
            ProductCategoryListResult?.takeIf { !it.Success }?.Message,
            UnitListResult?.takeIf { !it.Success }?.Message,
            CurrencyListResult?.takeIf { !it.Success }?.Message,
            ColorListResult?.takeIf { !it.Success }?.Message,
            MaterialTypeListResult?.takeIf { !it.Success }?.Message,
            PaymentTermListResult?.takeIf { !it.Success }?.Message,
            TradeTermListResult?.takeIf { !it.Success }?.Message
        ).firstOrNull()
}

class RfqController(
    private val executeService: IExecuteService,
    private val buyerRequestRepository: IBuyerRequestRepository,
    private val sendedOfferRepository: ISendedOfferRepository,
    private val productCategoryRepository: IProductCategoryRepository,
    private val systemDescUnitRepository: ISystemDescUnitRepository,
    private val systemDescCurrencyRepository: ISystemDescCurrencyRepository,
    private val systemDescColorRepository: ISystemDescColorRepository,
    private val systemDescMaterialTypeRepository: ISystemDescMaterialTypeRepository,
    private val systemDescPaymentTermRepository: ISystemDescPaymentTermRepository,
    private val systemDescTradeTermRepository: ISystemDescTradeTermRepository
) : BaseController() {

    private val _state = MutableStateFlow(RfqControllerState())
    val State: StateFlow<RfqControllerState> = _state.asStateFlow()

    fun GetBuyerRequests(memberId: Int, count: Int = 100) {
        if (memberId <= 0) {
            SetError("Üye bilgisi bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetBuyerRequests")

            val response = executeService.GetAsync(
                cacheKey = "b2b.Rfq.GetBuyerRequests.$memberId.$count"
            ) {
                buyerRequestRepository.GetBuyerRequestsByMemberAsync(
                    memberId = memberId,
                    count = count
                )
            }

            Complete {
                copy(
                    BuyerRequestListResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }
        }
    }

    fun GetBuyerRequest(buyerRequestKey: String) {
        if (buyerRequestKey.isBlank()) {
            SetError("RFQ anahtarı bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetBuyerRequest")

            val response = executeService.GetAsync(
                cacheKey = "b2b.Rfq.GetBuyerRequest.$buyerRequestKey"
            ) {
                buyerRequestRepository.GetBuyerRequestsByIdExtendedAsync(
                    buyerRequestKey = buyerRequestKey
                )
            }

            Complete {
                copy(
                    BuyerRequestDetailResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }
        }
    }

    fun InsertBuyerRequest(model: BuyerRequestInsertModel, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            Start("InsertBuyerRequest")

            val response = executeService.PostAsync(
                operationType = "b2b.Rfq.InsertBuyerRequest"
            ) {
                buyerRequestRepository.InsertAsync(model)
            }

            Complete {
                copy(
                    BuyerRequestInsertResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }

            if (response.Success) onSuccess()
        }
    }

    fun UpdateBuyerRequest(model: BuyerRequestUpdateModel, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            Start("UpdateBuyerRequest")

            val response = executeService.PostAsync(
                operationType = "b2b.Rfq.UpdateBuyerRequest"
            ) {
                buyerRequestRepository.UpdateAsync(model)
            }

            Complete {
                copy(
                    BuyerRequestUpdateResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }

            if (response.Success) onSuccess()
        }
    }

    fun DeleteBuyerRequest(buyerRequestKey: String, onSuccess: () -> Unit = {}) {
        if (buyerRequestKey.isBlank()) {
            SetError("Silinecek RFQ kaydı bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("DeleteBuyerRequest")

            val response = executeService.PostAsync(
                operationType = "b2b.Rfq.DeleteBuyerRequest"
            ) {
                buyerRequestRepository.DeleteAsync(buyerRequestKey)
            }

            Complete {
                copy(
                    BuyerRequestDeleteResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }

            if (response.Success) onSuccess()
        }
    }

    fun GetSendedOffers(buyerRequestKey: String, count: Int = 100) {
        if (buyerRequestKey.isBlank()) {
            SetError("RFQ anahtarı bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetSendedOffers")

            val response = executeService.GetAsync(
                cacheKey = "b2b.Rfq.GetSendedOffers.$buyerRequestKey.$count"
            ) {
                sendedOfferRepository.GetSendedOffersAsync(
                    buyerRequestKey = buyerRequestKey,
                    count = count
                )
            }

            Complete {
                copy(
                    SendedOfferListResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }
        }
    }

    fun GetSendedOffer(sendedOfferId: Int) {
        if (sendedOfferId <= 0) {
            SetError("Teklif bilgisi bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetSendedOffer")

            val response = executeService.GetAsync(
                cacheKey = "b2b.Rfq.GetSendedOffer.$sendedOfferId"
            ) {
                sendedOfferRepository.GetSendedOfferByIdExtendedAsync(
                    sendedOfferId = sendedOfferId
                )
            }

            Complete {
                copy(
                    SendedOfferDetailResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }
        }
    }

    fun LoadCreateOptions(languageId: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsCreateOptionsLoading = true,
                    CurrentAction = "LoadCreateOptions",
                    ErrorMessage = null
                )
            }

            val productCategories = executeService.GetAsync(
                cacheKey = "b2b.Rfq.ProductCategories"
            ) {
                productCategoryRepository.GetProductCategoryListAsync()
            }

            val units = executeService.GetAsync(
                cacheKey = "b2b.Rfq.Units"
            ) {
                systemDescUnitRepository.GetSystemDescUnitListAsync()
            }

            val currencies = executeService.GetAsync(
                cacheKey = "b2b.Rfq.Currencies.$languageId"
            ) {
                systemDescCurrencyRepository.GetSystemDescCurrenciesAsync(
                    languageId = languageId,
                    count = 100
                )
            }

            val colors = executeService.GetAsync(
                cacheKey = "b2b.Rfq.Colors"
            ) {
                systemDescColorRepository.GetSystemDescColorListAsync()
            }

            val materialTypes = executeService.GetAsync(
                cacheKey = "b2b.Rfq.MaterialTypes"
            ) {
                systemDescMaterialTypeRepository.GetSystemDescMaterialTypeListAsync()
            }

            val paymentTerms = executeService.GetAsync(
                cacheKey = "b2b.Rfq.PaymentTerms"
            ) {
                systemDescPaymentTermRepository.GetSystemDescPaymentTermListAsync()
            }

            val tradeTerms = executeService.GetAsync(
                cacheKey = "b2b.Rfq.TradeTerms"
            ) {
                systemDescTradeTermRepository.GetSystemDescTradeTermListAsync()
            }

            _state.update {
                it.copy(
                    IsCreateOptionsLoading = false,
                    CurrentAction = null,
                    ProductCategoryListResult = productCategories,
                    UnitListResult = units,
                    CurrencyListResult = currencies,
                    ColorListResult = colors,
                    MaterialTypeListResult = materialTypes,
                    PaymentTermListResult = paymentTerms,
                    TradeTermListResult = tradeTerms,
                    ErrorMessage = listOfNotNull(
                        productCategories.takeIf { result -> !result.Success }?.Message,
                        units.takeIf { result -> !result.Success }?.Message,
                        currencies.takeIf { result -> !result.Success }?.Message,
                        colors.takeIf { result -> !result.Success }?.Message,
                        materialTypes.takeIf { result -> !result.Success }?.Message,
                        paymentTerms.takeIf { result -> !result.Success }?.Message,
                        tradeTerms.takeIf { result -> !result.Success }?.Message
                    ).firstOrNull()
                )
            }
        }
    }

    fun ClearBuyerRequestDetail() {
        _state.update {
            it.copy(
                BuyerRequestDetailResult = null,
                BuyerRequestUpdateDetailResult = null
            )
        }
    }

    fun ClearSendedOfferDetail() {
        _state.update {
            it.copy(SendedOfferDetailResult = null)
        }
    }

    fun ClearInsertResult() {
        _state.update {
            it.copy(
                BuyerRequestInsertResult = null,
                ErrorMessage = null
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

    private fun Complete(update: RfqControllerState.() -> RfqControllerState) {
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
                IsCreateOptionsLoading = false,
                CurrentAction = null,
                ErrorMessage = message
            )
        }
    }
}