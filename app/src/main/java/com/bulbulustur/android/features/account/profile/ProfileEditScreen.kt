package com.bulbulustur.android.features.account.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (
        name: String,
        surname: String,
        gender: String,
        profession: String,
        birthDate: String,
        country: String,
        city: String,
        district: String
    ) -> Unit = { _, _, _, _, _, _, _, _ -> }
) {
    val nameState = remember {
        mutableStateOf("Murat")
    }

    val surnameState = remember {
        mutableStateOf("Erkan")
    }

    val genderState = remember {
        mutableStateOf("")
    }

    val professionState = remember {
        mutableStateOf("")
    }

    val birthDateState = remember {
        mutableStateOf("")
    }

    val countryState = remember {
        mutableStateOf("Türkiye")
    }

    val cityState = remember {
        mutableStateOf("")
    }

    val districtState = remember {
        mutableStateOf("")
    }

    AccountPageScaffold(
        title = "Kullanıcı Bilgileri",
        kicker = "Hesap Profili",
        description = "Hesabınıza ait kişisel ve konum bilgilerini buradan güncelleyebilirsiniz.",
        backButtonText = "Hesabıma Dön",
        onBackClick = onBackClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
                ) {
                    AccountFormSectionHeader(
                        title = "Kişisel Bilgiler",
                        description = "Ad, soyad, meslek ve doğum tarihi bilgilerinizi güncelleyin."
                    )

                    AccountTextField(
                        value = nameState.value,
                        onValueChange = { value ->
                            nameState.value = value
                        },
                        label = "İsim",
                        placeholder = "İsminiz"
                    )

                    AccountTextField(
                        value = surnameState.value,
                        onValueChange = { value ->
                            surnameState.value = value
                        },
                        label = "Soyisim",
                        placeholder = "Soyisminiz"
                    )

                    AccountTextField(
                        value = genderState.value,
                        onValueChange = { value ->
                            genderState.value = value
                        },
                        label = "Cinsiyet",
                        placeholder = "Seçiniz"
                    )

                    AccountTextField(
                        value = professionState.value,
                        onValueChange = { value ->
                            professionState.value = value
                        },
                        label = "Meslek",
                        placeholder = "Mesleğiniz"
                    )

                    AccountTextField(
                        value = birthDateState.value,
                        onValueChange = { value ->
                            birthDateState.value = value
                        },
                        label = "Doğum Günü",
                        placeholder = "gg.aa.yyyy",
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
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
                ) {
                    AccountFormSectionHeader(
                        title = "Konum Bilgileri",
                        description = "Ülke, şehir ve ilçe bilgilerinizi düzenleyin."
                    )

                    AccountTextField(
                        value = countryState.value,
                        onValueChange = { value ->
                            countryState.value = value
                        },
                        label = "Ülke",
                        placeholder = "Ülke seçiniz"
                    )

                    AccountTextField(
                        value = cityState.value,
                        onValueChange = { value ->
                            cityState.value = value
                        },
                        label = "Şehir",
                        placeholder = "Şehir seçiniz"
                    )

                    AccountTextField(
                        value = districtState.value,
                        onValueChange = { value ->
                            districtState.value = value
                        },
                        label = "İlçe",
                        placeholder = "İlçe seçiniz"
                    )
                }
            }

            BbButton(
                text = "Güncelle",
                onClick = {
                    onSaveClick(
                        nameState.value,
                        surnameState.value,
                        genderState.value,
                        professionState.value,
                        birthDateState.value,
                        countryState.value,
                        cityState.value,
                        districtState.value
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
private fun AccountFormSectionHeader(
    title: String,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
private fun AccountTextField(
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
        shape = BbRadius.Input,
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