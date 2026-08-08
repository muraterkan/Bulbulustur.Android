package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

private const val SellerOnboardingUrl = "https://www.bulbulustur.com/seller"

@Composable
fun StoreOnboardingInfoScreen(
    onBackClick: () -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "9a9add6f-9ab5-47dc-863d-7a57fb437a0d", fallback = "Satıcı Başvurusu"),
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
                top = BBSpacing.SectionGapCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                SellerOnboardingHeroCard(
                    onContinueWebClick = {
                        uriHandler.openUri(SellerOnboardingUrl)
                    }
                )
            }

            item {
                SellerOnboardingStepCard(
                    icon = Icons.Outlined.Business,
                    title = "Şirket bilgileri",
                    description = BBLocalization.Current.Get(key = "834c714e-645b-411f-a971-9d80f242e210", fallback = "Mağaza başvurusu için şirket ve iletişim bilgileri web panelinde tamamlanır.")
                )
            }

            item {
                SellerOnboardingStepCard(
                    icon = Icons.Outlined.RequestQuote,
                    title = BBLocalization.Current.Get(key = "2bb360bf-843a-48b1-947f-2924e98e98ab", fallback = "Belgeler ve onay"),
                    description = BBLocalization.Current.Get(key = "71509df1-555d-436b-8e5b-334f84e8af3d", fallback = "Gerekli belgeler, başvuru değerlendirmesi ve satıcı onayı web üzerinden yürütülür.")
                )
            }

            item {
                SellerOnboardingStepCard(
                    icon = Icons.Outlined.Inventory2,
                    title = BBLocalization.Current.Get(key = "84be47c2-4c83-4aa1-8ed6-172002c11985", fallback = "Ürün ve mağaza yönetimi"),
                    description = BBLocalization.Current.Get(key = "10a17c32-f9ea-4c47-bafa-36947bbd1844", fallback = "Ürün yükleme, mağaza vitrini ve sipariş yönetimi satıcı panelinden yapılır.")
                )
            }

            item {
                SellerOnboardingPrimaryButton(
                    text = BBLocalization.Current.Get(key = "80e87ccf-f5c5-4c3d-9e48-a650af3af2d2", fallback = "Web Sitemizden Devam Et"),
                    icon = Icons.AutoMirrored.Outlined.OpenInNew,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        uriHandler.openUri(SellerOnboardingUrl)
                    }
                )
            }
        }
    }
}

@Composable
private fun SellerOnboardingHeroCard(
    onContinueWebClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.Box2Xl),
                shape = BBRadius.XlShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.Size2Xl)
                    )
                }
            }

            Text(
                text = "Bulbulustur'da Satıcı Olun",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(key = "e4106127-c08b-41d5-96b4-3a0164af48e7", fallback = "Mağaza açma başvurusu, şirket bilgileri ve ürün yönetimi web paneli üzerinden tamamlanır. Mobil uygulama alıcı deneyimi için tasarlanmıştır."),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SellerOnboardingPrimaryButton(
                text = BBLocalization.Current.Get(key = "80e87ccf-f5c5-4c3d-9e48-a650af3af2d2", fallback = "Web Sitemizden Devam Et"),
                icon = Icons.AutoMirrored.Outlined.OpenInNew,
                modifier = Modifier.fillMaxWidth(),
                onClick = onContinueWebClick
            )
        }
    }
}

@Composable
private fun SellerOnboardingStepCard(
    icon: ImageVector,
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
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(BBIcon.SizeSm)
                    )

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

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
private fun SellerOnboardingPrimaryButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(BBSpacing.Space12)
            .clip(BBRadius.PillShape)
            .clickable { onClick() },
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primary,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.SizeMd)
            )

            Spacer(modifier = Modifier.size(BBSpacing.Space2))

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SellerOnboardingInfoScreenPreview() {
    BbTheme {
        StoreOnboardingInfoScreen()
    }
}
