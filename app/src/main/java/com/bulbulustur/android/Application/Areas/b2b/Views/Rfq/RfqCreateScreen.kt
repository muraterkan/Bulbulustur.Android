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
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BuyerRequestInsertModel
import java.time.OffsetDateTime

@Composable
fun RfqCreateScreen(
    memberId: Int,
    productCategories: List<ProductCategoryDTO>,
    units: List<SystemDescUnitDTO>,
    currencies: List<SystemDescCurrencyDTO>,
    colors: List<SystemDescColorDTO>,
    materialTypes: List<SystemDescMaterialTypeDTO>,
    paymentTerms: List<SystemDescPaymentTermDTO>,
    tradeTerms: List<SystemDescTradeTermDTO>,
    isOptionsLoading: Boolean = false,
    isSubmitting: Boolean = false,
    errorMessage: String? = null,
    initialProductName: String = "",
    onBackClick: () -> Unit = {},
    onRetryOptionsClick: () -> Unit = {},
    onCategorySearch: (String) -> Unit = {},
    onSendClick: (BuyerRequestInsertModel) -> Unit = {}
) {
    var productName by remember { mutableStateOf(initialProductName) }
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

    val categoryOptions = productCategories
        .filter { it.ProductCategoryId > 0 && it.CategoryName.isNotBlank() }
        .sortedBy { it.CategoryName }
        .map {
            BbSelectOption(
                value = it.ProductCategoryId.toString(),
                text = it.CategoryName
            )
        }

    val unitOptions = units
        .filter { it.SystemDescUnitId > 0 && it.Content.isNotBlank() }
        .sortedBy { it.Sequence }
        .map {
            BbSelectOption(
                value = it.SystemDescUnitId.toString(),
                text = buildString {
                    append(it.Content)

                    if (it.Symbol.isNotBlank()) {
                        append(" (")
                        append(it.Symbol)
                        append(")")
                    }
                }
            )
        }

    val currencyOptions = currencies
        .filter { it.SystemDescCurrencyId > 0 && it.Content.isNotBlank() }
        .map {
            BbSelectOption(
                value = it.SystemDescCurrencyId.toString(),
                text = buildString {
                    append(it.Content)

                    if (it.IsoCode.isNotBlank()) {
                        append(" - ")
                        append(it.IsoCode)
                    }
                }
            )
        }

    val colorOptions = colors
        .filter { it.SystemDescColorId > 0 && it.Content.isNotBlank() }
        .sortedBy { it.Content }
        .map {
            BbSelectOption(
                value = it.SystemDescColorId.toString(),
                text = it.Content
            )
        }

    val materialTypeOptions = materialTypes
        .filter { it.SystemDescMaterialTypeId > 0 && it.Content.isNotBlank() }
        .sortedBy { it.Sorting }
        .map {
            BbSelectOption(
                value = it.SystemDescMaterialTypeId.toString(),
                text = it.Content
            )
        }

    val paymentTermOptions = paymentTerms
        .filter { it.SystemDescPaymentTermId > 0 && it.Content.isNotBlank() }
        .sortedBy { it.Content }
        .map {
            BbSelectOption(
                value = it.SystemDescPaymentTermId.toString(),
                text = it.Content
            )
        }

    val tradeTermOptions = tradeTerms
        .filter { it.SystemDescTradeTermId > 0 && it.Content.isNotBlank() }
        .sortedBy { it.Content }
        .map {
            BbSelectOption(
                value = it.SystemDescTradeTermId.toString(),
                text = it.Content
            )
        }

    val hasAllOptions =
        categoryOptions.isNotEmpty() &&
                unitOptions.isNotEmpty() &&
                currencyOptions.isNotEmpty() &&
                colorOptions.isNotEmpty() &&
                materialTypeOptions.isNotEmpty() &&
                paymentTermOptions.isNotEmpty() &&
                tradeTermOptions.isNotEmpty()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Teklif Talebi",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        Text(
                            text = "Teklif Talebi Oluştur",
                            style = BbTypography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "Ürün, miktar ve ticari koşulları belirterek tedarikçilerden teklif alın.",
                            style = BbTypography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            if (isOptionsLoading) {
                item {
                    BbCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbCardVariant.Outlined,
                        padding = BbCardPadding.Medium
                    ) {
                        Text(
                            text = "Form listeleri yükleniyor...",
                            style = BbTypography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            if (!isOptionsLoading && !hasAllOptions) {
                item {
                    BbCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbCardVariant.Outlined,
                        padding = BbCardPadding.Medium
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                        ) {
                            Text(
                                text = errorMessage ?: "RFQ form listelerinden biri veya birkaçı yüklenemedi.",
                                style = BbTypography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )

                            BbButton(
                                text = "Listeleri Tekrar Yükle",
                                onClick = onRetryOptionsClick,
                                modifier = Modifier.fillMaxWidth(),
                                variant = BbButtonVariant.Outline,
                                size = BbButtonSize.Medium
                            )
                        }
                    }
                }
            }

            item {
                RfqCreateSection(title = "Ürün Bilgileri") {
                    BbTextInput(
                        value = productName,
                        onValueChange = {
                            productName = it
                            validationMessage = null
                        },
                        label = "Ürün Adı",
                        placeholder = "Örn. Endüstriyel vana",
                        enabled = !isSubmitting
                    )

                    BbCategorySearchSelectInput(
                        selectedValue = categoryId,
                        onValueChange = {
                            categoryId = it
                            validationMessage = null
                        },
                        onSearchTextChange = onCategorySearch,
                        options = categoryOptions,
                        label = "Kategori",
                        placeholder = if (isOptionsLoading) {
                            "Kategoriler yükleniyor..."
                        } else {
                            "Kategori seçiniz"
                        },
                        searchPlaceholder = "Kategori ara",
                        maximumVisibleOptionCount = 50,
                        enabled = !isOptionsLoading &&
                                !isSubmitting &&
                                categoryOptions.isNotEmpty()
                    )

                    BbTextarea(
                        value = productDescription,
                        onValueChange = {
                            productDescription = it
                            validationMessage = null
                        },
                        label = "Ürün Açıklaması",
                        placeholder = "Ürünün teknik ve ticari özelliklerini açıklayın.",
                        enabled = !isSubmitting
                    )
                }
            }

            item {
                RfqCreateSection(title = "Miktar ve Fiyat") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        RfqNumberInput(
                            value = purchaseQuantity,
                            onValueChange = {
                                purchaseQuantity = it
                                validationMessage = null
                            },
                            label = "Miktar",
                            modifier = Modifier.weight(1f),
                            enabled = !isSubmitting
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            BbSelectInput(
                                selectedValue = unitId,
                                onValueChange = {
                                    unitId = it
                                    validationMessage = null
                                },
                                options = unitOptions,
                                label = "Birim",
                                placeholder = "Birim seçiniz",
                                enabled = !isOptionsLoading && !isSubmitting && unitOptions.isNotEmpty()
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        RfqNumberInput(
                            value = unitPrice,
                            onValueChange = {
                                unitPrice = it
                                validationMessage = null
                            },
                            label = "Birim Fiyat",
                            modifier = Modifier.weight(1f),
                            enabled = !isSubmitting
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            BbSelectInput(
                                selectedValue = currencyId,
                                onValueChange = {
                                    currencyId = it
                                    validationMessage = null
                                },
                                options = currencyOptions,
                                label = "Para Birimi",
                                placeholder = "Para birimi seçiniz",
                                enabled = !isOptionsLoading && !isSubmitting && currencyOptions.isNotEmpty()
                            )
                        }
                    }
                }
            }

            item {
                RfqCreateSection(title = "Ürün Özellikleri") {
                    BbSelectInput(
                        selectedValue = colorId,
                        onValueChange = {
                            colorId = it
                            validationMessage = null
                        },
                        options = colorOptions,
                        label = "Renk",
                        placeholder = "Renk seçiniz",
                        enabled = !isOptionsLoading && !isSubmitting && colorOptions.isNotEmpty()
                    )

                    BbSelectInput(
                        selectedValue = materialTypeId,
                        onValueChange = {
                            materialTypeId = it
                            validationMessage = null
                        },
                        options = materialTypeOptions,
                        label = "Malzeme Türü",
                        placeholder = "Malzeme türü seçiniz",
                        enabled = !isOptionsLoading && !isSubmitting && materialTypeOptions.isNotEmpty()
                    )
                }
            }

            item {
                RfqCreateSection(title = "Ticari Koşullar") {
                    BbSelectInput(
                        selectedValue = paymentTermId,
                        onValueChange = {
                            paymentTermId = it
                            validationMessage = null
                        },
                        options = paymentTermOptions,
                        label = "Ödeme Şartı",
                        placeholder = "Ödeme şartı seçiniz",
                        enabled = !isOptionsLoading && !isSubmitting && paymentTermOptions.isNotEmpty()
                    )

                    BbSelectInput(
                        selectedValue = tradeTermId,
                        onValueChange = {
                            tradeTermId = it
                            validationMessage = null
                        },
                        options = tradeTermOptions,
                        label = "Ticaret Şartı",
                        placeholder = "Ticaret şartı seçiniz",
                        enabled = !isOptionsLoading && !isSubmitting && tradeTermOptions.isNotEmpty()
                    )

                    BbTextInput(
                        value = shippingTarget,
                        onValueChange = {
                            shippingTarget = it
                            validationMessage = null
                        },
                        label = "Teslimat Hedefi",
                        placeholder = "Örn. Türkiye / İstanbul / Ambarlı Port",
                        enabled = !isSubmitting
                    )
                }
            }

            val visibleError = validationMessage ?: errorMessage?.takeIf { hasAllOptions }

            if (!visibleError.isNullOrBlank()) {
                item {
                    BbCard(
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbCardVariant.Outlined,
                        padding = BbCardPadding.Medium
                    ) {
                        Text(
                            text = visibleError,
                            style = BbTypography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }

            item {
                BbButton(
                    text = "Teklif Talebini Gönder",
                    onClick = {
                        val selectedCategory = productCategories.firstOrNull {
                            it.ProductCategoryId == categoryId.toIntOrNull()
                        }

                        val selectedUnit = units.firstOrNull {
                            it.SystemDescUnitId == unitId.toIntOrNull()
                        }

                        val selectedCurrency = currencies.firstOrNull {
                            it.SystemDescCurrencyId == currencyId.toIntOrNull()
                        }

                        val selectedColor = colors.firstOrNull {
                            it.SystemDescColorId == colorId.toIntOrNull()
                        }

                        val selectedMaterialType = materialTypes.firstOrNull {
                            it.SystemDescMaterialTypeId == materialTypeId.toIntOrNull()
                        }

                        val selectedPaymentTerm = paymentTerms.firstOrNull {
                            it.SystemDescPaymentTermId == paymentTermId.toIntOrNull()
                        }

                        val selectedTradeTerm = tradeTerms.firstOrNull {
                            it.SystemDescTradeTermId == tradeTermId.toIntOrNull()
                        }

                        validationMessage = when {
                            memberId <= 0 -> "Üye bilgisi bulunamadı."
                            productName.isBlank() -> "Ürün adı zorunludur."
                            selectedCategory == null -> "Kategori seçiniz."
                            purchaseQuantity.toDoubleOrNull() == null -> "Geçerli bir miktar giriniz."
                            selectedUnit == null -> "Birim seçiniz."
                            unitPrice.toDoubleOrNull() == null -> "Geçerli bir birim fiyat giriniz."
                            selectedCurrency == null -> "Para birimi seçiniz."
                            selectedColor == null -> "Renk seçiniz."
                            selectedMaterialType == null -> "Malzeme türü seçiniz."
                            selectedPaymentTerm == null -> "Ödeme şartı seçiniz."
                            selectedTradeTerm == null -> "Ticaret şartı seçiniz."
                            shippingTarget.isBlank() -> "Teslimat hedefi zorunludur."
                            productDescription.isBlank() -> "Ürün açıklaması zorunludur."
                            else -> null
                        }

                        if (validationMessage != null) return@BbButton

                        val now = OffsetDateTime.now()

                        onSendClick(
                            BuyerRequestInsertModel(
                                InsertedBy = memberId,
                                InsertedDate = now.toString(),
                                StatusId = 1,
                                BuyerRequestKey = "",
                                MemberName = "",
                                ProductName = productName.trim(),
                                ProductDescription = productDescription.trim(),
                                CategoryId = selectedCategory!!.ProductCategoryId,
                                CategoryName = selectedCategory.CategoryName,
                                TradeTermId = selectedTradeTerm!!.SystemDescTradeTermId,
                                TradeTermName = selectedTradeTerm.Content,
                                PurchaseQuantity = purchaseQuantity.toDouble(),
                                UnitId = selectedUnit!!.SystemDescUnitId,
                                UnitName = selectedUnit.Content,
                                UnitPrice = unitPrice.toDouble(),
                                ColorId = selectedColor!!.SystemDescColorId,
                                ColorName = selectedColor.Content,
                                MaterialTypeId = selectedMaterialType!!.SystemDescMaterialTypeId,
                                MaterialTypeName = selectedMaterialType.Content,
                                CurrencyId = selectedCurrency!!.SystemDescCurrencyId,
                                CurrencyName = selectedCurrency.Content,
                                CurrencySymbol = selectedCurrency.CurrencySymbol,
                                ShippingTypeId = 0,
                                ShippingTypeName = "",
                                PaymentTermId = selectedPaymentTerm!!.SystemDescPaymentTermId,
                                PaymentTermName = selectedPaymentTerm.Content,
                                ShippingTarget = shippingTarget.trim(),
                                LastRequestDate = now.plusDays(7).toString(),
                                MaxbudgetId = 0,
                                MaxbudgetName = ""
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Primary,
                    size = BbButtonSize.Medium,
                    enabled = hasAllOptions && !isOptionsLoading && !isSubmitting,
                    isLoading = isSubmitting,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Send,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun RfqCreateSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Text(
                text = title,
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            content()
        }
    }
}

@Composable
private fun RfqNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(
                newValue.filter {
                    it.isDigit() || it == '.' || it == ','
                }.replace(',', '.')
            )
        },
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },
        singleLine = true,
        enabled = enabled,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
}