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
import com.bulbulustur.android.Application.Localization.BBLocalization
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
                title = BBLocalization.Current.Get(key = "0e4889ea-d010-4173-befa-145a608d91b6", fallback = "Doğru ekibimize daha hızlı ulaşın"),
                subtitle = BBLocalization.Current.Get(key = "00ebd101-c059-41dd-b64b-abc099738655", fallback = "Talebinize uygun iletişim alanını seçin")
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
                title = BBLocalization.Current.Get(key = "0d8fef2d-cb6f-4e38-9ade-982929fd66b2", fallback = "İletişime geçmenin başka yolu da var"),
                subtitle = BBLocalization.Current.Get(key = "9f586d2d-7904-4488-a46f-0a5d5c2d8218", fallback = "Genel iletişim ve yardım merkezi kanalları")
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
                title = BBLocalization.Current.Get(key = "9ef8310c-41c7-477b-a3d9-af4218787955", fallback = "Sık sorulan sorular"),
                subtitle = BBLocalization.Current.Get(key = "45ce4be5-4e83-4fe3-bbaa-9f23549dae6e", fallback = "İletişim öncesi hızlı cevaplar")
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
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = BBLocalization.Current.Get(key = "48fc40fa-ca5a-4e5a-9add-5dc14c5d61bd", fallback = "Bize Ulaşın"),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = BBLocalization.Current.Get(key = "d70b4a99-dca7-46b6-80ed-b124d3513f24", fallback = "Size nasıl yardımcı olabiliriz?"),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "c0d2b753-9adf-4f80-9de4-2087879fb203", fallback = "Bulbulustur ekibi; satıcı, tedarikçi, müşteri desteği, yatırımcı desteği ve iş birlikleri için doğru kanala yönlendirir."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.sm),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.sm)
            ) {
                BbChip(
                    text = BBLocalization.Current.Get(key = "4090dd0c-9586-4764-afc6-f098565fa7ae", fallback = ""),
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = BBLocalization.Current.Get(key = "5d0ca576-61d3-4392-8279-37ebaa20b112", fallback = "Satıcı desteği"),
                    selected = false,
                    onClick = {}
                )

                BbChip(
                    text = BBLocalization.Current.Get(key = "d70791d0-68e8-487c-94b5-d4002ff4b135", fallback = "Yatırımcı ilişkileri"),
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
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = BBLocalization.Current.Get(key = "95d658cb-8400-470a-8a9d-bad58d8a7d67", fallback = "Hızlı iletişim"),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            ContactInfoRow(
                title = BBLocalization.Current.Get(key = "4090dd0c-9586-4764-afc6-f098565fa7ae", fallback = ""),
                value = "info@bulbulustur.com",
                icon = Icons.Outlined.Email
            )

            ContactInfoRow(
                title = BBLocalization.Current.Get(key = "43c30728-ac8e-4b4a-99ac-138fdbe37aab", fallback = ""),
                value = "Pazartesi - Cuma / 09:00 - 18:00",
                icon = Icons.Outlined.Work
            )

            ContactInfoRow(
                title = BBLocalization.Current.Get(key = "ba7ade8e-52c2-4154-aac6-e0e20df6e803", fallback = "Konum"),
                value = BBLocalization.Current.Get(key = "77dde9df-f325-499d-a108-d1b481d1fe90", fallback = "İstanbul, Türkiye"),
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
            tint = MaterialTheme.colorScheme.primary
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
                tint = MaterialTheme.colorScheme.primary
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
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
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
                    tint = MaterialTheme.colorScheme.primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "4090dd0c-9586-4764-afc6-f098565fa7ae", fallback = ""),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = BBLocalization.Current.Get(key = "c369318f-36de-4b4c-b5ab-2f5fddfb658b", fallback = "Departman seçmeden genel iletişim talebi bırakın."),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
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
                    tint = MaterialTheme.colorScheme.primary
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.xs)
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "9d632e94-da2e-446a-bd4e-42f716b1cf88", fallback = "Yardım merkezine git"),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = BBLocalization.Current.Get(key = "c1ef7d15-6b45-4035-9bc9-4fba43986586", fallback = "Sorularınız için rehberleri ve destek başlıklarını inceleyin."),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = Icons.Outlined.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
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
                tint = MaterialTheme.colorScheme.primary
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
                tint = MaterialTheme.colorScheme.onSurfaceVariant
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
            title = BBLocalization.Current.Get(key = "9a9add6f-9ab5-47dc-863d-7a57fb437a0d", fallback = "Tedarikçi / Satıcı başvurusu"),
            description = BBLocalization.Current.Get(key = "60e3b3c7-bbba-47fa-8194-4e727ddd43f9", fallback = "Bulbulustur ekosisteminde satış yapmak veya tedarikçi olarak yer almak için başvuru sürecine geçin."),
            actionLabel = BBLocalization.Current.Get(key = "6a18c043-0ab7-4b2a-ab4d-adcafb7cf7cc", fallback = "Başvuru sürecine geç"),
            target = ContactChannelTarget.SellerApplication,
            icon = Icons.Outlined.Storefront
        ),
        ContactChannelItem(
            channelId = 2,
            title = BBLocalization.Current.Get(key = "a2805229-b6c3-4470-b0ca-bbf833f3b95a", fallback = "Müşteri desteği"),
            description = BBLocalization.Current.Get(key = "9f4c79e1-8d1e-4a7f-b9ff-5da7ff6fa5f3", fallback = "Sipariş, ödeme, üyelik ve kullanım deneyimiyle ilgili destek alın."),
            actionLabel = BBLocalization.Current.Get(key = "9d632e94-da2e-446a-bd4e-42f716b1cf88", fallback = "Yardım merkezine git"),
            target = ContactChannelTarget.Support,
            icon = Icons.Outlined.SupportAgent
        ),
        ContactChannelItem(
            channelId = 3,
            title = BBLocalization.Current.Get(key = "d70791d0-68e8-487c-94b5-d4002ff4b135", fallback = "Yatırımcı ilişkileri"),
            description = BBLocalization.Current.Get(key = "205875dd-2407-4da7-9ace-27bbbaacc5e5", fallback = "Basın bülteni, kurumsal sunum, yatırım ilişkileri ve medya iletişimi için bize ulaşın."),
            actionLabel = BBLocalization.Current.Get(key = "1e4b1d75-1355-44ce-98f7-979bdf72eeaa", fallback = "Kurumsal bilgi alın"),
            target = ContactChannelTarget.Investor,
            icon = Icons.Outlined.Business
        ),
        ContactChannelItem(
            channelId = 4,
            title = BBLocalization.Current.Get(key = "4090dd0c-9586-4764-afc6-f098565fa7ae", fallback = ""),
            description = BBLocalization.Current.Get(key = "785f0240-6c84-430d-b6d3-e494576644c1", fallback = "Bulbulustur hakkında öneri, bilgi ve genel sorularınızı iletin."),
            actionLabel = BBLocalization.Current.Get(key = "a439130c-b2cf-496f-9868-93ef084d9aec", fallback = "Genel iletişime geç"),
            target = ContactChannelTarget.GeneralContact,
            icon = Icons.Outlined.Email
        ),
        ContactChannelItem(
            channelId = 5,
            title = BBLocalization.Current.Get(key = "0881aa82-2e41-4737-b4f6-f7fc198e0359", fallback = "Satış ve kurumsal çözümler"),
            description = BBLocalization.Current.Get(key = "6324680f-89e4-41a8-b71e-ac06eec9cc7c", fallback = "Kurumsal çözüm, toplu satış ve ticari iş birlikleri için satış ekibiyle görüşün."),
            actionLabel = BBLocalization.Current.Get(key = "a439130c-b2cf-496f-9868-93ef084d9aec", fallback = "Satış ile iletişime geç"),
            target = ContactChannelTarget.Sales,
            icon = Icons.Outlined.Payments
        ),
        ContactChannelItem(
            channelId = 6,
            title = BBLocalization.Current.Get(key = "eaf557b8-4925-4f0b-85ef-733efd386ae0", fallback = "Marka ve iş birlikleri"),
            description = BBLocalization.Current.Get(key = "fbed88f5-236f-49ee-8ebc-d28205f905ee", fallback = "Marka iş birlikleri, kampanya ortaklıkları ve stratejik çalışmalar için iletişime geçin."),
            actionLabel = BBLocalization.Current.Get(key = "ea5ebee1-0cc8-4054-a2c4-745c70db84c5", fallback = "İş birliğini başlat"),
            target = ContactChannelTarget.Partnership,
            icon = Icons.Outlined.Handshake
        )
    )
}

private fun contactFaqItems(): List<ContactFaqItem> {
    return listOf(
        ContactFaqItem(
            faqId = 1,
            question = BBLocalization.Current.Get(key = "fabde33a-eca8-4eaf-a5bf-5af8d40b1e25", fallback = "Doğru ekibe daha hızlı ulaşmak için ne yapmalıyım?"),
            shortAnswer = BBLocalization.Current.Get(key = "6d8205ad-b7b4-4d80-9a4a-d033632f2f47", fallback = "Talebinizin türüne göre ilgili iletişim kanalını seçmeniz yeterlidir.")
        ),
        ContactFaqItem(
            faqId = 2,
            question = BBLocalization.Current.Get(key = "678035e2-0d20-4e60-b5b6-11d2a4225ece", fallback = "Yardım merkezi hangi konularda destek sağlar?"),
            shortAnswer = BBLocalization.Current.Get(key = "dac55d87-664f-498a-bae2-cd8684f202d3", fallback = "Sipariş, ödeme, hesap, ürün, tedarik ve entegrasyon konularında rehberler sunar.")
        ),
        ContactFaqItem(
            faqId = 3,
            question = BBLocalization.Current.Get(key = "d2e14c79-8241-4a1f-8809-69df3b5422bc", fallback = "Telefon veya e-posta ile doğrudan ulaşabilir miyim?"),
            shortAnswer = BBLocalization.Current.Get(key = "2f452741-78c5-4eee-be70-79574db28b45", fallback = "Genel iletişim için info@bulbulustur.com adresini kullanabilirsiniz.")
        ),
        ContactFaqItem(
            faqId = 4,
            question = BBLocalization.Current.Get(key = "38b1b0f5-560e-4766-ac50-b0b399f35c7b", fallback = "Marka iş birlikleri için nasıl başvurabilirim?"),
            shortAnswer = BBLocalization.Current.Get(key = "b5728321-4059-41b9-8607-d4e63290bb88", fallback = "Marka ve iş birlikleri kanalından talebinizi iletebilirsiniz.")
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

