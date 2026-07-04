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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import androidx.compose.foundation.layout.size

@Composable
fun CompanyInfoEditScreen(
    company: CompanyDTO?,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onSaveClick: (CompanyUpdateModel) -> Unit = {}
) {
    var companyName by remember { mutableStateOf("") }
    var taxOffice by remember { mutableStateOf("") }
    var taxId by remember { mutableStateOf("") }
    var mersisNo by remember { mutableStateOf("") }
    var kepAddress by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }
    var yearEstablished by remember { mutableStateOf("") }
    var postCode by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    LaunchedEffect(company?.CompanyId) {
        if (company == null) return@LaunchedEffect

        companyName = company.CompanyName
        taxOffice = company.TaxOffice
        taxId = company.TaxId
        mersisNo = company.MersisNo
        kepAddress = company.KepAddress
        email = company.Email
        url = company.Url
        yearEstablished = company.YearEstablished
        postCode = company.PostCode
        address = company.Address
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { BbInnerPageHeader(title = "Firma Bilgilerini Düzenle", onBackClick = onBackClick) }
    ) { innerPadding ->
        when {
            isLoading && company == null -> {
                Box(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            !errorMessage.isNullOrBlank() && company == null -> {
                Box(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(innerPadding).padding(BBSpacing.PageHorizontal),
                    contentAlignment = Alignment.Center
                ) {
                    CompanyEditErrorState(message = errorMessage, onRetryClick = onRetryClick)
                }
            }

            company == null -> {
                Box(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(innerPadding).padding(BBSpacing.PageHorizontal),
                    contentAlignment = Alignment.Center
                ) {
                    CompanyEditNotFoundState(onRetryClick = onRetryClick)
                }
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(innerPadding).verticalScroll(rememberScrollState()).padding(
                        PaddingValues(start = BBSpacing.PageHorizontal, top = BBSpacing.PageTopCompact, end = BBSpacing.PageHorizontal, bottom = BBSpacing.PageBottom)
                    ),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGap)
                ) {
                    CompanyInfoEditIntroCard()

                    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
                        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
                            CompanySectionHeader(title = "Firma Kimliği", description = "Firma ünvanı ve resmi kayıt bilgileri.")
                            CompanyTextField(value = companyName, onValueChange = { companyName = it }, label = "Firma Ünvanı", placeholder = "Firma ünvanı")
                            CompanyTextField(value = yearEstablished, onValueChange = { yearEstablished = it }, label = "Kuruluş Yılı", placeholder = "2025", keyboardType = KeyboardType.Number)
                            CompanyTextField(value = taxOffice, onValueChange = { taxOffice = it }, label = "Vergi Dairesi", placeholder = "Vergi dairesi")
                            CompanyTextField(value = taxId, onValueChange = { taxId = it }, label = "Vergi Numarası", placeholder = "Vergi numarası", keyboardType = KeyboardType.Number)
                            CompanyTextField(value = mersisNo, onValueChange = { mersisNo = it }, label = "MERSİS No", placeholder = "MERSİS numarası", keyboardType = KeyboardType.Number)
                            CompanyTextField(value = kepAddress, onValueChange = { kepAddress = it }, label = "KEP Adresi", placeholder = "firma@hs01.kep.tr", keyboardType = KeyboardType.Email)
                        }
                    }

                    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
                        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
                            CompanySectionHeader(title = "İletişim ve Adres", description = "Firma iletişim bilgileri ve resmi adresi.")
                            CompanyTextField(value = email, onValueChange = { email = it }, label = "E-Posta", placeholder = "firma@ornek.com", keyboardType = KeyboardType.Email)
                            CompanyTextField(value = url, onValueChange = { url = it }, label = "Web Sitesi", placeholder = "https://www.ornek.com", keyboardType = KeyboardType.Uri)
                            CompanyTextField(value = postCode, onValueChange = { postCode = it }, label = "Posta Kodu", placeholder = "Posta kodu", keyboardType = KeyboardType.Number)
                            OutlinedTextField(
                                value = address,
                                onValueChange = { address = it },
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text(text = "Adres") },
                                placeholder = { Text(text = "Firma adresi") },
                                minLines = 3,
                                shape = BBRadius.Input,
                                colors = CompanyTextFieldColors()
                            )
                        }
                    }

                    CompanyInfoNote()

                    if (!errorMessage.isNullOrBlank()) {
                        CompanyInlineError(message = errorMessage)
                    }

                    BbButton(
                        text = if (isLoading) "Kaydediliyor..." else "Firma Bilgilerini Kaydet",
                        onClick = {
                            onSaveClick(
                                CompanyUpdateModel(
                                    CompanyId = company.CompanyId,
                                    CompanyTypeId = company.CompanyTypeId,
                                    CompanyName = companyName.trim(),
                                    CountryId = company.CountryId,
                                    CountryStateId = company.CountryStateId,
                                    CountryDepartmentId = company.CountryDepartmentId,
                                    CityId = company.CityId,
                                    DistrictId = company.DistrictId,
                                    PostCode = postCode.trim(),
                                    Address = address.trim(),
                                    Url = url.trim(),
                                    MersisNo = mersisNo.trim(),
                                    KepAddress = kepAddress.trim(),
                                    TaxOffice = taxOffice.trim(),
                                    TaxId = taxId.trim(),
                                    YearEstablished = yearEstablished.trim(),
                                    Email = email.trim()
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        variant = BbButtonVariant.Primary,
                        size = BbButtonSize.Medium,
                        enabled = !isLoading && companyName.isNotBlank()
                    )
                }
            }
        }
    }
}

@Composable
private fun CompanyInfoEditIntroCard() {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Text(
            text = "Toptan işlem, teklif ve kurumsal fatura süreçlerinde kullanılabilecek firma bilgilerinizi buradan düzenleyebilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CompanySectionHeader(title: String, description: String) {
    Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
        Text(text = title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
        Text(text = description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        shape = BBRadius.Input,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = CompanyTextFieldColors()
    )
}

@Composable
private fun CompanyTextFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = MaterialTheme.colorScheme.surface,
    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
)

@Composable
private fun CompanyInfoNote() {
    Box(modifier = Modifier.fillMaxWidth().background(color = BBColors.Blue.Blue50, shape = BBRadius.LgShape).padding(BBSpacing.CardPaddingCompact)) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
            Text(text = "Kurumsal Bilgi Notu", style = MaterialTheme.typography.labelLarge, color = BBColors.Blue.Blue700)
            Text(
                text = "Ülke, bölge, şehir, ilçe ve şirket tipi seçimleri mevcut kayıttan korunur. Bu alanlar adres seçim altyapısı bağlandığında ayrıca düzenlenebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CompanyInlineError(message: String) {
    Box(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.errorContainer, shape = BBRadius.LgShape).padding(BBSpacing.CardPaddingCompact)) {
        Text(text = message, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onErrorContainer)
    }
}

@Composable
private fun CompanyEditErrorState(message: String, onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.ErrorOutline, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(BBIcon.Section))
            Text(text = message, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            BbButton(text = "Tekrar Dene", onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyEditNotFoundState(onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.Business, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.Section))
            Text(text = "Firma bilgisi bulunamadı", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            BbButton(text = "Tekrar Dene", onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}
