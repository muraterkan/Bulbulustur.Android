package com.bulbulustur.android.features.account.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.features.account.components.AccountPageScaffold
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.theme.BbColors
import com.bulbulustur.android.ui.theme.BbRadius
import com.bulbulustur.android.ui.theme.BbSpacing

@Composable
fun LoginActivitiesScreen(
    onBackClick: () -> Unit = {}
) {
    val loginActivities = getDemoLoginActivities()

    AccountPageScaffold(
        title = "Giriş Hareketleri",
        kicker = "Hesap Güvenliği",
        description = "Hesabınıza yapılan son girişleri, cihaz bilgilerini ve konum ipuçlarını buradan takip edebilirsiniz.",
        backButtonText = "Güvenliğe Dön",
        onBackClick = onBackClick
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap)
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LoginActivityIconBox(
                    text = activity.shortCode
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
                ) {
                    Text(
                        text = activity.deviceName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = activity.browserName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
            verticalArrangement = Arrangement.spacedBy(BbSpacing.Space3)
        ) {
            LoginActivityEmptyIconBox()

            Text(
                text = "Giriş kaydı bulunmuyor",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabınıza ait giriş hareketleri oluştuğunda burada listelenir.",
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
        verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun LoginActivityStatusBadge(
    trusted: Boolean
) {
    val backgroundColor = if (trusted) {
        BbColors.Green.Green50
    } else {
        BbColors.Orange.Orange50
    }

    val textColor = if (trusted) {
        BbColors.Green.Green700
    } else {
        BbColors.Orange.Orange700
    }

    val text = if (trusted) {
        "Tanıdık giriş"
    } else {
        "Kontrol edilmeli"
    }

    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = BbRadius.Badge
            )
            .padding(
                horizontal = BbSpacing.BadgePaddingHorizontal,
                vertical = BbSpacing.BadgePaddingVertical
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
            .size(BbSpacing.Space14)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.XlShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = BbColors.Yellow.Yellow800
        )
    }
}

@Composable
private fun LoginActivityEmptyIconBox() {
    Box(
        modifier = Modifier
            .size(BbSpacing.Space12)
            .background(
                color = BbColors.Yellow.Yellow100,
                shape = BbRadius.LgShape
            )
            .padding(BbSpacing.Space2),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "IP",
            style = MaterialTheme.typography.titleSmall,
            color = BbColors.Yellow.Yellow800
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