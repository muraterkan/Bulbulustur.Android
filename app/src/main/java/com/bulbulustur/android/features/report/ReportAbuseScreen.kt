package com.bulbulustur.android.features.report

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbSectionHeader
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun ReportAbuseScreen(
    targetId: Int = 1,
    targetTitle: String = "Ortobella Confort Hakiki Deri Topuk Dikeni Terlik M13",
    targetType: ReportTargetType = ReportTargetType.Product,
    onBackClick: () -> Unit = {},
    onSubmitClick: (ReportAbuseFormState) -> Unit = {}
) {
    val reportReasons = remember {
        getReportReasonItems()
    }

    var selectedReason by remember {
        mutableStateOf(reportReasons.first())
    }

    var reportDetail by remember {
        mutableStateOf("")
    }

    val formState = ReportAbuseFormState(
        targetId = targetId,
        targetTitle = targetTitle,
        targetType = targetType,
        reason = selectedReason,
        detail = reportDetail
    )

    Scaffold(
        bottomBar = {
            ReportAbuseBottomBar(
                canSubmit = selectedReason.id > 0,
                onSubmitClick = {
                    onSubmitClick(formState)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = BbSpacing.md,
                    top = BbSpacing.md,
                    end = BbSpacing.md,
                    bottom = BbSpacing.xl
                ),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.md)
        ) {
            ReportAbuseTopBar(
                onBackClick = onBackClick
            )

            ReportAbuseHeroCard(
                targetTitle = targetTitle,
                targetType = targetType
            )

            ReportTargetCard(
                targetTitle = targetTitle,
                targetType = targetType
            )

            ReportSafetyInfoCard()

            ReportReasonSection(
                reasons = reportReasons,
                selectedReason = selectedReason,
                onReasonClick = {
                    selectedReason = it
                }
            )

            ReportDetailSection(
                detail = reportDetail,
                onDetailChange = {
                    reportDetail = it
                }
            )

            ReportPrivacyNoticeCard()
        }
    }
}

@Composable
private fun ReportAbuseTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(BbSpacing.xl)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    onBackClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = BbColors.TextStrong
            )
        }

        Spacer(modifier = Modifier.width(BbSpacing.md))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Kötüye kullanım bildir",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Güvenlik ve kalite bildirimi",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextStrong.copy(alpha = 0.62f)
            )
        }
    }
}

@Composable
private fun ReportAbuseHeroCard(
    targetTitle: String,
    targetType: ReportTargetType
) {
    BbCard {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            ReportStatusPill(
                text = "Güvenlik ve kalite bildirimi"
            )

            Text(
                text = "Kötüye kullanım bildir",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Listelenen ${targetType.displayName.lowercase()} isminin, açıklamasının veya içeriğinin Bulbulustur kurallarını ihlal ettiğini düşünüyorsan bize bildirebilirsin.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextStrong.copy(alpha = 0.68f)
            )

            Spacer(modifier = Modifier.height(BbSpacing.xs))

            ReportTargetMiniPill(
                text = targetTitle
            )
        }
    }
}

@Composable
private fun ReportTargetCard(
    targetTitle: String,
    targetType: ReportTargetType
) {
    BbCard {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.xxl)
                    .clip(RoundedCornerShape(BbRadius.md))
                    .background(BbColors.Success),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = targetType.shortCode,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface
                )
            }

            Spacer(modifier = Modifier.width(BbSpacing.md))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = targetType.displayName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.TextStrong
                )

                Text(
                    text = targetTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = BbColors.TextStrong.copy(alpha = 0.68f),
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
private fun ReportSafetyInfoCard() {
    BbCard {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            Text(
                text = "Bildiriminiz incelenir",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = BbColors.TextStrong
            )

            Text(
                text = "Ürün bilgisi, görsel, açıklama veya satıcıyla ilgili uygunsuz bir durum fark ettiysen kısa bilgilerle bildirebilirsin.",
                style = MaterialTheme.typography.bodyMedium,
                color = BbColors.TextStrong.copy(alpha = 0.68f)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.xs)
            ) {
                ReportSafetyBullet(
                    text = "Yanıltıcı ürün bilgisi"
                )

                ReportSafetyBullet(
                    text = "Uygunsuz görsel veya açıklama"
                )

                ReportSafetyBullet(
                    text = "Sahte ürün veya kötüye kullanım şüphesi"
                )
            }
        }
    }
}

@Composable
private fun ReportSafetyBullet(
    text: String
) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "✓",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = BbColors.Success
        )

        Spacer(modifier = Modifier.width(BbSpacing.sm))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = BbColors.TextStrong.copy(alpha = 0.68f)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ReportReasonSection(
    reasons: List<ReportReasonItem>,
    selectedReason: ReportReasonItem,
    onReasonClick: (ReportReasonItem) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
    ) {
        BbSectionHeader(
            title = "Şikayet nedenini seç",
            subtitle = "Bildirimini daha hızlı değerlendirebilmemiz için uygun nedeni seç."
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BbSpacing.sm),
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            reasons.forEach { reason ->
                FilterChip(
                    selected = selectedReason.id == reason.id,
                    onClick = {
                        onReasonClick(reason)
                    },
                    label = {
                        Text(text = reason.title)
                    }
                )
            }
        }
    }
}

@Composable
private fun ReportDetailSection(
    detail: String,
    onDetailChange: (String) -> Unit
) {
    BbCard {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.sm)
        ) {
            BbSectionHeader(
                title = "Bildirim detayları",
                subtitle = "Kısa bir açıklama yazman incelemeyi kolaylaştırır."
            )

            TextField(
                value = detail,
                onValueChange = onDetailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BbSpacing.Space16 + BbSpacing.Space16)
                    .clip(RoundedCornerShape(BbRadius.lg)),
                placeholder = {
                    Text(
                        text = "Bilmemiz gereken başka bir şey var mı? Detayları buraya yaz."
                    )
                },
                shape = RoundedCornerShape(BbRadius.lg),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
private fun ReportPrivacyNoticeCard() {
    BbCard {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(BbSpacing.lg)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "i",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = BbColors.Success
                )
            }

            Spacer(modifier = Modifier.width(BbSpacing.sm))

            Text(
                text = "Kişisel bilgi, ödeme bilgisi veya üçüncü kişilere ait özel bilgiler paylaşma. Bildirimin kalite ve güvenlik kontrolleri kapsamında değerlendirilebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = BbColors.TextStrong.copy(alpha = 0.68f)
            )
        }
    }
}

@Composable
private fun ReportAbuseBottomBar(
    canSubmit: Boolean,
    onSubmitClick: () -> Unit
) {
    val buttonContainerColor = if (canSubmit) {
        BbColors.Success
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    val buttonContentColor = if (canSubmit) {
        MaterialTheme.colorScheme.surface
    } else {
        BbColors.TextStrong.copy(alpha = 0.46f)
    }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = BbSpacing.xs,
        shadowElevation = BbSpacing.sm
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BbSpacing.md)
                .clip(RoundedCornerShape(BbRadius.pill))
                .background(buttonContainerColor)
                .clickable(enabled = canSubmit) {
                    onSubmitClick()
                }
                .padding(
                    horizontal = BbSpacing.lg,
                    vertical = BbSpacing.md
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Gönder",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = buttonContentColor
            )
        }
    }
}

@Composable
private fun ReportStatusPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.sm,
                vertical = BbSpacing.xs
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = BbColors.Success
        )
    }
}

@Composable
private fun ReportTargetMiniPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(BbRadius.pill))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = BbSpacing.sm,
                vertical = BbSpacing.xs
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = BbColors.TextStrong
        )
    }
}

enum class ReportTargetType(
    val displayName: String,
    val shortCode: String
) {
    Product(
        displayName = "Ürün",
        shortCode = "Ü"
    ),
    Store(
        displayName = "Mağaza",
        shortCode = "M"
    ),
    Review(
        displayName = "Yorum",
        shortCode = "Y"
    ),
    Question(
        displayName = "Soru",
        shortCode = "S"
    ),
    Message(
        displayName = "Mesaj",
        shortCode = "M"
    )
}

data class ReportAbuseFormState(
    val targetId: Int,
    val targetTitle: String,
    val targetType: ReportTargetType,
    val reason: ReportReasonItem,
    val detail: String
)

data class ReportReasonItem(
    val id: Int,
    val title: String
)

private fun getReportReasonItems(): List<ReportReasonItem> {
    return listOf(
        ReportReasonItem(
            id = 1,
            title = "Yanıltıcı ürün bilgisi"
        ),
        ReportReasonItem(
            id = 2,
            title = "Uygunsuz görsel veya açıklama"
        ),
        ReportReasonItem(
            id = 3,
            title = "Sahte ürün şüphesi"
        ),
        ReportReasonItem(
            id = 4,
            title = "Yasaklı ürün"
        ),
        ReportReasonItem(
            id = 5,
            title = "Kötüye kullanım"
        ),
        ReportReasonItem(
            id = 6,
            title = "Diğer"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ReportAbuseScreenPreview() {
    ReportAbuseScreen()
}