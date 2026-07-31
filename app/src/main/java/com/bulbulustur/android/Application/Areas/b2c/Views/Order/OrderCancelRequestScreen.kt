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
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderCancelationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.OrderCancelationInsertModel

@Composable
fun OrderCancelRequestScreen(
    orderStoreLineId: Long,
    orderKey: String,
    memberId: Int,
    languageId: Int,
    onBackClick: () -> Unit = {},
    onSubmitSuccess: () -> Unit = {},
    controller: OrderController = viewModel()
) {
    val state by controller.State.collectAsStateWithLifecycle()

    var selectedReasonId by remember {
        mutableIntStateOf(0)
    }

    var description by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        controller.ResetCancelationResult()
        controller.GetOrderCancelationTypes()
    }

    LaunchedEffect(state.IsCancelationCompleted) {
        if (state.IsCancelationCompleted) {
            controller.ResetCancelationResult()
            onSubmitSuccess()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "İptal Talebi",
                subtitle = "Sipariş satırı için iptal talebi oluştur.",
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
            item {
                OrderCancelIntroCard(
                    orderKey = orderKey,
                    orderStoreLineId = orderStoreLineId
                )
            }

            when {
                orderStoreLineId <= 0L || orderKey.isBlank() -> {
                    item {
                        OrderCancelMessageCard(
                            title = "Sipariş bilgisi eksik",
                            description = "İptal talebi için sipariş satırı veya sipariş anahtarı bulunamadı."
                        )
                    }
                }

                memberId <= 0 -> {
                    item {
                        OrderCancelMessageCard(
                            title = "Oturum bilgisi bulunamadı",
                            description = "İptal talebi oluşturmak için hesabınıza giriş yapmanız gerekiyor."
                        )
                    }
                }

                state.IsLoading &&
                        state.CurrentAction == "GetOrderCancelationTypes" &&
                        state.CancelationTypes.isEmpty() -> {
                    item {
                        OrderCancelLoadingCard(
                            text = "İptal nedenleri yükleniyor"
                        )
                    }
                }

                state.ErrorMessage != null &&
                        state.CancelationTypes.isEmpty() &&
                        state.CurrentAction == "GetOrderCancelationTypes" -> {
                    item {
                        OrderCancelMessageCard(
                            title = "İptal nedenleri alınamadı",
                            description = state.ErrorMessage.orEmpty()
                        )
                    }
                }

                state.CancelationTypes.isEmpty() -> {
                    item {
                        OrderCancelMessageCard(
                            title = "İptal nedeni bulunamadı",
                            description = "İptal talebi için kullanılabilir neden kaydı bulunamadı."
                        )
                    }
                }

                else -> {
                    item {
                        OrderCancelReasonCard(
                            reasons = state.CancelationTypes,
                            selectedReasonId = selectedReasonId,
                            onReasonClick = { reasonId ->
                                selectedReasonId = reasonId
                            }
                        )
                    }

                    item {
                        OrderCancelDescriptionCard(
                            description = description,
                            onDescriptionChange = { value ->
                                description = value
                            }
                        )
                    }

                    item {
                        OrderCancelWarningCard()
                    }

                    state.ErrorMessage
                        ?.takeIf {
                            state.CurrentAction == "InsertOrderCancelationAsync"
                        }
                        ?.let { errorMessage ->
                            item {
                                OrderCancelMessageCard(
                                    title = "Talep gönderilemedi",
                                    description = errorMessage
                                )
                            }
                        }

                    item {
                        OrderCancelActionCard(
                            isLoading = state.IsLoading &&
                                    state.CurrentAction == "InsertOrderCancelationAsync",
                            isEnabled = selectedReasonId > 0 &&
                                    orderStoreLineId > 0 &&
                                    orderKey.isNotBlank() &&
                                    memberId > 0,
                            onBackClick = onBackClick,
                            onSubmitClick = {
                                controller.InsertOrderCancelationAsync(
                                    languageId = languageId,
                                    memberId = memberId,
                                    insertModel = OrderCancelationInsertModel(
                                        InsertedBy = memberId,
                                        OrderStoreLineId = orderStoreLineId.toInt(),
                                        OrderCancelationTypeId = selectedReasonId,
                                        OrderKey = orderKey,
                                        Description = description.trim()
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderCancelIntroCard(
    orderKey: String,
    orderStoreLineId: Long
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            OrderCancelIconBox(
                icon = Icons.Outlined.ReceiptLong,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "İptal Talebi Oluştur",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = orderKey.ifBlank { "Sipariş bilgisi bulunamadı" },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (orderStoreLineId > 0) {
                    Text(
                        text = "Sipariş satırı: $orderStoreLineId",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderCancelReasonCard(
    reasons: List<SystemDescOrderCancelationTypeDTO>,
    selectedReasonId: Int,
    onReasonClick: (Int) -> Unit
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
            OrderCancelSectionTitle(
                title = BBLocalization.Current.Get(key = "6a5272b2-6e98-4371-a596-a3816429a463", fallback = "İptal Nedeni"),
                subtitle = BBLocalization.Current.Get(key = "56eedafa-dae0-471a-8228-df07162a4d88", fallback = "Bu ürünü neden iptal etmek istediğinizi seçin.")
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                reasons.forEach { reason ->
                    OrderCancelReasonRow(
                        text = reason.Content.ifBlank {
                            "İptal nedeni #${reason.OrderCancelationTypeId}"
                        },
                        selected = reason.OrderCancelationTypeId == selectedReasonId,
                        onClick = {
                            onReasonClick(reason.OrderCancelationTypeId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderCancelReasonRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.LgShape,
        color = if (selected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.CardPaddingCompact),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (selected) {
                    Icons.Outlined.CheckCircle
                } else {
                    Icons.Outlined.RadioButtonUnchecked
                },
                contentDescription = null,
                tint = if (selected) {
                    BBColors.Yellow.Yellow800
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                modifier = Modifier.size(BBIcon.Action)
            )

            Text(
                text = text,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun OrderCancelDescriptionCard(
    description: String,
    onDescriptionChange: (String) -> Unit
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
            OrderCancelSectionTitle(
                title = BBLocalization.Current.Get(key = "db0a3356-2fa4-4c1f-9432-2c299ac52b92", fallback = "Açıklama"),
                subtitle = "Talebin değerlendirilmesi için kısa bir açıklama yazabilirsiniz."
            )

            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                modifier = Modifier.fillMaxWidth(),
                minLines = 5,
                shape = BBRadius.Input,
                placeholder = {
                    Text(
                        text = "İptal talebinizle ilgili açıklama yazın."
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.RequestQuote,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@Composable
private fun OrderCancelWarningCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            OrderCancelIconBox(
                icon = Icons.Outlined.Info,
                backgroundColor = BBColors.Blue.Blue50,
                iconColor = BBColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "İptal Süreci",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Talebiniz sipariş durumuna göre değerlendirilir. Sonuç sipariş kayıtlarına yansıtılır.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun OrderCancelActionCard(
    isLoading: Boolean,
    isEnabled: Boolean,
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit
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
            BbButton(
                text = if (isLoading) {
                    "Talep Gönderiliyor"
                } else {
                    BBLocalization.Current.Get(key = "d2843cb0-ebbe-4d77-9873-62ac1d7ea9ee", fallback = "Talebi Gönder")
                },
                onClick = onSubmitClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = isEnabled && !isLoading,
                leadingIcon = {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(BBIcon.ButtonIcon),
                            strokeWidth = BBSpacing.BorderThin
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Send,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(BBIcon.ButtonIcon)
                        )
                    }
                }
            )

            BbButton(
                text = BBLocalization.Current.Get(key = "b820bc4a-7523-4901-a326-a07c9ec43637", fallback = "Sipariş Detaylarına Dön"),
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium
            )
        }
    }
}

@Composable
private fun OrderCancelLoadingCard(text: String) {
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
                text = text,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun OrderCancelMessageCard(
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
            OrderCancelIconBox(
                icon = Icons.Outlined.Info,
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

@Composable
private fun OrderCancelSectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
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
private fun OrderCancelIconBox(
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