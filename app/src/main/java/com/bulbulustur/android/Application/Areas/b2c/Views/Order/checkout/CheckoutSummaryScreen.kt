package com.bulbulustur.android.Application.Areas.b2c.Views.order.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCheckboxRow
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun CheckoutSummaryScreen(
    onBackClick: () -> Unit = {},
    onEditAddressClick: () -> Unit = {},
    onEditCargoClick: () -> Unit = {},
    onEditPaymentClick: () -> Unit = {},
    onCompleteOrderClick: (CheckoutSummaryApproval) -> Unit = {}
) {
    val screenData = remember {
        getCheckoutSummaryScreenData()
    }

    var distanceSalesAgreementApproved by remember {
        mutableStateOf(false)
    }

    var preliminaryInformationApproved by remember {
        mutableStateOf(false)
    }

    val canCompleteOrder = distanceSalesAgreementApproved && preliminaryInformationApproved

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.PageTopCompact,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.PageBottomCompact
                ),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
            ) {
                item {
                    CheckoutSummaryTopBar(
                        onBackClick = onBackClick
                    )
                }

                item {
                    CheckoutProgressCard(
                        currentStep = "4",
                        title = "Sipariş özeti",
                        description = "Ödeme öncesi bilgilerini son kez kontrol et."
                    )
                }

                item {
                    CheckoutSummaryAddressCard(
                        address = screenData.address,
                        onEditAddressClick = onEditAddressClick
                    )
                }

                item {
                    CheckoutSummaryCargoCard(
                        cargo = screenData.cargo,
                        onEditCargoClick = onEditCargoClick
                    )
                }

                item {
                    CheckoutSummaryPaymentCard(
                        payment = screenData.payment,
                        onEditPaymentClick = onEditPaymentClick
                    )
                }

                item {
                    CheckoutSummarySectionTitle(
                        title = "Sipariş ürünleri",
                        description = "${screenData.products.size} ürün siparişe dahil."
                    )
                }

                items(screenData.products) { product ->
                    CheckoutSummaryProductCard(
                        product = product
                    )
                }

                item {
                    CheckoutSummaryTotalCard(
                        total = screenData.total
                    )
                }

                item {
                    CheckoutSummaryAgreementCard(
                        distanceSalesAgreementApproved = distanceSalesAgreementApproved,
                        onDistanceSalesAgreementChange = {
                            distanceSalesAgreementApproved = it
                        },
                        preliminaryInformationApproved = preliminaryInformationApproved,
                        onPreliminaryInformationChange = {
                            preliminaryInformationApproved = it
                        }
                    )
                }
            }

            CheckoutSummaryBottomBar(
                totalPriceText = screenData.total.totalPriceText,
                canCompleteOrder = canCompleteOrder,
                onCompleteOrderClick = {
                    onCompleteOrderClick(
                        CheckoutSummaryApproval(
                            distanceSalesAgreementApproved = distanceSalesAgreementApproved,
                            preliminaryInformationApproved = preliminaryInformationApproved
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun CheckoutSummaryTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BBIcon.BoxMd)
                .clip(BBRadius.PillShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(BBSpacing.Space3))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Sipariş özeti",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Checkout adım 4 / 4",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutProgressCard(
    currentStep: String,
    title: String,
    description: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .clip(BBRadius.PillShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = currentStep,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(BBSpacing.Space4))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CheckoutSummaryAddressCard(
    address: CheckoutSummaryAddress,
    onEditAddressClick: () -> Unit
) {
    CheckoutSummaryInfoCard(
        title = "Teslimat adresi",
        actionText = BBLocalization.Current.Get(key = "46abcd8f-976d-4c12-a55f-8aa3c4abb38b", fallback = ""),
        onActionClick = onEditAddressClick
    ) {
        Text(
            text = address.title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space1))

        Text(
            text = address.fullName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space1))

        Text(
            text = address.fullAddress,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CheckoutSummaryCargoCard(
    cargo: CheckoutSummaryCargo,
    onEditCargoClick: () -> Unit
) {
    CheckoutSummaryInfoCard(
        title = "Kargo bilgisi",
        actionText = BBLocalization.Current.Get(key = "46abcd8f-976d-4c12-a55f-8aa3c4abb38b", fallback = ""),
        onActionClick = onEditCargoClick
    ) {
        Text(
            text = cargo.companySummaryText,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space1))

        Text(
            text = cargo.deliveryEstimateText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space1))

        Text(
            text = cargo.packageSummaryText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CheckoutSummaryPaymentCard(
    payment: CheckoutSummaryPayment,
    onEditPaymentClick: () -> Unit
) {
    CheckoutSummaryInfoCard(
        title = "Ödeme yöntemi",
        actionText = BBLocalization.Current.Get(key = "46abcd8f-976d-4c12-a55f-8aa3c4abb38b", fallback = ""),
        onActionClick = onEditPaymentClick
    ) {
        Text(
            text = payment.methodTitle,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space1))

        Text(
            text = payment.description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CheckoutSummaryInfoCard(
    title: String,
    actionText: String,
    onActionClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = actionText,
                    modifier = Modifier.clickable {
                        onActionClick()
                    },
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(BBSpacing.Space3))

            content()
        }
    }
}

@Composable
private fun CheckoutSummaryProductCard(
    product: CheckoutSummaryProductItem
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.Box2Xl)
                    .clip(BBRadius.XlShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = product.imageText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(BBSpacing.Space4))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))

                Text(
                    text = "${product.storeName} . ${product.variantText}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space2))

                Text(
                    text = product.priceText,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = "x${product.quantity}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CheckoutSummaryTotalCard(
    total: CheckoutSummaryTotal
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            CheckoutSummaryTotalRow(
                title = "Ürün toplamı",
                value = total.productTotalText
            )

            CheckoutSummaryTotalRow(
                title = "Kargo",
                value = total.cargoTotalText
            )

            CheckoutSummaryTotalRow(
                title = "İndirim",
                value = total.discountText
            )

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant
            )

            CheckoutSummaryTotalRow(
                title = BBLocalization.Current.Get(key = "0234baa2-519d-42ae-a2e8-760ebc0a1d06", fallback = "Ödenecek Tutar"),
                value = total.totalPriceText,
                isStrong = true
            )
        }
    }
}

@Composable
private fun CheckoutSummaryTotalRow(
    title: String,
    value: String,
    isStrong: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = if (isStrong) {
                MaterialTheme.typography.titleSmall
            } else {
                MaterialTheme.typography.bodySmall
            },
            fontWeight = if (isStrong) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = value,
            style = if (isStrong) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodySmall
            },
            fontWeight = if (isStrong) {
                FontWeight.Bold
            } else {
                FontWeight.SemiBold
            },
            color = if (isStrong) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
    }
}

@Composable
private fun CheckoutSummaryAgreementCard(
    distanceSalesAgreementApproved: Boolean,
    onDistanceSalesAgreementChange: (Boolean) -> Unit,
    preliminaryInformationApproved: Boolean,
    onPreliminaryInformationChange: (Boolean) -> Unit
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
            CheckoutSummarySectionTitle(
                title = "Sözleşme onayları",
                description = "Siparişi tamamlamadan önce gerekli bilgilendirmeleri onayla."
            )

            BbCheckboxRow(
                checked = distanceSalesAgreementApproved,
                onCheckedChange = onDistanceSalesAgreementChange,
                title = BBLocalization.Current.Get(key = "09fe0c8a-ee0f-44cb-8b00-28dbe3a5dd9b", fallback = "")
            )

            BbCheckboxRow(
                checked = preliminaryInformationApproved,
                onCheckedChange = onPreliminaryInformationChange,
                title = "Ön bilgilendirme formunu okudum ve onaylıyorum."
            )
        }
    }
}

@Composable
private fun CheckoutSummaryBottomBar(
    totalPriceText: String,
    canCompleteOrder: Boolean,
    onCompleteOrderClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = BBSpacing.Space1,
        shadowElevation = BBSpacing.Space2
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = BBSpacing.PageHorizontal,
                    top = BBSpacing.Space3,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.Space4
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Toplam",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(BBSpacing.Space1))

                Text(
                    text = totalPriceText,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(BBSpacing.Space3))

            BbButton(
                text = "Siparişi tamamla",
                onClick = onCompleteOrderClick,
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = canCompleteOrder
            )
        }
    }
}

@Composable
private fun CheckoutSummarySectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(BBSpacing.Space1))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class CheckoutSummaryScreenData(
    val address: CheckoutSummaryAddress,
    val cargo: CheckoutSummaryCargo,
    val payment: CheckoutSummaryPayment,
    val products: List<CheckoutSummaryProductItem>,
    val total: CheckoutSummaryTotal
)

data class CheckoutSummaryAddress(
    val title: String,
    val fullName: String,
    val fullAddress: String
)

data class CheckoutSummaryCargo(
    val companySummaryText: String,
    val deliveryEstimateText: String,
    val packageSummaryText: String
)

data class CheckoutSummaryPayment(
    val methodTitle: String,
    val description: String
)

data class CheckoutSummaryProductItem(
    val id: Int,
    val name: String,
    val storeName: String,
    val variantText: String,
    val priceText: String,
    val quantity: Int,
    val imageText: String
)

data class CheckoutSummaryTotal(
    val productTotalText: String,
    val cargoTotalText: String,
    val discountText: String,
    val totalPriceText: String
)

data class CheckoutSummaryApproval(
    val distanceSalesAgreementApproved: Boolean,
    val preliminaryInformationApproved: Boolean
)

private fun getCheckoutSummaryScreenData(): CheckoutSummaryScreenData {
    return CheckoutSummaryScreenData(
        address = CheckoutSummaryAddress(
            title = "Ev adresim",
            fullName = "Murat Erkan",
            fullAddress = "Kızılay Mah. Atatürk Bulvarı No: 12 Daire: 8 Çankaya / Ankara"
        ),
        cargo = CheckoutSummaryCargo(
            companySummaryText = "2 mağaza için kargo seçildi",
            deliveryEstimateText = "Tahmini teslimat: 1-4 iş günü",
            packageSummaryText = "2 ayrı paket gönderimi"
        ),
        payment = CheckoutSummaryPayment(
            methodTitle = "Yeni kart ile ödeme",
            description = "3D Secure doğrulama ödeme sağlayıcısı üzerinden tamamlanır."
        ),
        products = listOf(
            CheckoutSummaryProductItem(
                id = 1,
                name = "Kadın klasik sneaker ayakkabı",
                storeName = "Ortobella Store",
                variantText = "Beyaz . 38",
                priceText = "₺899,90",
                quantity = 1,
                imageText = "P1"
            ),
            CheckoutSummaryProductItem(
                id = 2,
                name = "Rahat taban günlük ayakkabı",
                storeName = "Ortobella Store",
                variantText = "Siyah . 39",
                priceText = "₺749,90",
                quantity = 1,
                imageText = "P2"
            ),
            CheckoutSummaryProductItem(
                id = 3,
                name = "Oversize pamuklu basic tişört",
                storeName = "Moda Nova",
                variantText = "Lacivert . M",
                priceText = "₺349,90",
                quantity = 2,
                imageText = "P3"
            )
        ),
        total = CheckoutSummaryTotal(
            productTotalText = "₺2.759,80",
            cargoTotalText = "₺89,80",
            discountText = "-₺120,00",
            totalPriceText = "₺2.729,60"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CheckoutSummaryScreenPreview() {
    BbTheme {
        CheckoutSummaryScreen()
    }
}


