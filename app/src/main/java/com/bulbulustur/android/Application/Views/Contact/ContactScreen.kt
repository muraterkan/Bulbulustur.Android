package com.bulbulustur.android.Application.Views.Contact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ContactSupport
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Handshake
import androidx.compose.material.icons.outlined.HelpCenter
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun ContactScreen(
    onSellerApplicationClick: () -> Unit = {},
    onSupportClick: () -> Unit = {},
    onInvestorClick: () -> Unit = {},
    onGeneralContactClick: () -> Unit = {},
    onSalesContactClick: () -> Unit = {},
    onPartnershipClick: () -> Unit = {},
    onFaqClick: (Int) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BBSpacing.md),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.md)
    ) {
        item {
            ContactHeader()
        }

        item {
            ContactQuickInfoCard()
        }

        item {
            BbSectionHeader(
                title = "Doğru ekibimize daha hızlı ulaşın",
                subtitle = "Talebinize uygun iletişim alanını seçin"
            )
        }

        items(contactChannelItems()) { channel ->
            ContactChannelCard(
                channel = channel,
                onClick = channel.onClickResolver(
                    onSellerApplicationClick = onSellerApplicationClick,
                    onSupportClick = onSupportClick,
                    onInvestorClick = onInvestorClick,
                    onGeneralContactClick = onGeneralContactClick,
                    onSalesContactClick = onSalesContactClick,
                    onPartnershipClick = onPartnershipClick
                )
            )
        }

        item {
            BbSectionHeader(
                title = "İletişime geçmenin başka yolu da var",
                subtitle = "Genel iletişim ve yardım merkezi kanalları"
            )
        }

        item {
            ContactAlternativePanel(
                onGeneralContactClick = onGeneralContactClick,
                onSupportClick = onSupportClick
            )
        }

        item {
            BbSectionHeader(
                title = "Sık sorulan sorular",
                subtitle = "İletişim öncesi hızlı cevaplar"
            )
        }

        items(contactFaqItems()) { faq ->
            ContactFaqCard(
                faq = faq,
                onClick = {
                    onFaqClick(faq.faqId)
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(BBSpacing.xl))
        }
    }
}

@Composable
private fun ContactHeader() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.lg),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ContactSupport,
                    contentDescription = null,
                    tint = BBColors.Primary
                )

                Text(
                    text = "Bize Ulaşın",
                    style = MaterialTheme.typography.labelLarge,
                    color = BBColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Size nasıl yardımcı olabiliriz?",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bulbulustur ekibi; satıcı, tedarikçi, müşteri desteği, yatırımcı desteği ve iş birlikleri için doğru kanala yönlendirir.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                BbChip(
                    text = "Genel iletişim",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Satıcı desteği",
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = "Yatırımcı ilişkileri",
                    selected = false,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun ContactQuickInfoCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.md),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                Icon(
                    imageVector = Icons.Outlined.SupportAgent,
                    contentDescription = null,
                    tint = BBColors.Primary
                )

                Text(
                    text = "Hızlı iletişim",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            ContactInfoRow(
                title = "Genel iletişim",
                value = "info@bulbulustur.com",
                icon = Icons.Outlined.Email
            )

            ContactInfoRow(
                title = "Çalışma saatleri",
                value = "Pazartesi - Cuma / 09:00 - 18:00",
                icon = Icons.Outlined.Work
            )

            ContactInfoRow(
                title = "Konum",
                value = "İstanbul, Türkiye",
                icon = Icons.Outlined.LocationOn
            )
        }
    }
}

@Composable
private fun ContactInfoRow(
    title: String,
    value: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = BBColors.Primary
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = BBColors.TextMuted
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun ContactChannelCard(
    channel: ContactChannelItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
        ) {
            Icon(
                imageVector = channel.icon,
                contentDescription = null,
                tint = BBColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = channel.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = channel.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = channel.actionLabel,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BBColors.Primary
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BBColors.TextMuted
            )
        }
    }
}

@Composable
private fun ContactAlternativePanel(
    onGeneralContactClick: () -> Unit,
    onSupportClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
    ) {
        BbCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = onGeneralContactClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.lg),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = BBColors.Primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
                ) {
                    Text(
                        text = "Genel iletişim formu",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Departman seçmeden genel iletişim talebi bırakın.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = BBColors.TextMuted
                )
            }
        }

        BbCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSupportClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.lg),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
            ) {
                Icon(
                    imageVector = Icons.Outlined.HelpCenter,
                    contentDescription = null,
                    tint = BBColors.Primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
                ) {
                    Text(
                        text = "Yardım merkezine git",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Sorularınız için rehberleri ve destek başlıklarını inceleyin.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = BBColors.TextMuted
                )
            }
        }
    }
}

@Composable
private fun ContactFaqCard(
    faq: ContactFaqItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.md)
        ) {
            Icon(
                imageVector = Icons.Outlined.QuestionAnswer,
                contentDescription = null,
                tint = BBColors.Primary
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
            ) {
                Text(
                    text = faq.question,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = faq.shortAnswer,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = BBColors.TextMuted
            )
        }
    }
}

private data class ContactChannelItem(
    val channelId: Int,
    val title: String,
    val description: String,
    val actionLabel: String,
    val target: ContactChannelTarget,
    val icon: ImageVector
) {
    fun onClickResolver(
        onSellerApplicationClick: () -> Unit,
        onSupportClick: () -> Unit,
        onInvestorClick: () -> Unit,
        onGeneralContactClick: () -> Unit,
        onSalesContactClick: () -> Unit,
        onPartnershipClick: () -> Unit
    ): () -> Unit {
        return when (target) {
            ContactChannelTarget.SellerApplication -> onSellerApplicationClick
            ContactChannelTarget.Support -> onSupportClick
            ContactChannelTarget.Investor -> onInvestorClick
            ContactChannelTarget.GeneralContact -> onGeneralContactClick
            ContactChannelTarget.Sales -> onSalesContactClick
            ContactChannelTarget.Partnership -> onPartnershipClick
        }
    }
}

private enum class ContactChannelTarget {
    SellerApplication,
    Support,
    Investor,
    GeneralContact,
    Sales,
    Partnership
}

private data class ContactFaqItem(
    val faqId: Int,
    val question: String,
    val shortAnswer: String
)

private fun contactChannelItems(): List<ContactChannelItem> {
    return listOf(
        ContactChannelItem(
            channelId = 1,
            title = "Tedarikçi / Satıcı başvurusu",
            description = "Bulbulustur ekosisteminde satış yapmak veya tedarikçi olarak yer almak için başvuru sürecine geçin.",
            actionLabel = "Başvuru sürecine geç",
            target = ContactChannelTarget.SellerApplication,
            icon = Icons.Outlined.Storefront
        ),
        ContactChannelItem(
            channelId = 2,
            title = "Müşteri desteği",
            description = "Sipariş, ödeme, üyelik ve kullanım deneyimiyle ilgili destek alın.",
            actionLabel = "Yardım merkezine git",
            target = ContactChannelTarget.Support,
            icon = Icons.Outlined.SupportAgent
        ),
        ContactChannelItem(
            channelId = 3,
            title = "Yatırımcı ilişkileri",
            description = "Basın bülteni, kurumsal sunum, yatırım ilişkileri ve medya iletişimi için bize ulaşın.",
            actionLabel = "Kurumsal bilgi alın",
            target = ContactChannelTarget.Investor,
            icon = Icons.Outlined.Business
        ),
        ContactChannelItem(
            channelId = 4,
            title = "Genel iletişim",
            description = "Bulbulustur hakkında öneri, bilgi ve genel sorularınızı iletin.",
            actionLabel = "Genel iletişime geç",
            target = ContactChannelTarget.GeneralContact,
            icon = Icons.Outlined.Email
        ),
        ContactChannelItem(
            channelId = 5,
            title = "Satış ve kurumsal çözümler",
            description = "Kurumsal çözüm, toplu satış ve ticari iş birlikleri için satış ekibiyle görüşün.",
            actionLabel = "Satış ile iletişime geç",
            target = ContactChannelTarget.Sales,
            icon = Icons.Outlined.Payments
        ),
        ContactChannelItem(
            channelId = 6,
            title = "Marka ve iş birlikleri",
            description = "Marka iş birlikleri, kampanya ortaklıkları ve stratejik çalışmalar için iletişime geçin.",
            actionLabel = "İş birliğini başlat",
            target = ContactChannelTarget.Partnership,
            icon = Icons.Outlined.Handshake
        )
    )
}

private fun contactFaqItems(): List<ContactFaqItem> {
    return listOf(
        ContactFaqItem(
            faqId = 1,
            question = "Doğru ekibe daha hızlı ulaşmak için ne yapmalıyım?",
            shortAnswer = "Talebinizin türüne göre ilgili iletişim kanalını seçmeniz yeterlidir."
        ),
        ContactFaqItem(
            faqId = 2,
            question = "Yardım merkezi hangi konularda destek sağlar?",
            shortAnswer = "Sipariş, ödeme, hesap, ürün, tedarik ve entegrasyon konularında rehberler sunar."
        ),
        ContactFaqItem(
            faqId = 3,
            question = "Telefon veya e-posta ile doğrudan ulaşabilir miyim?",
            shortAnswer = "Genel iletişim için info@bulbulustur.com adresini kullanabilirsiniz."
        ),
        ContactFaqItem(
            faqId = 4,
            question = "Marka iş birlikleri için nasıl başvurabilirim?",
            shortAnswer = "Marka ve iş birlikleri kanalından talebinizi iletebilirsiniz."
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun ContactScreenPreview() {
    BbTheme {
        ContactScreen()
    }
}
