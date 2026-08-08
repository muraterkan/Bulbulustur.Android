package com.bulbulustur.android.Application.Areas.b2b.Views.Rfq

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCategorySearchSelectInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbSelectOption
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextInput
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbTextarea
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescColorDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaterialTypeDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescTradeTermDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescUnitDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestUpdateModel

@Composable
fun RfqEditScreen(
    model: BuyerRequestUpdateModel?,
    productCategories: List<ProductCategoryDTO>,
    units: List<SystemDescUnitDTO>,
    currencies: List<SystemDescCurrencyDTO>,
    colors: List<SystemDescColorDTO>,
    materialTypes: List<SystemDescMaterialTypeDTO>,
    paymentTerms: List<SystemDescPaymentTermDTO>,
    tradeTerms: List<SystemDescTradeTermDTO>,
    isLoading: Boolean = false,
    isSubmitting: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onCategorySearch: (String) -> Unit = {},
    onSaveClick: (BuyerRequestUpdateModel) -> Unit = {}
) {
    var initialized by remember(model?.BuyerRequestId) { mutableStateOf(false) }
    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var categoryId by remember { mutableStateOf("") }
    var purchaseQuantity by remember { mutableStateOf("") }
    var unitId by remember { mutableStateOf("") }
    var unitPrice by remember { mutableStateOf("") }
    var currencyId by remember { mutableStateOf("") }
    var colorId by remember { mutableStateOf("") }
    var materialTypeId by remember { mutableStateOf("") }
    var paymentTermId by remember { mutableStateOf("") }
    var tradeTermId by remember { mutableStateOf("") }
    var shippingTarget by remember { mutableStateOf("") }
    var validationMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(model?.BuyerRequestId) {
        val value = model ?: return@LaunchedEffect
        productName = value.ProductName
        productDescription = value.ProductDescription
        categoryId = value.CategoryId.toString()
        purchaseQuantity = value.PurchaseQuantity.toString()
        unitId = value.UnitId.toString()
        unitPrice = value.UnitPrice.toString()
        currencyId = value.CurrencyId.toString()
        colorId = value.ColorId.toString()
        materialTypeId = value.MaterialTypeId.toString()
        paymentTermId = value.PaymentTermId.toString()
        tradeTermId = value.TradeTermId.toString()
        shippingTarget = value.ShippingTarget
        initialized = true
    }

    val categoryOptions = productCategories.filter { it.ProductCategoryId > 0 && it.CategoryName.isNotBlank() }.sortedBy { it.CategoryName }.map { BbSelectOption(it.ProductCategoryId.toString(), it.CategoryName) }
    val unitOptions = units.filter { it.SystemDescUnitId > 0 && it.Content.isNotBlank() }.sortedBy { it.Sequence }.map { BbSelectOption(it.SystemDescUnitId.toString(), if (it.Symbol.isBlank()) it.Content else "${it.Content} (${it.Symbol})") }
    val currencyOptions = currencies.filter { it.SystemDescCurrencyId > 0 && it.Content.isNotBlank() }.map { BbSelectOption(it.SystemDescCurrencyId.toString(), if (it.IsoCode.isBlank()) it.Content else "${it.Content} - ${it.IsoCode}") }
    val colorOptions = colors.filter { it.SystemDescColorId > 0 && it.Content.isNotBlank() }.sortedBy { it.Content }.map { BbSelectOption(it.SystemDescColorId.toString(), it.Content) }
    val materialOptions = materialTypes.filter { it.SystemDescMaterialTypeId > 0 && it.Content.isNotBlank() }.sortedBy { it.Sorting }.map { BbSelectOption(it.SystemDescMaterialTypeId.toString(), it.Content) }
    val paymentOptions = paymentTerms.filter { it.SystemDescPaymentTermId > 0 && it.Content.isNotBlank() }.sortedBy { it.Content }.map { BbSelectOption(it.SystemDescPaymentTermId.toString(), it.Content) }
    val tradeOptions = tradeTerms.filter { it.SystemDescTradeTermId > 0 && it.Content.isNotBlank() }.sortedBy { it.Content }.map { BbSelectOption(it.SystemDescTradeTermId.toString(), it.Content) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = { BbInnerPageHeader(title = BBLocalization.Current.Get(key = "6a23f3ad-9109-471d-a670-7b5a40cf3cd9", fallback = "RFQ Düzenle"), onBackClick = onBackClick) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant).padding(innerPadding),
            contentPadding = PaddingValues(start = BBSpacing.PageHorizontal, top = BBSpacing.PageTopCompact, end = BBSpacing.PageHorizontal, bottom = BBSpacing.PageBottom),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            if (isLoading || !initialized) item { RfqEditMessageCard(BBLocalization.Current.Get(key = "b31264eb-9873-46b7-af43-1b1eeadde164", fallback = "RFQ bilgileri yükleniyor...")) }

            if (!errorMessage.isNullOrBlank()) {
                item {
                    RfqEditSection(BBLocalization.Current.Get(key = "30aee7b0-b131-4f63-8659-f78378ac20f3", fallback = "")) {
                        Text(errorMessage, color = MaterialTheme.colorScheme.error, style = BbTypography.bodySmall)
                        BbButton(text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"), onClick = onRetryClick, modifier = Modifier.fillMaxWidth(), variant = BbButtonVariant.Outline, size = BbButtonSize.Medium)
                    }
                }
            }

            if (initialized) {
                item {
                    RfqEditSection(BBLocalization.Current.Get(key = "90509413-3f80-4a57-b43b-21738dc74b50", fallback = "Ürün Bilgileri")) {
                        BbTextInput(value = productName, onValueChange = { productName = it; validationMessage = null }, label = BBLocalization.Current.Get(key = "6096bd2f-af02-449b-80ba-481a9f5ca31b", fallback = "Ürün Adı"), placeholder = BBLocalization.Current.Get(key = "6096bd2f-af02-449b-80ba-481a9f5ca31b", fallback = "Ürün adı"), enabled = !isSubmitting)
                        BbCategorySearchSelectInput(selectedValue = categoryId, onValueChange = { categoryId = it; validationMessage = null }, onSearchTextChange = onCategorySearch, options = categoryOptions, label = BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = ""), placeholder = BBLocalization.Current.Get(key = "4834b933-045e-4ad5-8a39-9fbfc5a2122a", fallback = ""), searchPlaceholder = "Kategori ara", maximumVisibleOptionCount = 50, enabled = !isSubmitting && categoryOptions.isNotEmpty())
                        BbTextarea(value = productDescription, onValueChange = { productDescription = it; validationMessage = null }, label = BBLocalization.Current.Get(key = "eb7e1e0a-57ec-49bf-9968-61f0e5b75e6c", fallback = "Ürün Açıklaması"), placeholder = BBLocalization.Current.Get(key = "70d6b07c-d82a-4e84-a776-1bfdfe9d7bba", fallback = "Ürün detayları"), enabled = !isSubmitting)
                    }
                }

                item {
                    RfqEditSection(BBLocalization.Current.Get(key = "bd347300-ac91-49d1-921b-ea7734a6be05", fallback = "Miktar ve Fiyat")) {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
                            RfqEditNumberInput(purchaseQuantity, { purchaseQuantity = it; validationMessage = null }, BBLocalization.Current.Get(key = "64f1e179-caee-4a60-9500-d35fbc4ed554", fallback = "Miktar"), Modifier.weight(1f), !isSubmitting)
                            Column(Modifier.weight(1f)) { BbSelectInput(selectedValue = unitId, onValueChange = { unitId = it; validationMessage = null }, options = unitOptions, label = BBLocalization.Current.Get(key = "8c9bc441-0d68-4f53-9549-179f61d7ece0", fallback = "Birim"), placeholder = BBLocalization.Current.Get(key = "723120da-c41c-4722-8827-f0bce1d29c34", fallback = "Birim seçiniz"), enabled = !isSubmitting && unitOptions.isNotEmpty()) }
                        }

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
                            RfqEditNumberInput(unitPrice, { unitPrice = it; validationMessage = null }, BBLocalization.Current.Get(key = "a3f9904a-f7e6-4ef6-a79b-5fbbcbb9dd22", fallback = "Birim Fiyat"), Modifier.weight(1f), !isSubmitting)
                            Column(Modifier.weight(1f)) { BbSelectInput(selectedValue = currencyId, onValueChange = { currencyId = it; validationMessage = null }, options = currencyOptions, label = BBLocalization.Current.Get(key = "47942374-ab80-47b3-af0f-c8a6aaf728e3", fallback = ""), placeholder = "Para birimi seçiniz", enabled = !isSubmitting && currencyOptions.isNotEmpty()) }
                        }
                    }
                }

                item {
                    RfqEditSection(BBLocalization.Current.Get(key = "d036fcbd-c864-40cf-8899-cbdae6a6b913", fallback = "Ürün Özellikleri")) {
                        BbSelectInput(selectedValue = colorId, onValueChange = { colorId = it; validationMessage = null }, options = colorOptions, label = "Renk", placeholder = BBLocalization.Current.Get(key = "435c95c7-5210-4f4d-9805-b555e7e43ba2", fallback = ""), enabled = !isSubmitting && colorOptions.isNotEmpty())
                        BbSelectInput(selectedValue = materialTypeId, onValueChange = { materialTypeId = it; validationMessage = null }, options = materialOptions, label = BBLocalization.Current.Get(key = "db735556-6dc6-4008-96df-387f8e444159", fallback = "Malzeme Türü"), placeholder = "Malzeme seçiniz", enabled = !isSubmitting && materialOptions.isNotEmpty())
                    }
                }

                item {
                    RfqEditSection(BBLocalization.Current.Get(key = "b57feb8b-30a7-4c26-a638-cce10d96c69d", fallback = "Ticari Koşullar")) {
                        BbSelectInput(selectedValue = paymentTermId, onValueChange = { paymentTermId = it; validationMessage = null }, options = paymentOptions, label = BBLocalization.Current.Get(key = "0ce51541-2adb-4cf7-91be-d1fcb7ffe88a", fallback = ""), placeholder = "Ödeme şartı seçiniz", enabled = !isSubmitting && paymentOptions.isNotEmpty())
                        BbSelectInput(selectedValue = tradeTermId, onValueChange = { tradeTermId = it; validationMessage = null }, options = tradeOptions, label = BBLocalization.Current.Get(key = "6c7bdc8a-1a1d-465d-a2da-7b873fea5e6e", fallback = "Ticaret Şartı"), placeholder = "Ticaret şartı seçiniz", enabled = !isSubmitting && tradeOptions.isNotEmpty())
                        BbTextInput(value = shippingTarget, onValueChange = { shippingTarget = it; validationMessage = null }, label = BBLocalization.Current.Get(key = "79063e0f-af2c-4425-9c4a-90140dd6493f", fallback = "Teslimat Hedefi"), placeholder = BBLocalization.Current.Get(key = "5fb48c03-4f77-45ea-950e-29781833ebbb", fallback = "Ülke / Şehir / Liman"), enabled = !isSubmitting)
                    }
                }

                validationMessage?.let { message -> item { RfqEditMessageCard(message, true) } }

                item {
                    BbButton(
                        text = BBLocalization.Current.Get(key = "65ad2e53-fbbd-4ed9-a5ae-b653f567bf66", fallback = "Değişiklikleri Kaydet"),
                        onClick = {
                            val current = model ?: return@BbButton
                            val category = productCategories.firstOrNull { it.ProductCategoryId == categoryId.toIntOrNull() }
                            val unit = units.firstOrNull { it.SystemDescUnitId == unitId.toIntOrNull() }
                            val currency = currencies.firstOrNull { it.SystemDescCurrencyId == currencyId.toIntOrNull() }
                            val color = colors.firstOrNull { it.SystemDescColorId == colorId.toIntOrNull() }
                            val material = materialTypes.firstOrNull { it.SystemDescMaterialTypeId == materialTypeId.toIntOrNull() }
                            val payment = paymentTerms.firstOrNull { it.SystemDescPaymentTermId == paymentTermId.toIntOrNull() }
                            val trade = tradeTerms.firstOrNull { it.SystemDescTradeTermId == tradeTermId.toIntOrNull() }

                            validationMessage = when {
                                productName.isBlank() -> BBLocalization.Current.Get(key = "5e1968fa-63e1-41b4-98e1-23fc9d672acf", fallback = "Ürün adı zorunludur.")
                                category == null -> BBLocalization.Current.Get(key = "4834b933-045e-4ad5-8a39-9fbfc5a2122a", fallback = "")
                                purchaseQuantity.toDoubleOrNull()?.let { it > 0 } != true -> BBLocalization.Current.Get(key = "d5278341-347c-4718-bd53-d010fe5f426e", fallback = "Geçerli bir miktar giriniz.")
                                unit == null -> BBLocalization.Current.Get(key = "723120da-c41c-4722-8827-f0bce1d29c34", fallback = "Birim seçiniz.")
                                unitPrice.toDoubleOrNull()?.let { it >= 0 } != true -> BBLocalization.Current.Get(key = "bfce72d6-713b-4c4e-89d5-68cf28d59b22", fallback = "Geçerli bir birim fiyat giriniz.")
                                currency == null -> "Para birimi seçiniz."
                                color == null -> BBLocalization.Current.Get(key = "435c95c7-5210-4f4d-9805-b555e7e43ba2", fallback = "")
                                material == null -> "Malzeme türü seçiniz."
                                payment == null -> BBLocalization.Current.Get(key = "c89a68fc-73df-440a-b534-d51ce207c623", fallback = "Ödeme şartı seçiniz.")
                                trade == null -> "Ticaret şartı seçiniz."
                                shippingTarget.isBlank() -> BBLocalization.Current.Get(key = "add112df-cb36-4594-ace0-b5dc04fda383", fallback = "Teslimat hedefi zorunludur.")
                                productDescription.isBlank() -> BBLocalization.Current.Get(key = "1e181724-0c03-48c0-a49d-045484f088c5", fallback = "Ürün açıklaması zorunludur.")
                                else -> null
                            }

                            if (validationMessage != null) return@BbButton

                            onSaveClick(
                                current.copy(
                                    ProductName = productName.trim(),
                                    ProductDescription = productDescription.trim(),
                                    CategoryId = category!!.ProductCategoryId,
                                    CategoryName = category.CategoryName,
                                    TradeTermId = trade!!.SystemDescTradeTermId,
                                    TradeTermName = trade.Content,
                                    PurchaseQuantity = purchaseQuantity.toDouble(),
                                    UnitId = unit!!.SystemDescUnitId,
                                    UnitName = unit.Content,
                                    UnitPrice = unitPrice.toDouble(),
                                    ColorId = color!!.SystemDescColorId,
                                    ColorName = color.Content,
                                    MaterialTypeId = material!!.SystemDescMaterialTypeId,
                                    MaterialTypeName = material.Content,
                                    CurrencyId = currency!!.SystemDescCurrencyId,
                                    CurrencyName = currency.Content,
                                    CurrencySymbol = currency.CurrencySymbol,
                                    PaymentTermId = payment!!.SystemDescPaymentTermId,
                                    PaymentTermName = payment.Content,
                                    ShippingTarget = shippingTarget.trim()
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Medium,
                        enabled = !isLoading && !isSubmitting,
                        isLoading = isSubmitting,
                        leadingIcon = { Icon(Icons.Outlined.Save, null) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RfqEditSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
            Text(text = title, style = BbTypography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
            content()
        }
    }
}

@Composable
private fun RfqEditMessageCard(message: String, error: Boolean = false) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Text(text = message, style = BbTypography.bodyMedium, color = if (error) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun RfqEditNumberInput(value: String, onValueChange: (String) -> Unit, label: String, modifier: Modifier = Modifier, enabled: Boolean = true) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it.filter { ch -> ch.isDigit() || ch == '.' || ch == ',' }.replace(',', '.')) },
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        singleLine = true,
        enabled = enabled,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
}
