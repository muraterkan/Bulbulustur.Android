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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.HourglassTop
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Redeem
import androidx.compose.material.icons.outlined.Timelapse
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
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
import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CouponListScreen(
    coupons: List<MemberCouponDTO> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Kuponlarım",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && coupons.isEmpty() -> {
                CouponLoadingState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            !errorMessage.isNullOrBlank() && coupons.isEmpty() -> {
                CouponErrorState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    message = errorMessage,
                    onRetryClick = onRetryClick
                )
            }

            coupons.isEmpty() -> {
                CouponEmptyState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(innerPadding),
                    contentPadding = PaddingValues(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottom
                    ),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
                ) {
                    if (!errorMessage.isNullOrBlank()) {
                        item {
                            CouponFeedbackCard(
                                message = errorMessage,
                                onRetryClick = onRetryClick
                            )
                        }
                    }

                    items(
                        items = coupons,
                        key = { coupon -> coupon.MemberCouponId }
                    ) { coupon ->
                        CouponCard(coupon = coupon)
                    }
                }
            }
        }
    }
}

@Composable
private fun CouponCard(coupon: MemberCouponDTO) {
    val status = coupon.ResolveCouponStatus()
    val usageText = coupon.ResolveUsageText()
    val expireDate = coupon.LastUsingDate.orEmpty().ToCouponDateText()
    val code = coupon.CouponCode.orEmpty().ifBlank { "Kupon" }

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            CouponCardHeader(
                code = code,
                amountText = coupon.Amount.ToTurkishCurrency()
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                CouponInfoBox(
                    title = "Geçerlilik tarihi",
                    value = expireDate,
                    icon = Icons.Outlined.CalendarMonth,
                    iconColor = BBColors.Yellow.Yellow800
                )

                CouponInfoBox(
                    title = "Kullanım tarihi / sipariş no",
                    value = usageText,
                    icon = Icons.Outlined.ReceiptLong,
                    iconColor = BBColors.Blue.Blue600
                )

                if (coupon.UpAmount > 0.0) {
                    CouponInfoBox(
                        title = "Minimum sepet tutarı",
                        value = coupon.UpAmount.ToTurkishCurrency(),
                        icon = Icons.Outlined.LocalOffer,
                        iconColor = BBColors.Yellow.Yellow800
                    )
                }

                if (coupon.Descripion.orEmpty().isNotBlank()) {
                    CouponDescriptionBox(
                        description = coupon.Descripion.orEmpty()
                    )
                }

                CouponStatusBadge(status = status)
            }
        }
    }
}

@Composable
private fun CouponCardHeader(
    code: String,
    amountText: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(BBSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        CouponIconBox(
            icon = Icons.Outlined.LocalOffer
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Text(
                text = "Kupon kodu",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = code,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Miktar",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = amountText,
                style = MaterialTheme.typography.titleSmall,
                color = BBColors.Yellow.Yellow800
            )
        }

        CouponSmallIconBox(
            icon = Icons.Outlined.Redeem
        )
    }
}

@Composable
private fun CouponInfoBox(
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.SizeMd)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun CouponDescriptionBox(description: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CouponStatusBadge(status: CouponStatus) {
    val backgroundColor = when (status) {
        CouponStatus.Active -> BBColors.Orange.Orange50
        CouponStatus.Used -> BBColors.Green.Green50
        CouponStatus.Expired -> BBColors.Red.Red50
    }

    val textColor = when (status) {
        CouponStatus.Active -> BBColors.Orange.Orange700
        CouponStatus.Used -> BBColors.Green.Green700
        CouponStatus.Expired -> BBColors.Red.Red700
    }

    val icon = when (status) {
        CouponStatus.Active -> Icons.Outlined.HourglassTop
        CouponStatus.Used -> Icons.Outlined.CheckCircle
        CouponStatus.Expired -> Icons.Outlined.Timelapse
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Text(
                text = status.Title,
                style = MaterialTheme.typography.labelSmall,
                color = textColor
            )
        }
    }
}

@Composable
private fun CouponIconBox(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxLg)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.SizeLg)
        )
    }
}

@Composable
private fun CouponSmallIconBox(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxMd)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Yellow.Yellow800,
            modifier = Modifier.size(BBIcon.SizeMd)
        )
    }
}

@Composable
private fun CouponLoadingState(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun CouponErrorState(
    modifier: Modifier,
    message: String,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(BBSpacing.PageHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CouponIconBox(
            icon = Icons.Outlined.LocalOffer
        )

        Text(
            text = "Kuponlar alınamadı",
            modifier = Modifier.padding(top = BBSpacing.Space3),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = message,
            modifier = Modifier.padding(top = BBSpacing.Space2),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        BbButton(
            text = "Tekrar Dene",
            onClick = onRetryClick,
            modifier = Modifier.padding(top = BBSpacing.Space4),
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Medium
        )
    }
}

@Composable
private fun CouponFeedbackCard(
    message: String,
    onRetryClick: () -> Unit
) {
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
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )

            BbButton(
                text = "Tekrar Dene",
                onClick = onRetryClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Secondary,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun CouponEmptyState(modifier: Modifier) {
    Column(
        modifier = modifier.padding(BBSpacing.PageHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CouponIconBox(
            icon = Icons.Outlined.LocalOffer
        )

        Text(
            text = "Kupon bulunamadı",
            modifier = Modifier.padding(top = BBSpacing.Space3),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Hesabınıza tanımlı kupon oluştuğunda burada listelenecek.",
            modifier = Modifier.padding(top = BBSpacing.Space2),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun MemberCouponDTO.ResolveCouponStatus(): CouponStatus {
    if (Used == 1 || OrderId.orEmpty().isNotBlank()) {
        return CouponStatus.Used
    }

    val lastUsingDate = LastUsingDate.orEmpty().ToCouponLocalDate()

    if (lastUsingDate != null && lastUsingDate.isBefore(LocalDate.now())) {
        return CouponStatus.Expired
    }

    return CouponStatus.Active
}

private fun MemberCouponDTO.ResolveUsageText(): String {
    val usedDate = UsedDate.orEmpty().ToCouponDateText()
    val orderId = OrderId.orEmpty().trim()

    return when {
        usedDate != "-" && orderId.isNotBlank() -> "$usedDate / $orderId"
        usedDate != "-" -> usedDate
        orderId.isNotBlank() -> orderId
        else -> "-"
    }
}

private fun Double.ToTurkishCurrency(): String {
    val symbols = DecimalFormatSymbols(Locale("tr", "TR"))
    val formatter = DecimalFormat("#,##0.00", symbols)

    return "${formatter.format(this)} ₺"
}

private fun String.ToCouponDateText(): String {
    val date = ToCouponLocalDate() ?: return "-"

    if (date.year <= 1) {
        return "-"
    }

    return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}

private fun String.ToCouponLocalDate(): LocalDate? {
    val normalizedValue = trim()

    if (
        normalizedValue.isBlank() ||
        normalizedValue.startsWith("0001-01-01") ||
        normalizedValue.startsWith("1.01.0001")
    ) {
        return null
    }

    return runCatching {
        OffsetDateTime.parse(normalizedValue).toLocalDate()
    }.getOrElse {
        runCatching {
            LocalDateTime.parse(normalizedValue).toLocalDate()
        }.getOrElse {
            runCatching {
                LocalDate.parse(normalizedValue.substringBefore("T"))
            }.getOrNull()
        }
    }
}

private enum class CouponStatus(val Title: String) {
    Active(Title = "Etkin"),
    Used(Title = "Kullanılmış"),
    Expired(Title = "Süresi Dolmuş")
}