package com.bulbulustur.android.Areas.b2c.Views.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.wwwroot.components.BbButton
import com.bulbulustur.android.wwwroot.components.BbButtonSize
import com.bulbulustur.android.wwwroot.components.BbButtonVariant
import com.bulbulustur.android.wwwroot.components.BbCard
import com.bulbulustur.android.wwwroot.components.BbCardPadding
import com.bulbulustur.android.wwwroot.components.BbCardVariant
import com.bulbulustur.android.wwwroot.theme.BbColors
import com.bulbulustur.android.wwwroot.theme.BbIcon
import com.bulbulustur.android.wwwroot.theme.BbRadius
import com.bulbulustur.android.wwwroot.theme.BbSpacing

@Composable
fun OrderContractScreen(
    orderCode: String = "ORD-F4QO-AFPR-J5EX",
    createdDate: String = "10 Mayıs 2026 08:54",
    contractDate: String = "28 Temmuz 2025",
    onBackClick: () -> Unit = {},
    onPrintClick: () -> Unit = {}
) {
    val contract = getDemoOrderContract(
        orderCode = orderCode,
        createdDate = createdDate,
        contractDate = contractDate
    )

    OrderContractPageScaffold(
        title = "Sözleşme",
        subtitle = "Siparişe ait mesafeli satış sözleşmesi.",
        onBackClick = onBackClick
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = BbSpacing.PageHorizontal,
                top = BbSpacing.PageTopCompact,
                end = BbSpacing.PageHorizontal,
                bottom = BbSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
        ) {
            item {
                OrderContractSummaryCard(
                    contract = contract,
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
                        title = "SİPARİŞ",
                        value = contract.orderCode,
                        icon = Icons.Outlined.Numbers,
                        iconColor = BbColors.Yellow.Yellow800
                    )

                    OrderContractInfoBox(
                        modifier = Modifier.weight(1f),
                        title = "TARİH",
                        value = contract.createdDate,
                        icon = Icons.Outlined.CalendarMonth,
                        iconColor = BbColors.Blue.Blue600
                    )
                }
            }

            item {
                OrderContractPartiesCard(contract = contract)
            }

            item {
                OrderContractLegalTextCard(contract = contract)
            }

            item {
                OrderContractBottomActionCard(
                    onPrintClick = onPrintClick
                )
            }
        }
    }
}

@Composable
private fun OrderContractPageScaffold(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BbColors.SurfaceMuted)
            .navigationBarsPadding()
    ) {
        OrderContractTopHeader(
            title = title,
            subtitle = subtitle,
            onBackClick = onBackClick
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            content()
        }
    }
}

@Composable
private fun OrderContractTopHeader(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BbColors.Surface)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BbSpacing.PageHorizontal,
                    vertical = BbSpacing.Space3
                ),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space2),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(BbIcon.BoxMd)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Geri dön",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(BbIcon.TopBarIcon)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        HorizontalDivider(
            color = BbColors.Border
        )
    }
}

@Composable
private fun OrderContractSummaryCard(
    contract: OrderContractUiModel,
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
                    icon = Icons.Outlined.ReceiptLong,
                    backgroundColor = BbColors.Yellow.Yellow100,
                    iconColor = BbColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = "Mesafeli Satış Sözleşmesi",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = contract.orderCode,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            OrderContractNoticeBox(
                text = "Bu belge sipariş oluşturma anında geçerli olan satış koşulları, alıcı ve satıcı bilgileri ile teslimat hükümlerini içerir."
            )

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
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderContractPartiesCard(
    contract: OrderContractUiModel
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
            OrderContractSectionTitle(
                title = "Taraf Bilgileri",
                subtitle = "Sipariş sırasında kayıtlı alıcı ve satıcı bilgileri"
            )

            OrderContractPartyRow(
                title = "Alıcı",
                name = contract.buyerName,
                description = contract.buyerAddress
            )

            HorizontalDivider(
                color = BbColors.Border
            )

            OrderContractPartyRow(
                title = "Satıcı",
                name = contract.sellerName,
                description = contract.sellerAddress
            )
        }
    }
}

@Composable
private fun OrderContractLegalTextCard(
    contract: OrderContractUiModel
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.None
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BbColors.SurfaceMuted)
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
            ) {
                Text(
                    text = "HUKUKİ METİN",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Sözleşme İçeriği",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BbColors.Surface)
                    .padding(BbSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space5)
            ) {
                contract.sections.forEach { section ->
                    OrderContractLegalSection(
                        title = section.title,
                        body = section.body
                    )
                }

                OrderContractDateBox(
                    dateText = "Sözleşme Tarihi: ${contract.contractDate}"
                )
            }
        }
    }
}

@Composable
private fun OrderContractBottomActionCard(
    onPrintClick: () -> Unit
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
            OrderContractSectionTitle(
                title = "Belge İşlemleri",
                subtitle = "Sözleşmeyi görüntüleyebilir veya yazdırabilirsiniz."
            )

            BbButton(
                text = "Belgeyi Yazdır",
                onClick = onPrintClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Print,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
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
    icon: ImageVector,
    iconColor: Color
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(BbIcon.Action)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun OrderContractPartyRow(
    title: String,
    name: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.Top
    ) {
        OrderContractIconBox(
            icon = Icons.Outlined.Description,
            backgroundColor = BbColors.SurfaceMuted,
            iconColor = BbColors.TextStrong
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
        ) {
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = name,
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
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = body,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OrderContractSectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OrderContractNoticeBox(
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.Yellow.Yellow50,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextSubtle
        )
    }
}

@Composable
private fun OrderContractDateBox(
    dateText: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BbColors.Yellow.Yellow50,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.CardPaddingCompact)
    ) {
        Text(
            text = dateText,
            style = MaterialTheme.typography.titleSmall,
            color = BbColors.TextStrong
        )
    }
}

@Composable
private fun OrderContractIconBox(
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color
) {
    Box(
        modifier = Modifier
            .size(BbIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BbRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BbIcon.Action)
        )
    }
}

private fun getDemoOrderContract(
    orderCode: String,
    createdDate: String,
    contractDate: String
): OrderContractUiModel {
    return OrderContractUiModel(
        orderCode = orderCode,
        createdDate = createdDate,
        contractDate = contractDate,
        buyerName = "Murat Erkan",
        buyerAddress = "İstanbul / Türkiye · Alıcı adresi API bağlandığında gerçek sipariş adresinden beslenecek.",
        sellerName = "Ortobella",
        sellerAddress = "Satıcı firma ve mağaza bilgileri API bağlandığında gerçek sipariş satıcısından beslenecek.",
        sections = listOf(
            OrderContractSectionUiModel(
                title = "1 - Sözleşmenin Tarafları ve Konusu",
                body = "Bu sözleşme, $orderCode numaralı siparişe ilişkin ürün satışı, teslimat, ödeme ve tarafların temel hak ve yükümlülüklerini düzenler."
            ),
            OrderContractSectionUiModel(
                title = "2 - Ürün ve Sipariş Bilgileri",
                body = "Siparişe konu ürünlerin adı, adedi, satış bedeli, kargo bilgisi ve toplam ödeme tutarı sipariş detay ekranında gösterilen bilgilerden oluşur."
            ),
            OrderContractSectionUiModel(
                title = "3 - Teslimat Koşulları",
                body = "Teslimat, alıcının sipariş sırasında belirttiği adrese yapılır. Kargo süreci satıcı ve taşıyıcı firma operasyonuna göre güncellenir."
            ),
            OrderContractSectionUiModel(
                title = "4 - Cayma, İade ve İptal",
                body = "Alıcı, ilgili mevzuat ve platform kuralları çerçevesinde iptal, iade ve cayma haklarını kullanabilir. Ürün niteliğine göre istisnai durumlar oluşabilir."
            ),
            OrderContractSectionUiModel(
                title = "5 - Uyuşmazlıkların Çözümü",
                body = "Taraflar arasında uyuşmazlık oluşması halinde ilgili mevzuat kapsamında yetkili kurumlara başvurulabilir."
            )
        )
    )
}

private data class OrderContractUiModel(
    val orderCode: String,
    val createdDate: String,
    val contractDate: String,
    val buyerName: String,
    val buyerAddress: String,
    val sellerName: String,
    val sellerAddress: String,
    val sections: List<OrderContractSectionUiModel>
)

private data class OrderContractSectionUiModel(
    val title: String,
    val body: String
)