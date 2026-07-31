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
import androidx.compose.material.icons.outlined.AssignmentReturn
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.VerifiedUser
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
import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO

@Composable
fun OrderReturnRequestScreen(
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
        controller.ResetReturnRequestResult()
        controller.GetReturnRequestReasonsAsync()
    }

    LaunchedEffect(state.IsReturnRequestCompleted) {
        if (state.IsReturnRequestCompleted) {
            controller.ResetReturnRequestResult()
            onSubmitSuccess()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "c1b6be9d-c63a-494b-aa84-efbe15520640", fallback = "İade Talebi"),
                subtitle = "Teslim edilen ürün için iade talebi oluştur.",
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.CardGap
            )
        ) {
            item {
                OrderReturnIntroCard(
                    orderKey = orderKey,
                    orderStoreLineId = orderStoreLineId
                )
            }

            when {
                orderStoreLineId <= 0L || orderKey.isBlank() -> {
                    item {
                        OrderReturnMessageCard(
                            title = "Sipariş bilgisi eksik",
                            description = "İade talebi için sipariş satırı veya sipariş anahtarı bulunamadı."
                        )
                    }
                }

                memberId <= 0 -> {
                    item {
                        OrderReturnMessageCard(
                            title = "Oturum bilgisi bulunamadı",
                            description = "İade talebi oluşturmak için hesabınıza giriş yapmanız gerekiyor."
                        )
                    }
                }

                state.IsLoading &&
                        state.CurrentAction == "GetReturnRequestReasonsAsync" &&
                        state.ReturnRequestReasons.isEmpty() -> {
                    item {
                        OrderReturnLoadingCard(
                            text = "İade nedenleri yükleniyor"
                        )
                    }
                }

                state.ErrorMessage != null &&
                        state.ReturnRequestReasons.isEmpty() &&
                        state.CurrentAction == "GetReturnRequestReasonsAsync" -> {
                    item {
                        OrderReturnMessageCard(
                            title = "İade nedenleri alınamadı",
                            description = state.ErrorMessage.orEmpty()
                        )
                    }
                }

                state.ReturnRequestReasons.isEmpty() -> {
                    item {
                        OrderReturnMessageCard(
                            title = "İade nedeni bulunamadı",
                            description = "İade talebi için kullanılabilir neden kaydı bulunamadı."
                        )
                    }
                }

                else -> {
                    item {
                        OrderReturnReasonCard(
                            reasons = state.ReturnRequestReasons,
                            selectedReasonId = selectedReasonId,
                            onReasonClick = { reasonId ->
                                selectedReasonId = reasonId
                            }
                        )
                    }

                    item {
                        OrderReturnDescriptionCard(
                            description = description,
                            onDescriptionChange = { value ->
                                description = value
                            }
                        )
                    }

                    item {
                        OrderReturnProcessCard()
                    }

                    state.ErrorMessage
                        ?.takeIf {
                            state.CurrentAction == "InsertReturnRequestAsync"
                        }
                        ?.let { errorMessage ->
                            item {
                                OrderReturnMessageCard(
                                    title = "Talep gönderilemedi",
                                    description = errorMessage
                                )
                            }
                        }

                    item {
                        OrderReturnActionCard(
                            isLoading = state.IsLoading &&
                                    state.CurrentAction == "InsertReturnRequestAsync",
                            isEnabled = selectedReasonId > 0 &&
                                    description.isNotBlank() &&
                                    orderStoreLineId > 0 &&
                                    orderKey.isNotBlank() &&
                                    memberId > 0,
                            onBackClick = onBackClick,
                            onSubmitClick = {
                                controller.InsertReturnRequestAsync(
                                    languageId = languageId,
                                    memberId = memberId,
                                    returnRequest = ReturnRequestDTO(
                                        InsertedBy = memberId,
                                        MemberId = memberId,
                                        OrderStoreLineId = orderStoreLineId.toInt(),
                                        OrderKey = orderKey,
                                        ReturnRequestReasonId = selectedReasonId,
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
private fun OrderReturnIntroCard(
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
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.Top
        ) {
            OrderReturnIconBox(
                icon = Icons.Outlined.AssignmentReturn,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                iconColor = BBColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = "İade Talebi Oluştur",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "İade nedeninizi seçin. Talep gönderildikten sonra ilgili sipariş satırı üzerinden takip edilebilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (orderKey.isNotBlank() || orderStoreLineId > 0L) {
                    Text(
                        text = "Sipariş: $orderKey • Satır: $orderStoreLineId",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderReturnReasonCard(
    reasons: List<SystemDescReturnRequestReasonDTO>,
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
            OrderReturnSectionTitle(
                title = "İade Nedeni",
                subtitle = "Bu ürünü neden iade etmek istediğinizi seçin."
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                reasons.forEach { reason ->
                    OrderReturnReasonRow(
                        text = reason.content,
                        selected = reason.systemDescReturnRequestReasonId == selectedReasonId,
                        onClick = {
                            onReasonClick(reason.systemDescReturnRequestReasonId)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun OrderReturnReasonRow(
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
                .padding(
                    BBSpacing.CardPaddingCompact
                ),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
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
                modifier = Modifier.size(
                    BBIcon.Action
                )
            )

            Text(
                text = text.ifBlank { "İade nedeni" },
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun OrderReturnDescriptionCard(
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            OrderReturnSectionTitle(
                title = BBLocalization.Current.Get(key = "db0a3356-2fa4-4c1f-9432-2c299ac52b92", fallback = "Açıklama"),
                subtitle = "İade talebinizin gönderilebilmesi için kısa bir açıklama yazın."
            )

            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                shape = BBRadius.Input,
                placeholder = {
                    Text(
                        text = "Üründe yaşadığınız sorunu kısaca açıklayın."
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
private fun OrderReturnProcessCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.Top
        ) {
            OrderReturnIconBox(
                icon = Icons.Outlined.VerifiedUser,
                backgroundColor = BBColors.Blue.Blue50,
                iconColor = BBColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = "Talep süreci",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Talebiniz satıcı veya destek ekibi tarafından incelenir. Gerekirse sizinle iletişime geçilebilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun OrderReturnLoadingCard(
    text: String
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(
                    BBIcon.Action
                ),
                strokeWidth = BBSpacing.Space1
            )

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OrderReturnMessageCard(
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
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.Top
        ) {
            OrderReturnIconBox(
                icon = Icons.Outlined.Info,
                backgroundColor = BBColors.Orange.Orange50,
                iconColor = BBColors.Orange.Orange600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = title,
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
}

@Composable
private fun OrderReturnActionCard(
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
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            )
        ) {
            BbButton(
                text = BBLocalization.Current.Get(key = "d2843cb0-ebbe-4d77-9873-62ac1d7ea9ee", fallback = "Talebi Gönder"),
                onClick = onSubmitClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = isEnabled,
                isLoading = isLoading,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(
                            BBIcon.ButtonIcon
                        )
                    )
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
private fun OrderReturnSectionTitle(
    title: String,
    subtitle: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
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
private fun OrderReturnIconBox(
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color
) {
    Box(
        modifier = Modifier
            .size(
                BBIcon.BoxMd
            )
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
            modifier = Modifier.size(
                BBIcon.Action
            )
        )
    }
}