package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeFields
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeState
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

@Composable
fun CompanyInfoEditScreen(
    company: CompanyDTO?,
    addressCascadeState: AddressCascadeState,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onCountrySelected: (Int) -> Unit = {},
    onCountryStateSelected: (Int) -> Unit = {},
    onCountryDepartmentSelected: (Int?) -> Unit = {},
    onCitySelected: (Int) -> Unit = {},
    onDistrictSelected: (Int?) -> Unit = {},
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

    val selection = addressCascadeState.Selection

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "761ceb0a-2578-4b71-8c50-5b0d0998aefe", fallback = "Firma Bilgilerini Düzenle"),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && company == null -> {
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(innerPadding), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            !errorMessage.isNullOrBlank() && company == null -> {
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(innerPadding).padding(BBSpacing.PageHorizontal), contentAlignment = Alignment.Center) {
                    CompanyEditErrorState(message = errorMessage, onRetryClick = onRetryClick)
                }
            }

            company == null -> {
                Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(innerPadding).padding(BBSpacing.PageHorizontal), contentAlignment = Alignment.Center) {
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
                            CompanySectionHeader(title = BBLocalization.Current.Get(key = "68ddf12a-a56d-4957-a06d-6e7c04a6e8b9", fallback = "Firma Kimliği"), description = BBLocalization.Current.Get(key = "4ed9c7db-0c24-4137-911b-73c2ae90e5a1", fallback = "Firma ünvanı ve resmi kayıt bilgileri."))
                            CompanyTextField(value = companyName, onValueChange = { companyName = it }, label = BBLocalization.Current.Get(key = "333ea126-03ff-49e1-88c0-15e71623761b", fallback = "Firma Ünvanı"), placeholder = BBLocalization.Current.Get(key = "b5b67485-4996-428e-a973-6de996cf4875", fallback = "Firma ünvanı"))
                            CompanyTextField(value = yearEstablished, onValueChange = { yearEstablished = it }, label = BBLocalization.Current.Get(key = "2439777a-0431-4929-9600-07df5586ad67", fallback = "Kuruluş yılı"), placeholder = "2025", keyboardType = KeyboardType.Number)
                            CompanyTextField(value = taxOffice, onValueChange = { taxOffice = it }, label = BBLocalization.Current.Get(key = "8c42e65e-d7a4-4ff2-9dce-e4073d4dc335", fallback = "Vergi Dairesi"), placeholder = BBLocalization.Current.Get(key = "8c42e65e-d7a4-4ff2-9dce-e4073d4dc335", fallback = "Vergi dairesi"))
                            CompanyTextField(value = taxId, onValueChange = { taxId = it }, label = BBLocalization.Current.Get(key = "0f94c70f-fe11-4d18-8561-64d8499637df", fallback = "Vergi Numarası"), placeholder = "Vergi numarası", keyboardType = KeyboardType.Number)
                            CompanyTextField(value = mersisNo, onValueChange = { mersisNo = it }, label = BBLocalization.Current.Get(key = "cb3df3f8-c596-444a-b920-9240350f757d", fallback = "MERSİS No"), placeholder = "MERSİS numarası", keyboardType = KeyboardType.Number)
                            CompanyTextField(value = kepAddress, onValueChange = { kepAddress = it }, label = BBLocalization.Current.Get(key = "4f33b03a-913c-4ea8-9640-a58bd2f68dac", fallback = "KEP Adresi"), placeholder = "firma@hs01.kep.tr", keyboardType = KeyboardType.Email)
                        }
                    }

                    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
                        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
                            CompanySectionHeader(title = BBLocalization.Current.Get(key = "92693121-67da-4c9a-acd8-27fe2398dfab", fallback = "İletişim ve Adres"), description = BBLocalization.Current.Get(key = "c0fa7ae0-5ace-4c4b-ab69-89e8112333ee", fallback = "Firma iletişim bilgileri ve resmi adresi."))
                            CompanyTextField(value = email, onValueChange = { email = it }, label = BBLocalization.Current.Get(key = "1246f9ff-205d-4d92-84ee-7c8c7a3f2d46", fallback = "E-Posta"), placeholder = "firma@ornek.com", keyboardType = KeyboardType.Email)
                            CompanyTextField(value = url, onValueChange = { url = it }, label = BBLocalization.Current.Get(key = "a8fcc3ce-6d1a-40be-b752-974c9b774d7b", fallback = "Web Sitesi"), placeholder = "https://www.ornek.com", keyboardType = KeyboardType.Uri)
                            AddressCascadeFields(
                                state = addressCascadeState,
                                onCountrySelected = onCountrySelected,
                                onCountryStateSelected = onCountryStateSelected,
                                onCountryDepartmentSelected = onCountryDepartmentSelected,
                                onCitySelected = onCitySelected,
                                onDistrictSelected = onDistrictSelected,
                                modifier = Modifier.fillMaxWidth(),
                                enabled = !isLoading
                            )
                            CompanyTextField(value = postCode, onValueChange = { postCode = it }, label = BBLocalization.Current.Get(key = "fff66b6e-cf51-4dde-a421-b8ce3df436d0", fallback = "Posta Kodu"), placeholder = "Posta kodu", keyboardType = KeyboardType.Number)
                            OutlinedTextField(
                                value = address,
                                onValueChange = { address = it },
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text(text = BBLocalization.Current.Get(key = "af1da4df-7298-4cd9-b256-371d098b59f7", fallback = "Adres")) },
                                placeholder = { Text(text = BBLocalization.Current.Get(key = "3a2cadb6-403f-4d44-af1e-7ecda701cd10", fallback = "Firma adresi")) },
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
                        text = if (isLoading) "Kaydediliyor..." else BBLocalization.Current.Get(key = "b717bf92-f273-4bcb-8b08-c98a4eb477b9", fallback = "Firma Bilgilerini Kaydet"),
                        onClick = {
                            onSaveClick(
                                CompanyUpdateModel(
                                    CompanyId = company.CompanyId,
                                    CompanyTypeId = company.CompanyTypeId,
                                    CompanyName = companyName.trim(),
                                    CountryId = selection.CountryId,
                                    CountryStateId = selection.CountryStateId,
                                    CountryDepartmentId = selection.CountryDepartmentId,
                                    CityId = selection.CityId,
                                    DistrictId = selection.DistrictId,
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
                        enabled = !isLoading && !addressCascadeState.IsLoading && companyName.isNotBlank() && addressCascadeState.IsValid
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
            text = BBLocalization.Current.Get(key = "206f3320-77dc-4c54-ac3c-5b561ca6113c", fallback = "Toptan işlem, teklif ve kurumsal fatura süreçlerinde kullanılabilecek firma bilgilerinizi buradan düzenleyebilirsiniz."),
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
            Text(text = BBLocalization.Current.Get(key = "4d514b62-27c1-4720-a745-a1f254179ba1", fallback = "Kurumsal Bilgi Notu"), style = MaterialTheme.typography.labelLarge, color = BBColors.Blue.Blue700)
            Text(
                text = BBLocalization.Current.Get(key = "a6d566bc-6727-43e9-9c16-0597e30a49b1", fallback = "Ülke, şehir ve ilçe bilgilerinizi bu ekrandan güncelleyebilirsiniz. Şirket tipi düzenlemesi ayrı olarak eklenecektir."),
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
            BbButton(text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"), onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyEditNotFoundState(onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.Business, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.Section))
            Text(text = BBLocalization.Current.Get(key = "4f467a63-d0ab-423f-ae06-65c598e5a641", fallback = "Firma bilgisi bulunamadı"), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            BbButton(text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"), onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}