package com.bulbulustur.android.Application.Areas.b2c.Controllers

import android.util.Log
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.payment.CheckoutCardEntryModel
import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.CheckoutContractDTO
import com.bulbulustur.android.businesslayer.Core.DTO.CheckoutBasketSnapshotDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBasketRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ICheckoutSnapshotRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IContractRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.CheckoutContractRequestModel
import com.bulbulustur.android.businesslayer.Core.Model.BasketSummaryModel
import com.bulbulustur.android.businesslayer.Core.Model.RequestModels.GatewayPaymentCard
import com.bulbulustur.android.businesslayer.Core.Model.RequestModels.GatewayPaymentCreateRequest
import com.bulbulustur.android.businesslayer.Core.Model.RequestModels.GatewayPaymentLineItem
import com.bulbulustur.android.businesslayer.Core.Model.ResponseModels.GatewayPaymentCreateResponse
import com.bulbulustur.android.businesslayer.Core.Service.IPaymentService
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CheckoutSnapshotInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID
import com.google.gson.Gson

data class CheckoutControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,

    val AddressListResult: Result<List<MemberAddressDTO>>? = null,
    val AddressDetailResult: Result<MemberAddressUpdateModel?>? = null,
    val AddressInsertResult: Result<Unit>? = null,
    val AddressUpdateResult: Result<Unit>? = null,


    val SelectedDeliveryAddressId: Int = 0,

    val SelectedInvoiceAddressId: Int = 0,

    val ContractResult: Result<CheckoutContractDTO>? = null,
    val IsContractLoading: Boolean = false,
    val ContractErrorMessage: String? = null,

    val PaymentCard: CheckoutCardEntryModel? = null,
    val InstallmentCount: Int = 1,

    val IsPaymentLoading: Boolean = false,
    val CheckoutKey: String = "",
    val PaymentResult: Result<GatewayPaymentCreateResponse>? = null,
    val PaymentIntentId: Int = 0,
    val PaymentAttemptId: Int = 0,
    val PaymentStatus: Int = 0,
    val PaymentRedirectUrl: String? = null,
    val PaymentHtmlContent: String? = null,
    val PaymentErrorCode: String? = null,
    val PaymentErrorMessage: String? = null,

    val ErrorMessage: String? = null
) {
    val Addresses: List<MemberAddressDTO>
        get() = AddressListResult?.Data.orEmpty()

    val AddressDetail: MemberAddressUpdateModel?
        get() = AddressDetailResult?.Data


    val SelectedDeliveryAddress: MemberAddressDTO?
        get() =
            Addresses.firstOrNull {
                it.MemberAddressId == SelectedDeliveryAddressId
            }

    val SelectedInvoiceAddress: MemberAddressDTO?
        get() =
            Addresses.firstOrNull {
                it.MemberAddressId == SelectedInvoiceAddressId
            }

    val PreInformationHtml: String get() = ContractResult?.Data?.PreInformationHtml.orEmpty()

    val DistanceSellingHtml: String get() = ContractResult?.Data?.DistanceSellingHtml.orEmpty()

}

class CheckoutController(
    private val executeService: IExecuteService,
    private val memberAddressRepository: IMemberAddressRepository,
    private val contractRepository: IContractRepository,
    private val basketRepository: IBasketRepository,
    private val checkoutSnapshotRepository: ICheckoutSnapshotRepository,
    private val paymentService: IPaymentService
) : BaseController() {

    private val _state =
        MutableStateFlow(
            CheckoutControllerState()
        )

    val State: StateFlow<CheckoutControllerState> =
        _state.asStateFlow()

    fun LoadAddresses(
        memberId: Int,
        count: Int = 100
    ) {
        if (memberId <= 0) {
            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressListResult = null,
                    SelectedDeliveryAddressId = 0,
                    ErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "LoadAddresses",
                    ErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = ""
                ) {
                    memberAddressRepository.GetAccountAddressesAsync(
                        memberId = memberId,
                        count = count
                    )
                }

            _state.update { currentState ->
                val addresses =
                    response.Data.orEmpty()

                val selectedAddressId =
                    currentState.SelectedDeliveryAddressId
                        .takeIf { selectedId ->
                            selectedId > 0 &&
                                addresses.any { address ->
                                    address.MemberAddressId ==
                                        selectedId
                                }
                        }
                        ?: addresses
                            .firstOrNull {
                                it.IsDefault
                            }
                            ?.MemberAddressId
                        ?: addresses
                            .firstOrNull()
                            ?.MemberAddressId
                        ?: 0

                currentState.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressListResult = response,
                    SelectedDeliveryAddressId =
                        selectedAddressId,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun LoadAddress(
        memberId: Int,
        addressKey: String
    ) {
        if (
            memberId <= 0 ||
            addressKey.isBlank()
        ) {
            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressDetailResult = null,
                    ErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "LoadAddress",
                    AddressDetailResult = null,
                    ErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = ""
                ) {
                    memberAddressRepository.GetAccountAddressByIdAsync(
                        memberId = memberId,
                        addressKey = addressKey
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressDetailResult = response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun InsertAddress(
        memberId: Int,
        model: MemberAddressInsertModel,
        onSuccess: (() -> Unit)? = null
    ) {
        if (memberId <= 0) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "InsertAddress",
                    AddressInsertResult = null,
                    ErrorMessage = null
                )
            }

            val response =
                executeService.PostAsync(
                    operationType = "Checkout.Address.Insert"
                ) {
                    memberAddressRepository.InsertAccountAddressAsync(
                        memberId = memberId,
                        model = model
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressInsertResult = response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }

            if (response.Success) {
                LoadAddresses(memberId)
                onSuccess?.invoke()
            }
        }
    }

    fun UpdateAddress(
        memberId: Int,
        model: MemberAddressUpdateModel,
        onSuccess: (() -> Unit)? = null
    ) {
        if (memberId <= 0) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "UpdateAddress",
                    AddressUpdateResult = null,
                    ErrorMessage = null
                )
            }

            val response =
                executeService.PostAsync(
                    operationType = "Checkout.Address.Update"
                ) {
                    memberAddressRepository.UpdateAccountAddressAsync(
                        memberId = memberId,
                        model = model
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressUpdateResult = response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }

            if (response.Success) {
                LoadAddresses(memberId)
                onSuccess?.invoke()
            }
        }
    }

    fun ClearAddressDetail() {
        _state.update {
            it.copy(
                AddressDetailResult = null,
                ErrorMessage = null
            )
        }
    }

    fun SelectDeliveryAddress(
        memberAddressId: Int
    ) {
        if (memberAddressId <= 0) return

        _state.update { currentState ->
            if (
                currentState.Addresses.none { address ->
                    address.MemberAddressId ==
                            memberAddressId
                }
            ) {
                currentState
            } else {
                currentState.copy(
                    SelectedDeliveryAddressId =
                        memberAddressId,

                    SelectedInvoiceAddressId =
                        memberAddressId
                )
            }
        }
    }

    fun SelectInvoiceAddress(
        memberAddressId: Int
    ) {
        if (memberAddressId <= 0) return

        _state.update { currentState ->
            if (
                currentState.Addresses.none { address ->
                    address.MemberAddressId ==
                            memberAddressId
                }
            ) {
                currentState
            } else {
                currentState.copy(
                    SelectedInvoiceAddressId =
                        memberAddressId
                )
            }
        }
    }
    fun SelectPaymentCard(card: CheckoutCardEntryModel) {
        if (!card.isComplete) return

        _state.update {
            it.copy(
                PaymentCard = card,
                ErrorMessage = null
            )
        }
    }

    fun SelectInstallment(installmentCount: Int) {
        _state.update {
            it.copy(
                InstallmentCount = installmentCount.coerceAtLeast(1)
            )
        }
    }

    fun ClearPaymentCard() {
        _state.update {
            it.copy(
                PaymentCard = null,
                InstallmentCount = 1
            )
        }
    }

    fun LoadContracts(memberId: Int, languageId: Int, deliveryAddressId: Int, invoiceAddressId: Int, installmentCount: Int) {
        if (memberId <= 0 || deliveryAddressId <= 0 || invoiceAddressId <= 0) {
            _state.update {
                it.copy(
                    ContractResult = null,
                    IsContractLoading = false,
                    ContractErrorMessage = null
                )
            }
            return
        }

        val safeInstallmentCount = installmentCount.coerceAtLeast(1)

        viewModelScope.launch {
            _state.update { it.copy(IsContractLoading = true, ContractErrorMessage = null) }

            val request = CheckoutContractRequestModel(
                MemberId = memberId,
                LanguageId = languageId,
                DeliveryAddressId = deliveryAddressId,
                InvoiceAddressId = invoiceAddressId,
                InstallmentCount = safeInstallmentCount
            )

            Log.d(
                "ContractDebug",
                "REQUEST MemberId=${request.MemberId} LanguageId=${request.LanguageId} DeliveryAddressId=${request.DeliveryAddressId} InvoiceAddressId=${request.InvoiceAddressId} InstallmentCount=${request.InstallmentCount}"
            )

            val response = executeService.PostAsync(operationType = "Checkout.Contract.Load") {
                contractRepository.GetCheckoutContractsAsync(request)
            }

            Log.d(
                "ContractDebug",
                "RESPONSE Success=${response.Success} PreLength=${response.Data?.PreInformationHtml?.length ?: 0} DistanceLength=${response.Data?.DistanceSellingHtml?.length ?: 0} Message=${response.Message}"
            )

            _state.update {
                it.copy(
                    ContractResult = response,
                    IsContractLoading = false,
                    ContractErrorMessage = response.Message.takeIf { !response.Success })
            }
        }
    }

    fun CompleteCheckout(
        memberId: Int,
        languageId: Int,
        onThreeDsRequired: () -> Unit = {},
        onSuccess: (Int, String) -> Unit = { _, _ -> }
    ) {
        val currentState = _state.value
        val card = currentState.PaymentCard

        if (memberId <= 0) {
            _state.update {
                it.copy(
                    PaymentErrorMessage = "Geçerli üye bilgisi zorunludur."
                )
            }
            return
        }

        if (currentState.SelectedDeliveryAddressId <= 0) {
            _state.update {
                it.copy(
                    PaymentErrorMessage = "Teslimat adresi zorunludur."
                )
            }
            return
        }

        if (card == null || !card.isComplete) {
            _state.update {
                it.copy(
                    PaymentErrorMessage = "Kart bilgileri eksik."
                )
            }
            return
        }

        val deliveryAddress = currentState.SelectedDeliveryAddress
        val invoiceAddress =
            currentState.SelectedInvoiceAddress ?: deliveryAddress

        if (deliveryAddress == null || invoiceAddress == null) {
            _state.update {
                it.copy(
                    PaymentErrorMessage = "Adres bilgileri bulunamadı."
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsPaymentLoading = true,
                    PaymentResult = null,
                    PaymentErrorCode = null,
                    PaymentErrorMessage = null,
                    PaymentRedirectUrl = null,
                    PaymentHtmlContent = null
                )
            }

            val basketResult =
                basketRepository.GetBasketsAsync(
                    memberId = memberId,
                    count = 100
                )

            val summaryResult =
                basketRepository.GetBasketSummaryAsync(
                    memberId = memberId
                )

            val basketItems = basketResult.Data.orEmpty()
            val basketSummary = summaryResult.Data

            if (
                !basketResult.Success ||
                basketItems.isEmpty() ||
                !summaryResult.Success ||
                basketSummary == null
            ) {
                _state.update {
                    it.copy(
                        IsPaymentLoading = false,
                        PaymentErrorMessage =
                            basketResult.Message.ifBlank {
                                summaryResult.Message.ifBlank {
                                    "Sepetiniz boş görünüyor."
                                }
                            }
                    )
                }
                return@launch
            }

            if (basketItems.any { it.StoreId <= 0 }) {
                _state.update {
                    it.copy(
                        IsPaymentLoading = false,
                        PaymentErrorMessage =
                            "Sepette mağaza bilgisi bulunmayan ürün var."
                    )
                }
                return@launch
            }

            val paymentAmount =
                BigDecimal.valueOf(
                    basketSummary.GrossTotal
                ).setScale(
                    2,
                    RoundingMode.HALF_UP
                )

            if (paymentAmount <= BigDecimal.ZERO) {
                _state.update {
                    it.copy(
                        IsPaymentLoading = false,
                        PaymentErrorMessage =
                            "Ödeme tutarı geçersiz."
                    )
                }
                return@launch
            }

            val installmentCount =
                currentState.InstallmentCount
                    .coerceAtLeast(1)

            val checkoutKey =
                "BB-${UUID.randomUUID().toString().replace("-", "")}"

            fun buildAddressText(
                address: MemberAddressDTO
            ): String {
                return buildString {
                    append(address.Name)
                    append(" ")
                    append(address.Surname)
                    append(" - ")
                    append(address.Address)
                    append(" ")
                    append(address.PostCode)
                }.trim()
            }

            val invoiceAddressText =
                buildAddressText(invoiceAddress)

            val deliveryAddressText =
                buildAddressText(deliveryAddress)

            val immutableBasketSnapshot =
                CheckoutBasketSnapshotDTO(
                    Items = basketItems,
                    Totals = BasketSummaryModel(
                        SubTotal = basketSummary.SubTotal,
                        ShippingTotal = basketSummary.ShippingCost,
                        Vat = basketSummary.VatTotal,
                        GrandTotal = basketSummary.GrossTotal,
                        NetTotal = basketSummary.NetTotal,
                        StoreShippingBreakdown = basketSummary.StoreShippingBreakdown
                    )
                )

            val basketSnapshotJson =
                Gson().toJson(immutableBasketSnapshot)

            val snapshotInsert =
                CheckoutSnapshotInsertModel(
                    CheckoutKey = checkoutKey,
                    InsertedBy = memberId,
                    LanguageId = languageId,
                    FlowType = 1,
                    InvoiceAddressId = invoiceAddress.MemberAddressId,
                    DeliveryAddressId = deliveryAddress.MemberAddressId,
                    InvoiceAddressText = invoiceAddressText,
                    DeliveryAddressText = deliveryAddressText,
                    BaseAmount = paymentAmount,
                    PaymentAmount = paymentAmount,
                    Currency = "TRY",
                    InstallmentCount = installmentCount,
                    BasketSnapshot = basketSnapshotJson
                )

            val snapshotResult =
                checkoutSnapshotRepository.Insert(snapshotInsert)

            if (!snapshotResult.Success) {
                _state.update {
                    it.copy(
                        IsPaymentLoading = false,
                        CheckoutKey = checkoutKey,
                        PaymentErrorMessage =
                            snapshotResult.Message.ifBlank {
                                "Checkout kaydı oluşturulamadı."
                            }
                    )
                }
                return@launch
            }

            val paymentItems =
                basketItems.map { item ->
                    GatewayPaymentLineItem(
                        ReferenceId =
                            "product:${item.ProductId}:${item.VariantId}:${item.BasketId}",
                        Name =
                            item.ProductName.ifBlank {
                                "Ürün ${item.ProductId}"
                            },
                        Quantity =
                            item.Quantity,
                        UnitPrice =
                            BigDecimal.valueOf(
                                item.UnitPrice
                            ).setScale(
                                2,
                                RoundingMode.HALF_UP
                            ),
                        TotalAmount =
                            BigDecimal.valueOf(
                                item.TotalPrice
                            ).setScale(
                                2,
                                RoundingMode.HALF_UP
                            ),
                        StoreId =
                            item.StoreId
                    )
                }.toMutableList()

            basketSummary
                .StoreShippingBreakdown
                .forEach { shipping ->
                    val shippingAmount =
                        BigDecimal.valueOf(
                            shipping.StoreShippingAfterDiscount
                        ).setScale(
                            2,
                            RoundingMode.HALF_UP
                        )

                    if (shippingAmount > BigDecimal.ZERO) {
                        paymentItems.add(
                            GatewayPaymentLineItem(
                                ReferenceId =
                                    "shipping:${shipping.StoreId}",
                                Name =
                                    "Kargo",
                                Quantity =
                                    1,
                                UnitPrice =
                                    shippingAmount,
                                TotalAmount =
                                    shippingAmount,
                                StoreId =
                                    shipping.StoreId
                            )
                        )
                    }
                }

            val paymentItemsTotal =
                paymentItems.fold(
                    BigDecimal.ZERO
                ) { total, item ->
                    total + item.TotalAmount
                }.setScale(
                    2,
                    RoundingMode.HALF_UP
                )

            if (paymentItemsTotal != paymentAmount) {
                _state.update {
                    it.copy(
                        IsPaymentLoading = false,
                        CheckoutKey = checkoutKey,
                        PaymentErrorMessage =
                            "Ödeme toplamı uyuşmuyor. Checkout: " +
                            "$paymentAmount TRY, satırlar: " +
                            "$paymentItemsTotal TRY."
                    )
                }
                return@launch
            }

            val expiryDigits =
                card.expiry.filter(Char::isDigit)

            if (expiryDigits.length != 4) {
                _state.update {
                    it.copy(
                        IsPaymentLoading = false,
                        PaymentErrorMessage =
                            "Kart son kullanma tarihi geçersiz."
                    )
                }
                return@launch
            }

            val languageCode =
                when (languageId) {
                    1 -> "tr"
                    2 -> "en"
                    3 -> "de"
                    4 -> "fr"
                    5 -> "es"
                    6 -> "it"
                    7 -> "pt"
                    8 -> "nl"
                    9 -> "pl"
                    10 -> "ru"
                    11 -> "ar"
                    else -> "tr"
                }

            val paymentRequest =
                GatewayPaymentCreateRequest(
                    IdempotencyKey = checkoutKey,
                    OrderId = checkoutKey,
                    OrderKey = checkoutKey,

                    MemberId = memberId,
                    LanguageId = languageId,

                    Amount = paymentAmount,
                    Currency = "TRY",

                    FlowType = 1,
                    PaymentMethodType = 1,

                    Card =
                        GatewayPaymentCard(
                            CardHolderName =
                                card.cardHolderName,
                            CardNumber =
                                card.normalizedCardNumber,
                            ExpireMonth =
                                expiryDigits.substring(0, 2),
                            ExpireYear =
                                expiryDigits.substring(2, 4),
                            Cvc =
                                card.cvc,
                            StoreCard =
                                false,
                            CardAlias =
                                null
                        ),

                    StoredPaymentMethodId = null,
                    StoredPaymentMethodCvc = null,

                    InstallmentCount =
                        installmentCount,

                    RequireThreeDs =
                        true,

                    InvoiceAddressId =
                        invoiceAddress.MemberAddressId,
                    DeliveryAddressId =
                        deliveryAddress.MemberAddressId,

                    InvoiceAddressText =
                        invoiceAddressText,
                    DeliveryAddressText =
                        deliveryAddressText,

                    Items =
                        paymentItems,

                    ReturnUrl =
                        "https://www.bulbulustur.com/b2c/order/success?checkoutKey=$checkoutKey",

                    FailUrl =
                        "https://www.bulbulustur.com/b2c/order/fail?checkoutKey=$checkoutKey",

                    ClientIp =
                        null,

                    UserAgent =
                        "Bulbulustur.Android",

                    SessionId =
                        null,

                    DeviceFingerprintHash =
                        null,

                    CustomerName =
                        "${deliveryAddress.Name} ${deliveryAddress.Surname}",

                    CustomerEmail =
                        null,

                    CustomerPhone =
                        deliveryAddress.Phone,

                    BillingCity =
                        null,

                    DeliveryCity =
                        null,

                    LanguageCode =
                        languageCode
                )

            val paymentResult =
                paymentService.CreateGatewayPaymentAsync(
                    paymentRequest
                )

            val payment =
                paymentResult.Data

            if (!paymentResult.Success || payment == null) {
                _state.update {
                    it.copy(
                        IsPaymentLoading = false,
                        CheckoutKey = checkoutKey,
                        PaymentResult = paymentResult,
                        PaymentErrorCode =
                            payment?.ErrorCode,
                        PaymentErrorMessage =
                            payment?.Message
                                ?: paymentResult.Message.ifBlank {
                                    "Ödeme başlatılamadı."
                                }
                    )
                }
                return@launch
            }

            _state.update {
                it.copy(
                    IsPaymentLoading = false,
                    CheckoutKey = checkoutKey,
                    PaymentResult = paymentResult,
                    PaymentIntentId =
                        payment.PaymentIntentId,
                    PaymentAttemptId =
                        payment.PaymentAttemptId,
                    PaymentStatus =
                        payment.Status,
                    PaymentRedirectUrl =
                        payment.RedirectUrl,
                    PaymentHtmlContent =
                        payment.HtmlContent,
                    PaymentErrorCode =
                        payment.ErrorCode,
                    PaymentErrorMessage =
                        payment.Message
                )
            }

            if (
                !payment.HtmlContent.isNullOrBlank() ||
                !payment.RedirectUrl.isNullOrBlank()
            ) {
                onThreeDsRequired()
                return@launch
            }

            if (payment.Status == 100) {
                ResolveCompletedOrder(
                    checkoutKey = checkoutKey,
                    onSuccess = onSuccess
                )
            }
        }
    }


    fun CompletePaymentReturn(
        checkoutKey: String,
        onSuccess: (Int, String) -> Unit
    ) {
        if (checkoutKey.isBlank()) return

        viewModelScope.launch {
            ResolveCompletedOrder(
                checkoutKey = checkoutKey,
                onSuccess = onSuccess
            )
        }
    }

    private suspend fun ResolveCompletedOrder(
        checkoutKey: String,
        onSuccess: (Int, String) -> Unit
    ) {
        val paymentStatusResult =
            paymentService.GetGatewayPaymentStatusAsync(
                checkoutKey
            )

        val paymentStatus =
            paymentStatusResult.Data

        if (
            !paymentStatusResult.Success ||
            paymentStatus == null
        ) {
            _state.update {
                it.copy(
                    IsPaymentLoading = false,
                    PaymentErrorMessage =
                        paymentStatusResult.Message.ifBlank {
                            "Ödeme durumu alınamadı."
                        }
                )
            }

            return
        }

        if (paymentStatus.Status != 100) {
            _state.update {
                it.copy(
                    IsPaymentLoading = false,
                    PaymentStatus = paymentStatus.Status,
                    PaymentErrorMessage =
                        "Ödeme henüz tamamlanmadı. Durum: ${paymentStatus.Status}"
                )
            }

            return
        }

        val snapshotResult =
            checkoutSnapshotRepository.GetByCheckoutKey(
                checkoutKey
            )

        val snapshot =
            snapshotResult.Data

        if (
            !snapshotResult.Success ||
            snapshot == null
        ) {
            _state.update {
                it.copy(
                    IsPaymentLoading = false,
                    PaymentStatus = paymentStatus.Status,
                    PaymentErrorMessage =
                        snapshotResult.Message.ifBlank {
                            "Checkout kaydı alınamadı."
                        }
                )
            }

            return
        }

        val orderId =
            snapshot.OrderId ?: 0

        val orderKey =
            snapshot.OrderKey
                ?.takeIf { it.isNotBlank() }
                ?: checkoutKey

        if (orderId <= 0) {
            _state.update {
                it.copy(
                    IsPaymentLoading = false,
                    PaymentStatus = paymentStatus.Status,
                    PaymentErrorMessage =
                        "Ödeme başarılı ancak sipariş henüz oluşturulamadı."
                )
            }

            return
        }

        _state.update {
            it.copy(
                IsPaymentLoading = false,
                PaymentStatus = paymentStatus.Status,
                PaymentErrorCode = null,
                PaymentErrorMessage = null
            )
        }

        onSuccess(
            orderId,
            orderKey
        )
    }

}
