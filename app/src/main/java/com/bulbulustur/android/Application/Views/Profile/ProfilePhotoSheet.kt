package com.bulbulustur.android.Application.Views.Profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePhotoSheet(
    hasProfilePhoto: Boolean,
    onDismiss: () -> Unit,
    onTakePhotoClick: () -> Unit,
    onSelectFromGalleryClick: () -> Unit,
    onRemovePhotoClick: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    start = BBSpacing.PageHorizontal,
                    end = BBSpacing.PageHorizontal,
                    bottom = BBSpacing.PageBottom
                ),
            verticalArrangement = Arrangement.spacedBy(
                BBSpacing.Space4
            )
        ) {
            ProfilePhotoSheetHeader()

            BbCard(
                modifier = Modifier.fillMaxWidth(),
                variant = BbCardVariant.Outlined,
                padding = BbCardPadding.None
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ProfilePhotoSheetActionRow(
                        icon = Icons.Outlined.CameraAlt,
                        title = "Fotoğraf Çek",
                        subtitle = "Kamerayı açarak yeni profil fotoğrafı çek.",
                        onClick = onTakePhotoClick
                    )

                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant
                    )

                    ProfilePhotoSheetActionRow(
                        icon = Icons.Outlined.PhotoLibrary,
                        title = "Albümden Seç",
                        subtitle = "Cihaz galerinizden profil fotoğrafı seç.",
                        onClick = onSelectFromGalleryClick
                    )

                    if (hasProfilePhoto) {
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outlineVariant
                        )

                        ProfilePhotoSheetActionRow(
                            icon = Icons.Outlined.DeleteOutline,
                            title = "Fotoğrafı Kaldır",
                            subtitle = "Mevcut profil fotoğrafını kaldır.",
                            danger = true,
                            onClick = onRemovePhotoClick
                        )
                    }
                }
            }

            BbButton(
                text = "Vazgeç",
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth(),
                variant = BbButtonVariant.Light,
                size = BbButtonSize.Medium,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                        modifier = Modifier.size(
                            BBIcon.ButtonIcon
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun ProfilePhotoSheetHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space1
        )
    ) {
        Text(
            text = "Profil Fotoğrafı",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = "Hesabınızda görünecek tek aktif profil fotoğrafını buradan yönetebilirsiniz.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProfilePhotoSheetActionRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    danger: Boolean = false,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Default,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space3
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (danger) {
                    BBColors.Red.Red500
                } else {
                    MaterialTheme.colorScheme.primary
                },
                modifier = Modifier.size(
                    BBIcon.Feature
                )
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space1
                )
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = if (danger) {
                        BBColors.Red.Red500
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
