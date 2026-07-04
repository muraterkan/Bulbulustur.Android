package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.bulbulustur.android.businesslayer.Core.DTO.MemberSubscriptionDTO
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun SubscriptionDetailScreen(
    subscription: MemberSubscriptionDTO?,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = { BbInnerPageHeader(title = "Abonelik Detayı", onBackClick = onBackClick) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surfaceVariant).padding(innerPadding),
            contentPadding = PaddingValues(start = BBSpacing.PageHorizontal, top = BBSpacing.PageTopCompact, end = BBSpacing.PageHorizontal, bottom = BBSpacing.PageBottom),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            when {
                isLoading -> item { SubscriptionDetailLoadingState() }
                !errorMessage.isNullOrBlank() -> item { SubscriptionDetailErrorState(message = errorMessage, onRetryClick = onRetryClick) }
                subscription == null -> item { SubscriptionDetailNotFoundState(onRetryClick = onRetryClick) }
                else -> {
                    item { SubscriptionHeroCard(subscription = subscription) }

                    item {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
                            SubscriptionDetailInfoBox(modifier = Modifier.weight(1f), title = "BAŞLANGIÇ", value = subscription.StartDate.ToSubscriptionDateText(), iconColor = BBColors.Blue.Blue600)
                            SubscriptionDetailInfoBox(modifier = Modifier.weight(1f), title = "BİTİŞ", value = subscription.EndDate.ToSubscriptionDateText(), iconColor = BBColors.Orange.Orange600)
                        }
                    }

                    item { SubscriptionDetailPriceCard(subscription = subscription) }
                    item { SubscriptionPlanInfoCard(subscription = subscription) }

                    item {
                        BbButton(
                            text = "Aboneliklerime Dön",
                            onClick = onBackClick,
                            modifier = Modifier.fillMaxWidth(),
                            variant = BbButtonVariant.Light,
                            size = BbButtonSize.Medium,
                            leadingIcon = { Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(BBIcon.ButtonIcon)) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SubscriptionHeroCard(subscription: MemberSubscriptionDTO) {
    val active = subscription.IsActiveSubscription()

    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(BBIcon.BoxMd).background(color = MaterialTheme.colorScheme.primaryContainer, shape = BBRadius.LgShape), contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Outlined.Verified, contentDescription = null, tint = BBColors.Yellow.Yellow800, modifier = Modifier.size(BBIcon.Action))
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(text = if (active) "AKTİF ABONELİK" else "SÜRESİ DOLMUŞ ABONELİK", style = MaterialTheme.typography.labelSmall, color = if (active) BBColors.Green.Green700 else MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = subscription.GetSubscriptionTitle(), style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Text(text = "Abonelik dönemi ve plan ücretiniz aşağıdaki bilgilerle kayıtlıdır.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun SubscriptionDetailInfoBox(modifier: Modifier, title: String, value: String, iconColor: Color) {
    Row(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surface, shape = BBRadius.LgShape).padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Outlined.CalendarMonth, contentDescription = null, tint = iconColor, modifier = Modifier.size(BBIcon.Inline))

        Column(verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
            Text(text = title, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = value, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}

@Composable
private fun SubscriptionDetailPriceCard(subscription: MemberSubscriptionDTO) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Medium) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(BBIcon.BoxMd).background(color = MaterialTheme.colorScheme.primaryContainer, shape = BBRadius.LgShape), contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Outlined.CreditCard, contentDescription = null, tint = BBColors.Yellow.Yellow800, modifier = Modifier.size(BBIcon.Action))
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(text = "Plan Ücreti", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = subscription.GetPriceText(), style = MaterialTheme.typography.titleMedium, color = BBColors.Yellow.Yellow800)
            }
        }
    }
}

@Composable
private fun SubscriptionPlanInfoCard(subscription: MemberSubscriptionDTO) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.None) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth().padding(BBSpacing.CardPadding), verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)) {
                Text(text = "Plan Bilgileri", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Text(text = "Aboneliğinize ait temel plan, dönem ve ücret bilgileri.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            SubscriptionPlanRow("Abonelik Tipi", subscription.SubscriptionTypeName.ifBlank { "-" })
            SubscriptionPlanRow("Plan Tipi", subscription.SubscriptionPlanTypeName.ifBlank { "-" })
            SubscriptionPlanRow("Başlangıç Tarihi", subscription.StartDate.ToSubscriptionDateText())
            SubscriptionPlanRow("Bitiş Tarihi", subscription.EndDate.ToSubscriptionDateText())
            SubscriptionPlanRow("Plan Fiyatı", subscription.GetPriceText(), valueColor = BBColors.Yellow.Yellow800)
        }
    }
}

@Composable
private fun SubscriptionPlanRow(label: String, value: String, valueColor: Color = MaterialTheme.colorScheme.onSurface) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = BBSpacing.CardPadding, vertical = BBSpacing.Space3),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelSmall, color = valueColor)
    }

    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
}

@Composable
private fun SubscriptionDetailLoadingState() {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun SubscriptionDetailErrorState(message: String, onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.ErrorOutline, contentDescription = null, tint = MaterialTheme.colorScheme.error, modifier = Modifier.size(BBIcon.Empty))
            Text(text = message, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            BbButton(text = "Tekrar Dene", onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

@Composable
private fun SubscriptionDetailNotFoundState(onRetryClick: () -> Unit) {
    BbCard(modifier = Modifier.fillMaxWidth(), variant = BbCardVariant.Outlined, padding = BbCardPadding.Large) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)) {
            Icon(imageVector = Icons.Outlined.Subscriptions, contentDescription = null, tint = BBColors.Yellow.Yellow800, modifier = Modifier.size(BBIcon.Empty))
            Text(text = "Abonelik bulunamadı", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
            Text(text = "İstenen abonelik kaydı bulunamadı veya görüntülenemiyor.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            BbButton(text = "Tekrar Dene", onClick = onRetryClick, variant = BbButtonVariant.Primary, size = BbButtonSize.Small)
        }
    }
}

private fun MemberSubscriptionDTO.GetSubscriptionTitle(): String {
    if (Subscription.isNotBlank()) return Subscription
    if (SubscriptionTypeName.isNotBlank() && SubscriptionPlanTypeName.isNotBlank()) return "$SubscriptionTypeName / $SubscriptionPlanTypeName"
    if (SubscriptionTypeName.isNotBlank()) return SubscriptionTypeName
    if (SubscriptionPlanTypeName.isNotBlank()) return SubscriptionPlanTypeName
    return "Abonelik"
}

private fun MemberSubscriptionDTO.GetPriceText(): String {
    val symbol = CurrencySymbol.ifBlank { "₺" }
    return "${String.format(Locale("tr", "TR"), "%.2f", PlanPrice)} $symbol"
}

private fun MemberSubscriptionDTO.IsActiveSubscription(): Boolean {
    val endDate = EndDate.ToSubscriptionLocalDate() ?: return StatusId > 0
    return !endDate.isBefore(LocalDate.now())
}

private fun String.ToSubscriptionDateText(): String {
    val date = ToSubscriptionLocalDate() ?: return ifBlank { "-" }
    return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale("tr", "TR")))
}

private fun String.ToSubscriptionLocalDate(): LocalDate? {
    if (isBlank()) return null
    return runCatching { LocalDate.parse(substringBefore("T")) }.getOrNull()
}
