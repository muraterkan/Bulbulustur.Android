package com.bulbulustur.android.features.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonSize
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing
import com.bulbulustur.android.ui.theme.BbTypography

@Composable
fun OrderContractScreen(
    orderCode: String = "ORD-F4QO-AFPR-J5EX",
    createdDate: String = "10 Mayıs 2026 08:54",
    onBackClick: () -> Unit = {},
    onPrintClick: () -> Unit = {}
) {
    OrderDocumentScaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.PageTopCompact
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                OrderContractHeader(
                    orderCode = orderCode,
                    onBackClick = onBackClick
                )
            }

            item {
                OrderContractSummaryCard(
                    orderCode = orderCode,
                    createdDate = createdDate,
                    onPrintClick = onPrintClick
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
                ) {
                    OrderContractInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "SİPARİŞ NUMARASI",
                        value = orderCode,
                        icon = Icons.Outlined.Numbers
                    )

                    OrderContractInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "OLUŞTURULMA TARİHİ",
                        value = createdDate,
                        icon = Icons.Outlined.CalendarMonth
                    )
                }
            }

            item {
                OrderContractLegalCard(
                    orderCode = orderCode
                )
            }

            item {
                Spacer(modifier = Modifier.height(BbSpacing.PageBottomCompact))
            }
        }
    }
}

@Composable
private fun OrderDocumentScaffold(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.SurfaceMuted)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        content()
    }
}

@Composable
private fun OrderContractHeader(
    orderCode: String,
    onBackClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            BbButton(
                text = "Siparişlerime Dön",
                onClick = onBackClick,
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Small,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbSpacing.Space5)
                    )
                }
            )

            OrderContractKicker(
                text = "Sözleşme Belgesi"
            )

            Text(
                text = "Mesafeli Satış Sözleşmesi",
                style = BbTypography.headlineSmall,
                color = BbColors.TextStrong
            )

            Text(
                text = "Siparişe ait mesafeli satış sözleşmesini bu ekrandan görüntüleyebilir ve ihtiyaç halinde yazdırabilirsiniz.",
                style = BbTypography.bodySmall,
                color = BbColors.TextMuted
            )

            OrderContractOrderCodeBox(
                orderCode = orderCode
            )
        }
    }
}

@Composable
private fun OrderContractSummaryCard(
    orderCode: String,
    createdDate: String,
    onPrintClick: () -> Unit
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderContractIconBox(
                    icon = Icons.Outlined.ReceiptLong
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "SİPARİŞ NUMARASI",
                        style = BbTypography.labelSmall,
                        color = BbColors.TextMuted
                    )

                    Text(
                        text = orderCode,
                        style = BbTypography.titleSmall,
                        color = BbColors.TextStrong
                    )

                    Text(
                        text = createdDate,
                        style = BbTypography.bodySmall,
                        color = BbColors.TextMuted
                    )
                }
            }

            BbButton(
                text = "Yazdır",
                onClick = onPrintClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Print,
                        contentDescription = null,
                        tint = BbColors.TextStrong,
                        modifier = Modifier.size(BbSpacing.Space5)
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderContractInfoBox(
    modifier: Modifier,
    title: String,
    value: String,
    icon: ImageVector
) {
    Box(
        modifier = modifier
            .background(
                color = BbColors.SurfaceMuted,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            OrderContractSmallIconBox(
                icon = icon
            )

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
private fun OrderContractLegalCard(
    orderCode: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BbColors.SurfaceMuted)
                    .padding(BbSpacing.CardPadding)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "HUKUKİ METİN",
                        style = BbTypography.labelSmall,
                        color = BbColors.TextMuted
                    )

                    Text(
                        text = "Mesafeli Satış Sözleşmesi",
                        style = BbTypography.titleMedium,
                        color = BbColors.TextStrong
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BbColors.Surface)
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space5)
            ) {
                OrderContractLegalSection(
                    title = "1 - Sözleşmenin Tarafları ve Konusu",
                    body = "Bu belge, $orderCode numaralı siparişe ilişkin temel satış koşullarını ve tarafların haklarını özetler."
                )

                OrderContractLegalSection(
                    title = "1.1 Satıcı Bilgileri",
                    body = "Satıcı bilgileri, sipariş sırasında kayıtlı olan mağaza ve firma bilgilerinden alınır."
                )

                OrderContractLegalSection(
                    title = "1.2 Alıcı Bilgileri",
                    body = "Alıcı bilgileri, sipariş oluşturulurken beyan edilen teslimat ve fatura bilgilerinden oluşur."
                )

                OrderContractLegalSection(
                    title = "12 - Uyuşmazlıkların Çözümü",
                    body = "Taraflar arasında uyuşmazlık oluşması halinde ilgili mevzuat kapsamında yetkili kurumlara başvurulabilir."
                )

                OrderContractDateBox(
                    dateText = "Tarih: 2025-07-28"
                )
            }
        }
    }
}

@Composable
private fun OrderContractLegalSection(
    title: String,
    body: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
    ) {
        Text(
            text = title,
            style = BbTypography.titleSmall,
            color = BbColors.TextStrong
        )

        Text(
            text = body,
            style = BbTypography.bodyMedium,
            color = BbColors.TextSubtle
        )
    }
}

@Composable
private fun OrderContractOrderCodeBox(
    orderCode: String
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
        OrderContractSmallIconBox(
            icon = Icons.Outlined.Description
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = "SİPARİŞ NUMARASI",
                style = BbTypography.labelSmall,
                color = BbColors.TextMuted
            )

            Text(
                text = orderCode,
                style = BbTypography.titleSmall,
                color = BbColors.TextStrong
            )
        }
    }
}

@Composable
private fun OrderContractDateBox(
    dateText: String
) {
    Box(
        modifier = Modifier
            .background(
                color = BbColors.Yellow.Yellow50,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Text(
            text = dateText,
            style = BbTypography.titleSmall,
            color = BbColors.TextStrong
        )
    }
}

@Composable
private fun OrderContractKicker(
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = BbTypography.labelSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun OrderContractIconBox(
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
private fun OrderContractSmallIconBox(
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space9)
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
            modifier = Modifier.size(BbSpacing.Space5)
        )
    }
}