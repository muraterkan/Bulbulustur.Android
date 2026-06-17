package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun CompanyInfoEditScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (
        companyName: String,
        taxOffice: String,
        taxNumber: String,
        mersisNo: String,
        phone: String,
        email: String,
        address: String
    ) -> Unit = { _, _, _, _, _, _, _ -> }
) {
    val companyNameState = remember {
        mutableStateOf("")
    }

    val taxOfficeState = remember {
        mutableStateOf("")
    }

    val taxNumberState = remember {
        mutableStateOf("")
    }

    val mersisNoState = remember {
        mutableStateOf("")
    }

    val phoneState = remember {
        mutableStateOf("")
    }

    val emailState = remember {
        mutableStateOf("")
    }

    val addressState = remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Firma Bilgilerini Düzenle",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    PaddingValues(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
        ) {
            CompanyInfoEditIntroCard()

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                ) {
                    CompanySectionHeader(
                        title = "Firma Kimliği",
                        description = "Firma adınız ve vergi bilgileriniz."
                    )

                    CompanyTextField(
                        value = companyNameState.value,
                        onValueChange = { value ->
                            companyNameState.value = value
                        },
                        label = "Firma Ünvanı",
                        placeholder = "Firma ünvanı"
                    )

                    CompanyTextField(
                        value = taxOfficeState.value,
                        onValueChange = { value ->
                            taxOfficeState.value = value
                        },
                        label = "Vergi Dairesi",
                        placeholder = "Vergi dairesi"
                    )

                    CompanyTextField(
                        value = taxNumberState.value,
                        onValueChange = { value ->
                            taxNumberState.value = value
                        },
                        label = "Vergi Numarası",
                        placeholder = "Vergi numarası",
                        keyboardType = KeyboardType.Number
                    )

                    CompanyTextField(
                        value = mersisNoState.value,
                        onValueChange = { value ->
                            mersisNoState.value = value
                        },
                        label = "MERSİS No",
                        placeholder = "MERSİS numarası",
                        keyboardType = KeyboardType.Number
                    )
                }
            }

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
                ) {
                    CompanySectionHeader(
                        title = "İletişim Ve Adres",
                        description = "Firma iletişim bilgileriniz ve resmi adresiniz."
                    )

                    CompanyTextField(
                        value = phoneState.value,
                        onValueChange = { value ->
                            phoneState.value = value
                        },
                        label = "Telefon",
                        placeholder = "+90 5xx xxx xx xx",
                        keyboardType = KeyboardType.Phone
                    )

                    CompanyTextField(
                        value = emailState.value,
                        onValueChange = { value ->
                            emailState.value = value
                        },
                        label = "E-Posta",
                        placeholder = "firma@ornek.com",
                        keyboardType = KeyboardType.Email
                    )

                    OutlinedTextField(
                        value = addressState.value,
                        onValueChange = { value ->
                            addressState.value = value
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text(text = "Adres")
                        },
                        placeholder = {
                            Text(text = "Firma adresi")
                        },
                        minLines = 3,
                        shape = BBRadius.Input,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }

            CompanyInfoNote()

            BbButton(
                text = "Firma Bilgilerini Kaydet",
                onClick = {
                    onSaveClick(
                        companyNameState.value,
                        taxOfficeState.value,
                        taxNumberState.value,
                        mersisNoState.value,
                        phoneState.value,
                        emailState.value,
                        addressState.value
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun CompanyInfoEditIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = "Toptan işlem, teklif ve kurumsal fatura süreçlerinde kullanılabilecek firma bilgilerinizi buradan düzenleyebilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CompanySectionHeader(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CompanyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        singleLine = true,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    )
}

@Composable
private fun CompanyInfoNote() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BBColors.Blue.Blue50,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = "Kurumsal Bilgi Notu",
                style = MaterialTheme.typography.labelLarge,
                color = BBColors.Blue.Blue700
            )

            Text(
                text = "Bu bilgiler teklif, fatura ve kurumsal işlem süreçlerinde kullanılabilir. Gerçek API bağlandığında doğrulama kuralları ayrıca uygulanacaktır.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

