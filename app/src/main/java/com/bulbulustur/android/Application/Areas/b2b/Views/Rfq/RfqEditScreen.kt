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
        topBar = { BbInnerPageHeader(title = "RFQ Düzenle", onBackClick = onBackClick) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant).padding(innerPadding),
            contentPadding = PaddingValues(start = BBSpacing.PageHorizontal, top = BBSpacing.PageTopCompact, end = BBSpacing.PageHorizontal, bottom = BBSpacing.PageBottom),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            if (isLoading || !initialized) item { RfqEditMessageCard("RFQ bilgileri yükleniyor...") }

            if (!errorMessage.isNullOrBlank()) {
                item {
                    RfqEditSection("Hata") {
                        Text(errorMessage, color = MaterialTheme.colorScheme.error, style = BbTypography.bodySmall)
                        BbButton(text = "Tekrar Dene", onClick = onRetryClick, modifier = Modifier.fillMaxWidth(), variant = BbButtonVariant.Outline, size = BbButtonSize.Medium)
                    }
                }
            }

            if (initialized) {
                item {
                    RfqEditSection("Ürün Bilgileri") {
                        BbTextInput(value = productName, onValueChange = { productName = it; validationMessage = null }, label = "Ürün Adı", placeholder = "Ürün adı", enabled = !isSubmitting)
                        BbCategorySearchSelectInput(selectedValue = categoryId, onValueChange = { categoryId = it; validationMessage = null }, onSearchTextChange = onCategorySearch, options = categoryOptions, label = "Kategori", placeholder = "Kategori seçiniz", searchPlaceholder = "Kategori ara", maximumVisibleOptionCount = 50, enabled = !isSubmitting && categoryOptions.isNotEmpty())
                        BbTextarea(value = productDescription, onValueChange = { productDescription = it; validationMessage = null }, label = "Ürün Açıklaması", placeholder = "Ürün detayları", enabled = !isSubmitting)
                    }
                }

                item {
                    RfqEditSection("Miktar ve Fiyat") {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
                            RfqEditNumberInput(purchaseQuantity, { purchaseQuantity = it; validationMessage = null }, "Miktar", Modifier.weight(1f), !isSubmitting)
                            Column(Modifier.weight(1f)) { BbSelectInput(selectedValue = unitId, onValueChange = { unitId = it; validationMessage = null }, options = unitOptions, label = "Birim", placeholder = "Birim seçiniz", enabled = !isSubmitting && unitOptions.isNotEmpty()) }
                        }

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
                            RfqEditNumberInput(unitPrice, { unitPrice = it; validationMessage = null }, "Birim Fiyat", Modifier.weight(1f), !isSubmitting)
                            Column(Modifier.weight(1f)) { BbSelectInput(selectedValue = currencyId, onValueChange = { currencyId = it; validationMessage = null }, options = currencyOptions, label = "Para Birimi", placeholder = "Para birimi seçiniz", enabled = !isSubmitting && currencyOptions.isNotEmpty()) }
                        }
                    }
                }

                item {
                    RfqEditSection("Ürün Özellikleri") {
                        BbSelectInput(selectedValue = colorId, onValueChange = { colorId = it; validationMessage = null }, options = colorOptions, label = "Renk", placeholder = "Renk seçiniz", enabled = !isSubmitting && colorOptions.isNotEmpty())
                        BbSelectInput(selectedValue = materialTypeId, onValueChange = { materialTypeId = it; validationMessage = null }, options = materialOptions, label = "Malzeme Türü", placeholder = "Malzeme seçiniz", enabled = !isSubmitting && materialOptions.isNotEmpty())
                    }
                }

                item {
                    RfqEditSection("Ticari Koşullar") {
                        BbSelectInput(selectedValue = paymentTermId, onValueChange = { paymentTermId = it; validationMessage = null }, options = paymentOptions, label = "Ödeme Şartı", placeholder = "Ödeme şartı seçiniz", enabled = !isSubmitting && paymentOptions.isNotEmpty())
                        BbSelectInput(selectedValue = tradeTermId, onValueChange = { tradeTermId = it; validationMessage = null }, options = tradeOptions, label = "Ticaret Şartı", placeholder = "Ticaret şartı seçiniz", enabled = !isSubmitting && tradeOptions.isNotEmpty())
                        BbTextInput(value = shippingTarget, onValueChange = { shippingTarget = it; validationMessage = null }, label = "Teslimat Hedefi", placeholder = "Ülke / Şehir / Liman", enabled = !isSubmitting)
                    }
                }

                validationMessage?.let { message -> item { RfqEditMessageCard(message, true) } }

                item {
                    BbButton(
                        text = "Değişiklikleri Kaydet",
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
                                productName.isBlank() -> "Ürün adı zorunludur."
                                category == null -> "Kategori seçiniz."
                                purchaseQuantity.toDoubleOrNull()?.let { it > 0 } != true -> "Geçerli bir miktar giriniz."
                                unit == null -> "Birim seçiniz."
                                unitPrice.toDoubleOrNull()?.let { it >= 0 } != true -> "Geçerli bir birim fiyat giriniz."
                                currency == null -> "Para birimi seçiniz."
                                color == null -> "Renk seçiniz."
                                material == null -> "Malzeme türü seçiniz."
                                payment == null -> "Ödeme şartı seçiniz."
                                trade == null -> "Ticaret şartı seçiniz."
                                shippingTarget.isBlank() -> "Teslimat hedefi zorunludur."
                                productDescription.isBlank() -> "Ürün açıklaması zorunludur."
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
