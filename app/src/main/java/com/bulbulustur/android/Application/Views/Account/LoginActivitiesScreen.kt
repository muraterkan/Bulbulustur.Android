package com.bulbulustur.android.Application.Views.Account

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun LoginActivitiesScreen(
    onBackClick: () -> Unit = {}
) {
    val loginActivities = getDemoLoginActivities()

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
            if (loginActivities.isEmpty()) {
                item {
                    LoginActivitiesEmptyState()
                }
            }

            items(
                items = loginActivities,
                key = { activity -> activity.loginActivityId }
            ) { activity ->
                LoginActivityCard(
                    activity = activity
                )
            }
        }
    }
}

@Composable
private fun LoginActivityCard(
    activity: LoginActivityUiModel
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LoginActivityIconBox(
                    text = activity.shortCode
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = activity.deviceName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = activity.browserName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    LoginActivityStatusBadge(
                        trusted = activity.trusted
                    )
                }
            }

            LoginActivityInfoRow(
                label = "IP Adresi",
                value = activity.ipAddress
            )

            LoginActivityInfoRow(
                label = "Konum",
                value = activity.locationText
            )

            LoginActivityInfoRow(
                label = "Tarih",
                value = activity.loginDate
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
            LoginActivityEmptyIconBox()

            Text(
                text = "Giriş Kaydı Bulunmuyor",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabınıza ait giriş hareketleri oluştuĞunda burada listelenir.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
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
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun LoginActivityStatusBadge(
    trusted: Boolean
) {
    val backgroundColor = if (trusted) {
        BBColors.Green.Green50
    } else {
        BBColors.Orange.Orange50
    }

    val textColor = if (trusted) {
        BBColors.Green.Green700
    } else {
        BBColors.Orange.Orange700
    }

    val text = if (trusted) {
        "Tanıdık Giriş"
    } else {
        "Kontrol Edilmeli"
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BBRadius.Badge
            )
            .padding(
                horizontal = BBSpacing.BadgePaddingHorizontal,
                vertical = BBSpacing.BadgePaddingVertical
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

@Composable
private fun LoginActivityIconBox(
    text: String
) {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space14)
            .background(
                color = BBColors.Yellow.Yellow100,
                shape = BBRadius.XlShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = BBColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun LoginActivityEmptyIconBox() {
    Box(
        modifier = Modifier
            .size(BBSpacing.Space12)
            .background(
                color = BBColors.Yellow.Yellow100,
                shape = BBRadius.LgShape
            )
            .padding(BBSpacing.Space2),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "IP",
            style = MaterialTheme.typography.titleSmall,
            color = BBColors.Yellow.Yellow800
        )
    }
}

private fun getDemoLoginActivities(): List<LoginActivityUiModel> {
    return listOf(
        LoginActivityUiModel(
            loginActivityId = 1,
            deviceName = "Windows Chrome",
            browserName = "Chrome",
            ipAddress = "176.88.xxx.xxx",
            locationText = "Türkiye",
            loginDate = "04 Haziran 2026 15:42",
            shortCode = "PC",
            trusted = true
        ),
        LoginActivityUiModel(
            loginActivityId = 2,
            deviceName = "Android Mobil",
            browserName = "Bulbulustur Android App",
            ipAddress = "176.88.xxx.xxx",
            locationText = "Türkiye",
            loginDate = "03 Haziran 2026 21:10",
            shortCode = "MB",
            trusted = true
        )
    )
}

private data class LoginActivityUiModel(
    val loginActivityId: Int,
    val deviceName: String,
    val browserName: String,
    val ipAddress: String,
    val locationText: String,
    val loginDate: String,
    val shortCode: String,
    val trusted: Boolean
)


