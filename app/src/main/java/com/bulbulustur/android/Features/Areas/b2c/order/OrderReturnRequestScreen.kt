package com.bulbulustur.android.Features.Areas.b2c.order

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AssignmentReturn
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Ui.components.BbButton
import com.bulbulustur.android.Ui.components.BbButtonSize
import com.bulbulustur.android.Ui.components.BbButtonVariant
import com.bulbulustur.android.Ui.components.BbCard
import com.bulbulustur.android.Ui.components.BbCardPadding
import com.bulbulustur.android.Ui.components.BbCardVariant
import com.bulbulustur.android.Ui.theme.BbColors
import com.bulbulustur.android.Ui.theme.BbIcon
import com.bulbulustur.android.Ui.theme.BbRadius
import com.bulbulustur.android.Ui.theme.BbSpacing

@Composable
fun OrderReturnRequestScreen(
    onBackClick: () -> Unit = {},
    onSubmitClick: () -> Unit = {}
) {
    val reasons = remember {
        listOf(
            "Ürün beklentimi karşılamadı",
            "Yanlış ürün gönderildi",
            "Ürün hasarlı veya kusurlu geldi",
            "Eksik ürün veya parça var",
            "Diğer"
        )
    }

    var selectedReason by remember {
        mutableStateOf(reasons.first())
    }

    var description by remember {
        mutableStateOf("")
    }

    OrderReturnPageScaffold(
        title = "İade Talebi",
        subtitle = "Teslim edilen ürün için iade talebi oluştur.",
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
                OrderReturnIntroCard()
            }

            item {
                OrderReturnReasonCard(
                    reasons = reasons,
                    selectedReason = selectedReason,
                    onReasonClick = { reason ->
                        selectedReason = reason
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

            item {
                OrderReturnActionCard(
                    onBackClick = onBackClick,
                    onSubmitClick = onSubmitClick
                )
            }
        }
    }
}

@Composable
private fun OrderReturnPageScaffold(
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
        OrderReturnTopHeader(
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
private fun OrderReturnTopHeader(
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
private fun OrderReturnIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            OrderReturnIconBox(
                icon = Icons.Outlined.AssignmentReturn,
                backgroundColor = BbColors.Yellow.Yellow100,
                iconColor = BbColors.Yellow.Yellow800
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
            }
        }
    }
}

@Composable
private fun OrderReturnReasonCard(
    reasons: List<String>,
    selectedReason: String,
    onReasonClick: (String) -> Unit
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
            OrderReturnSectionTitle(
                title = "İade Nedeni",
                subtitle = "Bu ürünü neden iade etmek istediğinizi seçin."
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space2)
            ) {
                reasons.forEach { reason ->
                    OrderReturnReasonRow(
                        text = reason,
                        selected = reason == selectedReason,
                        onClick = {
                            onReasonClick(reason)
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (selected) BbColors.Yellow.Yellow50 else MaterialTheme.colorScheme.surfaceVariant,
                shape = BbRadius.LgShape
            )
            .clickable {
                onClick()
            }
            .padding(BbSpacing.CardPaddingCompact),
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (selected) Icons.Outlined.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
            contentDescription = null,
            tint = if (selected) BbColors.Yellow.Yellow800 else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BbIcon.Action)
        )

        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            OrderReturnSectionTitle(
                title = "Açıklama",
                subtitle = "İsterseniz iade talebinizle ilgili ek bilgi yazabilirsiniz."
            )

            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                shape = BbRadius.Input,
                placeholder = {
                    Text(
                        text = "Üründe yaşadığınız sorunu kısaca açıklayın."
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Description,
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
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
            verticalAlignment = Alignment.Top
        ) {
            OrderReturnIconBox(
                icon = Icons.Outlined.VerifiedUser,
                backgroundColor = BbColors.Blue.Blue50,
                iconColor = BbColors.Blue.Blue600
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
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
private fun OrderReturnActionCard(
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            BbButton(
                text = "Talebi Gönder",
                onClick = onSubmitClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Primary,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(BbIcon.ButtonIcon)
                    )
                }
            )

            BbButton(
                text = "Sipariş Detayına Dön",
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
private fun OrderReturnIconBox(
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