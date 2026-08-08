package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO

@Composable
fun CompanyB2BIndexScreen(
    company: CompanyDTO?,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onActivateClick: () -> Unit = {}
) {
    var agreementAccepted by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = { BbInnerPageHeader(title = BBLocalization.Current.Get(key = "1c536c06-90cf-4dfa-a5c9-532b5276374a", fallback = "B2B Index"), onBackClick = onBackClick) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant).padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            when {
                isLoading -> item { CompanyB2BLoadingState() }
                !errorMessage.isNullOrBlank() -> item {
                    CompanyB2BErrorState(message = errorMessage, onRetryClick = onRetryClick)
                }
                company == null -> item { CompanyB2BNotFoundState(onRetryClick = onRetryClick) }
                else -> {
                    item { CompanyB2BIndexIntroCard() }
                    item { CompanyB2BIndexSummaryCard(company = company) }
                    item { CompanyB2BIndexStatsGrid(company = company) }

                    item {
                        CompanyB2BIndexSection(
                            title = BBLocalization.Current.Get(key = "27c038e0-8bcd-475c-ba6a-83cdb08c4053", fallback = "B2B Index Ne Sağlar?"),
                            description = BBLocalization.Current.Get(key = "6c3662ff-e317-487e-9b82-4018ce5c6993", fallback = "Firmanızın toptan ticaret akışlarında daha görünür olmasına yardımcı olur."),
                            icon = Icons.Outlined.WorkspacePremium
                        ) {
                            CompanyB2BIndexBenefitRow(
                                title = BBLocalization.Current.Get(key = "a0a8ab67-0d72-439c-a52b-f3befadf44a1", fallback = "Global Görünürlük"),
                                description = "Şirket profiliniz uluslararası alıcılar için daha keşfedilebilir hale gelir.",
                                icon = Icons.Outlined.Public
                            )

                            CompanyB2BDivider()

                            CompanyB2BIndexBenefitRow(
                                title = BBLocalization.Current.Get(key = "64194408-118e-4ce1-8a70-b57998b16235", fallback = "RFQ Fırsatları"),
                                description = BBLocalization.Current.Get(key = "c5e99d2c-3365-4497-9620-c6f75b7919c7", fallback = "Potansiyel alıcılardan gelen fiyat teklifi süreçlerine daha yakın olursunuz."),
                                icon = Icons.Outlined.RequestQuote
                            )

                            CompanyB2BDivider()

                            CompanyB2BIndexBenefitRow(
                                title = BBLocalization.Current.Get(key = "fcc5e39f-8321-4b88-ba71-0e73835565c2", fallback = "Kurumsal Vitrin"),
                                description = "Şirket bilgileriniz daha düzenli ve güven veren bir B2B profilinde sunulur.",
                                icon = Icons.Outlined.Storefront
                            )
                        }
                    }

                    item {
                        CompanyB2BIndexAgreementCard(
                            isAccepted = agreementAccepted,
                            onAcceptedChange = { agreementAccepted = it }
                        )
                    }

                    item {
                        BbButton(
                            text = "Şirketimi B2B Index'e Dahil Et",
                            onClick = onActivateClick,
                            modifier = Modifier.fillMaxWidth(),
                            variant = if (agreementAccepted) BbButtonVariant.Primary else BbButtonVariant.Light,
                            size = BbButtonSize.Large,
                            enabled = agreementAccepted
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CompanyB2BIndexIntroCard() {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(BBIcon.BoxXl).background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = BBRadius.XlShape
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Language,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(
                    text = BBLocalization.Current.Get(key = "c144cc23-458b-4173-914b-858f4bf1ddf1", fallback = "Şirketinizi Global Alıcılara Açın"),
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Toptan alıcıların firmanızı keşfetmesi ve kurumsal profilinize ulaşması için şirketinizi B2B Index'e dahil edin.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CompanyB2BIndexSummaryCard(company: CompanyDTO) {
    Box(
        modifier = Modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.inverseSurface,
            shape = BBRadius.XlShape
        ).padding(BBSpacing.CardPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(BBIcon.BoxXl).background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = BBRadius.XlShape
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Storefront,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(
                    text = company.CompanyName.ifBlank { "-" },
                    style = BbTypography.titleMedium,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )

                Text(
                    text = listOf(company.CompanyType, company.CityName, company.CountryName)
                        .filter { it.isNotBlank() }
                        .joinToString(" · ")
                        .ifBlank { "-" },
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Muted)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompanyB2BIndexStatusPill(
                        text = if (company.B2bIndex) BBLocalization.Current.Get(key = "665ebc8d-3ce5-48db-b56c-28c1c2aeca0e", fallback = "B2B Index Aktif") else BBLocalization.Current.Get(key = "2c599839-1d89-42d6-85ce-7abfbf0f068a", fallback = "B2B Index Kapalı"),
                        icon = Icons.Outlined.Verified
                    )

                    CompanyB2BIndexStatusPill(
                        text = if (company.StatusId > 0) BBLocalization.Current.Get(key = "aba64183-7779-425e-b7c4-15f4b781cfd9", fallback = "Profil Aktif") else BBLocalization.Current.Get(key = "1dac9be8-6bab-4927-8ac4-6d1dadfc6d56", fallback = "Profil Pasif"),
                        icon = Icons.Outlined.CheckCircle
                    )
                }
            }
        }
    }
}

@Composable
private fun CompanyB2BIndexStatusPill(text: String, icon: ImageVector) {
    Row(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Overlay),
            shape = BBRadius.Badge
        ).padding(horizontal = BBSpacing.Space2, vertical = BBSpacing.Space1),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(BBIcon.Size2Xs)
        )

        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}

@Composable
private fun CompanyB2BIndexStatsGrid(company: CompanyDTO) {
    Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyB2BIndexStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Storefront,
                label = BBLocalization.Current.Get(key = "68ddf12a-a56d-4957-a06d-6e7c04a6e8b9", fallback = "Firma Kimliği"),
                value = company.CompanyKey.ifBlank { "-" }
            )

            CompanyB2BIndexStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.LocationOn,
                label = BBLocalization.Current.Get(key = "78edf13c-8be3-45b2-9550-a50e0e733a28", fallback = "Lokasyon"),
                value = company.CityName.ifBlank { "-" }
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyB2BIndexStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Public,
                label = BBLocalization.Current.Get(key = "8d5b3d5b-f34d-46d9-bbbb-a5407e8190a0", fallback = "Görünürlük"),
                value = if (company.B2bIndex) BBLocalization.Current.Get(key = "66e51b09-1fc9-48d0-bdfd-b795252d90de", fallback = "Açık") else BBLocalization.Current.Get(key = "fecd4b5c-9c9d-4d13-9906-49ac69360bfe", fallback = "Kapalı")
            )

            CompanyB2BIndexStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.RequestQuote,
                label = "RFQ",
                value = if (company.B2bIndex) BBLocalization.Current.Get(key = "66e51b09-1fc9-48d0-bdfd-b795252d90de", fallback = "Açık") else BBLocalization.Current.Get(key = "0ef9f86d-50af-4f26-a932-f66d3a835f98", fallback = "Hazır")
            )
        }
    }
}

@Composable
private fun CompanyB2BIndexStatCard(modifier: Modifier, icon: ImageVector, label: String, value: String) {
    BbCard(modifier = modifier, variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            Box(
                modifier = Modifier.size(BBIcon.BoxMd).background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = BBRadius.PillShape
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Ui)
                )
            }

            Text(text = label, style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = value, style = BbTypography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
private fun CompanyB2BIndexSection(
    title: String,
    description: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.None) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(BBSpacing.CardPadding),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(BBIcon.BoxMd).background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(BBIcon.Ui)
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                    Text(text = title, style = BbTypography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
                    Text(text = description, style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            CompanyB2BDivider()
            Column { content() }
        }
    }
}

@Composable
private fun CompanyB2BIndexBenefitRow(title: String, description: String, icon: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(BBIcon.BoxMd).background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.PillShape
            ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.Ui)
            )
        }

        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
            Text(text = title, style = BbTypography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
            Text(text = description, style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun CompanyB2BIndexAgreementCard(isAccepted: Boolean, onAcceptedChange: (Boolean) -> Unit) {
    BbCard(
        modifier = Modifier.fillMaxWidth().toggleable(
            value = isAccepted,
            role = Role.Checkbox,
            onValueChange = onAcceptedChange
        ),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = isAccepted,
                onCheckedChange = onAcceptedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.outline,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary
                )
            )

            Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(
                    text = "Bulbulustur Kullanıcı Sözleşmesi'ni okudum ve kabul ediyorum.",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "B2B Index'e dahil olduğunuzda şirket profiliniz ve uygun kurumsal bilgileriniz platform üzerinde görünür olabilir.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CompanyB2BLoadingState() {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun CompanyB2BErrorState(message: String, onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.ErrorOutline,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(BBIcon.Section)
            )
            Text(text = message, style = BbTypography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            BbButton(text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"), onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyB2BNotFoundState(onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Icon(
                imageVector = Icons.Outlined.Storefront,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.Section)
            )
            Text(text = BBLocalization.Current.Get(key = "4f467a63-d0ab-423f-ae06-65c598e5a641", fallback = "Firma bilgisi bulunamadı"), style = BbTypography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            BbButton(text = BBLocalization.Current.Get(key = "9d1ce783-da20-464b-9203-cd1ce09918c6", fallback = "Tekrar Dene"), onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyB2BDivider() {
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
}
