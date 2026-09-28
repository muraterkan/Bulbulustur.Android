package com.bulbulustur.android.Application.Navigation.Graph

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import com.bulbulustur.android.Application.Areas.b2c.Controllers.BasketController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.CheckoutController
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderCancelRequestScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderContractScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderDetailScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderListScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderReturnRequestScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderReviewCreateScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.OrderShipmentTrackingScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutPriceSummary
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutScreenData
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSelectionDisplay
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryScreenData
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryAddress
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryCargo
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryPayment
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryProductItem
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.CheckoutSummaryTotal
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.OrderSuccessScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.payment.CheckoutPaymentCardUiModel
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.payment.CheckoutInstallmentUiModel
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.address.CheckoutAddressCreateScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.address.CheckoutAddressEditScreen
import com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout.address.CheckoutAddressListScreen
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.OrderRoutes
import com.bulbulustur.android.Application.Navigation.Routes.StoreRoutes
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeEvent
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeSelection

fun NavGraphBuilder.orderGraph(
    navigator: BulbulusturNavigator,
    memberId: Int,
    languageId: Int,
    checkoutController: CheckoutController,
    basketController: BasketController,
    addressCascadeController: AddressCascadeController
){
    composable(OrderRoutes.List) {
        OrderListScreen(
            memberId = memberId,
            onBackClick = { navigator.back() },
            onOrderDetailClick = { orderId, orderKey ->
                navigator.navController.navigate(
                    OrderRoutes.detail(orderId, orderKey)
                )
            }
        )
    }

    composable(OrderRoutes.Checkout) {
        val checkoutState =
            checkoutController.State
                .collectAsState()
                .value

        val basketState =
            basketController.State
                .collectAsState() .value

        var selectedInstallmentCount by remember { mutableIntStateOf(1) }

        LaunchedEffect(memberId) {
            checkoutController.LoadAddresses(
                memberId = memberId
            )

            basketController.List(
                memberId = memberId
            )

            basketController.Summary(
                memberId = memberId
            )
        }

        val basketSummary =
            basketState.BasketSummary

        val contractDeliveryAddressId = checkoutState.SelectedDeliveryAddressId
        val contractInvoiceAddressId = checkoutState.SelectedInvoiceAddressId.takeIf { it > 0 } ?: contractDeliveryAddressId

        LaunchedEffect(
            memberId,
            languageId,
            contractDeliveryAddressId,
            contractInvoiceAddressId,
            selectedInstallmentCount
        )
        {
            if (memberId > 0 && contractDeliveryAddressId > 0 && contractInvoiceAddressId > 0)
            {
                checkoutController.LoadContracts(
                    memberId = memberId,
                    languageId = languageId,
                    deliveryAddressId = contractDeliveryAddressId,
                    invoiceAddressId = contractInvoiceAddressId,
                    installmentCount = selectedInstallmentCount
                )
            }
        }

        val checkoutPayableTotal = basketSummary?.GrossTotal ?: 0.0

        CheckoutScreen(
            data = CheckoutScreenData(
                addresses = checkoutState.Addresses,


                selectedDeliveryAddressId =
                    checkoutState.SelectedDeliveryAddressId,



                selectedInvoiceAddressId =
                    checkoutState.SelectedInvoiceAddressId,

                basketItemCount =
                    basketState.ItemCount,


                basketItems =
                    basketState.BasketItems,

                memberCoupons =
                    basketState.Coupons,

                selectedCoupon =
                    basketState.SelectedCoupon,

                isCouponLoading =
                    basketState.IsCouponLoading,

                couponErrorMessage =
                    basketState.CouponErrorMessage,


                deliveryAddress =
                    checkoutState.SelectedDeliveryAddress
                        ?.let { address ->
                            CheckoutSelectionDisplay(
                                title = address.AddressTitle,
                                description = address.Address
                            )
                        },

                invoiceAddress =
                    checkoutState.SelectedInvoiceAddress
                        ?.let { address ->
                            CheckoutSelectionDisplay(
                                title = address.AddressTitle,
                                description = address.Address
                            )
                        },


                preInformationHtml = checkoutState.PreInformationHtml,
                distanceSellingHtml = checkoutState.DistanceSellingHtml,
                isContractLoading = checkoutState.IsContractLoading,

                paymentCardUi =
                    checkoutState.PaymentCard
                        ?.let { card ->
                            CheckoutPaymentCardUiModel(
                                bankName = "Kart",
                                cardAlias = card.cardHolderName,
                                maskedNumber = card.maskedNumber,
                                cardBrand = "CARD"
                            )
                        },

                paymentInstallmentsUi =
                    listOf(
                        CheckoutInstallmentUiModel(
                            installmentCount = 1,
                            title = "Tek Çekim",
                            monthlyAmountText =
                                "₺${String.format("%.2f", checkoutPayableTotal).replace(".", ",")}",
                            totalAmountText =
                                "₺${String.format("%.2f", checkoutPayableTotal).replace(".", ",")}",
                            isSelected = true
                        )
                    ),

                summary = CheckoutPriceSummary(
                    productTotalText = basketSummary?.SubTotal?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty(),
                    cargoTotalText = basketSummary?.ShippingCost?.let { value -> "₺${String.format("%.2f", value).replace(".", ",")}" }.orEmpty(),
                    payableTotalText = "₺${String.format("%.2f", checkoutPayableTotal).replace(".", ",")}"
                ),

                canContinue =
                    basketSummary != null &&
                    basketState.BasketItems.isNotEmpty() &&
                    checkoutState.SelectedDeliveryAddressId > 0 &&
                    checkoutState.PaymentCard?.isComplete == true
            ),

            onBackClick = {
                navigator.back()
            },

            onAddressClick = {
                navigator.navController.navigate(
                    OrderRoutes.checkoutAddressList(
                        OrderRoutes.CheckoutAddressTypeDelivery
                    )
                )
            },

            onAddressSelected = { address ->

                checkoutController.SelectDeliveryAddress(
                    memberAddressId =
                        address.MemberAddressId
                )
            },


            onInvoiceAddressSelected = { address ->
                checkoutController.SelectInvoiceAddress(
                    memberAddressId =
                        address.MemberAddressId
                )
            },

            onCouponSelected = { coupon ->
                basketController.SelectCoupon(
                    coupon
                )
            },

            onCouponCodeApply = { code ->
                basketController.SelectCouponByCode(
                    code
                )
            },

            onPaymentInstallmentSelected = { installmentCount ->
                selectedInstallmentCount =
                    installmentCount.coerceAtLeast(1)

                checkoutController.SelectInstallment(
                    installmentCount
                )
            },

            onPaymentCardEntryChange = { card ->
                checkoutController.SelectPaymentCard(
                    card
                )
            },

            onPreInformationClick = {
                if (memberId > 0 && contractDeliveryAddressId > 0 && contractInvoiceAddressId > 0)
                {
                    Log.d(
                        "ContractDebug",
                        "PRE CLICK memberId=$memberId deliveryAddressId=$contractDeliveryAddressId invoiceAddressId=$contractInvoiceAddressId installmentCount=$selectedInstallmentCount"
                    )

                    checkoutController.LoadContracts(
                        memberId = memberId,
                        languageId = languageId,
                        deliveryAddressId = contractDeliveryAddressId,
                        invoiceAddressId = contractInvoiceAddressId,
                        installmentCount = selectedInstallmentCount
                    )
                }
            },

            onDistanceSalesContractClick = {
                if (memberId > 0 && contractDeliveryAddressId > 0 && contractInvoiceAddressId > 0)
                {
                    Log.d(
                        "ContractDebug",
                        "DISTANCE CLICK memberId=$memberId deliveryAddressId=$contractDeliveryAddressId invoiceAddressId=$contractInvoiceAddressId installmentCount=$selectedInstallmentCount"
                    )

                    checkoutController.LoadContracts(
                        memberId = memberId,
                        languageId = languageId,
                        deliveryAddressId = contractDeliveryAddressId,
                        invoiceAddressId = contractInvoiceAddressId,
                        installmentCount = selectedInstallmentCount
                    )
                }
            },

            onContinueClick = {

                navigator.navController.navigate(
                    OrderRoutes.CheckoutSummary
                )
            }


        )
    }

    composable(
        route = OrderRoutes.CheckoutAddressList,
        arguments = listOf(
            navArgument(OrderRoutes.ArgCheckoutAddressType) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->

        val checkoutState =
            checkoutController.State
                .collectAsState()
                .value

        val addressType =
            backStackEntry.arguments
                ?.getString(OrderRoutes.ArgCheckoutAddressType)
                .orEmpty()

        LaunchedEffect(memberId) {
            checkoutController.LoadAddresses(
                memberId = memberId
            )
        }

        CheckoutAddressListScreen(
            addresses = checkoutState.Addresses,

            isLoading =
                checkoutState.IsLoading &&
                        checkoutState.CurrentAction == "LoadAddresses",

            errorMessage =
                checkoutState.AddressListResult
                    ?.takeIf { !it.Success }
                    ?.Message,

            selectedAddressId =
                if (
                    addressType ==
                    OrderRoutes.CheckoutAddressTypeDelivery
                ) {
                    checkoutState.SelectedDeliveryAddressId
                        .takeIf { it > 0 }
                } else {
                    null
                },

            onBackClick = {
                navigator.back()
            },

            onCreateAddressClick = {
                navigator.navController.navigate(
                    OrderRoutes.checkoutAddressCreate(
                        addressType
                    )
                )
            },

            onEditAddressClick = { addressKey ->
                if (addressKey.isNotBlank()) {
                    navigator.navController.navigate(
                        OrderRoutes.checkoutAddressEdit(
                            addressType = addressType,
                            addressKey = addressKey
                        )
                    )
                }
            },

            onSelectAddressClick = { memberAddressId ->
                if (
                    addressType ==
                    OrderRoutes.CheckoutAddressTypeDelivery
                ) {
                    checkoutController.SelectDeliveryAddress(
                        memberAddressId = memberAddressId
                    )

                    navigator.back()
                }
            },

            onRetryClick = {
                checkoutController.LoadAddresses(
                    memberId = memberId
                )
            }
        )
    }

    composable(
        route = OrderRoutes.CheckoutAddressCreate,
        arguments = listOf(
            navArgument(OrderRoutes.ArgCheckoutAddressType) {
                type = NavType.StringType
            }
        )
    ) {
        val checkoutState =
            checkoutController.State
                .collectAsState()
                .value

        val addressCascadeState =
            addressCascadeController.State
                .collectAsState()
                .value

        LaunchedEffect(Unit) {
            addressCascadeController.OnEvent(
                AddressCascadeEvent.Clear
            )

            addressCascadeController.OnEvent(
                AddressCascadeEvent.LoadCountries(
                    LanguageId = languageId
                )
            )
        }

        CheckoutAddressCreateScreen(
            addressCascadeState = addressCascadeState,

            isLoading =
                checkoutState.IsLoading &&
                        checkoutState.CurrentAction == "InsertAddress",

            errorMessage =
                checkoutState.AddressInsertResult
                    ?.takeIf { !it.Success }
                    ?.Message,

            onBackClick = {
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.Clear
                )

                navigator.back()
            },

            onCountrySelected = { countryId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountry(
                        CountryId = countryId,
                        LanguageId = languageId
                    )
                )
            },

            onCountryStateSelected = { countryStateId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryState(
                        CountryStateId = countryStateId,
                        LanguageId = languageId
                    )
                )
            },

            onCountryDepartmentSelected = { countryDepartmentId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryDepartment(
                        CountryDepartmentId = countryDepartmentId,
                        LanguageId = languageId
                    )
                )
            },

            onCitySelected = { cityId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCity(
                        CityId = cityId,
                        LanguageId = languageId
                    )
                )
            },

            onDistrictSelected = { districtId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectDistrict(
                        DistrictId = districtId
                    )
                )
            },

            onSaveClick = { model ->
                checkoutController.InsertAddress(
                    memberId = memberId,
                    model = model,
                    onSuccess = {
                        addressCascadeController.OnEvent(
                            AddressCascadeEvent.Clear
                        )

                        navigator.back()
                    }
                )
            }
        )
    }

    composable(
        route = OrderRoutes.CheckoutAddressEdit,
        arguments = listOf(
            navArgument(OrderRoutes.ArgCheckoutAddressType) {
                type = NavType.StringType
            },
            navArgument(OrderRoutes.ArgCheckoutAddressKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->

        val checkoutState =
            checkoutController.State
                .collectAsState()
                .value

        val addressCascadeState =
            addressCascadeController.State
                .collectAsState()
                .value

        val addressKey =
            backStackEntry.arguments
                ?.getString(OrderRoutes.ArgCheckoutAddressKey)
                .orEmpty()

        val address =
            checkoutState.AddressDetail
                ?.takeIf {
                    it.AddressKey == addressKey
                }

        LaunchedEffect(
            addressKey,
            memberId
        ) {
            addressCascadeController.OnEvent(
                AddressCascadeEvent.Clear
            )

            checkoutController.ClearAddressDetail()

            checkoutController.LoadAddress(
                memberId = memberId,
                addressKey = addressKey
            )
        }

        LaunchedEffect(
            address?.AddressKey,
            address?.CountryId,
            address?.CountryStateId,
            address?.CountryDepartmentId,
            address?.CityId,
            address?.DistrictId
        ) {
            val currentAddress =
                address ?: return@LaunchedEffect

            addressCascadeController.OnEvent(
                AddressCascadeEvent.SetInitialSelection(
                    Selection = AddressCascadeSelection(
                        CountryId =
                            currentAddress.CountryId ?: 0,

                        CountryStateId =
                            currentAddress.CountryStateId ?: 0,

                        CountryDepartmentId =
                            currentAddress.CountryDepartmentId,

                        CityId =
                            currentAddress.CityId ?: 0,

                        DistrictId =
                            currentAddress.DistrictId
                    ),

                    LanguageId = languageId
                )
            )
        }

        CheckoutAddressEditScreen(
            address = address,
            addressCascadeState = addressCascadeState,

            isLoading =
                checkoutState.IsLoading &&
                        (
                                checkoutState.CurrentAction == "LoadAddress" ||
                                        checkoutState.CurrentAction == "UpdateAddress"
                                ),

            errorMessage =
                checkoutState.AddressDetailResult
                    ?.takeIf { !it.Success }
                    ?.Message
                    ?: checkoutState.AddressUpdateResult
                        ?.takeIf { !it.Success }
                        ?.Message,

            onBackClick = {
                checkoutController.ClearAddressDetail()

                addressCascadeController.OnEvent(
                    AddressCascadeEvent.Clear
                )

                navigator.back()
            },

            onCountrySelected = { countryId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountry(
                        CountryId = countryId,
                        LanguageId = languageId
                    )
                )
            },

            onCountryStateSelected = { countryStateId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryState(
                        CountryStateId = countryStateId,
                        LanguageId = languageId
                    )
                )
            },

            onCountryDepartmentSelected = { countryDepartmentId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryDepartment(
                        CountryDepartmentId = countryDepartmentId,
                        LanguageId = languageId
                    )
                )
            },

            onCitySelected = { cityId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCity(
                        CityId = cityId,
                        LanguageId = languageId
                    )
                )
            },

            onDistrictSelected = { districtId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectDistrict(
                        DistrictId = districtId
                    )
                )
            },

            onSaveClick = { model ->
                checkoutController.UpdateAddress(
                    memberId = memberId,
                    model = model,
                    onSuccess = {
                        checkoutController.ClearAddressDetail()

                        addressCascadeController.OnEvent(
                            AddressCascadeEvent.Clear
                        )

                        navigator.back()
                    }
                )
            }
        )
    }

    composable(OrderRoutes.CheckoutSummary) {
        val checkoutState =
            checkoutController.State
                .collectAsState()
                .value

        val basketState =
            basketController.State
                .collectAsState()
                .value

        val basketSummary =
            basketState.BasketSummary

        val deliveryAddress =
            checkoutState.SelectedDeliveryAddress

        LaunchedEffect(memberId) {
            checkoutController.LoadAddresses(
                memberId = memberId
            )

            basketController.List(
                memberId = memberId
            )

            basketController.Summary(
                memberId = memberId
            )
        }

        val summaryData =
            CheckoutSummaryScreenData(
                address =
                    CheckoutSummaryAddress(
                        title =
                            deliveryAddress
                                ?.AddressTitle
                                .orEmpty(),

                        fullName =
                            listOf(
                                deliveryAddress
                                    ?.Name
                                    .orEmpty(),

                                deliveryAddress
                                    ?.Surname
                                    .orEmpty()
                            )
                                .filter {
                                    it.isNotBlank()
                                }
                                .joinToString(" "),

                        fullAddress =
                            deliveryAddress
                                ?.Address
                                .orEmpty()
                    ),

                cargo =
                    CheckoutSummaryCargo(
                        companySummaryText =
                            basketSummary
                                ?.ShippingCost
                                ?.let { value ->
                                    if (value > 0.0) {
                                        "₺${String.format("%.2f", value).replace(".", ",")} kargo"
                                    } else {
                                        "Ücretsiz kargo"
                                    }
                                }
                                .orEmpty(),

                        deliveryEstimateText =
                            "Kargo firması sipariş akışında belirlenir.",

                        packageSummaryText =
                            basketSummary
                                ?.StoreShippingBreakdown
                                ?.takeIf {
                                    it.isNotEmpty()
                                }
                                ?.let { breakdown ->
                                    "${breakdown.size} mağaza için kargo hesaplandı."
                                }
                                .orEmpty()
                    ),

                payment =
                    CheckoutSummaryPayment(
                        methodTitle =
                            "Banka / Kredi Kartı",

                        description =
                            checkoutState.PaymentCard
                                ?.maskedNumber
                                ?.takeIf {
                                    it.isNotBlank()
                                }
                                ?: "Kart bilgisi girilmedi"
                    ),

                products =
                    basketState.BasketItems
                        .map { basket ->
                            CheckoutSummaryProductItem(
                                id =
                                    basket.BasketId,

                                name =
                                    basket.ProductName,

                                storeName =
                                    basket.Store,

                                variantText =
                                    listOf(
                                        basket.Color,
                                        basket.Size
                                    )
                                        .filter {
                                            it.isNotBlank()
                                        }
                                        .joinToString(" · "),

                                priceText =
                                    "${basket.CurrencySymbol.ifBlank { "₺" }}${String.format("%.2f", basket.TotalPrice).replace(".", ",")}",

                                quantity =
                                    basket.Quantity,

                                imageText =
                                    basket.ProductName
                                        .trim()
                                        .take(2)
                                        .uppercase()
                            )
                        },

                total =
                    CheckoutSummaryTotal(
                        productTotalText =
                            basketSummary
                                ?.SubTotal
                                ?.let { value ->
                                    "₺${String.format("%.2f", value).replace(".", ",")}"
                                }
                                .orEmpty(),

                        cargoTotalText =
                            basketSummary
                                ?.ShippingCost
                                ?.let { value ->
                                    "₺${String.format("%.2f", value).replace(".", ",")}"
                                }
                                .orEmpty(),

                        discountText =
                            "",

                        totalPriceText =
                            basketSummary
                                ?.GrossTotal
                                ?.let { value ->
                                    "₺${String.format("%.2f", value).replace(".", ",")}"
                                }
                                .orEmpty()
                    )
            )

        CheckoutSummaryScreen(
            data =
                summaryData,

            onBackClick = {
                navigator.back()
            },

            onEditAddressClick = {
                navigator.back()
            },

            onEditPaymentClick = {
                navigator.back()
            },

            onCompleteOrderClick = {
                checkoutController.CompleteCheckout(
                    memberId = memberId,
                    languageId = languageId
                )
            }
        )
    }

    composable(
        route = OrderRoutes.Success,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderId) {
                type = NavType.IntType
            },
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments
            ?.getInt(OrderRoutes.ArgOrderId)
            ?: 0

        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()

        OrderSuccessScreen(
            orderId = orderId,
            onGoHomeClick = {
                navigator.navigateToRetailHome()
            },
            onOrderDetailClick = { selectedOrderId ->
                navigator.navController.navigate(
                    OrderRoutes.detail(
                        orderId = selectedOrderId,
                        orderKey = orderKey
                    )
                )
            },
            onContinueShoppingClick = {
                navigator.navigateToRetailHome()
            },
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onBasketClick = {
                navigator.navigateToRetailBasket()
            },
            onAccountClick = {
                navigator.navigateToAccount()
            }
        )
    }

    composable(
        route = OrderRoutes.Detail,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderId) {
                type = NavType.IntType
            },
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderId = backStackEntry.arguments
            ?.getInt(OrderRoutes.ArgOrderId)
            ?: 0

        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()


        OrderDetailScreen(
            orderId = orderId,
            orderKey = orderKey,
            memberId = memberId,

            onBackClick = {
                navigator.back()
            },
            onContractClick = { storeKey ->
                navigator.navController.navigate(
                    OrderRoutes.contract(
                        orderKey = orderKey,
                        storeKey = storeKey
                    )
                )
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreDetail
                )
            },
            onSupportClick = {},
            onCancelRequestClick = { orderStoreLineId, selectedOrderKey ->
                navigator.navController.navigate(
                    OrderRoutes.cancelRequest(
                        orderStoreLineId = orderStoreLineId,
                        orderKey = selectedOrderKey
                    )
                )
            },
            onReturnRequestClick = { orderStoreLineId, selectedOrderKey ->
                navigator.navController.navigate(
                    OrderRoutes.returnRequest(
                        orderStoreLineId = orderStoreLineId,
                        orderKey = selectedOrderKey
                    )
                )
            },
            onReviewCreateClick = { orderStoreLineId, productId, productSecureKey ->
                navigator.navController.navigate(
                    OrderRoutes.reviewCreate(
                        orderStoreLineId = orderStoreLineId,
                        productId = productId,
                        productSecureKey = productSecureKey
                    )
                )
            },
            onShipmentTrackingClick = { cargoTrackingNumber ->
                navigator.navController.navigate(
                    OrderRoutes.shipmentTracking(
                        cargoTrackingNumber
                    )
                )
            }
        )
    }

    composable(
        route = OrderRoutes.Contract,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            },
            navArgument(OrderRoutes.ArgStoreKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()

        val storeKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgStoreKey)
            .orEmpty()

        OrderContractScreen(
            orderKey = orderKey,
            storeKey = storeKey,
            onBackClick = {
                navigator.back()
            },
            onPrintClick = { contractText ->
                // Android paylaşım/yazdırma entegrasyonu sonraki teknik fazda bağlanacak.
            }
        )
    }

    composable(
        route = OrderRoutes.CancelRequest,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderStoreLineId) {
                type = NavType.LongType
            },
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderStoreLineId = backStackEntry.arguments
            ?.getLong(OrderRoutes.ArgOrderStoreLineId)
            ?: 0L

        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()

        OrderCancelRequestScreen(
            orderStoreLineId = orderStoreLineId,
            orderKey = orderKey,
            memberId = memberId,
            languageId = languageId,
            onBackClick = {
                navigator.back()
            },
            onSubmitSuccess = {
                navigator.back()
            }
        )
    }

    composable(
        route = OrderRoutes.ReturnRequest,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderStoreLineId) {
                type = NavType.LongType
            },
            navArgument(OrderRoutes.ArgOrderKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderStoreLineId = backStackEntry.arguments
            ?.getLong(OrderRoutes.ArgOrderStoreLineId)
            ?: 0L

        val orderKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgOrderKey)
            .orEmpty()

        OrderReturnRequestScreen(
            orderStoreLineId = orderStoreLineId,
            orderKey = orderKey,
            memberId = memberId,
            languageId = languageId,
            onBackClick = {
                navigator.back()
            },
            onSubmitSuccess = {
                navigator.back()
            }
        )
    }

    composable(
        route = OrderRoutes.ReviewCreate,
        arguments = listOf(
            navArgument(OrderRoutes.ArgOrderStoreLineId) {
                type = NavType.LongType
            },
            navArgument(OrderRoutes.ArgProductId) {
                type = NavType.LongType
            },
            navArgument(OrderRoutes.ArgProductSecureKey) {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val orderStoreLineId = backStackEntry.arguments
            ?.getLong(OrderRoutes.ArgOrderStoreLineId)
            ?: 0L

        val productId = backStackEntry.arguments
            ?.getLong(OrderRoutes.ArgProductId)
            ?: 0L

        val productSecureKey = backStackEntry.arguments
            ?.getString(OrderRoutes.ArgProductSecureKey)
            .orEmpty()

        OrderReviewCreateScreen(
            orderStoreLineId = orderStoreLineId,
            productId = productId,
            productSecureKey = productSecureKey,
            memberId = memberId,
            onBackClick = {
                navigator.back()
            },
            onSubmitSuccess = {
                navigator.back()
            }
        )
    }

    composable(
        route = OrderRoutes.ShipmentTracking,
        arguments = listOf(
            navArgument(OrderRoutes.ArgCargoTrackingNumber) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val cargoTrackingNumber = backStackEntry.arguments
            ?.getInt(OrderRoutes.ArgCargoTrackingNumber)
            ?: 0

        OrderShipmentTrackingScreen(
            cargoTrackingNumber = cargoTrackingNumber,
            memberId = memberId,
            onBackClick = {
                navigator.back()
            }
        )
    }
}