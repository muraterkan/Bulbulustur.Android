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
import com.bulbulustur.android.businesslayer.Core.Interface.IAssignedToSellerRepository
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.Log
import java.util.Locale

data class RfqControllerState(
    val IsLoading: Boolean = false,
    val IsCreateOptionsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val ErrorMessage: String? = null,
    val DeletingBuyerRequestKey: String? = null,

    val BuyerRequestListResult: Result<List<BuyerRequestDTO>>? = null,
    val BuyerRequestDetailResult: Result<BuyerRequestDTO?>? = null,
    val BuyerRequestUpdateDetailResult: Result<BuyerRequestUpdateModel?>? = null,
    val BuyerRequestInsertResult: Result<Unit>? = null,
    val BuyerRequestUpdateResult: Result<Unit>? = null,
    val BuyerRequestDeleteResult: Result<Unit>? = null,

    val SendedOfferListResult: Result<List<SendedOfferDTO>>? = null,
    val SendedOfferDetailResult: Result<SendedOfferDTO?>? = null,

    val ProductCategoryListResult: Result<List<ProductCategoryDTO>>? = null,
    val ProductCategorySearchResults: List<ProductCategoryDTO> = emptyList(),
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

    val BuyerRequestForUpdate: BuyerRequestUpdateModel?
        get() = BuyerRequestUpdateDetailResult?.Data

    val SendedOffers: List<SendedOfferDTO>
        get() = SendedOfferListResult?.Data.orEmpty()

    val SendedOffer: SendedOfferDTO?
        get() = SendedOfferDetailResult?.Data

    val ProductCategories: List<ProductCategoryDTO>
        get() = ProductCategorySearchResults

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
            ProductCategoryListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?.let { "Ürün kategorileri: $it" },

            UnitListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?.let { "Birimler: $it" },

            CurrencyListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?.let { "Para birimleri: $it" },

            ColorListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?.let { "Renkler: $it" },

            MaterialTypeListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?.let { "Malzeme tipleri: $it" },

            PaymentTermListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?.let { "Ödeme şartları: $it" },

            TradeTermListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?.let { "Ticaret şartları: $it" }
        ).firstOrNull()
}

class RfqController(
    private val executeService: IExecuteService,
    private val buyerRequestRepository: IBuyerRequestRepository,
    private val assignedToSellerRepository: IAssignedToSellerRepository,
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
    private var CachedProductCategories: List<ProductCategoryDTO> = emptyList()
    val State: StateFlow<RfqControllerState> = _state.asStateFlow()

    fun GetBuyerRequests(
        memberId: Int,
        count: Int = 100
    ) {
        if (memberId <= 0) {
            SetError("Üye bilgisi bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetBuyerRequests")

            val response = executeService.GetAsync(
                cacheKey = ""
            ) {
                buyerRequestRepository.GetBuyerRequestsByMemberAsync(
                    memberId = memberId,
                    count = count
                )
            }

            Complete {
                copy(
                    BuyerRequestListResult = response,
                    ErrorMessage = response
                        .takeIf { !it.Success }
                        ?.Message
                )
            }
        }
    }

    fun GetBuyerRequest(
        buyerRequestKey: String
    ) {
        if (buyerRequestKey.isBlank()) {
            SetError("RFQ anahtarı bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetBuyerRequest")

            val response = executeService.GetAsync(
                cacheKey = ""
            ) {
                buyerRequestRepository.GetBuyerRequestsByIdExtendedAsync(
                    buyerRequestKey = buyerRequestKey
                )
            }

            Complete {
                copy(
                    BuyerRequestDetailResult = response,
                    ErrorMessage = response
                        .takeIf { !it.Success }
                        ?.Message
                )
            }
        }
    }

    fun GetBuyerRequestForUpdate(buyerRequestKey: String) {
        if (buyerRequestKey.isBlank()) {
            SetError("RFQ anahtarı bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetBuyerRequestForUpdate")

            val response = executeService.GetAsync(
                cacheKey = ""
            ) {
                buyerRequestRepository.GetBuyerRequestsByIdAsync(
                    buyerRequestKey = buyerRequestKey
                )
            }

            Complete {
                copy(
                    BuyerRequestUpdateDetailResult = response,
                    ErrorMessage = response.takeIf { !it.Success }?.Message
                )
            }
        }
    }

    fun InsertBuyerRequest(
        model: BuyerRequestInsertModel,
        onSuccess: () -> Unit = {}
    ) {
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
                    ErrorMessage = response
                        .takeIf { !it.Success }
                        ?.Message
                )
            }

            if (response.Success) {
                onSuccess()
            }
        }
    }

    fun UpdateBuyerRequest(
        model: BuyerRequestUpdateModel,
        onSuccess: () -> Unit = {}
    ) {
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
                    ErrorMessage = response
                        .takeIf { !it.Success }
                        ?.Message
                )
            }

            if (response.Success) {
                onSuccess()
            }
        }
    }

    fun DeleteBuyerRequest(
        buyerRequestKey: String,
        onSuccess: () -> Unit = {}
    ) {
        if (buyerRequestKey.isBlank()) {
            SetError("Silinecek RFQ kaydı bulunamadı.")
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(IsLoading = true, CurrentAction = "DeleteBuyerRequest", DeletingBuyerRequestKey = buyerRequestKey, ErrorMessage = null) }

            val response = executeService.PostAsync(
                operationType = "b2b.Rfq.DeleteBuyerRequest"
            ) {
                buyerRequestRepository.DeleteAsync(
                    buyerRequestKey
                )
            }

            Complete {
                copy(
                    BuyerRequestDeleteResult = response,
                    DeletingBuyerRequestKey = null,
                    ErrorMessage = response
                        .takeIf { !it.Success }
                        ?.Message
                )
            }

            if (response.Success) {
                onSuccess()
            }
        }
    }

    fun GetSendedOffers(
        buyerRequestKey: String,
        count: Int = 100
    ) {
        if (buyerRequestKey.isBlank()) {
            SetError("RFQ anahtarı bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetSendedOffers")

            val response = executeService.GetAsync(
                cacheKey = ""
            ) {
                sendedOfferRepository.GetSendedOffersAsync(
                    buyerRequestKey = buyerRequestKey,
                    count = count
                )
            }

            Complete {
                copy(
                    SendedOfferListResult = response,
                    ErrorMessage = response
                        .takeIf { !it.Success }
                        ?.Message
                )
            }
        }
    }

    fun GetSendedOffer(
        sendedOfferId: Int
    ) {
        if (sendedOfferId <= 0) {
            SetError("Teklif bilgisi bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("GetSendedOffer")

            val response = executeService.GetAsync(
                cacheKey = ""
            ) {
                sendedOfferRepository
                    .GetSendedOfferByIdExtendedAsync(
                        sendedOfferId = sendedOfferId
                    )
            }

            Complete {
                copy(
                    SendedOfferDetailResult = response,
                    ErrorMessage = response
                        .takeIf { !it.Success }
                        ?.Message
                )
            }
        }
    }

    fun ResolveSellerMemberId(
        assignedToSellerId: Int,
        onSuccess: (Int) -> Unit
    ) {
        if (assignedToSellerId <= 0) {
            SetError("Satıcı atama bilgisi bulunamadı.")
            return
        }

        viewModelScope.launch {
            Start("ResolveSellerMemberId")

            val response = executeService.GetAsync(
                cacheKey = "b2b.Rfq.AssignedSeller."
            ) {
                assignedToSellerRepository.GetAssignedToSellersByIdExtendedAsync(
                    assignedToSellerId = assignedToSellerId
                )
            }

            val sellerMemberId = response.Data?.AssignedMemberId ?: 0

            Complete {
                copy(
                    ErrorMessage = when {
                        !response.Success -> response.Message
                        sellerMemberId <= 0 -> "Satıcı üye bilgisi bulunamadı."
                        else -> null
                    }
                )
            }

            if (response.Success && sellerMemberId > 0) {
                onSuccess(sellerMemberId)
            }
        }
    }

    fun LoadCreateOptions(
        languageId: Int
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsCreateOptionsLoading = true,
                    CurrentAction = "LoadCreateOptions",
                    ErrorMessage = null
                )
            }

            val productCategories =
                executeService.GetAsync(
                    cacheKey =
                        "b2b.Rfq.ProductCategories.$languageId"
                ) {
                    productCategoryRepository
                        .GetCachedProductCategoriesAsync(
                            languageId = languageId
                        )
                }

            val initialProductCategoryResults =
                withContext(Dispatchers.Default) {
                    CachedProductCategories =
                        productCategories.Data
                            .orEmpty()
                            .filter {
                                it.ProductCategoryId > 0 &&
                                        it.CategoryName.isNotBlank()

                            }
                    Log.d(
                        "RFQ_CATEGORY",
                        "Cached category count=${CachedProductCategories.size}"
                    )

                    CachedProductCategories
                        .asSequence()
                        .sortedBy { it.CategoryName }
                        .take(50)
                        .toList()
                }

            val units =
                executeService.GetAsync(
                    cacheKey =
                        "b2b.Rfq.Units.$languageId"
                ) {
                    systemDescUnitRepository
                        .GetSystemDescUnitsAsync(
                            languageId = languageId,
                            count = 100
                        )
                }

            val currencies =
                executeService.GetAsync(
                    cacheKey =
                        "b2b.Rfq.Currencies.$languageId"
                ) {
                    systemDescCurrencyRepository
                        .GetSystemDescCurrenciesAsync(
                            languageId = languageId,
                            count = 100
                        )
                }

            val colors =
                executeService.GetAsync(
                    cacheKey =
                        "b2b.Rfq.Colors.$languageId"
                ) {
                    systemDescColorRepository
                        .GetSystemDescColorsAsync(
                            languageId = languageId,
                            count = 100
                        )
                }

            val materialTypes =
                executeService.GetAsync(
                    cacheKey =
                        "b2b.Rfq.MaterialTypes.$languageId"
                ) {
                    systemDescMaterialTypeRepository
                        .GetSystemDescMaterialTypesAsync(
                            languageId = languageId,
                            count = 100
                        )
                }

            val paymentTerms =
                executeService.GetAsync(
                    cacheKey =
                        "b2b.Rfq.PaymentTerms.$languageId"
                ) {
                    systemDescPaymentTermRepository
                        .GetSystemDescPaymentTermsAsync(
                            languageId = languageId,
                            count = 100
                        )
                }

            val tradeTerms =
                executeService.GetAsync(
                    cacheKey =
                        "b2b.Rfq.TradeTerms.$languageId"
                ) {
                    systemDescTradeTermRepository
                        .GetSystemDescTradeTermsAsync(
                            languageId = languageId,
                            count = 100
                        )
                }

            val firstErrorMessage =
                listOfNotNull(
                    productCategories
                        .takeIf { !it.Success }
                        ?.Message
                        ?.let {
                            "Ürün kategorileri: $it"
                        },

                    units
                        .takeIf { !it.Success }
                        ?.Message
                        ?.let {
                            "Birimler: $it"
                        },

                    currencies
                        .takeIf { !it.Success }
                        ?.Message
                        ?.let {
                            "Para birimleri: $it"
                        },

                    colors
                        .takeIf { !it.Success }
                        ?.Message
                        ?.let {
                            "Renkler: $it"
                        },

                    materialTypes
                        .takeIf { !it.Success }
                        ?.Message
                        ?.let {
                            "Malzeme tipleri: $it"
                        },

                    paymentTerms
                        .takeIf { !it.Success }
                        ?.Message
                        ?.let {
                            "Ödeme şartları: $it"
                        },

                    tradeTerms
                        .takeIf { !it.Success }
                        ?.Message
                        ?.let {
                            "Ticaret şartları: $it"
                        }
                ).firstOrNull()

            _state.update {
                it.copy(
                    IsCreateOptionsLoading = false,
                    CurrentAction = null,
                    ProductCategoryListResult =
                        productCategories.takeIf { !it.Success },
                    ProductCategorySearchResults =
                        initialProductCategoryResults,
                    UnitListResult =
                        units,
                    CurrencyListResult =
                        currencies,
                    ColorListResult =
                        colors,
                    MaterialTypeListResult =
                        materialTypes,
                    PaymentTermListResult =
                        paymentTerms,
                    TradeTermListResult =
                        tradeTerms,
                    ErrorMessage =
                        firstErrorMessage
                )
            }
        }
    }

    fun SearchProductCategories(query: String) {
        viewModelScope.launch {
            val turkishLocale = Locale("tr", "TR")
            val normalizedQuery = query
                .trim()
                .lowercase(turkishLocale)

            val results = withContext(Dispatchers.Default) {
                CachedProductCategories
                    .asSequence()
                    .filter {
                        normalizedQuery.isBlank() ||
                                it.CategoryName
                                    .lowercase(turkishLocale)
                                    .contains(normalizedQuery)
                    }
                    .sortedBy { it.CategoryName }
                    .take(50)
                    .toList()
            }

            _state.update {
                it.copy(
                    ProductCategorySearchResults = results
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
            it.copy(
                SendedOfferDetailResult = null
            )
        }
    }

    fun ClearUpdateResult() {
        _state.update { it.copy(BuyerRequestUpdateResult = null, BuyerRequestUpdateDetailResult = null, ErrorMessage = null) }
    }

    fun ClearInsertResult() {
        _state.update {
            it.copy(
                BuyerRequestInsertResult = null,
                ErrorMessage = null
            )
        }
    }

    private fun Start(
        action: String
    ) {
        _state.update {
            it.copy(
                IsLoading = true,
                CurrentAction = action,
                ErrorMessage = null
            )
        }
    }

    private fun Complete(
        update:
        RfqControllerState.() ->
        RfqControllerState
    ) {
        _state.update {
            it.update().copy(
                IsLoading = false,
                CurrentAction = null
            )
        }
    }

    private fun SetError(
        message: String
    ) {
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