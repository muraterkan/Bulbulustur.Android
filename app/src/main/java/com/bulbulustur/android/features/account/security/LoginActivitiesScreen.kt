package com.bulbulustur.android.features.account.security

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.ui.components.BbButton
import com.bulbulustur.android.ui.components.BbButtonVariant
import com.bulbulustur.android.ui.components.BbCard
import com.bulbulustur.android.ui.components.BbCardPadding
import com.bulbulustur.android.ui.components.BbCardVariant
import com.bulbulustur.android.ui.components.BbChip
import com.bulbulustur.android.ui.theme.BbSpacing

data class LoginActivityUiState(
    val loginActivityId: Int,
    val deviceName: String,
    val browserName: String,
    val locationText: String,
    val ipAddress: String,
    val loginDateText: String,
    val isCurrentSession: Boolean,
    val isSuccessful: Boolean
)

@Composable
fun LoginActivitiesScreen(
    loginActivities: List<LoginActivityUiState> = createSampleLoginActivities(),
    onBackClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onChangeEmailClick: () -> Unit = {},
    onDeactivateAccountClick: () -> Unit = {},
    onRefreshClick: () -> Unit = {},
    isRefreshing: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = BbSpacing.PageHorizontal,
                vertical = BbSpacing.PageTop
            ),
        verticalArrangement = Arrangement.spacedBy(BbSpacing.SectionGap)
    ) {
        BbButton(
            text = "Hesabıma Dön",
            onClick = onBackClick,
            variant = BbButtonVariant.Outline
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            BbChip(
                text = "Güvenlik Kayıtları"
            )

            Text(
                text = "Giriş Etkinlikleri",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Hesabına yapılan son giriş hareketlerini, cihaz bilgilerini ve oturum durumlarını buradan kontrol edebilirsin.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        BbCard(
            modifier = Modifier.fillMaxWidth(),
            variant = BbCardVariant.Outlined,
            padding = BbCardPadding.Large
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Güvenlik kontrolü",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Tanımadığın bir cihaz veya konum görürsen şifreni değiştirmen ve hesabını kontrol etmen önerilir.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                BbButton(
                    text = "Listeyi Yenile",
                    onClick = onRefreshClick,
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbButtonVariant.Outline,
                    isLoading = isRefreshing
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Son girişler",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            if (loginActivities.isEmpty()) {
                LoginActivitiesEmptyCard()
            } else {
                loginActivities.forEach { loginActivity ->
                    LoginActivityCard(
                        loginActivity = loginActivity
                    )
                }
            }
        }

        AccountSecurityBottomMenu(
            selectedItem = AccountSecurityMenuItem.LoginActivities,
            onLoginActivitiesClick = {},
            onChangePasswordClick = onChangePasswordClick,
            onChangeEmailClick = onChangeEmailClick,
            onDeactivateAccountClick = onDeactivateAccountClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun LoginActivityCard(
    loginActivity: LoginActivityUiState
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(BbSpacing.Space1),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = loginActivity.deviceName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = loginActivity.browserName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (loginActivity.isCurrentSession) {
                    BbChip(
                        text = "Mevcut Oturum"
                    )
                } else {
                    BbChip(
                        text = if (loginActivity.isSuccessful) {
                            "Başarılı"
                        } else {
                            "Başarısız"
                        }
                    )
                }
            }

            LoginActivityInfoRow(
                label = "Konum",
                value = loginActivity.locationText
            )

            LoginActivityInfoRow(
                label = "IP Adresi",
                value = loginActivity.ipAddress
            )

            LoginActivityInfoRow(
                label = "Tarih",
                value = loginActivity.loginDateText
            )

            if (!loginActivity.isSuccessful) {
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Text(
                        text = "Bu giriş denemesi başarısız görünüyor. Tanımadığın bir hareketse şifreni değiştirmen iyi olur.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun LoginActivityInfoRow(
    label: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(2f)
        )
    }
}

@Composable
private fun LoginActivitiesEmptyCard() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BbSpacing.CardGap),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Henüz giriş etkinliği bulunamadı",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Giriş kayıtları API bağlantısı tamamlandığında burada listelenecek.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun createSampleLoginActivities(): List<LoginActivityUiState> {
    return listOf(
        LoginActivityUiState(
            loginActivityId = 1,
            deviceName = "Windows Notebook",
            browserName = "Chrome",
            locationText = "Ankara, Türkiye",
            ipAddress = "88.xxx.xxx.xxx",
            loginDateText = "Bugün, 20:42",
            isCurrentSession = true,
            isSuccessful = true
        ),
        LoginActivityUiState(
            loginActivityId = 2,
            deviceName = "Android Telefon",
            browserName = "Bulbulustur App",
            locationText = "Kayseri, Türkiye",
            ipAddress = "176.xxx.xxx.xxx",
            loginDateText = "Dün, 18:10",
            isCurrentSession = false,
            isSuccessful = true
        ),
        LoginActivityUiState(
            loginActivityId = 3,
            deviceName = "Bilinmeyen Cihaz",
            browserName = "Mobile Browser",
            locationText = "Konum doğrulanamadı",
            ipAddress = "31.xxx.xxx.xxx",
            loginDateText = "2 gün önce, 01:24",
            isCurrentSession = false,
            isSuccessful = false
        )
    )
}