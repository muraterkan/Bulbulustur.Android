package com.bulbulustur.android.Application.Views.Account

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.MemberPreferenceDTO

@Composable
fun CommunicationPreferenceScreen(
    preferences: List<MemberPreferenceDTO>,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onPreferenceChanged: (MemberPreferenceDTO, Boolean) -> Unit = { _, _ -> },
    onRetryClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(key = "2cb7179b-eb4b-46fd-bae3-9265b9f9bc02", fallback = "Bildirim ve İzinler"),
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
                BbCard(
                    modifier = Modifier.fillMaxWidth(),
                    variant = BbCardVariant.Outlined,
                    padding = BbCardPadding.Medium
                ) {
                    Text(
                        text = BBLocalization.Current.Get(key = "1764aa48-4f77-48f4-bbac-1419321cecb4", fallback = "E-posta, SMS ve telefon iletişim tercihlerinizi buradan yönetebilirsiniz."),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            when {
                isLoading && preferences.isEmpty() -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(BBSpacing.Space6),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                !errorMessage.isNullOrBlank() && preferences.isEmpty() -> {
                    item {
                        BbCard(
                            modifier = Modifier.fillMaxWidth(),
                            variant = BbCardVariant.Outlined,
                            padding = BbCardPadding.Medium,
                            onClick = onRetryClick
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                            ) {
                                Text(
                                    text = BBLocalization.Current.Get(key = "6d558ac0-89c2-48c9-b66c-053e0ef413bc", fallback = "Tercihler yüklenemedi"),
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.error
                                )

                                Text(
                                    text = errorMessage,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Text(
                                    text = BBLocalization.Current.Get(key = "7e313006-8bd1-4836-9937-bbe3e4ac5288", fallback = "Tekrar denemek için dokunun."),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                preferences.isEmpty() -> {
                    item {
                        BbCard(
                            modifier = Modifier.fillMaxWidth(),
                            variant = BbCardVariant.Outlined,
                            padding = BbCardPadding.Medium
                        ) {
                            Text(
                                text = BBLocalization.Current.Get(key = "744fb307-ace5-4159-8eba-e7d21ac2b52f", fallback = "Tanımlı iletişim tercihi bulunamadı."),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                else -> {
                    items(
                        items = preferences,
                        key = { preference ->
                            preference.PreferenceTypeId
                        }
                    ) { preference ->
                        CommunicationPreferenceRow(
                            preference = preference,
                            enabled = !isLoading,
                            onCheckedChange = { checked ->
                                onPreferenceChanged(preference, checked)
                            }
                        )
                    }
                }
            }

            if (!errorMessage.isNullOrBlank() && preferences.isNotEmpty()) {
                item {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
private fun CommunicationPreferenceRow(
    preference: MemberPreferenceDTO,
    enabled: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space4),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = preference.PreferenceName.ifBlank {
                        BBLocalization.Current.Get(key = "77fc0b2c-051f-405d-92f0-7066a2f7f670", fallback = "İletişim tercihi")
                    },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                preference.PreferenceDescription
                    ?.takeIf { it.isNotBlank() }
                    ?.let { description ->
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
            }

            Switch(
                checked = preference.PreferenceValue,
                onCheckedChange = onCheckedChange,
                enabled = enabled
            )
        }
    }
}
