package com.bulbulustur.android.Application.Areas.b2c.Views.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bulbulustur.android.Application.Areas.b2c.Controllers.OrderController
import com.bulbulustur.android.Application.Localization.BBLocalization
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
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReviewInsertModel

@Composable
fun OrderReviewCreateScreen(
    orderStoreLineId: Long,
    productId: Long,
    productSecureKey: String,
    memberId: Int,
    onBackClick: () -> Unit = {},
    onSubmitSuccess: () -> Unit = {},
    controller: OrderController = viewModel()
) {
    val state by controller.State.collectAsStateWithLifecycle()

    var rating by remember {
        mutableIntStateOf(5)
    }

    var content by remember {
        mutableStateOf("")
    }

    var accepted by remember {
        mutableStateOf(false)
    }

    val canSubmit = orderStoreLineId > 0 &&
            productId > 0 &&
            productSecureKey.isNotBlank() &&
            memberId > 0 &&
            content.isNotBlank() &&
            rating in 1..5 &&
            accepted &&
            !state.IsLoading

    LaunchedEffect(Unit) {
        controller.ResetReviewResult()
    }

    LaunchedEffect(state.IsReviewCompleted) {
        if (state.IsReviewCompleted) {
            controller.ResetReviewResult()
            onSubmitSuccess()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "4a122fac-b49e-493c-8188-f9023d494762", fallback = "Ürün Değerlendirme"),
                subtitle = BBLocalization.Current.Get(key = "d6ead38d-dd99-470e-9330-62839a243c02", fallback = "Satın aldığınız ürün için yorum yazın."),
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
                OrderReviewProductCard(
                    productId = productId,
                    orderStoreLineId = orderStoreLineId
                )
            }

            item {
                OrderReviewRatingCard(
                    rating = rating,
                    onRatingChange = { value ->
                        rating = value
                    }
                )
            }

            item {
                OrderReviewContentCard(
                    content = content,
                    onContentChange = { value ->
                        content = value
                    }
                )
            }

            item {
                OrderReviewAgreementCard(
                    accepted = accepted,
                    onAcceptedChange = { value ->
                        accepted = value
                    }
                )
            }

            state.ErrorMessage
                ?.takeIf {
                    state.CurrentAction == "InsertReviewAsync"
                }
                ?.let { errorMessage ->
                    item {
                        OrderReviewErrorCard(
                            message = errorMessage
                        )
                    }
                }

            item {
                OrderReviewActionCard(
                    canSubmit = canSubmit,
                    isLoading = state.IsLoading &&
                            state.CurrentAction == "InsertReviewAsync",
                    onBackClick = onBackClick,
                    onSubmitClick = {
                        controller.InsertReviewAsync(
                            ReviewInsertModel(
                                InsertedBy = memberId,
                                SourceType = "Product",
                                ItemId = productId.toInt(),
                                SecureKey = productSecureKey,
                                MemberId = memberId,
                                Content = content.trim(),
                                Rating = rating.toDouble()
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun OrderReviewProductCard(
    productId: Long,
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxXl)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.Feature)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "f92fb730-fadd-4e71-8ac7-769333b19083", fallback = "Değerlendirilecek Ürün"),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = "Ürün #$productId",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Sipariş satırı: $orderStoreLineId",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun OrderReviewRatingCard(
    rating: Int,
    onRatingChange: (Int) -> Unit
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
            OrderReviewSectionTitle(
                title = BBLocalization.Current.Get(key = "ff8659ff-509e-444e-9c59-472a54a9af89", fallback = "Puanınız"),
                subtitle = BBLocalization.Current.Get(key = "01c98b4a-601e-4837-a449-056329a0f9f2", fallback = "")
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                (1..5).forEach { value ->
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "$value yıldız",
                        tint = if (value <= rating) {
                            BBColors.Yellow.Yellow600
                        } else {
                            MaterialTheme.colorScheme.outlineVariant
                        },
                        modifier = Modifier
                            .size(BBIcon.Size2Xl)
                            .clickable {
                                onRatingChange(value)
                            }
                    )
                }

                Text(
                    text = "$rating/5",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun OrderReviewContentCard(
    content: String,
    onContentChange: (String) -> Unit
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
            OrderReviewSectionTitle(
                title = BBLocalization.Current.Get(key = "7d0f7ae4-b195-435e-9c17-de6fc8511e50", fallback = "Değerlendirmeniz"),
                subtitle = BBLocalization.Current.Get(key = "9b930caf-b338-4ce8-978e-df7bb99af20c", fallback = "Ürün deneyiminizi birkaç cümleyle anlatın.")
            )

            OutlinedTextField(
                value = content,
                onValueChange = onContentChange,
                modifier = Modifier.fillMaxWidth(),
                minLines = 5,
                shape = BBRadius.Input,
                placeholder = {
                    Text(
                        text = BBLocalization.Current.Get(key = "92a8aa7f-3d51-42b9-a878-b45b532fe0a6", fallback = "Ürün deneyiminizi yazın.")
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
private fun OrderReviewAgreementCard(
    accepted: Boolean,
    onAcceptedChange: (Boolean) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            onAcceptedChange(!accepted)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.Top
        ) {
            Checkbox(
                checked = accepted,
                onCheckedChange = onAcceptedChange
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = BBLocalization.Current.Get(key = "2f994618-0765-4476-932f-45ff0c3bbf14", fallback = "Değerlendirme kurallarını kabul ediyorum."),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = BBLocalization.Current.Get(key = "9c5dcfb6-f6f0-4aac-aeb8-b461c8723529", fallback = "Değerlendirmenin gerçek ürün deneyimine dayanması gerekir."),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun OrderReviewActionCard(
    canSubmit: Boolean,
    isLoading: Boolean,
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
                    BBLocalization.Current.Get(key = "747533e0-13c8-4b49-82ca-ebf9fea6b37f", fallback = "Gönderiliyor")
                } else {
                    BBLocalization.Current.Get(key = "000bf440-0909-4b14-b7f1-cba8861e579f", fallback = "Değerlendir")
                },
                onClick = onSubmitClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                enabled = canSubmit,
                leadingIcon = {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(BBIcon.ButtonIcon)
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
private fun OrderReviewErrorCard(message: String) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun OrderReviewSectionTitle(
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