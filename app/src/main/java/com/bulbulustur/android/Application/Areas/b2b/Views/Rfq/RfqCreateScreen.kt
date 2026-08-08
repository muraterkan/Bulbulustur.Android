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
                title = BBLocalization.Current.Get(key = "d6a3a561-934c-46b0-af29-c48498e0171c", fallback = "Teklif Talebi"),
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
                            text = BBLocalization.Current.Get(key = "203882aa-6872-41de-a0db-26b13a6389e3", fallback = ""),
                            style = BbTypography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = BBLocalization.Current.Get(key = "68197456-74c2-4d7d-ab5f-ffe8c3a93929", fallback = "Ürün, miktar ve ticari koşulları belirterek tedarikçilerden teklif alın."),
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
                            text = BBLocalization.Current.Get(key = "ebc489e0-2a6b-41e8-ab72-bfdf11b7671f", fallback = "Form listeleri yükleniyor..."),
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
                                text = errorMessage ?: BBLocalization.Current.Get(key = "b3dbe95f-f43f-4c7c-8f4f-870947a12735", fallback = "RFQ form listelerinden biri veya birkaçı yüklenemedi."),
                                style = BbTypography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )

                            BbButton(
                                text = BBLocalization.Current.Get(key = "8fb163da-caf2-49b6-ab8b-0062d84a4dbb", fallback = "Listeleri Tekrar Yükle"),
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
                RfqCreateSection(title = BBLocalization.Current.Get(key = "90509413-3f80-4a57-b43b-21738dc74b50", fallback = "Ürün Bilgileri")) {
                    BbTextInput(
                        value = productName,
                        onValueChange = {
                            productName = it
                            validationMessage = null
                        },
                        label = BBLocalization.Current.Get(key = "6096bd2f-af02-449b-80ba-481a9f5ca31b", fallback = "Ürün Adı"),
                        placeholder = BBLocalization.Current.Get(key = "3d190c1c-efe2-498b-b08e-6d9702a18798", fallback = "Örn. Endüstriyel vana"),
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
                        label = BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = ""),
                        placeholder = if (isOptionsLoading) {
                            BBLocalization.Current.Get(key = "27a77f70-0597-4a4b-b0c3-640b0c388903", fallback = "Kategoriler yükleniyor...")
                        } else {
                            BBLocalization.Current.Get(key = "4834b933-045e-4ad5-8a39-9fbfc5a2122a", fallback = "")
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
                        label = BBLocalization.Current.Get(key = "eb7e1e0a-57ec-49bf-9968-61f0e5b75e6c", fallback = "Ürün Açıklaması"),
                        placeholder = BBLocalization.Current.Get(key = "290a2ae0-30d1-4281-87ae-ce84f01d52fc", fallback = "Ürünün teknik ve ticari özelliklerini açıklayın."),
                        enabled = !isSubmitting
                    )
                }
            }

            item {
                RfqCreateSection(title = BBLocalization.Current.Get(key = "bd347300-ac91-49d1-921b-ea7734a6be05", fallback = "Miktar ve Fiyat")) {
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
                            label = BBLocalization.Current.Get(key = "64f1e179-caee-4a60-9500-d35fbc4ed554", fallback = "Miktar"),
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
                                label = BBLocalization.Current.Get(key = "8c9bc441-0d68-4f53-9549-179f61d7ece0", fallback = "Birim"),
                                placeholder = BBLocalization.Current.Get(key = "723120da-c41c-4722-8827-f0bce1d29c34", fallback = "Birim seçiniz"),
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
                            label = BBLocalization.Current.Get(key = "a3f9904a-f7e6-4ef6-a79b-5fbbcbb9dd22", fallback = "Birim Fiyat"),
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
                                label = BBLocalization.Current.Get(key = "47942374-ab80-47b3-af0f-c8a6aaf728e3", fallback = ""),
                                placeholder = BBLocalization.Current.Get(key = "6b93a1bc-caf7-4b20-b9e0-858268ef4dd0", fallback = "Para birimi seçiniz"),
                                enabled = !isOptionsLoading && !isSubmitting && currencyOptions.isNotEmpty()
                            )
                        }
                    }
                }
            }

            item {
                RfqCreateSection(title = BBLocalization.Current.Get(key = "d036fcbd-c864-40cf-8899-cbdae6a6b913", fallback = "Ürün Özellikleri")) {
                    BbSelectInput(
                        selectedValue = colorId,
                        onValueChange = {
                            colorId = it
                            validationMessage = null
                        },
                        options = colorOptions,
                        label = BBLocalization.Current.Get(key = "846acd44-dbbf-4aa8-a537-cac0de8a1ef8", fallback = "Renk"),
                        placeholder = BBLocalization.Current.Get(key = "435c95c7-5210-4f4d-9805-b555e7e43ba2", fallback = ""),
                        enabled = !isOptionsLoading && !isSubmitting && colorOptions.isNotEmpty()
                    )

                    BbSelectInput(
                        selectedValue = materialTypeId,
                        onValueChange = {
                            materialTypeId = it
                            validationMessage = null
                        },
                        options = materialTypeOptions,
                        label = BBLocalization.Current.Get(key = "db735556-6dc6-4008-96df-387f8e444159", fallback = "Malzeme Türü"),
                        placeholder = BBLocalization.Current.Get(key = "db735556-6dc6-4008-96df-387f8e444159", fallback = "Malzeme türü seçiniz"),
                        enabled = !isOptionsLoading && !isSubmitting && materialTypeOptions.isNotEmpty()
                    )
                }
            }

            item {
                RfqCreateSection(title = BBLocalization.Current.Get(key = "b57feb8b-30a7-4c26-a638-cce10d96c69d", fallback = "Ticari Koşullar")) {
                    BbSelectInput(
                        selectedValue = paymentTermId,
                        onValueChange = {
                            paymentTermId = it
                            validationMessage = null
                        },
                        options = paymentTermOptions,
                        label = BBLocalization.Current.Get(key = "0ce51541-2adb-4cf7-91be-d1fcb7ffe88a", fallback = ""),
                        placeholder = BBLocalization.Current.Get(key = "c89a68fc-73df-440a-b534-d51ce207c623", fallback = "Ödeme şartı seçiniz"),
                        enabled = !isOptionsLoading && !isSubmitting && paymentTermOptions.isNotEmpty()
                    )

                    BbSelectInput(
                        selectedValue = tradeTermId,
                        onValueChange = {
                            tradeTermId = it
                            validationMessage = null
                        },
                        options = tradeTermOptions,
                        label = BBLocalization.Current.Get(key = "6c7bdc8a-1a1d-465d-a2da-7b873fea5e6e", fallback = "Ticaret Şartı"),
                        placeholder = BBLocalization.Current.Get(key = "31cd0a1c-1d7d-4bed-b816-9aa00fcf93c4", fallback = "Ticaret şartı seçiniz"),
                        enabled = !isOptionsLoading && !isSubmitting && tradeTermOptions.isNotEmpty()
                    )

                    BbTextInput(
                        value = shippingTarget,
                        onValueChange = {
                            shippingTarget = it
                            validationMessage = null
                        },
                        label = BBLocalization.Current.Get(key = "79063e0f-af2c-4425-9c4a-90140dd6493f", fallback = "Teslimat Hedefi"),
                        placeholder = BBLocalization.Current.Get(key = "e735f88b-1e7d-4150-909c-1bcd3607d0f3", fallback = "Örn. Türkiye / İstanbul / Ambarlı Port"),
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
                    text = BBLocalization.Current.Get(key = "9795306e-334f-4b00-8535-097e26c0fda6", fallback = "Teklif Talebini Gönder"),
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
                            memberId <= 0 -> BBLocalization.Current.Get(key = "b8f76c94-db02-44ad-b1d7-9cd3b235fb75", fallback = "Üye bilgisi bulunamadı.")
                            productName.isBlank() -> BBLocalization.Current.Get(key = "5e1968fa-63e1-41b4-98e1-23fc9d672acf", fallback = "Ürün adı zorunludur.")
                            selectedCategory == null -> BBLocalization.Current.Get(key = "4834b933-045e-4ad5-8a39-9fbfc5a2122a", fallback = "")
                            purchaseQuantity.toDoubleOrNull() == null -> BBLocalization.Current.Get(key = "d5278341-347c-4718-bd53-d010fe5f426e", fallback = "Geçerli bir miktar giriniz.")
                            selectedUnit == null -> BBLocalization.Current.Get(key = "723120da-c41c-4722-8827-f0bce1d29c34", fallback = "Birim seçiniz.")
                            unitPrice.toDoubleOrNull() == null -> BBLocalization.Current.Get(key = "bfce72d6-713b-4c4e-89d5-68cf28d59b22", fallback = "Geçerli bir birim fiyat giriniz.")
                            selectedCurrency == null -> "Para birimi seçiniz."
                            selectedColor == null -> BBLocalization.Current.Get(key = "435c95c7-5210-4f4d-9805-b555e7e43ba2", fallback = "")
                            selectedMaterialType == null -> "Malzeme türü seçiniz."
                            selectedPaymentTerm == null -> BBLocalization.Current.Get(key = "c89a68fc-73df-440a-b534-d51ce207c623", fallback = "Ödeme şartı seçiniz.")
                            selectedTradeTerm == null -> "Ticaret şartı seçiniz."
                            shippingTarget.isBlank() -> BBLocalization.Current.Get(key = "add112df-cb36-4594-ace0-b5dc04fda383", fallback = "Teslimat hedefi zorunludur.")
                            productDescription.isBlank() -> BBLocalization.Current.Get(key = "1e181724-0c03-48c0-a49d-045484f088c5", fallback = "Ürün açıklaması zorunludur.")
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