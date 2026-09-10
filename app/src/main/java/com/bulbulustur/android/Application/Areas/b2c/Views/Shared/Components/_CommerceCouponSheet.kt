package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BbCommerceCouponSheet(
    coupons: List<MemberCouponDTO>,
    selectedCoupon: MemberCouponDTO?,
    basketTotal: Double,
    isLoading: Boolean,
    errorMessage: String?,
    onCouponCodeApply: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var couponCode by rememberSaveable(
        selectedCoupon?.MemberCouponId
    ) {
        mutableStateOf(
            selectedCoupon
                ?.CouponCode
                .orEmpty()
        )
    }

    val availableCoupons =
        coupons.filter { coupon ->
            coupon.IsCommerceCouponUsable(
                basketTotal =
                    basketTotal
            )
        }

    ModalBottomSheet(
        onDismissRequest =
            onDismiss,
        containerColor =
            MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(
                        start =
                            BBSpacing.PageHorizontal,
                        end =
                            BBSpacing.PageHorizontal,
                        bottom =
                            BBSpacing.PageBottomCompact
                    ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space3
                )
        ) {
            Text(
                text =
                    "Kupon ve İndirimler",
                style =
                    MaterialTheme.typography.titleLarge,
                fontWeight =
                    FontWeight.Bold
            )

            OutlinedTextField(
                value =
                    couponCode,
                onValueChange = {
                    couponCode =
                        it
                },
                modifier =
                    Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text =
                            "Kupon"
                    )
                },
                singleLine =
                    true,
                shape =
                    BBRadius.Input
            )

            BbButton(
                text =
                    "Kuponu Uygula",
                onClick = {
                    val code =
                        couponCode.trim()

                    if (
                        code.isNotBlank()
                    ) {
                        onCouponCodeApply(
                            code
                        )

                        onDismiss()
                    }
                },
                modifier =
                    Modifier.fillMaxWidth(),
                variant =
                    BbButtonVariant.Primary,
                size =
                    BbButtonSize.Medium,
                enabled =
                    couponCode.isNotBlank()
            )

            HorizontalDivider(
                color =
                    MaterialTheme.colorScheme
                        .outlineVariant
            )

            Text(
                text =
                    "Kullanılabilir Kuponlar",
                style =
                    MaterialTheme.typography.titleSmall,
                fontWeight =
                    FontWeight.Bold
            )

            when {
                isLoading -> {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(
                                    96.dp
                                ),
                        contentAlignment =
                            Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color =
                                MaterialTheme.colorScheme.primary
                        )
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    Text(
                        text =
                            errorMessage,
                        style =
                            MaterialTheme.typography.bodyMedium,
                        color =
                            MaterialTheme.colorScheme.error
                    )
                }

                availableCoupons.isEmpty() -> {
                    Text(
                        text =
                            "Kullanılabilir kupon bulunamadı.",
                        style =
                            MaterialTheme.typography.bodyMedium,
                        color =
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                    )
                }

                else -> {
                    LazyColumn(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .heightIn(
                                    max =
                                        360.dp
                                )
                    ) {
                        itemsIndexed(
                            items =
                                availableCoupons,
                            key = {
                                    _,
                                    coupon ->
                                coupon.MemberCouponId
                            }
                        ) {
                                index,
                                coupon ->

                            BbCommerceCouponRow(
                                coupon =
                                    coupon,
                                 onClick = {
                                    couponCode =
                                        coupon.CouponCode
                                            .orEmpty()
                                }
                            )

                            if (
                                index !=
                                availableCoupons.lastIndex
                            ) {
                                HorizontalDivider(
                                    color =
                                        MaterialTheme.colorScheme
                                            .outlineVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun BbCommerceCouponRow(
    coupon: MemberCouponDTO,
    onClick: () -> Unit
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .padding(
                    vertical =
                        BBSpacing.Space3
                ),
        horizontalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space3
            ),
        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Column(
            modifier =
                Modifier.weight(
                    1f
                ),
            verticalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space1
                )
        ) {
            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                Text(
                    text =
                        coupon.CouponCode
                            .orEmpty()
                            .ifBlank {
                                "Kupon"
                            },
                    style =
                        MaterialTheme.typography.titleSmall,
                    fontWeight =
                        FontWeight.Bold
                )

                if (
                    coupon.Amount > 0.0
                ) {
                    Text(
                        text =
                            FormatCommerceCouponMoney(
                                coupon.Amount
                            ),
                        style =
                            MaterialTheme.typography.titleSmall,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme.colorScheme.primary
                    )
                }
            }

            val description =
                coupon.Descripion.orEmpty()

            if (
                description.isNotBlank()
            ) {
                Text(
                    text =
                        description,
                    style =
                        MaterialTheme.typography.bodySmall,
                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant,
                    maxLines =
                        2,
                    overflow =
                        TextOverflow.Ellipsis
                )
            }

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(
                        BBSpacing.Space2
                    ),
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                if (
                    coupon.UpAmount > 0.0
                ) {
                    Text(
                        text =
                            "${
                                FormatCommerceCouponMoney(
                                    coupon.UpAmount
                                )
                            } ve üzeri",
                        style =
                            MaterialTheme.typography.labelSmall,
                        color =
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                    )
                }

                Text(
                    text =
                        "Kullanılabilir",
                    style =
                        MaterialTheme.typography.labelSmall,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


private fun MemberCouponDTO.IsCommerceCouponUsable(
    basketTotal: Double
): Boolean {
    if (
        Used != 0
    ) {
        return false
    }

    if (
        OrderId
            .orEmpty()
            .isNotBlank()
    ) {
        return false
    }

    val lastUsingDate =
        LastUsingDate
            .orEmpty()
            .ToCommerceCouponLocalDate()

    if (
        lastUsingDate != null &&
        lastUsingDate.isBefore(
            LocalDate.now()
        )
    ) {
        return false
    }

    if (
        UpAmount > 0.0 &&
        basketTotal < UpAmount
    ) {
        return false
    }

    return true
}


private fun String.ToCommerceCouponLocalDate(): LocalDate? {
    val value =
        trim()

    if (
        value.isBlank() ||
        value.startsWith(
            "0001-01-01"
        ) ||
        value.startsWith(
            "1900-01-01"
        )
    ) {
        return null
    }

    return runCatching {
        OffsetDateTime
            .parse(
                value
            )
            .toLocalDate()
    }.getOrElse {
        runCatching {
            LocalDateTime
                .parse(
                    value
                )
                .toLocalDate()
        }.getOrElse {
            runCatching {
                LocalDate.parse(
                    value.substringBefore(
                        "T"
                    )
                )
            }.getOrNull()
        }
    }
}


private fun FormatCommerceCouponMoney(
    value: Double
): String {
    return "₺${
        String
            .format(
                "%.2f",
                value
            )
            .replace(
                ".",
                ","
            )
    }"
}
