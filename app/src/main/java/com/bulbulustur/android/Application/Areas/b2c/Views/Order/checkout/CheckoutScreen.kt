package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.lazy.LazyRow
import coil3.compose.AsyncImage
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCheckboxRow
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime


@Composable
fun CheckoutScreen(
    data: CheckoutScreenData = CheckoutScreenData(),

    onBackClick: () -> Unit = {},

    /*
     * Eski OrderGraph contract'ını şimdilik koruyoruz.
     * Ürün sheet'i artık ekran içinde açılır.
     */
    onProductsClick: () -> Unit = {},
    onAddressClick: () -> Unit = {},

    onAddressSelected: (MemberAddressDTO) -> Unit = {},
    onInvoiceAddressSelected: (MemberAddressDTO) -> Unit = {},

    onAddDeliveryAddressClick: () -> Unit = {},
    onAddInvoiceAddressClick: () -> Unit = {},

    onEditDeliveryAddressClick: (MemberAddressDTO) -> Unit = {},
    onEditInvoiceAddressClick: (MemberAddressDTO) -> Unit = {},

    onInvoiceTypeClick: () -> Unit = {},
    onInvoiceTypeSelected: (CheckoutSelectionDisplay) -> Unit = {},

    onPaymentMethodClick: () -> Unit = {},
    onPaymentMethodSelected: (CheckoutSelectionDisplay) -> Unit = {},

    onCardClick: () -> Unit = {},
    onCardSelected: (CheckoutSelectionDisplay) -> Unit = {},

    onInstallmentClick: () -> Unit = {},
    onInstallmentSelected: (CheckoutSelectionDisplay) -> Unit = {},

    onCouponClick: () -> Unit = {},
    onCouponSelected: (MemberCouponDTO) -> Unit = {},
    onCouponCodeApply: (String) -> Unit = {},

    onPreInformationClick: () -> Unit = {},
    onDistanceSalesContractClick: () -> Unit = {},
    onWithdrawalRightClick: () -> Unit = {},

    onTermsAcceptedChange: (Boolean) -> Unit = {},

    onContinueClick: () -> Unit = {}
) {


var termsAccepted by rememberSaveable {
        mutableStateOf(false)
    }

    var showProductsInline by rememberSaveable {
        mutableStateOf(false)
    }

    var showDeliveryAddressSheet by rememberSaveable {
        mutableStateOf(false)
    }

    var showInvoiceAddressSheet by rememberSaveable {
        mutableStateOf(false)
    }

    var showCouponSheet by rememberSaveable {
        mutableStateOf(false)
    }

    var showOrderSummary by rememberSaveable {
        mutableStateOf(false)
    }

    var legalSheetType by remember {
        mutableStateOf<CheckoutLegalSheetType?>(null)
    }

    var selectionSheetType by remember {
        mutableStateOf<CheckoutSelectionSheetType?>(null)
    }

    var corporateInvoiceCompanyName by rememberSaveable {
        mutableStateOf("")
    }

    var corporateInvoiceTaxOffice by rememberSaveable {
        mutableStateOf("")
    }

    var corporateInvoiceTaxNumber by rememberSaveable {
        mutableStateOf("")
    }

    val hasCorporateInvoiceInfo =
        corporateInvoiceCompanyName.isNotBlank() &&
            corporateInvoiceTaxOffice.isNotBlank() &&
            corporateInvoiceTaxNumber.isNotBlank()

    val pageBackground =
        MaterialTheme.colorScheme.surfaceContainerLow


    val contractSnapshot =
        CheckoutContractSnapshot(
            deliveryAddress =
                data.deliveryAddress
                    ?.description
                    .orEmpty(),

            
invoiceAddress =
                (
                    data.invoiceAddress
                        ?: data.deliveryAddress
                )
                    ?.description
                    .orEmpty(),


            shippingCost =
                data.summary.cargoTotalText,

            grandTotal =
                data.summary.payableTotalText,

            installment =
                data.installment
                    ?.title
                    .orEmpty(),

            installmentDetail =
                data.installment
                    ?.description
                    .orEmpty(),

            providerFee =
                data.summary.commissionTotalText
        )


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = pageBackground
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            /*
             * ================================================================
             * NORMAL SCREEN
             * ================================================================
             */

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Surface(
                    color = MaterialTheme.colorScheme.surface
                ) {

                    BbInnerPageHeader(
                        title = "Güvenli Ödeme",
                        onBackClick = onBackClick
                    )
                }


                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant
                )


                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(pageBackground),

                    contentPadding =
                        PaddingValues(
                            top = BBSpacing.PageTopCompact,

                            /*
                             * Footer overlay olduğu için son eleman
                             * footer'ın altında kalmasın.
                             */
                            bottom =
                                BBSpacing.Space20 +
                                        BBSpacing.Space4
                        ),

                    verticalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.SectionGapCompact
                        )
                ) {

                    /*
                     * ========================================================
                     * PRODUCTS
                     * ========================================================
                     */

                    item {

                        CheckoutPageItem {

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(
                                    BBSpacing.Space2
                                )
                            ) {

                                CheckoutActionCard(
                                    title =
                                        "Sepetteki Ürünler (${data.basketItemCount})",

                                    description =
                                        "Siparişe dahil ürünleri görüntüle.",

                                    actionText =
                                        if (showProductsInline) {
                                            "Kapat"
                                        } else {
                                            "Görüntüle"
                                        },

                                    onClick = {

                                        showProductsInline =
                                            !showProductsInline

                                        onProductsClick()
                                    }
                                )


                                AnimatedVisibility(
                                    visible =
                                        showProductsInline,

                                    enter =
                                        expandVertically(),

                                    exit =
                                        shrinkVertically()
                                ) {

                                    CheckoutProductImageStrip(
                                        basketItems =
                                            data.basketItems
                                    )
                                }
                            }
                        }
                    }

                    /*
                     * ========================================================
                     * CARGO
                     * ========================================================
                     */

                    item {

                        CheckoutPageItem {

                            CheckoutCargoCard(
                                cargoTotalText =
                                    data.summary.cargoTotalText
                            )
                        }
                    }


                    /*
                     * ========================================================
                     * ADDRESS
                     * ========================================================
                     */

                    item {

                        CheckoutPageItem {

                            CheckoutSectionTitle(
                                title = "Teslimat ve Fatura",
                                description = "Teslimat ve fatura bilgilerini kontrol et.",
                                actionText = "Ekle / Düzenle",
                                onActionClick = onAddressClick
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title =
                                    BBLocalization.Current.Get(
                                        key =
                                            "fa3df4de-7069-4a3d-9dac-5a4ea9b88b65",

                                        fallback =
                                            "Teslimat Adresi"
                                    ),

                                value =
                                    data.deliveryAddress
                                        ?.title
                                        .orEmpty(),

                                description =
                                    data.deliveryAddress
                                        ?.description
                                        ?.takeIf {
                                            it.isNotBlank()
                                        }
                                        ?: "Teslimat adresini seç.",

                                actionText =
                                    if (
                                        data.deliveryAddress ==
                                        null
                                    ) {
                                        "Seç"
                                    } else {
                                        "Değiştir"
                                    },

                                /*
                                 * ARTIK FULL PAGE ADDRESS LIST'E
                                 * gitmiyoruz.
                                 *
                                 * Basit sheet açılıyor.
                                 */
                                onClick = {
                                    showDeliveryAddressSheet = true
                                }
                            )
                        }
                    }

                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title =
                                    "Fatura Adresi",

                                value =
                                    (
                                            data.invoiceAddress
                                                ?: data.deliveryAddress
                                            )
                                        ?.title
                                        .orEmpty(),

                                description =
                                    (
                                            data.invoiceAddress
                                                ?: data.deliveryAddress
                                            )
                                        ?.description
                                        ?.takeIf {
                                            it.isNotBlank()
                                        }
                                        ?: "Fatura adresini seç.",

                                actionText =
                                    if (
                                        data.invoiceAddress == null &&
                                        data.deliveryAddress == null
                                    ) {
                                        "Seç"
                                    } else {
                                        "Değiştir"
                                    },

                                onClick = {
                                    showInvoiceAddressSheet = true
                                }
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title = "Fatura Bilgileri",

                                value =
                                    corporateInvoiceCompanyName,

                                description =
                                    if (hasCorporateInvoiceInfo) {
                                        "Kurumsal fatura bilgileri eklendi."
                                    } else {
                                        "Kurumsal fatura bilgisi eklenmedi."
                                    },

                                actionText =
                                    if (hasCorporateInvoiceInfo) {
                                        "Düzenle"
                                    } else {
                                        "Ekle"
                                    },

                                onClick = {
                                    selectionSheetType =
                                        CheckoutSelectionSheetType.InvoiceInfo
                                }
                            )
                        }
                    }


                    /*
                     * ========================================================
                     * PAYMENT
                     * ========================================================
                     */

                    item {

                        CheckoutPageItem {

                            CheckoutSectionTitle(
                                title = "Ödeme Yöntemi",

                                description =
                                    "Ödeme yöntemini ve kullanacağın kartı seç."
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title = "Ödeme Yöntemi",

                                value =
                                    data.paymentMethod
                                        ?.title
                                        .orEmpty(),

                                description =
                                    data.paymentMethod
                                        ?.description
                                        ?.takeIf {
                                            it.isNotBlank()
                                        }
                                        ?: "Kullanılabilir ödeme yöntemini seç.",

                                actionText =
                                    if (
                                        data.paymentMethod ==
                                        null
                                    ) {
                                        "Seç"
                                    } else {
                                        "Değiştir"
                                    },

                                onClick = {
                                    onPaymentMethodClick()

                                    selectionSheetType =
                                        CheckoutSelectionSheetType.PaymentMethod
                                }
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title = "Kart Bilgileri",

                                value =
                                    data.card
                                        ?.title
                                        .orEmpty(),

                                description =
                                    data.card
                                        ?.description
                                        ?.takeIf {
                                            it.isNotBlank()
                                        }
                                        ?: "Kayıtlı kart seç veya yeni kart kullan.",

                                actionText =
                                    if (
                                        data.card ==
                                        null
                                    ) {
                                        "Kart Seç"
                                    } else {
                                        "Değiştir"
                                    },

                                onClick = {
                                    onCardClick()

                                    selectionSheetType =
                                        CheckoutSelectionSheetType.Card
                                }
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title =
                                    "Taksit Seçenekleri",

                                value =
                                    data.installment
                                        ?.title
                                        .orEmpty(),

                                description =
                                    data.installment
                                        ?.description
                                        ?.takeIf {
                                            it.isNotBlank()
                                        }
                                        ?: "Kart seçildikten sonra uygun taksit seçenekleri gösterilir.",

                                actionText =
                                    "Görüntüle",

                                onClick = {
                                    onInstallmentClick()

                                    selectionSheetType =
                                        CheckoutSelectionSheetType.Installment
                                }
                            )
                        }
                    }


                    /*
                     * ========================================================
                     * COUPONS
                     * ========================================================
                     */

                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title =
                                    BBLocalization.Current.Get(
                                        key =
                                            "b2007b6f-06c1-4ddf-b73e-2f6da5361af3",

                                        fallback =
                                            "Kupon ve İndirimler"
                                    ),

                                value =
                                    data.selectedCoupon
                                        ?.CouponCode
                                        .orEmpty(),

                                description =
                                    data.selectedCoupon
                                        ?.Descripion
                                        ?.takeIf {
                                            it.isNotBlank()
                                        }
                                        ?: "İndirim kodu ekle veya hesabına tanımlı kuponları görüntüle.",

                                actionText =
                                    if (
                                        data.selectedCoupon ==
                                        null
                                    ) {
                                        "Görüntüle"
                                    } else {
                                        "Değiştir"
                                    },

                                onClick = {
                                    /*
                                     * Parent burada repository load
                                     * tetikleyebilir.
                                     */
                                    onCouponClick()

                                    showCouponSheet = true
                                }
                            )
                        }
                    }


                    /*
                     * ========================================================
                     * ORDER SUMMARY
                     * ========================================================
                     */

                    item {

                        CheckoutPageItem {

                            CheckoutSectionTitle(
                                title = "Sipariş Özeti",

                                description =
                                    "Ödeme öncesi tutarları kontrol et."
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutOrderSummaryCard(
                                summary = data.summary
                            )
                        }
                    }


                    /*
                     * ========================================================
                     * SECURE PAYMENT
                     * ========================================================
                     */

                    item {

                        CheckoutPageItem {

                            CheckoutSecureInfoCard()
                        }
                    }


                    /*
                     * ========================================================
                     * LEGAL
                     * ========================================================
                     */

                    item {

                        CheckoutPageItem {

                            CheckoutSectionTitle(
                                title =
                                    "Sözleşmeler ve Formlar",

                                description =
                                    "Siparişi tamamlamadan önce yasal metinleri incele."
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title =
                                    "Ön Bilgilendirme Formu",

                                description =
                                    "Sipariş, teslimat ve ödeme bilgilerini içeren ön bilgilendirme formu.",

                                actionText =
                                    "Görüntüle",

                                onClick = {
                                    legalSheetType =
                                        CheckoutLegalSheetType.PreInformation

                                    onPreInformationClick()
                                }
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title =
                                    "Mesafeli Satış Sözleşmesi",

                                description =
                                    "Siparişe özel mesafeli satış sözleşmesini görüntüle.",

                                actionText =
                                    "Görüntüle",

                                onClick = {
                                    legalSheetType =
                                        CheckoutLegalSheetType.DistanceSales

                                    onDistanceSalesContractClick()
                                }
                            )
                        }
                    }


                    item {

                        CheckoutPageItem {

                            CheckoutActionCard(
                                title =
                                    "Cayma Hakkı",

                                description =
                                    "Cayma hakkı ve ilgili koşulları görüntüle.",

                                actionText =
                                    "Görüntüle",

                                onClick = {
                                    legalSheetType =
                                        CheckoutLegalSheetType.WithdrawalRight

                                    onWithdrawalRightClick()
                                }
                            )
                        }
                    }


                    /*
                     * Checkbox BAĞIMSIZ.
                     * İç içe Surface yok.
                     */

                    item {

                        CheckoutPageItem {

                            BbCheckboxRow(
                                checked =
                                    termsAccepted,

                                onCheckedChange = {
                                        checked ->

                                    termsAccepted =
                                        checked

                                    onTermsAcceptedChange(
                                        checked
                                    )
                                },

                                title =
                                    "Ön Bilgilendirme Formu'nu ve Mesafeli Satış Sözleşmesi'ni okudum ve onaylıyorum."
                            )
                        }
                    }
                }
            }


            /*
             * ================================================================
             * SUMMARY SCRIM
             * ================================================================
             */

            if (showOrderSummary) {

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(
                                MaterialTheme.colorScheme.scrim.copy(
                                    alpha = 0.32f
                                )
                            )
                            .clickable {
                                showOrderSummary = false
                            }
                )
            }


            /*
             * ================================================================
             * FIXED BOTTOM OVERLAY
             *
             * SUMMARY
             * ↓
             * FOOTER
             *
             * Footer her zaman ekranın DİBİNDE.
             * ================================================================
             */

            Column(
                modifier =
                    Modifier
                        .align(
                            Alignment.BottomCenter
                        )
                        .fillMaxWidth()
            ) {

                if (showOrderSummary) {

                    CheckoutOrderSummaryOverlay(
                        summary =
                            data.summary
                    )
                }


                CheckoutBottomBar(
                    totalPriceText =
                        data.summary.payableTotalText,

                    canContinue =
                        data.canContinue &&
                                termsAccepted,

                    summaryExpanded =
                        showOrderSummary,

                    onSummaryClick = {
                        showOrderSummary =
                            !showOrderSummary
                    },

                    onContinueClick =
                        onContinueClick
                )
            }
        }
    }

    /*
     * ========================================================================
     * DELIVERY ADDRESS
     * ========================================================================
     */

    if (showDeliveryAddressSheet) {

        CheckoutAddressSelectionSheet(
            title = "Teslimat Adresi Seç",

            addresses = data.addresses,

            selectedAddressId = data.selectedDeliveryAddressId,

            onAddressSelected = { address ->

                onAddressSelected( address
                )

                showDeliveryAddressSheet = false
            },

            onAddAddressClick = { showDeliveryAddressSheet = false

                onAddDeliveryAddressClick()
            },

            onDismiss = { showDeliveryAddressSheet = false

            }
        )
    }

    /*
     * ========================================================================
     * INVOICE ADDRESS
     * ========================================================================
     */

    if (showInvoiceAddressSheet) {

        CheckoutAddressSelectionSheet(
            title = "Fatura Adresi Seç",

            addresses = data.addresses,

            
selectedAddressId =
                data.selectedInvoiceAddressId
                    .takeIf { it > 0 }
                    ?: data.selectedDeliveryAddressId,


            onAddressSelected = {
                    address ->

                onInvoiceAddressSelected(
                    address
                )

                showInvoiceAddressSheet =
                    false
            },

            onAddAddressClick = {
                showInvoiceAddressSheet =
                    false

                onAddInvoiceAddressClick()
            },

            onDismiss = {
                showInvoiceAddressSheet =
                    false
            }
        )
    }


    /*
     * ========================================================================
     * COUPONS
     * ========================================================================
     */

    if (showCouponSheet) {

        CheckoutCouponSheet(
            coupons =
                data.memberCoupons,

            selectedCoupon =
                data.selectedCoupon,

            isLoading =
                data.isCouponLoading,

            errorMessage =
                data.couponErrorMessage,

            onCouponSelected = {
                    coupon ->

                onCouponSelected(
                    coupon
                )

                showCouponSheet =
                    false
            },

            onCouponCodeApply = {
                    code ->

                onCouponCodeApply(
                    code
                )
            },

            onDismiss = {
                showCouponSheet =
                    false
            }
        )
    }


    /*
     * ========================================================================
     * LEGAL HTML
     * ========================================================================
     */

    legalSheetType?.let {
            type ->

        when (type) {

            CheckoutLegalSheetType.PreInformation -> {

                CheckoutLegalSheet(
                    title =
                        "Ön Bilgilendirme Formu",

                    htmlContent =
                        data.preInformationHtml,

                    loading =
                        data.isContractLoading,

                    emptyText =
                        "Ön Bilgilendirme Formu henüz oluşturulmadı.",

                    snapshot =
                        contractSnapshot,

                    onDismiss = {
                        legalSheetType = null
                    }
                )
            }


            CheckoutLegalSheetType.DistanceSales -> {

                CheckoutLegalSheet(
                    title =
                        "Mesafeli Satış Sözleşmesi",

                    htmlContent =
                        data.distanceSellingHtml,

                    loading =
                        data.isContractLoading,

                    emptyText =
                        "Mesafeli Satış Sözleşmesi henüz oluşturulmadı.",

                    snapshot =
                        contractSnapshot,

                    onDismiss = {
                        legalSheetType = null
                    }
                )
            }


            CheckoutLegalSheetType.WithdrawalRight -> {

                CheckoutLegalSheet(
                    title =
                        "Cayma Hakkı",

                    htmlContent =
                        data.withdrawalRightHtml,

                    loading =
                        data.isContractLoading,

                    emptyText =
                        "Cayma hakkı bilgilendirmesi henüz oluşturulmadı.",

                    snapshot =
                        contractSnapshot,

                    onDismiss = {
                        legalSheetType = null
                    }
                )
            }
        }
    }


    /*
     * ========================================================================
     * OTHER SIMPLE SELECTION SHEETS
     * ========================================================================
     */

    selectionSheetType?.let { type ->

        when (type) {

            CheckoutSelectionSheetType.InvoiceInfo -> {

                CheckoutInvoiceInfoSheet(
                    companyName =
                        corporateInvoiceCompanyName,

                    taxOffice =
                        corporateInvoiceTaxOffice,

                    taxNumber =
                        corporateInvoiceTaxNumber,

                    onSave = {
                            companyName,
                            taxOffice,
                            taxNumber ->

                        corporateInvoiceCompanyName =
                            companyName

                        corporateInvoiceTaxOffice =
                            taxOffice

                        corporateInvoiceTaxNumber =
                            taxNumber

                        selectionSheetType =
                            null
                    },

                    onDismiss = {
                        selectionSheetType =
                            null
                    }
                )
            }


            CheckoutSelectionSheetType.PaymentMethod -> {

                CheckoutPlainSelectionSheet(
                    title =
                        "Ödeme Yöntemi",

                    items =
                        data.paymentMethodOptions,

                    selected =
                        data.paymentMethod,

                    emptyText =
                        "Kullanılabilir ödeme yöntemi bulunamadı.",

                    onSelected = {
                            selection ->

                        onPaymentMethodSelected(
                            selection
                        )

                        selectionSheetType =
                            null
                    },

                    onDismiss = {
                        selectionSheetType =
                            null
                    }
                )
            }


            CheckoutSelectionSheetType.Card -> {

                CheckoutPlainSelectionSheet(
                    title =
                        "Kart Seçimi",

                    items =
                        data.cardOptions,

                    selected =
                        data.card,

                    emptyText =
                        "Kayıtlı kart bulunamadı.",

                    onSelected = {
                            selection ->

                        onCardSelected(
                            selection
                        )

                        selectionSheetType =
                            null
                    },

                    onDismiss = {
                        selectionSheetType =
                            null
                    }
                )
            }


            CheckoutSelectionSheetType.Installment -> {

                CheckoutPlainSelectionSheet(
                    title =
                        "Taksit Seçenekleri",

                    items =
                        data.installmentOptions,

                    selected =
                        data.installment,

                    emptyText =
                        "Kart seçildikten sonra taksit seçenekleri gösterilecektir.",

                    onSelected = {
                            selection ->

                        onInstallmentSelected(
                            selection
                        )

                        selectionSheetType =
                            null
                    },

                    onDismiss = {
                        selectionSheetType =
                            null
                    }
                )
            }
        }
    }
}


/*
 * ============================================================================
 * PAGE
 * ============================================================================
 */

@Composable
private fun CheckoutPageItem(
    content: @Composable () -> Unit
) {

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal =
                        BBSpacing.PageHorizontal
                )
    ) {

        content()
    }
}

@Composable
private fun CheckoutSectionTitle(
    title: String,
    description: String = "",
    actionText: String = "",
    onActionClick: (() -> Unit)? = null
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (
                actionText.isNotBlank() &&
                onActionClick != null
            ) {

                Text(
                    text = actionText,
                    modifier = Modifier
                        .clip(BBRadius.PillShape)
                        .clickable {
                            onActionClick()
                        }
                        .padding(
                            horizontal = BBSpacing.Space2,
                            vertical = BBSpacing.Space1
                        ),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        if (description.isNotBlank()) {

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/*
 * ============================================================================
 * STANDARD CARD
 * ============================================================================
 */

@Composable
private fun CheckoutActionCard(
    title: String,
    value: String = "",
    description: String = "",
    actionText: String = "",
    onClick: (() -> Unit)? = null
) {

    val shape =
        MaterialTheme.shapes.large


    val modifier =
        if (onClick == null) {

            Modifier.fillMaxWidth()

        } else {

            Modifier
                .fillMaxWidth()
                .clip(shape)
                .clickable {
                    onClick()
                }
        }


    Surface(
        modifier =
            modifier,

        shape =
            shape,

        color =
            MaterialTheme.colorScheme.surface,

        border =
            BorderStroke(
                width = 1.dp,

                color =
                    MaterialTheme.colorScheme.outlineVariant
            )
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        BBSpacing.Space4
                    ),

            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                ),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column(
                modifier =
                    Modifier.weight(1f),

                verticalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
            ) {

                Text(
                    text = title,

                    style =
                        MaterialTheme.typography.titleSmall,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.onSurface
                )


                if (value.isNotBlank()) {

                    Text(
                        text = value,

                        style =
                            MaterialTheme.typography.bodyMedium,

                        fontWeight =
                            FontWeight.SemiBold,

                        color =
                            MaterialTheme.colorScheme.onSurface
                    )
                }


                if (description.isNotBlank()) {

                    Text(
                        text = description,

                        style =
                            MaterialTheme.typography.bodySmall,

                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }


            if (actionText.isNotBlank()) {

                Text(
                    text = actionText,

                    style =
                        MaterialTheme.typography.labelMedium,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }


            if (onClick != null) {

                Icon(
                    imageVector =
                        Icons.Outlined.ChevronRight,

                    contentDescription =
                        null,

                    tint =
                        MaterialTheme.colorScheme.onSurfaceVariant,

                    modifier =
                        Modifier.size(
                            BBIcon.Action
                        )
                )
            }
        }
    }
}


/*
 * ============================================================================
 * CARGO
 * ============================================================================
 */

@Composable
private fun CheckoutCargoCard(
    cargoTotalText: String
) {

    Surface(
        modifier =
            Modifier.fillMaxWidth(),

        shape =
            MaterialTheme.shapes.large,

        color =
            MaterialTheme.colorScheme.surface,

        border =
            BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant
            )
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        BBSpacing.Space4
                    ),

            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {

            Text(
                text =
                    "Teslimat / Kargo",

                style =
                    MaterialTheme.typography.titleSmall,

                fontWeight =
                    FontWeight.Bold
            )


            Text(
                text =
                    "Anlaşmalı Kargo",

                style =
                    MaterialTheme.typography.bodyMedium,

                fontWeight =
                    FontWeight.SemiBold
            )


            Text(
                text =
                    "Kargo firması sistem tarafından belirlenir.",

                style =
                    MaterialTheme.typography.bodySmall,

                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )


            HorizontalDivider(
                color =
                    MaterialTheme.colorScheme.outlineVariant
            )


            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text =
                        BBLocalization.Current.Get(
                            key =
                                "8fa1207a-2a06-4bdb-936b-f7da848e0f72",

                            fallback =
                                "Kargo"
                        ),

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )


                Text(
                    text =
                        cargoTotalText.ifBlank {
                            "—"
                        },

                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}


/*
 * ============================================================================
 * NORMAL ORDER SUMMARY CARD
 * ============================================================================
 */

@Composable
private fun CheckoutOrderSummaryCard(
    summary: CheckoutPriceSummary
) {

    Surface(
        modifier =
            Modifier.fillMaxWidth(),

        shape =
            MaterialTheme.shapes.large,

        color =
            MaterialTheme.colorScheme.surface,

        border =
            BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant
            )
    ) {

        CheckoutSummaryContent(
            summary =
                summary,

            modifier =
                Modifier.padding(
                    BBSpacing.Space4
                )
        )
    }
}


/*
 * ============================================================================
 * SECURE INFO
 *
 * Gerçek opposite surface token.
 * ============================================================================
 */

@Composable
private fun CheckoutSecureInfoCard() {

    Surface(
        modifier =
            Modifier.fillMaxWidth(),

        shape =
            MaterialTheme.shapes.large,

        color =
            MaterialTheme.colorScheme.inverseSurface,

        shadowElevation =
            BBSpacing.Space2
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        BBSpacing.Space4
                    ),

            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                ),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier =
                    Modifier
                        .size(
                            BBIcon.BoxMd
                        )
                        .clip(
                            BBRadius.PillShape
                        )
                        .background(
                            MaterialTheme.colorScheme.primary
                        ),

                contentAlignment =
                    Alignment.Center
            ) {

                Icon(
                    imageVector =
                        Icons.Outlined.Lock,

                    contentDescription =
                        null,

                    tint =
                        MaterialTheme.colorScheme.onPrimary,

                    modifier =
                        Modifier.size(
                            BBIcon.Action
                        )
                )
            }


            Column(
                modifier =
                    Modifier.weight(1f),

                verticalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space1
                    )
            ) {

                Text(
                    text =
                        "Güvenli ödeme",

                    style =
                        MaterialTheme.typography.titleSmall,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        MaterialTheme.colorScheme.inverseOnSurface
                )


                Text(
                    text =
                        "Ödeme işlemi güvenli ödeme altyapısı üzerinden gerçekleştirilir.",

                    style =
                        MaterialTheme.typography.bodySmall,

                    color =
                        MaterialTheme.colorScheme.inverseOnSurface.copy(
                            alpha = 0.78f
                        )
                )
            }
        }
    }
}


/*
 * ============================================================================
 * FOOTER
 *
 * Artık garip sarı dev Surface yok.
 * Sadece normal footer + brand primary button.
 * ============================================================================
 */

@Composable
private fun CheckoutBottomBar(
    totalPriceText: String,
    canContinue: Boolean,
    summaryExpanded: Boolean,
    onSummaryClick: () -> Unit,
    onContinueClick: () -> Unit
) {

    Surface(
        color =
            MaterialTheme.colorScheme.surface,

        tonalElevation =
            BBSpacing.Space1,

        shadowElevation =
            BBSpacing.Space3
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
        ) {

            HorizontalDivider(
                color =
                    MaterialTheme.colorScheme.outlineVariant
            )


            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal =
                                BBSpacing.PageHorizontal,

                            vertical =
                                BBSpacing.Space3
                        ),

                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space3
                    ),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Row(
                    modifier =
                        Modifier
                            .weight(1f)
                            .clip(
                                BBRadius.Button
                            )
                            .clickable {
                                onSummaryClick()
                            }
                            .padding(
                                horizontal =
                                    BBSpacing.Space2,

                                vertical =
                                    BBSpacing.Space2
                            ),

                    verticalAlignment =
                        Alignment.CenterVertically,

                    horizontalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.Space2
                        )
                ) {

                    Column(
                        modifier =
                            Modifier.weight(1f)
                    ) {

                        Text(
                            text =
                                BBLocalization.Current.Get(
                                    key =
                                        "e736c25f-c944-4f52-a206-819f93d64a29",

                                    fallback =
                                        "Toplam"
                                ),

                            style =
                                MaterialTheme.typography.labelSmall,

                            color =
                                MaterialTheme.colorScheme.onSurfaceVariant
                        )


                        Text(
                            text =
                                totalPriceText.ifBlank {
                                    "—"
                                },

                            style =
                                MaterialTheme.typography.titleMedium,

                            fontWeight =
                                FontWeight.Bold,

                            color =
                                MaterialTheme.colorScheme.onSurface
                        )
                    }


                    Icon(
                        imageVector =
                            if (summaryExpanded) {
                                Icons.Outlined.KeyboardArrowDown
                            } else {
                                Icons.Outlined.KeyboardArrowUp
                            },

                        contentDescription =
                            null,

                        tint =
                            MaterialTheme.colorScheme.primary,

                        modifier =
                            Modifier.size(
                                BBIcon.Action
                            )
                    )
                }


                BbButton(
                    text =
                        "Devam Et",

                    onClick =
                        onContinueClick,

                    modifier =
                        Modifier.weight(
                            1.35f
                        ),

                    variant =
                        BbButtonVariant.Primary,

                    size =
                        BbButtonSize.Medium,

                    enabled =
                        canContinue
                )
            }
        }
    }
}


/*
 * ============================================================================
 * SUMMARY OVERLAY
 *
 * Trendyol mantığı:
 *
 *   summary white panel
 *   -------------------
 *   footer fixed bottom
 * ============================================================================
 */

@Composable
private fun CheckoutOrderSummaryOverlay(
    summary: CheckoutPriceSummary
) {

    Surface(
        modifier =
            Modifier.fillMaxWidth(),

        color =
            MaterialTheme.colorScheme.surface,

        shape =
            MaterialTheme.shapes.extraLarge,

        shadowElevation =
            BBSpacing.Space4
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        start =
                            BBSpacing.PageHorizontal,

                        top =
                            BBSpacing.Space4,

                        end =
                            BBSpacing.PageHorizontal,

                        bottom =
                            BBSpacing.Space4
                    ),

            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                )
        ) {

            Text(
                text =
                    "Sepet Özeti",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight =
                    FontWeight.Bold
            )


            CheckoutSummaryContent(
                summary =
                    summary
            )
        }
    }
}


/*
 * ============================================================================
 * SUMMARY CONTENT
 * ============================================================================
 */

@Composable
private fun CheckoutSummaryContent(
    summary: CheckoutPriceSummary,
    modifier: Modifier = Modifier
) {

    Column(
        modifier =
            modifier.fillMaxWidth(),

        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            )
    ) {

        CheckoutSummaryRow(
            title =
                "Ürün Toplamı",

            value =
                summary.productTotalText
        )


        CheckoutSummaryRow(
            title =
                "Kargo",

            value =
                summary.cargoTotalText
        )


        if (
            summary.discountTotalText
                .isNotBlank()
        ) {

            CheckoutSummaryRow(
                title =
                    "İndirim",

                value =
                    summary.discountTotalText
            )
        }


        if (
            summary.couponTotalText
                .isNotBlank()
        ) {

            CheckoutSummaryRow(
                title =
                    "Kupon",

                value =
                    summary.couponTotalText
            )
        }


        if (
            summary.installmentFeeText
                .isNotBlank()
        ) {

            CheckoutSummaryRow(
                title =
                    "Taksit Farkı",

                value =
                    summary.installmentFeeText
            )
        }


        if (
            summary.commissionTotalText
                .isNotBlank()
        ) {

            CheckoutSummaryRow(
                title =
                    "Komisyon",

                value =
                    summary.commissionTotalText
            )
        }


        HorizontalDivider(
            color =
                MaterialTheme.colorScheme.outlineVariant
        )


        CheckoutSummaryRow(
            title =
                "Ödenecek Tutar",

            value =
                summary.payableTotalText,

            strong =
                true
        )
    }
}


@Composable
private fun CheckoutSummaryRow(
    title: String,
    value: String,
    strong: Boolean = false
) {

    Row(
        modifier =
            Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            ),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text =
                title,

            modifier =
                Modifier.weight(1f),

            style =
                if (strong) {
                    MaterialTheme.typography.titleSmall
                } else {
                    MaterialTheme.typography.bodyMedium
                },

            fontWeight =
                if (strong) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                },

            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )


        Text(
            text =
                value.ifBlank {
                    "—"
                },

            style =
                if (strong) {
                    MaterialTheme.typography.titleMedium
                } else {
                    MaterialTheme.typography.bodyMedium
                },

            fontWeight =
                FontWeight.Bold,

            color =
                if (strong) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
        )
    }
}


/*
 * ============================================================================
 * SIMPLE ADDRESS SHEET
 *
 * KART YOK.
 * KUTU YOK.
 * SADECE SATIR.
 * ============================================================================
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckoutAddressSelectionSheet(
    title: String,
    addresses: List<MemberAddressDTO>,
    selectedAddressId: Int,
    onAddressSelected: (MemberAddressDTO) -> Unit,
    onAddAddressClick: () -> Unit,
    onDismiss: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest =
            onDismiss,

        containerColor =
            MaterialTheme.colorScheme.surface
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(
                        bottom =
                            BBSpacing.PageBottomCompact
                    )
        ) {

            Text(
                text =
                    title,

                modifier =
                    Modifier.padding(
                        horizontal =
                            BBSpacing.PageHorizontal,

                        vertical =
                            BBSpacing.Space2
                    ),

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight =
                    FontWeight.Bold
            )


            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space2
                    )
            )


            if (addresses.isEmpty()) {

                Text(
                    text =
                        "Kayıtlı adres bulunamadı.",

                    modifier =
                        Modifier.padding(
                            horizontal =
                                BBSpacing.PageHorizontal,

                            vertical =
                                BBSpacing.Space4
                        ),

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

            } else {

                addresses.forEachIndexed {
                        index,
                        address ->

                    CheckoutAddressSelectionRow(
                        address = address,
                        selected =
                            address.MemberAddressId ==
                                    selectedAddressId,
                        onSelected = {
                            onAddressSelected(address)
                        }
                    )


                    if (
                        index !=
                        addresses.lastIndex
                    ) {

                        HorizontalDivider(
                            modifier =
                                Modifier.padding(
                                    horizontal =
                                        BBSpacing.PageHorizontal
                                ),

                            color =
                                MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier =
                    Modifier.padding(
                        top =
                            BBSpacing.Space2
                    ),

                color =
                    MaterialTheme.colorScheme.outlineVariant
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.Space3,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.Space2
                    )
            ) {

                BbButton(
                    text = "Yeni Adres Ekle",
                    onClick = onAddAddressClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium
                )
            }
        }
    }
}

@Composable
private fun CheckoutAddressSelectionRow(
    address: MemberAddressDTO,
    selected: Boolean,
    onSelected: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelected()
            }
            .padding(
                horizontal = BBSpacing.PageHorizontal,
                vertical = BBSpacing.Space3
            ),
        horizontalArrangement = Arrangement.spacedBy(
            BBSpacing.Space3
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = onSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary
            )
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {

            Text(
                text = address.AddressTitle.ifBlank {
                    "Adres"
                },
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = address.Address,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )

            if (address.Phone.isNotBlank()) {

                Text(
                    text = address.Phone,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Text(
            text = if (selected) {
                "Seçili"
            } else {
                "Seç"
            },
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun CheckoutProductImageStrip(
    basketItems: List<BasketDTO>
) {

    Surface(
        modifier =
            Modifier.fillMaxWidth(),

        shape =
            MaterialTheme.shapes.large,

        color =
            MaterialTheme.colorScheme.surface,

        border =
            BorderStroke(
                width =
                    1.dp,

                color =
                    MaterialTheme.colorScheme.outlineVariant
            )
    ) {

        if (basketItems.isEmpty()) {

            Text(
                text =
                    "Sepet ürünleri yükleniyor...",

                modifier =
                    Modifier.padding(
                        BBSpacing.Space3
                    ),

                style =
                    MaterialTheme.typography.bodySmall,

                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

        } else {

            LazyRow(
                modifier =
                    Modifier.fillMaxWidth(),

                contentPadding =
                    PaddingValues(
                        horizontal =
                            BBSpacing.Space3,

                        vertical =
                            BBSpacing.Space3
                    ),

                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
            ) {

                items(
                    count =
                        basketItems.size,

                    key = {
                            index ->

                        basketItems[index].BasketId
                    }
                ) {
                        index ->

                    val basket =
                        basketItems[index]

                    val imageUrl =
                        ImageUrlResolver.Resolve(
                            imagePath =
                                basket.DefaultPicture
                                    .ifBlank {
                                        basket.Picture
                                    }
                        )


                    Surface(
                        modifier =
                            Modifier.size(
                                72.dp
                            ),

                        shape =
                            MaterialTheme.shapes.medium,

                        color =
                            MaterialTheme.colorScheme
                                .surfaceContainerLow
                    ) {

                        AsyncImage(
                            model =
                                imageUrl,

                            contentDescription =
                                basket.ProductName,

                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .padding(
                                        BBSpacing.Space1
                                    ),

                            contentScale =
                                ContentScale.Fit
                        )
                    }
                }
            }
        }
    }
}

/*
 * ============================================================================
 * REAL BASKET PRODUCTS SHEET
 * ============================================================================
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckoutProductsSheet(
    basketItems: List<BasketDTO>,
    onDismiss: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest =
            onDismiss,

        containerColor =
            MaterialTheme.colorScheme.surface
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(
                        bottom =
                            BBSpacing.PageBottomCompact
                    )
        ) {

            Text(
                text =
                    "Sepetteki Ürünler (${basketItems.sumOf { it.Quantity }})",

                modifier =
                    Modifier.padding(
                        horizontal =
                            BBSpacing.PageHorizontal
                    ),

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight =
                    FontWeight.Bold
            )


            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )


            if (basketItems.isEmpty()) {

                Text(
                    text =
                        "Sepette ürün bulunamadı.",

                    modifier =
                        Modifier.padding(
                            horizontal =
                                BBSpacing.PageHorizontal,

                            vertical =
                                BBSpacing.Space4
                        ),

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

            } else {

                LazyColumn(
                    modifier =
                        Modifier.heightIn(
                            max = 560.dp
                        )
                ) {

                    itemsIndexed(
                        items =
                            basketItems,

                        key = {
                                _,
                                item ->

                            item.BasketId
                        }
                    ) {
                            index,
                            item ->

                        CheckoutBasketProductRow(
                            item =
                                item
                        )


                        if (
                            index !=
                            basketItems.lastIndex
                        ) {

                            HorizontalDivider(
                                modifier =
                                    Modifier.padding(
                                        horizontal =
                                            BBSpacing.PageHorizontal
                                    ),

                                color =
                                    MaterialTheme.colorScheme.outlineVariant
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun CheckoutBasketProductRow(
    item: BasketDTO
) {

    val unitPrice =
        item.UnitPrice.takeIf {
            it > 0.0
        }
            ?: if (item.Quantity > 0) {
                item.TotalPrice /
                        item.Quantity
            } else {
                item.TotalPrice
            }


    val currencySymbol =
        item.CurrencySymbol
            .takeIf {
                it.isNotBlank()
            }
            ?: "₺"


    val variantText =
        listOfNotNull(
            item.Color.takeIf {
                it.isNotBlank()
            },

            item.Size.takeIf {
                it.isNotBlank()
            }
        )
            .joinToString(
                separator = " · "
            )


    val imageUrl =
        ImageUrlResolver.Resolve(
            imagePath =
                item.DefaultPicture
                    .ifBlank {
                        item.Picture
                    }
        )


    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal =
                        BBSpacing.PageHorizontal,

                    vertical =
                        BBSpacing.Space3
                ),

        horizontalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            ),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Surface(
            modifier =
                Modifier.size(
                    68.dp
                ),

            shape =
                MaterialTheme.shapes.medium,

            color =
                MaterialTheme.colorScheme.surfaceContainerLow
        ) {

            if (imageUrl.isNotBlank()) {

                AsyncImage(
                    model =
                        imageUrl,

                    contentDescription =
                        item.ProductName,

                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(
                                BBSpacing.Space1
                            ),

                    contentScale =
                        ContentScale.Fit
                )

            } else {

                Box(
                    modifier =
                        Modifier.fillMaxSize(),

                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text =
                            item.ProductName
                                .trim()
                                .take(1)
                                .uppercase()
                                .ifBlank {
                                    "Ü"
                                },

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }
        }


        Column(
            modifier =
                Modifier.weight(1f),

            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space1
                )
        ) {

            Text(
                text =
                    item.ProductName,

                style =
                    MaterialTheme.typography.bodyMedium,

                fontWeight =
                    FontWeight.SemiBold,

                maxLines =
                    2
            )


            if (variantText.isNotBlank()) {

                Text(
                    text =
                        variantText,

                    style =
                        MaterialTheme.typography.bodySmall,

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }


            if (item.Store.isNotBlank()) {

                Text(
                    text =
                        item.Store,

                    style =
                        MaterialTheme.typography.labelSmall,

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }


            Text(
                text =
                    "Adet: ${item.Quantity}",

                style =
                    MaterialTheme.typography.labelMedium,

                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }


        Text(
            text =
                "$currencySymbol${
                    String
                        .format(
                            "%.2f",
                            unitPrice
                        )
                        .replace(
                            ".",
                            ","
                        )
                }",

            style =
                MaterialTheme.typography.titleSmall,

            fontWeight =
                FontWeight.Bold
        )
    }
}


/*
 * ============================================================================
 * REAL MEMBER COUPON SHEET
 * ============================================================================
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckoutCouponSheet(
    coupons: List<MemberCouponDTO>,
    selectedCoupon: MemberCouponDTO?,
    isLoading: Boolean,
    errorMessage: String?,
    onCouponSelected: (MemberCouponDTO) -> Unit,
    onCouponCodeApply: (String) -> Unit,
    onDismiss: () -> Unit
) {

    var couponCode by rememberSaveable {
        mutableStateOf("")
    }


    val availableCoupons =
        remember(coupons) {
            coupons.filter {
                    coupon ->
                coupon.IsCheckoutAvailable()
            }
        }


    ModalBottomSheet(
        onDismissRequest =
            onDismiss,

        containerColor =
            MaterialTheme.colorScheme.surface
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(
                        start =
                            BBSpacing.PageHorizontal,

                        end =
                            BBSpacing.PageHorizontal,

                        bottom =
                            BBSpacing.PageBottomCompact
                    ),

            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                )
        ) {

            Text(
                text =
                    "Kupon ve İndirimler",

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight =
                    FontWeight.Bold
            )


            OutlinedTextField(
                value =
                    couponCode,

                onValueChange = {
                    couponCode = it
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text(
                        text =
                            "İndirim kodu"
                    )
                },

                singleLine =
                    true,

                shape =
                    BBRadius.Input
            )


            BbButton(
                text =
                    "Kuponu Uygula",

                onClick = {
                    onCouponCodeApply(
                        couponCode.trim()
                    )
                },

                modifier =
                    Modifier.fillMaxWidth(),

                variant =
                    BbButtonVariant.Primary,

                size =
                    BbButtonSize.Medium,

                enabled =
                    couponCode.isNotBlank()
            )


            HorizontalDivider(
                color =
                    MaterialTheme.colorScheme.outlineVariant
            )


            Text(
                text =
                    "Hesabına Tanımlı Kuponlar",

                style =
                    MaterialTheme.typography.titleSmall,

                fontWeight =
                    FontWeight.Bold
            )


            when {

                isLoading -> {

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(
                                    96.dp
                                ),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        CircularProgressIndicator(
                            color =
                                MaterialTheme.colorScheme.primary
                        )
                    }
                }


                !errorMessage.isNullOrBlank() -> {

                    Text(
                        text =
                            errorMessage,

                        color =
                            MaterialTheme.colorScheme.error
                    )
                }


                availableCoupons.isEmpty() -> {

                    Text(
                        text =
                            "Kullanılabilir kupon bulunamadı.",

                        style =
                            MaterialTheme.typography.bodyMedium,

                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }


                else -> {

                    availableCoupons.forEachIndexed {
                            index,
                            coupon ->

                        CheckoutCouponRow(
                            coupon =
                                coupon,

                            selected =
                                selectedCoupon
                                    ?.MemberCouponId ==
                                        coupon.MemberCouponId,

                            onClick = {
                                onCouponSelected(
                                    coupon
                                )
                            }
                        )


                        if (
                            index !=
                            availableCoupons.lastIndex
                        ) {

                            HorizontalDivider(
                                color =
                                    MaterialTheme.colorScheme.outlineVariant
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun CheckoutCouponRow(
    coupon: MemberCouponDTO,
    selected: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .padding(
                    vertical =
                        BBSpacing.Space3
                ),

        horizontalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            ),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Icon(
            imageVector =
                Icons.Outlined.ConfirmationNumber,

            contentDescription =
                null,

            tint =
                MaterialTheme.colorScheme.primary,

            modifier =
                Modifier.size(
                    BBIcon.Action
                )
        )


        Column(
            modifier =
                Modifier.weight(1f),

            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space1
                )
        ) {

            Text(
                text =
                    coupon.CouponCode
                        .ifBlank {
                            "Kupon"
                        },

                style =
                    MaterialTheme.typography.titleSmall,

                fontWeight =
                    FontWeight.Bold
            )


            if (
                coupon.Descripion
                    .isNotBlank()
            ) {

                Text(
                    text =
                        coupon.Descripion,

                    style =
                        MaterialTheme.typography.bodySmall,

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )
            }


            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space2
                    )
            ) {

                if (coupon.Amount > 0.0) {

                    Text(
                        text =
                            "${FormatTurkishMoney(coupon.Amount)} indirim",

                        style =
                            MaterialTheme.typography.labelMedium,

                        fontWeight =
                            FontWeight.Bold,

                        color =
                            MaterialTheme.colorScheme.primary
                    )
                }


                if (coupon.UpAmount > 0.0) {

                    Text(
                        text =
                            "Min. ${FormatTurkishMoney(coupon.UpAmount)}",

                        style =
                            MaterialTheme.typography.labelSmall,

                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }


        RadioButton(
            selected =
                selected,

            onClick =
                onClick,

            colors =
                RadioButtonDefaults.colors(
                    selectedColor =
                        MaterialTheme.colorScheme.primary
                )
        )
    }
}


/*
 * ============================================================================
 * LEGAL HTML SHEET
 *
 * Web checkout backend'in ürettiği HTML burada render edilir.
 * Android kendi yasal metnini üretmez.
 * ============================================================================
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckoutLegalSheet(
    title: String,
    htmlContent: String,
    loading: Boolean,
    emptyText: String,
    snapshot: CheckoutContractSnapshot,
    onDismiss: () -> Unit
) {

    val onSurfaceColor =
        MaterialTheme.colorScheme.onSurface

    val primaryColor =
        MaterialTheme.colorScheme.primary


    ModalBottomSheet(
        onDismissRequest =
            onDismiss,

        containerColor =
            MaterialTheme.colorScheme.surface
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(
                        start =
                            BBSpacing.PageHorizontal,

                        end =
                            BBSpacing.PageHorizontal,

                        bottom =
                            BBSpacing.PageBottomCompact
                    )
        ) {

            Text(
                text =
                    title,

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight =
                    FontWeight.Bold
            )


            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )


            CheckoutContractSnapshotView(
                snapshot =
                    snapshot
            )


            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )


            HorizontalDivider(
                color =
                    MaterialTheme.colorScheme.outlineVariant
            )


            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )


            when {

                loading -> {

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(
                                    180.dp
                                ),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        CircularProgressIndicator(
                            color =
                                MaterialTheme.colorScheme.primary
                        )
                    }
                }


                htmlContent.isBlank() -> {

                    Text(
                        text =
                            emptyText,

                        style =
                            MaterialTheme.typography.bodyMedium,

                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }


                else -> {

                    /*
                     * MinSdk 34 olduğu için platform Html.fromHtml
                     * doğrudan yeterli.
                     *
                     * JS / WebView yok.
                     * Backend'in oluşturduğu trusted HTML'in
                     * semantic metin kısmını gösteriyoruz.
                     */

                    Column(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .heightIn(
                                    max =
                                        540.dp
                                )
                                .verticalScroll(
                                    rememberScrollState()
                                )
                    ) {

                        AndroidView(
                            modifier =
                                Modifier.fillMaxWidth(),

                            factory = {
                                    context ->

                                TextView(context).apply {

                                    textSize =
                                        15f

                                    setLineSpacing(
                                        0f,
                                        1.18f
                                    )

                                    setTextColor(
                                        onSurfaceColor.toArgb()
                                    )

                                    setLinkTextColor(
                                        primaryColor.toArgb()
                                    )

                                    movementMethod =
                                        LinkMovementMethod.getInstance()
                                }
                            },

                            update = {
                                    textView ->

                                textView.text =
                                    Html.fromHtml(
                                        htmlContent,
                                        Html.FROM_HTML_MODE_LEGACY
                                    )
                            }
                        )
                    }
                }
            }


            Spacer(
                modifier =
                    Modifier.height(
                        BBSpacing.Space3
                    )
            )
        }
    }
}


/*
 * ============================================================================
 * CONTRACT PAYMENT SNAPSHOT
 *
 * Web ContractPaymentModel ile aynı kavramları
 * UI'da özet olarak gösteriyoruz.
 * ============================================================================
 */

@Composable
private fun CheckoutContractSnapshotView(
    snapshot: CheckoutContractSnapshot
) {

    Column(
        modifier =
            Modifier.fillMaxWidth(),

        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {

        if (
            snapshot.deliveryAddress
                .isNotBlank()
        ) {

            CheckoutContractRow(
                title =
                    "Teslimat Adresi",

                value =
                    snapshot.deliveryAddress
            )
        }


        if (
            snapshot.invoiceAddress
                .isNotBlank()
        ) {

            CheckoutContractRow(
                title =
                    "Fatura Adresi",

                value =
                    snapshot.invoiceAddress
            )
        }


        CheckoutContractRow(
            title =
                "Kargo",

            value =
                snapshot.shippingCost
                    .ifBlank {
                        "—"
                    }
        )


        if (
            snapshot.installment
                .isNotBlank()
        ) {

            CheckoutContractRow(
                title =
                    "Taksit",

                value =
                    listOf(
                        snapshot.installment,
                        snapshot.installmentDetail
                    )
                        .filter {
                            it.isNotBlank()
                        }
                        .joinToString(
                            separator =
                                " · "
                        )
            )
        }


        if (
            snapshot.providerFee
                .isNotBlank()
        ) {

            CheckoutContractRow(
                title =
                    "Provider / Taksit Farkı",

                value =
                    snapshot.providerFee
            )
        }


        CheckoutContractRow(
            title =
                "Nihai Tutar",

            value =
                snapshot.grandTotal
                    .ifBlank {
                        "—"
                    },

            strong =
                true
        )
    }
}


@Composable
private fun CheckoutContractRow(
    title: String,
    value: String,
    strong: Boolean = false
) {

    Row(
        modifier =
            Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            )
    ) {

        Text(
            text =
                title,

            modifier =
                Modifier.weight(0.36f),

            style =
                MaterialTheme.typography.bodySmall,

            color =
                MaterialTheme.colorScheme.onSurfaceVariant
        )


        Text(
            text =
                value,

            modifier =
                Modifier.weight(0.64f),

            style =
                MaterialTheme.typography.bodySmall,

            fontWeight =
                if (strong) {
                    FontWeight.Bold
                } else {
                    FontWeight.Medium
                },

            color =
                MaterialTheme.colorScheme.onSurface
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckoutInvoiceInfoSheet(
    companyName: String,
    taxOffice: String,
    taxNumber: String,
    onSave: (String, String, String) -> Unit,
    onDismiss: () -> Unit
) {

    var companyNameValue by rememberSaveable(companyName) {
        mutableStateOf(companyName)
    }

    var taxOfficeValue by rememberSaveable(taxOffice) {
        mutableStateOf(taxOffice)
    }

    var taxNumberValue by rememberSaveable(taxNumber) {
        mutableStateOf(taxNumber)
    }

    val isEdit =
        companyName.isNotBlank() ||
            taxOffice.isNotBlank() ||
            taxNumber.isNotBlank()

    val canSave =
        companyNameValue.isNotBlank() &&
            taxOfficeValue.isNotBlank() &&
            taxNumberValue.isNotBlank()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = BBSpacing.PageHorizontal,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottomCompact
                    ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                )
        ) {

            Text(
                text = "Fatura Bilgileri",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Kurumsal fatura istiyorsanız şirket bilgilerinizi girin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = companyNameValue,
                onValueChange = {
                    companyNameValue = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Şirket / Ticari Unvan")
                },
                singleLine = true
            )

            OutlinedTextField(
                value = taxOfficeValue,
                onValueChange = {
                    taxOfficeValue = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Vergi Dairesi")
                },
                singleLine = true
            )

            OutlinedTextField(
                value = taxNumberValue,
                onValueChange = {
                    taxNumberValue = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Vergi Numarası")
                },
                singleLine = true
            )

            BbButton(
                text =
                    if (isEdit) {
                        "Güncelle"
                    } else {
                        "Ekle"
                    },
                onClick = {
                    onSave(
                        companyNameValue.trim(),
                        taxOfficeValue.trim(),
                        taxNumberValue.trim()
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = canSave
            )
        }
    }
}


/*
 * ============================================================================
 * PLAIN GENERIC SELECTION SHEET
 *
 * Kart ordusu YOK.
 * ============================================================================
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckoutPlainSelectionSheet(
    title: String,
    items: List<CheckoutSelectionDisplay>,
    selected: CheckoutSelectionDisplay?,
    emptyText: String,
    onSelected: (CheckoutSelectionDisplay) -> Unit,
    onDismiss: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest =
            onDismiss,

        containerColor =
            MaterialTheme.colorScheme.surface
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(
                        bottom =
                            BBSpacing.PageBottomCompact
                    )
        ) {

            Text(
                text =
                    title,

                modifier =
                    Modifier.padding(
                        horizontal =
                            BBSpacing.PageHorizontal,

                        vertical =
                            BBSpacing.Space2
                    ),

                style =
                    MaterialTheme.typography.titleLarge,

                fontWeight =
                    FontWeight.Bold
            )


            if (items.isEmpty()) {

                Text(
                    text =
                        emptyText,

                    modifier =
                        Modifier.padding(
                            horizontal =
                                BBSpacing.PageHorizontal,

                            vertical =
                                BBSpacing.Space4
                        ),

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

            } else {

                items.forEachIndexed {
                        index,
                        item ->

                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelected(
                                        item
                                    )
                                }
                                .padding(
                                    horizontal =
                                        BBSpacing.PageHorizontal,

                                    vertical =
                                        BBSpacing.Space3
                                ),

                        horizontalArrangement =
                            Arrangement.spacedBy(
                                BBSpacing.Space3
                            ),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        RadioButton(
                            selected =
                                selected
                                    ?.title ==
                                        item.title,

                            onClick = {
                                onSelected(
                                    item
                                )
                            },

                            colors =
                                RadioButtonDefaults.colors(
                                    selectedColor =
                                        MaterialTheme.colorScheme.primary
                                )
                        )


                        Column(
                            modifier =
                                Modifier.weight(1f)
                        ) {

                            Text(
                                text =
                                    item.title,

                                style =
                                    MaterialTheme.typography.titleSmall,

                                fontWeight =
                                    FontWeight.SemiBold
                            )


                            if (
                                item.description
                                    .isNotBlank()
                            ) {

                                Text(
                                    text =
                                        item.description,

                                    style =
                                        MaterialTheme.typography.bodySmall,

                                    color =
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }


                    if (
                        index !=
                        items.lastIndex
                    ) {

                        HorizontalDivider(
                            modifier =
                                Modifier.padding(
                                    horizontal =
                                        BBSpacing.PageHorizontal
                                ),

                            color =
                                MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }
        }
    }
}


/*
 * ============================================================================
 * HELPERS
 * ============================================================================
 */

private fun MemberCouponDTO.IsCheckoutAvailable(): Boolean {

    if (
        Used == 1 ||
        OrderId.isNotBlank()
    ) {
        return false
    }


    val lastUsingDate =
        LastUsingDate.ToCouponLocalDate()


    if (
        lastUsingDate != null &&
        lastUsingDate.isBefore(
            LocalDate.now()
        )
    ) {
        return false
    }


    return true
}


private fun String.ToCouponLocalDate(): LocalDate? {

    val value =
        trim()


    if (
        value.isBlank() ||
        value.startsWith(
            "0001-01-01"
        ) ||
        value.startsWith(
            "1.01.0001"
        )
    ) {
        return null
    }


    return runCatching {

        OffsetDateTime
            .parse(value)
            .toLocalDate()

    }.getOrElse {

        runCatching {

            LocalDateTime
                .parse(value)
                .toLocalDate()

        }.getOrElse {

            runCatching {

                LocalDate.parse(
                    value.substringBefore(
                        "T"
                    )
                )

            }.getOrNull()
        }
    }
}


private fun FormatTurkishMoney(
    value: Double
): String {

    return "₺${
        String
            .format(
                "%.2f",
                value
            )
            .replace(
                ".",
                ","
            )
    }"
}


/*
 * ============================================================================
 * INTERNAL TYPES
 * ============================================================================
 */

private enum class CheckoutLegalSheetType {

    PreInformation,

    DistanceSales,

    WithdrawalRight
}


private enum class CheckoutSelectionSheetType {

    InvoiceInfo,

    PaymentMethod,

    Card,

    Installment
}


/*
 * ============================================================================
 * DATA
 * ============================================================================
 */

data class CheckoutProductDisplay(
    val title: String = "",
    val variantText: String = "",
    val quantity: Int = 1,
    val priceText: String = ""
)

data class CheckoutScreenData(

    /*
     * MEMBER ADDRESS
     */

    val addresses: List<MemberAddressDTO> =
        emptyList(),

    val selectedDeliveryAddressId: Int =
        0,

    val selectedInvoiceAddressId: Int =
        0,

    val deliveryAddress: CheckoutSelectionDisplay? =
        null,

    val invoiceAddress: CheckoutSelectionDisplay? =
        null,


    /*
     * BASKET
     */

    val basketItemCount: Int =
        0,

    val basketItems: List<BasketDTO> =
        emptyList(),

    val products: List<CheckoutProductDisplay> =
        emptyList(),

    /*
     * INVOICE
     */

    val invoiceType: CheckoutSelectionDisplay? =
        null,

    val invoiceTypeOptions: List<CheckoutSelectionDisplay> =
        emptyList(),


    /*
     * PAYMENT
     */

    val paymentMethod: CheckoutSelectionDisplay? =
        null,

    val paymentMethodOptions: List<CheckoutSelectionDisplay> =
        emptyList(),

    val card: CheckoutSelectionDisplay? =
        null,

    val cardOptions: List<CheckoutSelectionDisplay> =
        emptyList(),

    val installment: CheckoutSelectionDisplay? =
        null,

    val installmentOptions: List<CheckoutSelectionDisplay> =
        emptyList(),


    /*
     * REAL MEMBER COUPONS
     */

    val memberCoupons: List<MemberCouponDTO> =
        emptyList(),

    val selectedCoupon: MemberCouponDTO? =
        null,

    val isCouponLoading: Boolean =
        false,

    val couponErrorMessage: String? =
        null,


    /*
     * CONTRACT HTML
     *
     * Bunlar webdeki:
     *
     * _createPreInformationForm
     * _createDistanceSellingContract
     *
     * servislerinin API çıktısı olacak.
     */

    val preInformationHtml: String =
        "",

    val distanceSellingHtml: String =
        "",

    val withdrawalRightHtml: String =
        "",

    val isContractLoading: Boolean =
        false,


    /*
     * TOTAL
     */

    val summary: CheckoutPriceSummary =
        CheckoutPriceSummary(),

    val canContinue: Boolean =
        false
)


data class CheckoutSelectionDisplay(

    val title: String =
        "",

    val description: String =
        ""
)


data class CheckoutPriceSummary(

    val productTotalText: String =
        "",

    val cargoTotalText: String =
        "",

    val discountTotalText: String =
        "",

    val couponTotalText: String =
        "",

    val installmentFeeText: String =
        "",

    val commissionTotalText: String =
        "",

    val payableTotalText: String =
        ""
)


data class CheckoutContractSnapshot(

    val deliveryAddress: String =
        "",

    val invoiceAddress: String =
        "",

    val shippingCost: String =
        "",

    val grandTotal: String =
        "",

    val installment: String =
        "",

    val installmentDetail: String =
        "",

    val providerFee: String =
        ""
)


/*
 * ============================================================================
 * PREVIEW
 * ============================================================================
 */

@Preview(
    showBackground = true
)
@Composable
private fun CheckoutScreenPreview() {

    BbTheme {

        CheckoutScreen(
            data =
                CheckoutScreenData(

                    addresses =
                        listOf(
                            MemberAddressDTO(
                                MemberAddressId =
                                    1,

                                AddressTitle =
                                    "Ev Adresim",

                                Name =
                                    "Can",

                                Surname =
                                    "Yılmaz",

                                Address =
                                    "Fulya Mah. Aytekin Kotil Cad. No: 11/1",

                                Phone = "0532 123 45 67",

                                IsDefault =
                                    true
                            )
                        ),

                    selectedDeliveryAddressId = 1,

                    basketItemCount = 4,

                    deliveryAddress =
                        CheckoutSelectionDisplay(
                            title =
                                "Ev Adresim",

                            description =
                                "Fulya Mah. Aytekin Kotil Cad. No: 11/1"
                        ),

                    invoiceType =
                        CheckoutSelectionDisplay(
                            title =
                                "Bireysel",

                            description =
                                "Bireysel fatura"
                        ),

                    invoiceTypeOptions =
                        listOf(
                            CheckoutSelectionDisplay(
                                title =
                                    "Bireysel",

                                description =
                                    "Bireysel fatura"
                            ),

                            CheckoutSelectionDisplay(
                                title =
                                    "Kurumsal",

                                description =
                                    "Şirket adına fatura"
                            )
                        ),

                    paymentMethod =
                        CheckoutSelectionDisplay(
                            title =
                                "Banka / Kredi Kartı",

                            description =
                                "Kart ile güvenli ödeme"
                        ),

                    paymentMethodOptions =
                        listOf(
                            CheckoutSelectionDisplay(
                                title =
                                    "Banka / Kredi Kartı",

                                description =
                                    "Kart ile güvenli ödeme"
                            )
                        ),

                    installment =
                        CheckoutSelectionDisplay(
                            title =
                                "Tek Çekim",

                            description =
                                "Tek çekim"
                        ),

                    installmentOptions =
                        listOf(
                            CheckoutSelectionDisplay(
                                title =
                                    "Tek Çekim",

                                description =
                                    "₺15.454,54"
                            )
                        ),

                    summary =
                        CheckoutPriceSummary(
                            productTotalText =
                                "₺15.454,54",

                            cargoTotalText =
                                "₺0,00",

                            payableTotalText =
                                "₺15.454,54"
                        ),

                    canContinue =
                        true
                )
        )
    }
}