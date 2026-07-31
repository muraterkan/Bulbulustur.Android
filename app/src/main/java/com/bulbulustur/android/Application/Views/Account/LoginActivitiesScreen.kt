package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.History
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography
import com.bulbulustur.android.businesslayer.Core.DTO.MemberLoginActivityDTO

@Composable
fun LoginActivitiesScreen(
    activities: List<MemberLoginActivityDTO>,
    isLoading: Boolean,
    errorMessage: String?,
    onBackClick: () -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = "Giriş Hareketleri",
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                LoginActivitiesIntroCard()
            }

            when {
                isLoading -> {
                    item {
                        LoginActivitiesLoadingState()
                    }
                }

                !errorMessage.isNullOrBlank() -> {
                    item {
                        LoginActivitiesErrorState(
                            errorMessage = errorMessage,
                            onRetryClick = onRetryClick
                        )
                    }
                }

                activities.isEmpty() -> {
                    item {
                        LoginActivitiesEmptyState()
                    }
                }

                else -> {
                    items(
                        items = activities,
                        key = { activity -> activity.LogId }
                    ) { activity ->
                        LoginActivityCard(activity)
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginActivitiesIntroCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxMd)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.History,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Ui)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Oturum Geçmişi",
                    style = BbTypography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Hesabınıza yapılan son girişleri, cihazları ve erişim bilgilerini inceleyin.",
                    style = BbTypography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun LoginActivityCard(activity: MemberLoginActivityDTO) {
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
                LoginActivityIconBox(activity)

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = getLoginActivityTitle(activity),
                        style = BbTypography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = getLoginActivitySubtitle(activity),
                        style = BbTypography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            LoginActivityInfoRow(
                label = "IP Adresi",
                value = activity.Ip.ifBlank { "Bilgi bulunmuyor" }
            )

            LoginActivityInfoRow(
                label = "İşletim Sistemi",
                value = activity.Os.ifBlank { "Bilgi bulunmuyor" }
            )

            LoginActivityInfoRow(
                label = "Giriş Yöntemi",
                value = getLoginProviderText(activity.LoginProvider)
            )

            LoginActivityInfoRow(
                label = BBLocalization.Current.Get(key = "e4602d88-9a44-4ed3-827b-8844da4a88be", fallback = "Tarih"),
                value = activity.InsertedDate.ifBlank { "Bilgi bulunmuyor" }
            )
        }
    }
}

@Composable
private fun LoginActivityIconBox(activity: MemberLoginActivityDTO) {
    Box(
        modifier = Modifier
            .size(BBIcon.BoxLg)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = BBRadius.XlShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = getLoginActivityShortCode(activity),
            style = BbTypography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun LoginActivityInfoRow(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = label,
            style = BbTypography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = BbTypography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun LoginActivitiesLoadingState() {
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
            CircularProgressIndicator(
                modifier = Modifier.size(BBIcon.BoxSm),
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Giriş hareketleri yükleniyor",
                style = BbTypography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabınıza ait son oturum kayıtları getiriliyor.",
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LoginActivitiesErrorState(
    errorMessage: String,
    onRetryClick: () -> Unit
) {
    BbCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onRetryClick()
            },
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.errorContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ErrorOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Text(
                text = "Giriş Hareketleri Alınamadı",
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = errorMessage,
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Yeniden denemek için dokunun",
                style = BbTypography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun LoginActivitiesEmptyState() {
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
            Box(
                modifier = Modifier
                    .size(BBIcon.BoxLg)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = BBRadius.LgShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Devices,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(BBIcon.Section)
                )
            }

            Text(
                text = "Giriş Kaydı Bulunmuyor",
                style = BbTypography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabınıza ait giriş hareketleri oluştuğunda burada listelenecektir.",
                style = BbTypography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun getLoginActivityTitle(activity: MemberLoginActivityDTO): String {
    if (activity.Device.isNotBlank()) return activity.Device
    if (activity.Os.isNotBlank()) return activity.Os
    if (activity.Browser.isNotBlank()) return activity.Browser

    return "Bilinmeyen Cihaz"
}

private fun getLoginActivitySubtitle(activity: MemberLoginActivityDTO): String {
    val values = listOf(activity.Browser, activity.Os)
        .filter { value -> value.isNotBlank() }
        .distinct()

    return values.joinToString(" • ").ifBlank {
        "Cihaz bilgisi bulunmuyor"
    }
}

private fun getLoginActivityShortCode(activity: MemberLoginActivityDTO): String {
    val source = "${activity.Device} ${activity.Os}".lowercase()

    return when {
        "android" in source -> "AN"
        "iphone" in source || "ios" in source -> "IO"
        "windows" in source -> "PC"
        "mac" in source -> "MC"
        "linux" in source -> "LX"
        "tablet" in source -> "TB"
        "mobile" in source || "mobil" in source -> "MB"
        else -> "CI"
    }
}

private fun getLoginProviderText(loginProvider: String): String {
    if (loginProvider.isBlank()) return "Standart giriş"

    return when (loginProvider.lowercase()) {
        "google" -> "Google"
        "apple" -> "Apple"
        "facebook" -> "Facebook"
        "email", "password", "local" -> "E-posta ve şifre"
        else -> loginProvider
    }
}