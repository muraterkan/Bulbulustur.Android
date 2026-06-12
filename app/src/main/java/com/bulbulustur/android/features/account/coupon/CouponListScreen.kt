package com.bulbulustur.android.features.account

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
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbInnerPageHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbIcon
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun CouponListScreen(
    onBackClick: () -> Unit = {}
) {
    val coupons = getDemoCoupons()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Kuponlarım",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            if (coupons.isEmpty()) {
                item {
                    CouponEmptyState()
                }
            }

            items(
                items = coupons,
                key = { coupon -> coupon.couponId }
            ) { coupon ->
                CouponCard(
                    coupon = coupon
                )
            }
        }
    }
}

@Composable
private fun CouponCard(
    coupon: CouponUiModel
) {
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
                coupon = coupon
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                CouponInfoBox(
                    title = "Geçerlilik tarihi",
                    value = coupon.expireDate,
                    icon = Icons.Outlined.CalendarMonth,
                    iconColor = BbColors.Yellow.Yellow800
                )

                CouponInfoBox(
                    title = "Kullanım tarihi / sipariş no",
                    value = coupon.usageText,
                    icon = Icons.Outlined.ReceiptLong,
                    iconColor = BbColors.Blue.Blue600
                )

                CouponStatusBadge(
                    status = coupon.status
                )
            }
        }
    }
}

@Composable
private fun CouponCardHeader(
    coupon: CouponUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Yellow.Yellow50)
            .padding(BbSpacing.CardPadding),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        CouponIconBox(
            icon = Icons.Outlined.LocalOffer
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Text(
                text = "Kupon kodu",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = coupon.code,
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
                text = coupon.amountText,
                style = MaterialTheme.typography.titleSmall,
                color = BbColors.Yellow.Yellow800
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
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BbIcon.SizeMd)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
private fun CouponStatusBadge(
    status: CouponStatus
) {
    val backgroundColor = when (status) {
        CouponStatus.Active -> BbColors.Orange.Orange50
        CouponStatus.Used -> BbColors.Green.Green50
        CouponStatus.Expired -> BbColors.Red.Red50
    }

    val textColor = when (status) {
        CouponStatus.Active -> BbColors.Orange.Orange700
        CouponStatus.Used -> BbColors.Green.Green700
        CouponStatus.Expired -> BbColors.Red.Red700
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
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.Space3,
                vertical = BbSpacing.Space2
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(BbIcon.SizeSm)
            )

            Text(
                text = status.title,
                style = MaterialTheme.typography.labelSmall,
                color = textColor
            )
        }
    }
}

@Composable
private fun CouponIconBox(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxLg)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.SizeLg)
        )
    }
}

@Composable
private fun CouponSmallIconBox(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxMd)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbIcon.SizeMd)
        )
    }
}

@Composable
private fun CouponEmptyState() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            CouponIconBox(
                icon = Icons.Outlined.LocalOffer
            )

            Text(
                text = "Kupon bulunamadı",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabınıza tanımlı kupon oluştuğunda burada listelenecek.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getDemoCoupons(): List<CouponUiModel> {
    return listOf(
        CouponUiModel(
            couponId = 1,
            code = "WELCOME2026",
            amountText = "150,00 ₺",
            expireDate = "1.01.0001 00:00:00",
            usageText = "-",
            status = CouponStatus.Active
        ),
        CouponUiModel(
            couponId = 2,
            code = "BULBUL25",
            amountText = "250,00 ₺",
            expireDate = "1.01.0001 00:00:00",
            usageText = "-",
            status = CouponStatus.Used
        )
    )
}

private enum class CouponStatus(
    val title: String
) {
    Active(
        title = "Etkin"
    ),
    Used(
        title = "Kullanılmış"
    ),
    Expired(
        title = "Süresi Dolmuş"
    )
}

private data class CouponUiModel(
    val couponId: Int,
    val code: String,
    val amountText: String,
    val expireDate: String,
    val usageText: String,
    val status: CouponStatus
)