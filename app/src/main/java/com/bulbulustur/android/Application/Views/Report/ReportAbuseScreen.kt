package com.bulbulustur.android.Application.Views.Report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

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
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Kötüye Kullanım Bildir",
                onBackClick = onBackClick
            )
        },
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
                    PaddingValues(
                        start = BBSpacing.PageHorizontal,
                        top = BBSpacing.PageTopCompact,
                        end = BBSpacing.PageHorizontal,
                        bottom = BBSpacing.PageBottom
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            ReportAbuseIntroCard(
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
private fun ReportAbuseIntroCard(
    targetTitle: String,
    targetType: ReportTargetType
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = "Güvenlik Ve Kalite Bildirimi",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Listelenen ${targetType.displayName.lowercase()} isminin, açıklamasının veya içeriğinin Bulbulustur kurallarını ihlal ettiğini düşünüyorsanız bize bildirebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

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
                    .size(BBSpacing.Space12)
                    .background(
                        color = BBColors.Green.Green500,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = targetType.shortCode,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = BBColors.White
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = targetType.displayName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = targetTitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
private fun ReportSafetyInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Text(
                text = "Bildiriminiz İncelenir",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Ürün bilgisi, görsel, açıklama veya satıcıyla ilgili uygunsuz bir durum fark ettiyseniz kısa bilgilerle bildirebilirsiniz.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
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
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "✓",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = BBColors.Green.Green700
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        BbSectionHeader(
            title = "�?ikayet Nedenini Seç",
            subtitle = "Bildirimini daha hızlı değerlendirebilmemiz için uygun nedeni seç."
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
        ) {
            reasons.forEach { reason ->
                FilterChip(
                    selected = selectedReason.id == reason.id,
                    onClick = {
                        onReasonClick(reason)
                    },
                    label = {
                        Text(
                            text = reason.title
                        )
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
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            BbSectionHeader(
                title = "Bildirim Detayları",
                subtitle = "Kısa bir açıklama yazmanız incelemeyi kolaylaştırır."
            )

            TextField(
                value = detail,
                onValueChange = onDetailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BBSpacing.Space16 + BBSpacing.Space16)
                    .clip(BBRadius.LgShape),
                placeholder = {
                    Text(
                        text = "Bilmemiz gereken başka bir şey var mı? Detayları buraya yazın."
                    )
                },
                shape = BBRadius.LgShape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedIndicatorColor = BBColors.Transparent,
                    unfocusedIndicatorColor = BBColors.Transparent,
                    disabledIndicatorColor = BBColors.Transparent
                )
            )
        }
    }
}

@Composable
private fun ReportPrivacyNoticeCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(BBSpacing.Space6)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = BBRadius.PillShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "i",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = BBColors.Green.Green700
                )
            }

            Text(
                text = "Kişisel bilgi, ödeme bilgisi veya üçüncü kişilere ait özel bilgiler paylaşmayın. Bildirimin kalite ve güvenlik kontrolleri kapsamında değerlendirilebilir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ReportAbuseBottomBar(
    canSubmit: Boolean,
    onSubmitClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = BBSpacing.Space1,
        shadowElevation = BBSpacing.Space2
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.PageHorizontal)
        ) {
            BbButton(
                text = "Gönder",
                onClick = onSubmitClick,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Success,
                size = BbButtonSize.Medium,
                enabled = canSubmit
            )
        }
    }
}

@Composable
private fun ReportTargetMiniPill(
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = BBRadius.PillShape
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
            title = "Yanıltıcı Ürün Bilgisi"
        ),
        ReportReasonItem(
            id = 2,
            title = "Uygunsuz Görsel Veya Açıklama"
        ),
        ReportReasonItem(
            id = 3,
            title = "Sahte Ürün �?üphesi"
        ),
        ReportReasonItem(
            id = 4,
            title = "Yasaklı Ürün"
        ),
        ReportReasonItem(
            id = 5,
            title = "Kötüye Kullanım"
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
