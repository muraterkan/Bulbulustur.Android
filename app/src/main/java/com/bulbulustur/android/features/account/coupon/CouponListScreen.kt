package com.bulbulustur.android.features.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun CouponListScreen(
    onBackClick: () -> Unit = {}
) {
    val coupons = getDemoCoupons()

    AccountPageScaffold(
        title = "Kuponlarım",
        kicker = "Kupon Yönetimi",
        description = "Hesabınıza tanımlanan kuponları, kullanım durumlarını, geçerlilik tarihlerini ve sipariş bağlantılarını buradan takip edin.",
        backButtonText = "Hesabıma Dön",
        onBackClick = onBackClick
    ) {
        LazyColumn(
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
                .background(BbColors.Surface)
        ) {
            CouponCardHeader(
                coupon = coupon
            )

            CouponDivider()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
            ) {
                CouponInfoBox(
                    title = "GEÇERLİLİK TARİHİ",
                    value = coupon.expireDate,
                    icon = Icons.Outlined.CalendarMonth,
                    iconColor = BbColors.Yellow.Yellow800
                )

                CouponInfoBox(
                    title = "KULLANIM TARİHİ / SİPARİŞ NO",
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
            CouponLabel(
                text = "KUPON"
            )

            Text(
                text = coupon.code,
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            CouponLabel(
                text = "MİKTAR"
            )

            Text(
                text = coupon.amountText,
                style = BbTypography.titleSmall,
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
                color = BbColors.SurfaceMuted,
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
            modifier = Modifier.size(BbSpacing.Space5)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title,
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )

            Text(
                text = value,
                style = BbTypography.bodyMedium,
                color = BbColors.TextStrong
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
                modifier = Modifier.size(BbSpacing.Space4)
            )

            Text(
                text = status.title,
                style = BbTypography.labelSmall,
                color = textColor
            )
        }
    }
}

@Composable
private fun CouponLabel(
    text: String
) {
    Text(
        text = text,
        style = BbTypography.labelSmall,
        color = BbColors.TextMuted
    )
}

@Composable
private fun CouponIconBox(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space12)
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
            modifier = Modifier.size(BbSpacing.Space6)
        )
    }
}

@Composable
private fun CouponSmallIconBox(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space9)
            .background(
                color = BbColors.Surface,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BbColors.Yellow.Yellow800,
            modifier = Modifier.size(BbSpacing.Space5)
        )
    }
}

@Composable
private fun CouponDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Border)
            .padding(top = BbSpacing.None)
    )
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
                text = "Kayıt bulunamadı!",
                style = BbTypography.titleMedium,
                color = BbColors.TextStrong
            )

            Text(
                text = "Hesabınıza tanımlı kupon bulunmuyor. Kuponlarınız oluştuğunda burada listelenecek.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
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