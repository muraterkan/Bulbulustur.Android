[1mdiff --git a/app/src/main/java/com/bulbulustur/android/Application/Views/Account/AccountScreen.kt b/app/src/main/java/com/bulbulustur/android/Application/Views/Account/AccountScreen.kt[m
[1mindex 359729c..9c7c004 100644[m
[1m--- a/app/src/main/java/com/bulbulustur/android/Application/Views/Account/AccountScreen.kt[m
[1m+++ b/app/src/main/java/com/bulbulustur/android/Application/Views/Account/AccountScreen.kt[m
[36m@@ -75,7 +75,7 @@[m [mimport com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography[m
 import com.bulbulustur.android.R[m
 [m
 @Composable[m
[31m-fun AccountScreen([m
[32m+[m[32mfun DashboardScreen([m
     isLogoutLoading: Boolean = false,[m
     onSecurityClick: () -> Unit = {},[m
     onProfileClick: () -> Unit = {},[m
[1mdiff --git a/app/src/main/java/com/bulbulustur/android/Application/Views/Account/DashboardScreen.kt b/app/src/main/java/com/bulbulustur/android/Application/Views/Account/DashboardScreen.kt[m
[1mnew file mode 100644[m
[1mindex 0000000..872590a[m
[1m--- /dev/null[m
[1m+++ b/app/src/main/java/com/bulbulustur/android/Application/Views/Account/DashboardScreen.kt[m
[36m@@ -0,0 +1,473 @@[m
[32m+[m[32mpackage com.bulbulustur.android.Application.Views.Profile[m
[32m+[m
[32m+[m[32mimport androidx.compose.foundation.background[m
[32m+[m[32mimport androidx.compose.foundation.clickable[m
[32m+[m[32mimport androidx.compose.foundation.layout.Arrangement[m
[32m+[m[32mimport androidx.compose.foundation.layout.Box[m
[32m+[m[32mimport androidx.compose.foundation.layout.Column[m
[32m+[m[32mimport androidx.compose.foundation.layout.ColumnScope[m
[32m+[m[32mimport androidx.compose.foundation.layout.PaddingValues[m
[32m+[m[32mimport androidx.compose.foundation.layout.Row[m
[32m+[m[32mimport androidx.compose.foundation.layout.fillMaxSize[m
[32m+[m[32mimport androidx.compose.foundation.layout.fillMaxWidth[m
[32m+[m[32mimport androidx.compose.foundation.layout.padding[m
[32m+[m[32mimport androidx.compose.foundation.layout.size[m
[32m+[m[32mimport androidx.compose.foundation.lazy.LazyColumn[m
[32m+[m[32mimport androidx.compose.material.icons.Icons[m
[32m+[m[32mimport androidx.compose.material.icons.outlined.Badge[m
[32m+[m[32mimport androidx.compose.material.icons.outlined.CalendarMonth[m
[32m+[m[32mimport androidx.compose.material.icons.outlined.ChevronRight[m
[32m+[m[32mimport androidx.compose.material.icons.outlined.Edit[m
[32m+[m[32mimport androidx.compose.material.icons.outlined.LocationOn[m
[32m+[m[32mimport androidx.compose.material.icons.outlined.Person[m
[32m+[m[32mimport androidx.compose.material.icons.outlined.WorkOutline[m
[32m+[m[32mimport androidx.compose.material3.CircularProgressIndicator[m
[32m+[m[32mimport androidx.compose.material3.Icon[m
[32m+[m[32mimport androidx.compose.material3.MaterialTheme[m
[32m+[m[32mimport androidx.compose.material3.Scaffold[m
[32m+[m[32mimport androidx.compose.material3.Text[m
[32m+[m[32mimport androidx.compose.runtime.Composable[m
[32m+[m[32mimport androidx.compose.ui.Alignment[m
[32m+[m[32mimport androidx.compose.ui.Modifier[m
[32m+[m[32mimport androidx.compose.ui.graphics.vector.ImageVector[m
[32m+[m[32mimport androidx.compose.ui.unit.dp[m
[32m+[m[32mimport com.bulbulustur.android.Application.Shared.Address.AddressCascadeState[m
[32m+[m[32mimport com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader[m
[32m+[m[32mimport com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard[m
[32m+[m[32mimport com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding[m
[32m+[m[32mimport com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant[m
[32m+[m[32mimport com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon[m
[32m+[m[32mimport com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius[m
[32m+[m[32mimport com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing[m
[32m+[m[32mimport com.bulbulustur.android.Application.wwwroot.DesignTokens.BbTypography[m
[32m+[m[32mimport com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO[m
[32m+[m
[32m+[m[32m@Composable[m
[32m+[m[32mfun ProfileScreen([m
[32m+[m[32m    member: MemberDTO?,[m
[32m+[m[32m    addressCascadeState: AddressCascadeState,[m
[32m+[m[32m    isLoading: Boolean = false,[m
[32m+[m[32m    errorMessage: String? = null,[m
[32m+[m[32m    onBackClick: () -> Unit = {},[m
[32m+[m[32m    onEditClick: () -> Unit = {},[m
[32m+[m[32m    onGenderClick: () -> Unit = {},[m
[32m+[m[32m    onBirthDateClick: () -> Unit = {},[m
[32m+[m[32m    onAddressClick: () -> Unit = {},[m
[32m+[m[32m    onPhonesClick: () -> Unit = {},[m
[32m+[m[32m    onEmailClick: () -> Unit = {},[m
[32m+[m[32m    onCompanyInfoClick: () -> Unit = {},[m
[32m+[m[32m    onB2BStatusClick: () -> Unit = {}[m
[32m+[m[32m) {[m
[32m+[m[32m    val fullName = listOfNotNull([m
[32m+[m[32m        member?.Name?.trim()?.takeIf { it.isNotBlank() },[m
[32m+[m[32m        member?.Surname?.trim()?.takeIf { it.isNotBlank() }[m
[32m+[m[32m    )[m
[32m+[m[32m        .joinToString(" ")[m
[32m+[m[32m        .ifBlank {[m
[32m+[m[32m            "Ad soyad belirtilmemiş"[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m    val profession = member[m
[32m+[m[32m        ?.Profession[m
[32m+[m[32m        ?.trim()[m
[32m+[m[32m        ?.takeIf { it.isNotBlank() }[m
[32m+[m[32m        ?: "Meslek belirtilmemiş"[m
[32m+[m
[32m+[m[32m    val gender = member[m
[32m+[m[32m        ?.GenderId[m
[32m+[m[32m        ?.takeIf { it > 0 }[m
[32m+[m[32m        ?.let {[m
[32m+[m[32m            "Belirtilmiş"[m
[32m+[m[32m        }[m
[32m+[m[32m        ?: "Belirtilmemiş"[m
[32m+[m
[32m+[m[32m    val birthDate = member[m
[32m+[m[32m        ?.BirthDate[m
[32m+[m[32m        ?.trim()[m
[32m+[m[32m        ?.take(10)[m
[32m+[m[32m        ?.takeIf { it.isNotBlank() }[m
[32m+[m[32m        ?: "Belirtilmemiş"[m
[32m+[m
[32m+[m[32m    val countryName = addressCascadeState.Countries[m
[32m+[m[32m        .firstOrNull {[m
[32m+[m[32m            it.AddressCountryId == member?.CountryId[m
[32m+[m[32m        }[m
[32m+[m[32m        ?.Content[m
[32m+[m[32m        .orEmpty()[m
[32m+[m
[32m+[m[32m    val cityName = addressCascadeState.Cities[m
[32m+[m[32m        .firstOrNull {[m
[32m+[m[32m            it.AddressCityId == member?.CityId[m
[32m+[m[32m        }[m
[32m+[m[32m        ?.Content[m
[32m+[m[32m        .orEmpty()[m
[32m+[m
[32m+[m[32m    val location = listOf([m
[32m+[m[32m        countryName,[m
[32m+[m[32m        cityName[m
[32m+[m[32m    )[m
[32m+[m[32m        .filter {[m
[32m+[m[32m            it.isNotBlank()[m
[32m+[m[32m        }[m
[32m+[m[32m        .joinToString(" / ")[m
[32m+[m[32m        .ifBlank {[m
[32m+[m[32m            "Konum belirtilmemiş"[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m    Scaffold([m
[32m+[m[32m        containerColor = MaterialTheme.colorScheme.background,[m
[32m+[m[32m        topBar = {[m
[32m+[m[32m            BbInnerPageHeader([m
[32m+[m[32m                title = "Profilim",[m
[32m+[m[32m                onBackClick = onBackClick,[m
[32m+[m[32m                actionIcon = Icons.Outlined.Edit,[m
[32m+[m[32m                actionContentDescription = "Profili düzenle",[m
[32m+[m[32m                onActionClick = onEditClick[m
[32m+[m[32m            )[m
[32m+[m[32m        }[m
[32m+[m[32m    ) { innerPadding ->[m
[32m+[m[32m        LazyColumn([m
[32m+[m[32m            modifier = Modifier[m
[32m+[m[32m                .fillMaxSize()[m
[32m+[m[32m                .background([m
[32m+[m[32m                    MaterialTheme.colorScheme.surfaceVariant[m
[32m+[m[32m                )[m
[32m+[m[32m                .padding(innerPadding),[m
[32m+[m[32m            contentPadding = PaddingValues([m
[32m+[m[32m                start = BBSpacing.PageHorizontal,[m
[32m+[m[32m                top = BBSpacing.PageTopCompact,[m
[32m+[m[32m                end = BBSpacing.PageHorizontal,[m
[32m+[m[32m                bottom = BBSpacing.PageBottom[m
[32m+[m[32m            ),[m
[32m+[m[32m            verticalArrangement = Arrangement.spacedBy([m
[32m+[m[32m                BBSpacing.CardGap[m
[32m+[m[32m            )[m
[32m+[m[32m        ) {[m
[32m+[m[32m            when {[m
[32m+[m[32m                isLoading -> {[m
[32m+[m[32m                    item {[m
[32m+[m[32m                        ProfileLoadingContent()[m
[32m+[m[32m                    }[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m                !errorMessage.isNullOrBlank() -> {[m
[32m+[m[32m                    item {[m
[32m+[m[32m                        ProfileMessageCard([m
[32m+[m[32m                            message = errorMessage,[m
[32m+[m[32m                            isError = true[m
[32m+[m[32m                        )[m
[32m+[m[32m                    }[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m                member == null -> {[m
[32m+[m[32m                    item {[m
[32m+[m[32m                        ProfileMessageCard([m
[32m+[m[32m                            message = "Profil bilgileri bulunamadı.",[m
[32m+[m[32m                            isError = false[m
[32m+[m[32m                        )[m
[32m+[m[32m                    }[m
[32m+[m[32m                }[m
[32m+[m
[32m+[m[32m                else -> {[m
[32m+[m[32m                    item {[m
[32m+[m[32m                        ProfileSummaryCard([m
[32m+[m[32m                            fullName = fullName,[m
[32m+[m[32m                            profession = profession,[m
[32m+[m[32m                            onClick = onEditClick[m
[32m+[m[32m                        )[m
[32m+[m[32m                    }[m
[32m+[m
[32m+[m[32m                    item {[m
[32m+[m[32m                        ProfileSection([m
[32m+[m[32m                            title = "Profil Bilgileri",[m
[32m+[m[32m                            description = "Görünen kişisel bilgilerinizi yönetin."[m
[32m+[m[32m                        ) {[m
[32m+[m[32m                            ProfileRow([m
[32m+[m[32m                                title = "Ad Soyad",[m
[32m+[m[32m                                value = fullName,[m
[32m+[m[32m                                icon = Icons.Outlined.Person,[m
[32m+[m[32m                                onClick = onEditClick[m
[32m+[m[32m                            )[m
[32m+[m
[32m+[m[32m                            ProfileRowDivider()[m
[32m+[m
[32m+[m[32m                            ProfileRow([m
[32m+[m[32m                                title = "Meslek",[m
[32m+[m[32m                                value = profession,[m
[32m+[m[32m                                icon = Icons.Outlined.WorkOutline,[m
[32m+[m[32m                                onClick = onEditClick[m
[32m+[m[32m                            )[m
[32m+[m
[32m+[m[32m                            ProfileRowDivider()[m
[32m+[m
[32m+[m[32m                            ProfileRow([m
[32m+[m[32m                                title = "Cinsiyet",[m
[32m+[m[32m                                value = gender,[m
[32m+[m[32m                                icon = Icons.Outlined.Badge,[m
[32m+[m[32m                                onClick = onGenderClick[m
[32m+[m[32m                            )[m
[32m+[m
[32m+[m[32m                            ProfileRowDivider()[m
[32m+[m
[32m+[m[32m                            ProfileRow([m
[32m+[m[32m                                title = "Doğum Tarihi",[m
[32m+[m[32m                                value = birthDate,[m
[32m+[m[32m                                icon = Icons.Outlined.CalendarMonth,[m
[32m+[m[32m                                onClick = onBirthDateClick[m
[32m+[m[32m                            )[m
[32m+[m
[32m+[m[32m                            ProfileRowDivider()[m
[32m+[m
[32m+[m[32m                            ProfileRow([m
[32m+[m[32m                                title = "Ülke / Şehir",[m
[32m+[m[32m                                value = location,[m
[32m+[m[32m                                icon = Icons.Outlined.LocationOn,[m
[32m+[m[32m                                onClick = onAddressClick[m
[32m+[m[32m                            )[m
[32m+[m[32m                        }[m
[32m+[m[32m                    }[m
[32m+[m[32m                }[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32m@Composable[m
[32m+[m[32mprivate fun ProfileSummaryCard([m
[32m+[m[32m    fullName: String,[m
[32m+[m[32m    profession: String,[m
[32m+[m[32m    onClick: () -> Unit[m
[32m+[m[32m) {[m
[32m+[m[32m    BbCard([m
[32m+[m[32m        modifier = Modifier.fillMaxWidth(),[m
[32m+[m[32m        variant = BbCardVariant.Outlined,[m
[32m+[m[32m        padding = BbCardPadding.Medium,[m
[32m+[m[32m        onClick = onClick[m
[32m+[m[32m    ) {[m
[32m+[m[32m        Row([m
[32m+[m[32m            modifier = Modifier.fillMaxWidth(),[m
[32m+[m[32m            horizontalArrangement = Arrangement.spacedBy([m
[32m+[m[32m                BBSpacing.Space3[m
[32m+[m[32m            ),[m
[32m+[m[32m            verticalAlignment = Alignment.CenterVertically[m
[32m+[m[32m        ) {[m
[32m+[m[32m            Box([m
[32m+[m[32m                modifier = Modifier[m
[32m+[m[32m                    .size(BBIcon.BoxXl)[m
[32m+[m[32m                    .background([m
[32m+[m[32m                        color = MaterialTheme.colorScheme.primaryContainer,[m
[32m+[m[32m                        shape = BBRadius.XlShape[m
[32m+[m[32m                    ),[m
[32m+[m[32m                contentAlignment = Alignment.Center[m
[32m+[m[32m            ) {[m
[32m+[m[32m                Icon([m
[32m+[m[32m                    imageVector = Icons.Outlined.Person,[m
[32m+[m[32m                    contentDescription = null,[m
[32m+[m[32m                    tint = MaterialTheme.colorScheme.onPrimaryContainer,[m
[32m+[m[32m                    modifier = Modifier.size(BBIcon.Section)[m
[32m+[m[32m                )[m
[32m+[m[32m            }[m
[32m+[m
[32m+[m[32m            Column([m
[32m+[m[32m                modifier = Modifier.weight(1f),[m
[32m+[m[32m                verticalArrangement = Arrangement.spacedBy([m
[32m+[m[32m                    BBSpacing.Space1[m
[32m+[m[32m                )[m
[32m+[m[32m            ) {[m
[32m+[m[32m                Text([m
[32m+[m[32m                    text = fullName,[m
[32m+[m[32m                    style = BbTypography.titleLarge,[m
[32m+[m[32m                    color = MaterialTheme.colorScheme.onSurface[m
[32m+[m[32m                )[m
[32m+[m
[32m+[m[32m                Text([m
[32m+[m[32m                    text = profession,[m
[32m+[m[32m                    style = BbTypography.bodySmall,[m
[32m+[m[32m                    color = MaterialTheme.colorScheme.onSurfaceVariant[m
[32m+[m[32m                )[m
[32m+[m[32m            }[m
[32m+[m
[32m+[m[32m            Icon([m
[32m+[m[32m                imageVector = Icons.Outlined.ChevronRight,[m
[32m+[m[32m                contentDescription = null,[m
[32m+[m[32m                tint = MaterialTheme.colorScheme.onSurfaceVariant,[m
[32m+[m[32m                modifier = Modifier.size(BBIcon.Ui)[m
[32m+[m[32m            )[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32m@Composable[m
[32m+[m[32mprivate fun ProfileSection([m
[32m+[m[32m    title: String,[m
[32m+[m[32m    description: String,[m
[32m+[m[32m    content: @Composable ColumnScope.() -> Unit[m
[32m+[m[32m) {[m
[32m+[m[32m    BbCard([m
[32m+[m[32m        modifier = Modifier.fillMaxWidth(),[m
[32m+[m[32m        variant = BbCardVariant.Outlined,[m
[32m+[m[32m        padding = BbCardPadding.None[m
[32m+[m[32m    ) {[m
[32m+[m[32m        Column([m
[32m+[m[32m            modifier = Modifier[m
[32m+[m[32m                .fillMaxWidth()[m
[32m+[m[32m                .background([m
[32m+[m[32m                    MaterialTheme.colorScheme.surface[m
[32m+[m[32m                )[m
[32m+[m[32m        ) {[m
[32m+[m[32m            Column([m
[32m+[m[32m                modifier = Modifier[m
[32m+[m[32m                    .fillMaxWidth()[m
[32m+[m[32m                    .padding(BBSpacing.CardPadding),[m
[32m+[m[32m                verticalArrangement = Arrangement.spacedBy([m
[32m+[m[32m                    BBSpacing.Space1[m
[32m+[m[32m                )[m
[32m+[m[32m            ) {[m
[32m+[m[32m                Text([m
[32m+[m[32m                    text = title,[m
[32m+[m[32m                    style = BbTypography.titleSmall,[m
[32m+[m[32m                    color = MaterialTheme.colorScheme.onSurface[m
[32m+[m[32m                )[m
[32m+[m
[32m+[m[32m                Text([m
[32m+[m[32m                    text = description,[m
[32m+[m[32m                    style = BbTypography.bodySmall,[m
[32m+[m[32m                    color = MaterialTheme.colorScheme.onSurfaceVariant[m
[32m+[m[32m                )[m
[32m+[m[32m            }[m
[32m+[m
[32m+[m[32m            Column {[m
[32m+[m[32m                content()[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32m@Composable[m
[32m+[m[32mprivate fun ProfileRow([m
[32m+[m[32m    title: String,[m
[32m+[m[32m    value: String,[m
[32m+[m[32m    icon: ImageVector,[m
[32m+[m[32m    onClick: () -> Unit[m
[32m+[m[32m) {[m
[32m+[m[32m    Row([m
[32m+[m[32m        modifier = Modifier[m
[32m+[m[32m            .fillMaxWidth()[m
[32m+[m[32m            .clickable {[m
[32m+[m[32m                onClick()[m
[32m+[m[32m            }[m
[32m+[m[32m            .padding(BBSpacing.CardPadding),[m
[32m+[m[32m        horizontalArrangement = Arrangement.spacedBy([m
[32m+[m[32m            BBSpacing.Space3[m
[32m+[m[32m        ),[m
[32m+[m[32m        verticalAlignment = Alignment.CenterVertically[m
[32m+[m[32m    ) {[m
[32m+[m[32m        Box([m
[32m+[m[32m            modifier = Modifier[m
[32m+[m[32m                .size(BBIcon.BoxMd)[m
[32m+[m[32m                .background([m
[32m+[m[32m                    color = MaterialTheme.colorScheme.surfaceVariant,[m
[32m+[m[32m                    shape = BBRadius.PillShape[m
[32m+[m[32m                ),[m
[32m+[m[32m            contentAlignment = Alignment.Center[m
[32m+[m[32m        ) {[m
[32m+[m[32m            Icon([m
[32m+[m[32m                imageVector = icon,[m
[32m+[m[32m                contentDescription = null,[m
[32m+[m[32m                tint = MaterialTheme.colorScheme.onSurface,[m
[32m+[m[32m                modifier = Modifier.size(BBIcon.Ui)[m
[32m+[m[32m            )[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        Column([m
[32m+[m[32m            modifier = Modifier.weight(1f),[m
[32m+[m[32m            verticalArrangement = Arrangement.spacedBy([m
[32m+[m[32m                BBSpacing.Space1[m
[32m+[m[32m            )[m
[32m+[m[32m        ) {[m
[32m+[m[32m            Text([m
[32m+[m[32m                text = title,[m
[32m+[m[32m                style = BbTypography.labelSmall,[m
[32m+[m[32m                color = MaterialTheme.colorScheme.onSurfaceVariant[m
[32m+[m[32m            )[m
[32m+[m
[32m+[m[32m            Text([m
[32m+[m[32m                text = value,[m
[32m+[m[32m                style = BbTypography.titleSmall,[m
[32m+[m[32m                color = MaterialTheme.colorScheme.onSurface[m
[32m+[m[32m            )[m
[32m+[m[32m        }[m
[32m+[m
[32m+[m[32m        Box([m
[32m+[m[32m            modifier = Modifier[m
[32m+[m[32m                .size(BBIcon.BoxSm)[m
[32m+[m[32m                .background([m
[32m+[m[32m                    color = MaterialTheme.colorScheme.surfaceVariant,[m
[32m+[m[32m                    shape = BBRadius.PillShape[m
[32m+[m[32m                ),[m
[32m+[m[32m            contentAlignment = Alignment.Center[m
[32m+[m[32m        ) {[m
[32m+[m[32m            Icon([m
[32m+[m[32m                imageVector = Icons.Outlined.ChevronRight,[m
[32m+[m[32m                contentDescription = null,[m
[32m+[m[32m                tint = MaterialTheme.colorScheme.onSurfaceVariant,[m
[32m+[m[32m                modifier = Modifier.size(BBIcon.SizeSm)[m
[32m+[m[32m            )[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32m@Composable[m
[32m+[m[32mprivate fun ProfileRowDivider() {[m
[32m+[m[32m    Box([m
[32m+[m[32m        modifier = Modifier[m
[32m+[m[32m            .fillMaxWidth()[m
[32m+[m[32m            .padding([m
[32m+[m[32m                start = BBSpacing.CardPadding,[m
[32m+[m[32m                end = BBSpacing.CardPadding[m
[32m+[m[32m            )[m
[32m+[m[32m            .background([m
[32m+[m[32m                MaterialTheme.colorScheme.outlineVariant[m
[32m+[m[32m            )[m
[32m+[m[32m            .size([m
[32m+[m[32m                width = 1.dp,[m
[32m+[m[32m                height = 1.dp[m
[32m+[m[32m            )[m
[32m+[m[32m    )[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32m@Composable[m
[32m+[m[32mprivate fun ProfileLoadingContent() {[m
[32m+[m[32m    Box([m
[32m+[m[32m        modifier = Modifier[m
[32m+[m[32m            .fillMaxWidth()[m
[32m+[m[32m            .padding(BBSpacing.Space6),[m
[32m+[m[32m        contentAlignment = Alignment.Center[m
[32m+[m[32m    ) {[m
[32m+[m[32m        CircularProgressIndicator([m
[32m+[m[32m            color = MaterialTheme.colorScheme.primary[m
[32m+[m[32m        )[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
[32m+[m
[32m+[m[32m@Composable[m
[32m+[m[32mprivate fun ProfileMessageCard([m
[32m+[m[32m    message: String,[m
[32m+[m[32m    isError: Boolean[m
[32m+[m[32m) {[m
[32m+[m[32m    BbCard([m
[32m+[m[32m        modifier = Modifier.fillMaxWidth(),[m
[32m+[m[32m        variant = BbCardVariant.Outlined,[m
[32m+[m[32m        padding = BbCardPadding.Medium[m
[32m+[m[32m    ) {[m
[32m+[m[32m        Text([m
[32m+[m[32m            text = message,[m
[32m+[m[32m            style = BbTypography.bodySmall,[m
[32m+[m[32m            color = if (isError) {[m
[32m+[m[32m                MaterialTheme.colorScheme.error[m
[32m+[m[32m            } else {[m
[32m+[m[32m                MaterialTheme.colorScheme.onSurfaceVariant[m
[32m+[m[32m            }[m
[32m+[m[32m        )[m
[32m+[m[32m    }[m
[32m+[m[32m}[m
\ No newline at end of file[m
