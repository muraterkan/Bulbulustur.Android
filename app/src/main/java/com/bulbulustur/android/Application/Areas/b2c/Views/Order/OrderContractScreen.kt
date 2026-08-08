package com.bulbulustur.android.Application.Areas.b2c.Views.order

import com.bulbulustur.android.Application.Localization.BBLocalization

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bulbulustur.android.Application.Areas.b2c.Controllers.OrderController
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
import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO

@Composable
fun OrderContractScreen(
    orderKey: String,
    storeKey: String,
    onBackClick: () -> Unit = {},
    onPrintClick: (String) -> Unit = {},
    controller: OrderController = viewModel()
) {
    val state by controller.State.collectAsStateWithLifecycle()

    LaunchedEffect(orderKey, storeKey) {
        if (orderKey.isNotBlank() && storeKey.isNotBlank()) {
            controller.GetOrderStoreContractAsync(
                orderKey = orderKey,
                storeKey = storeKey
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "46aaf0f1-e0bc-429e-9a7d-5c26ee275449", fallback = "Sözleşme"),
                subtitle = BBLocalization.Current.Get(key = "cbadbcf8-3e92-481d-b7bd-c002a9003abb", fallback = "Siparişe ait mesafeli satış sözleşmesi."),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            when {
                orderKey.isBlank() || storeKey.isBlank() -> {
                    item {
                        OrderContractMessageCard(
                            title = BBLocalization.Current.Get(key = "bf9ae67b-414c-4dbb-8c78-afef77dffa5a", fallback = "Sözleşme bilgisi eksik"),
                            description = BBLocalization.Current.Get(key = "425b726a-13a6-4865-b3e8-2c468de48d02", fallback = "Sipariş veya mağaza anahtarı bulunamadı.")
                        )
                    }
                }

                state.IsLoading && state.Contract == null -> {
                    item {
                        OrderContractLoadingCard()
                    }
                }

                state.ErrorMessage != null && state.Contract == null -> {
                    item {
                        OrderContractMessageCard(
                            title = BBLocalization.Current.Get(key = "9891ee14-8be2-40e6-98fb-39a517213676", fallback = "Sözleşme alınamadı"),
                            description = state.ErrorMessage.orEmpty()
                        )
                    }
                }

                state.Contract == null -> {
                    item {
                        OrderContractMessageCard(
                            title = BBLocalization.Current.Get(key = "7f372c3b-70c4-4f07-a241-1519ddac1165", fallback = "Sözleşme bulunamadı"),
                            description = BBLocalization.Current.Get(key = "9a012116-37ca-46b2-a459-04ab2d36261c", fallback = "Bu sipariş ve mağaza için kayıtlı sözleşme bulunamadı.")
                        )
                    }
                }

                else -> {
                    val contract = state.Contract!!

                    item {
                        OrderContractSummaryCard(
                            contract = contract,
                            onPrintClick = {
                                onPrintClick(contract.ContractText)
                            }
                        )
                    }

                    item {
                        OrderContractInfoGrid(
                            contract = contract
                        )
                    }

                    item {
                        OrderContractLegalTextCard(
                            contractText = contract.ContractText
                        )
                    }

                    item {
                        OrderContractBottomActionCard(
                            onPrintClick = {
                                onPrintClick(contract.ContractText)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderContractSummaryCard(
    contract: ContractDTO,
    onPrintClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderContractIconBox(
                    icon = Icons.Outlined.ReceiptLong,
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    iconColor = BBColors.Yellow.Yellow800
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "cbadbcf8-3e92-481d-b7bd-c002a9003abb", fallback = "Mesafeli Satış Sözleşmesi"),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = contract.OrderKey,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            OrderContractNoticeBox(
                text = BBLocalization.Current.Get(key = "4c972645-8504-497b-81a7-277cd8a93251", fallback = "Bu belge, sipariş oluşturulduğu anda geçerli olan sözleşme metnidir.")
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "58656233-1be3-433e-9624-6018dac6a333", fallback = "Yazdır"),
                onClick = onPrintClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Print,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderContractInfoGrid(contract: ContractDTO) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            OrderContractInfoBox(
                modifier = Modifier.weight(1f),
                title = BBLocalization.Current.Get(key = "da8fa875-cb8f-4354-a354-86a2d8213bc8", fallback = "SİPARİŞ"),
                value = contract.OrderKey,
                icon = Icons.Outlined.Numbers,
                iconColor = BBColors.Yellow.Yellow800
            )

            OrderContractInfoBox(
                modifier = Modifier.weight(1f),
                title = BBLocalization.Current.Get(key = "a4bd79dd-e7ee-4407-9e7d-00582840c43a", fallback = "MAĞAZA"),
                value = contract.StoreKey,
                icon = Icons.Outlined.RequestQuote,
                iconColor = BBColors.Blue.Blue600
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            OrderContractInfoBox(
                modifier = Modifier.weight(1f),
                title = BBLocalization.Current.Get(key = "e4602d88-9a44-4ed3-827b-8844da4a88be", fallback = "TARİH"),
                value = contract.InsertedDate.ifBlank { "-" },
                icon = Icons.Outlined.CalendarMonth,
                iconColor = BBColors.Blue.Blue600
            )

            OrderContractInfoBox(
                modifier = Modifier.weight(1f),
                title = BBLocalization.Current.Get(key = "705d7bc6-c5db-41c1-b062-6aa236415d50", fallback = "VERSİYON"),
                value = contract.Version?.toString() ?: "-",
                icon = Icons.Outlined.ReceiptLong,
                iconColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderContractLegalTextCard(contractText: String) {
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
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(BBSpacing.CardPadding),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "8e3ace60-8ad0-4c13-9e6a-be2fc5a6eb5e", fallback = "HUKUKİ METİN"),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = BBLocalization.Current.Get(key = "56a81cd3-b265-494c-b8fc-80aee84a651c", fallback = "Sözleşme İçeriği"),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = contractText.ifBlank {
                    BBLocalization.Current.Get(key = "29f53147-06b4-45b5-a4dc-59cdd46fe460", fallback = "Sözleşme metni bulunamadı.")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(BBSpacing.CardPadding),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderContractBottomActionCard(onPrintClick: () -> Unit) {
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
                text = BBLocalization.Current.Get(key = "137cef6d-23b8-4142-963b-b2e402d26335", fallback = "Belge İşlemleri"),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "26aa82f2-8067-4f5f-8046-bc5175dd55fa", fallback = "Sözleşme belgesini yazdırabilir veya sistem paylaşım ekranına aktarabilirsiniz."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "a1fe8a9e-2d6d-4e04-aec4-aaf249ba60fe", fallback = "Belgeyi Yazdır"),
                onClick = onPrintClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Print,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(BBIcon.ButtonIcon)
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
                color = MaterialTheme.colorScheme.surface,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(BBIcon.Action)
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
private fun OrderContractNoticeBox(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.CardPaddingCompact)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            .size(BBIcon.BoxMd)
            .background(
                color = backgroundColor,
                shape = BBRadius.LgShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(BBIcon.Action)
        )
    }
}

@Composable
private fun OrderContractLoadingCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            CircularProgressIndicator()

            Text(
                text = BBLocalization.Current.Get(key = "83e14f2f-4997-446e-b721-0658818d209b", fallback = "Sözleşme yükleniyor"),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun OrderContractMessageCard(
    title: String,
    description: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            OrderContractIconBox(
                icon = Icons.Outlined.RequestQuote,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
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