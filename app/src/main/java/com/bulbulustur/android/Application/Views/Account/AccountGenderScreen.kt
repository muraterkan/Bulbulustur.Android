package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGenderDTO

@Composable
fun AccountGenderScreen(
    genders: List<SystemDescGenderDTO>,
    currentGenderId: Int,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onSaveClick: (genderId: Int) -> Unit = {}
) {
    val selectedGenderId = remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(currentGenderId) {
        selectedGenderId.intValue = currentGenderId
    }

    val selectableGenders = genders.filter {
        it.SystemDescGenderId > 0 && it.Content.isNotBlank()
    }

    val canSubmit = selectedGenderId.intValue > 0 &&
            selectedGenderId.intValue != currentGenderId &&
            !isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "b3e02907-6ed9-44db-bd6a-b9276a1f8046", fallback = "Cinsiyet"),
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
            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.Medium
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "fd012660-3646-49d1-bfdb-554655fc837d", fallback = "Profilinizde gösterilecek cinsiyet bilgisini seçin."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.None
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when {
                        isLoading && selectableGenders.isEmpty() -> {
                            Text(
                                text = BBLocalization.Current.Get(key = "bc391322-df48-40eb-a579-fe189ac1052b", fallback = "Cinsiyet seçenekleri yükleniyor..."),
                                modifier = Modifier.padding(BBSpacing.CardPadding),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        selectableGenders.isEmpty() -> {
                            Text(
                                text = "Cinsiyet seçeneği bulunamadı.",
                                modifier = Modifier.padding(BBSpacing.CardPadding),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        else -> {
                            selectableGenders.forEach { gender ->
                                ProfileGenderOption(
                                    gender = gender,
                                    selected = selectedGenderId.intValue == gender.SystemDescGenderId,
                                    enabled = !isLoading,
                                    onClick = {
                                        selectedGenderId.intValue = gender.SystemDescGenderId
                                    }
                                )
                            }
                        }
                    }
                }
            }

            errorMessage
                ?.takeIf { it.isNotBlank() }
                ?.let { message ->
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

            BbButton(
                text = BBLocalization.Current.Get(key = "58104fd9-46c6-4304-9abb-07f5273a33f9", fallback = "Güncelle"),
                onClick = {
                    onSaveClick(selectedGenderId.intValue)
                },
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = canSubmit,
                isLoading = isLoading
            )
        }
    }
}

@Composable
private fun ProfileGenderOption(
    gender: SystemDescGenderDTO,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = enabled,
                onClick = onClick
            )
            .padding(
                horizontal = BBSpacing.CardPadding,
                vertical = BBSpacing.Space3
            ),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled
        )

        Text(
            text = gender.Content,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
