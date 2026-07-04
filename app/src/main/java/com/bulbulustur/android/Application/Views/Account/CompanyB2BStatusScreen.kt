package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.bulbulustur.android.businesslayer.Core.DTO.MemberSubscriptionDTO

@Composable
fun CompanyB2BStatusScreen(
    company: CompanyDTO?,
    subscription: MemberSubscriptionDTO?,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onB2BManagementPanelClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = { BbInnerPageHeader(title = "B2B Index Durumu", onBackClick = onBackClick) }
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
                isLoading -> item { CompanyB2BStatusLoadingState() }
                !errorMessage.isNullOrBlank() -> item {
                    CompanyB2BStatusErrorState(message = errorMessage, onRetryClick = onRetryClick)
                }
                company == null -> item { CompanyB2BStatusNotFoundState(onRetryClick = onRetryClick) }
                else -> {
                    item { CompanyB2BStatusIntroCard(active = company.B2bIndex) }
                    item { CompanyB2BActiveSummaryCard(company = company, subscription = subscription) }
                    item { CompanyB2BStatusStatsGrid(company = company, subscription = subscription) }

                    item {
                        CompanyB2BStatusSection(
                            title = "B2B Listeleme Bilgileri",
                            description = "Bu bilgiler şirketinizin Bulbulustur toptan satış tarafındaki görünürlüğünü gösterir.",
                            icon = Icons.Outlined.WorkspacePremium
                        ) {
                            CompanyB2BStatusInfoRow(
                                title = "Şirket Adı",
                                value = company.CompanyName.ifBlank { "-" },
                                icon = Icons.Outlined.Business
                            )

                            CompanyB2BStatusDivider()

                            CompanyB2BStatusInfoRow(
                                title = "Listeleme Durumu",
                                value = if (company.B2bIndex) "B2B Index Aktif" else "B2B Index Kapalı",
                                icon = Icons.Outlined.Verified
                            )

                            CompanyB2BStatusDivider()

                            CompanyB2BStatusInfoRow(
                                title = "Abonelik Tipi",
                                value = subscription.GetSubscriptionTitle(),
                                icon = Icons.Outlined.Storefront
                            )

                            CompanyB2BStatusDivider()

                            CompanyB2BStatusInfoRow(
                                title = "Firma Kimliği",
                                value = company.CompanyKey.ifBlank { "-" },
                                icon = Icons.Outlined.Language
                            )
                        }
                    }

                    if (company.B2bIndex) {
                        item {
                            CompanyB2BManagementPanelCard(onClick = onB2BManagementPanelClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CompanyB2BStatusIntroCard(active: Boolean) {
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
                    imageVector = Icons.Outlined.Verified,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(
                    text = if (active) "Şirketiniz Toptan Ticaret Görünürlüğünde" else "B2B Index Görünürlüğünüz Kapalı",
                    style = BbTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = if (active) {
                        "Bulbulustur B2B Index üzerinde şirketiniz aktif olarak listeleniyor."
                    } else {
                        "Şirketiniz şu anda B2B Index üzerinde listelenmiyor."
                    },
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CompanyB2BActiveSummaryCard(company: CompanyDTO, subscription: MemberSubscriptionDTO?) {
    Box(
        modifier = Modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.inverseSurface,
            shape = BBRadius.XlShape
        ).padding(BBSpacing.CardPadding)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)) {
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
                        text = subscription.GetSubscriptionTitle(),
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = BBAlpha.Muted)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompanyB2BStatusPill(
                    text = if (company.B2bIndex) "B2B Index Aktif" else "B2B Index Kapalı",
                    icon = Icons.Outlined.Verified
                )

                CompanyB2BStatusPill(
                    text = if (company.B2bIndex) "Yönetim Hazır" else "Aktivasyon Gerekli",
                    icon = Icons.Outlined.WorkspacePremium
                )
            }
        }
    }
}

@Composable
private fun CompanyB2BStatusPill(text: String, icon: ImageVector) {
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
private fun CompanyB2BStatusStatsGrid(company: CompanyDTO, subscription: MemberSubscriptionDTO?) {
    Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyB2BStatusStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Verified,
                label = "Listeleme",
                value = if (company.B2bIndex) "Aktif" else "Kapalı"
            )

            CompanyB2BStatusStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.Storefront,
                label = "Abonelik",
                value = subscription?.SubscriptionPlanTypeName?.ifBlank { "-" } ?: "-"
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)) {
            CompanyB2BStatusStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.RequestQuote,
                label = "RFQ",
                value = if (company.B2bIndex) "Açık" else "Kapalı"
            )

            CompanyB2BStatusStatCard(
                modifier = Modifier.weight(1f),
                icon = Icons.Outlined.DateRange,
                label = "Kuruluş",
                value = company.YearEstablished.ifBlank { "-" }
            )
        }
    }
}

@Composable
private fun CompanyB2BStatusStatCard(modifier: Modifier, icon: ImageVector, label: String, value: String) {
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
private fun CompanyB2BStatusSection(
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

            CompanyB2BStatusDivider()
            Column { content() }
        }
    }
}

@Composable
private fun CompanyB2BStatusInfoRow(title: String, value: String, icon: ImageVector) {
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
            Text(text = title, style = BbTypography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = value, style = BbTypography.titleSmall, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
private fun CompanyB2BManagementPanelCard(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = BBRadius.XlShape
        ).clickable(onClick = onClick).padding(BBSpacing.CardPadding)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(BBIcon.BoxLg).background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = BBRadius.PillShape
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.OpenInNew,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(
                    text = "B2B Yönetim Paneline Git",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Text(
                    text = "Şirket profilinizi, ürünlerinizi ve toptan satış operasyonlarınızı yönetmek için panel tarafına geçin.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = BBAlpha.Muted)
                )
            }

            Box(
                modifier = Modifier.size(BBIcon.BoxSm).background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = BBRadius.PillShape
                ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.SizeSm)
                )
            }
        }
    }
}

@Composable
private fun CompanyB2BStatusLoadingState() {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun CompanyB2BStatusErrorState(message: String, onRetryClick: () -> Unit) {
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
            BbButton(text = "Tekrar Dene", onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyB2BStatusNotFoundState(onRetryClick: () -> Unit) {
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
            Text(text = "Firma bilgisi bulunamadı", style = BbTypography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            BbButton(text = "Tekrar Dene", onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun CompanyB2BStatusDivider() {
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
}

private fun MemberSubscriptionDTO?.GetSubscriptionTitle(): String {
    if (this == null) return "-"
    if (Subscription.isNotBlank()) return Subscription
    if (SubscriptionTypeName.isNotBlank() && SubscriptionPlanTypeName.isNotBlank()) return "$SubscriptionTypeName / $SubscriptionPlanTypeName"
    if (SubscriptionTypeName.isNotBlank()) return SubscriptionTypeName
    if (SubscriptionPlanTypeName.isNotBlank()) return SubscriptionPlanTypeName
    return "-"
}
