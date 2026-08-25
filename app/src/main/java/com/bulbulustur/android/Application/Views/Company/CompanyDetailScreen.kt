package com.bulbulustur.android.Application.Views.Company

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Factory
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.Views.Shared.Components.BbSectionHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButton
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbButtonVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBAlpha
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCertificateDTO
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPictureDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun CompanyDetailScreen(
    companyDto: CompanyDTO? = null,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onHomeClick: (Int) -> Unit = {},
    onProductListClick: (Int) -> Unit = {},
    onMessageClick: (Int) -> Unit = {},
    onContactClick: (Int) -> Unit = onMessageClick,
    onGalleryClick: (Int) -> Unit = {},
    onCertificateClick: (Int) -> Unit = {},
    onWebsiteClick: (String) -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BbInnerPageHeader(
                title = BBLocalization.Current.Get(
                    key = "24346286-0656-43c8-ae61-fe89a658f495",
                    fallback = "Firma Profili"
                ),
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        when {
            isLoading && companyDto == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            companyDto == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(BBSpacing.PageHorizontal),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = errorMessage?.takeIf { it.isNotBlank() }
                            ?: BBLocalization.Current.Get(
                                key = "8d62c316-0982-473f-b9ce-01b1aebccdf9",
                                fallback = "Şirket bilgisi bulunamadı."
                            ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (!errorMessage.isNullOrBlank()) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            }

            else -> {
                CompanyDetailContent(
                    company = companyDto,
                    innerPadding = innerPadding,
                    errorMessage = errorMessage,
                    onHomeClick = onHomeClick,
                    onProductListClick = onProductListClick,
                    onContactClick = onContactClick,
                    onGalleryClick = onGalleryClick,
                    onCertificateClick = onCertificateClick,
                    onWebsiteClick = onWebsiteClick
                )
            }
        }
    }
}

@Composable
private fun CompanyDetailContent(
    company: CompanyDTO,
    innerPadding: PaddingValues,
    errorMessage: String?,
    onHomeClick: (Int) -> Unit,
    onProductListClick: (Int) -> Unit,
    onContactClick: (Int) -> Unit,
    onGalleryClick: (Int) -> Unit,
    onCertificateClick: (Int) -> Unit,
    onWebsiteClick: (String) -> Unit
) {
    val companyPictures = company.CompanyPictures
        .orEmpty()
        .filter { picture ->
            picture.PictureName.orEmpty().isNotBlank()
        }

    val companyCertificates = company.CompanyCertificates
        .orEmpty()
        .filter { certificate ->
            certificate.Certificate.orEmpty().isNotBlank() ||
                    certificate.CertificateNumber.orEmpty().isNotBlank() ||
                    certificate.Picture.orEmpty().isNotBlank()
        }

    val hasAbout =
        company.About.orEmpty().isNotBlank() ||
                company.WhyUs.orEmpty().isNotBlank()

    val hasDetailedInformation =
        company.YearEstablished.orEmpty().isNotBlank() ||
                company.SystemDescNumberOfEmployee.orEmpty().isNotBlank() ||
                company.ExportMarketsList.orEmpty().isNotBlank() ||
                company.Address.orEmpty().isNotBlank() ||
                company.DistrictName.orEmpty().isNotBlank() ||
                company.CityName.orEmpty().isNotBlank() ||
                company.Url.orEmpty().isNotBlank()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(
            start = BBSpacing.PageHorizontal,
            top = BBSpacing.PageTopCompact,
            end = BBSpacing.PageHorizontal,
            bottom = BBSpacing.PageBottom
        ),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
    ) {
        if (!errorMessage.isNullOrBlank()) {
            item {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        item {
            CompanyDetailHero(company = company)
        }

        item {
            CompanyDetailMainActions(
                company = company,
                onProductListClick = onProductListClick,
                onContactClick = onContactClick
            )
        }

        item {
            CompanyDetailTabs(
                company = company,
                onHomeClick = onHomeClick,
                onProductListClick = onProductListClick,
                onContactClick = onContactClick
            )
        }

        if (companyPictures.isNotEmpty()) {
            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "e8a21e2e-de5d-4682-b64a-7cdd977fc29e",
                        fallback = "Firma Vitrini"
                    ),
                    subtitle = BBLocalization.Current.Get(
                        key = "cc80154e-6e00-4166-8b5a-9810cb5be734",
                        fallback = "Üretim, tesis, showroom veya kurumsal görseller"
                    )
                )
            }

            item {
                CompanyGalleryPreview(
                    companyId = company.CompanyId,
                    pictures = companyPictures,
                    onGalleryClick = onGalleryClick
                )
            }
        }

        if (hasAbout || hasDetailedInformation) {
            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "a8b9df1a-fadf-45e8-9ec8-9b369024ea5e",
                        fallback = "Detaylı Firma Profili"
                    ),
                    subtitle = BBLocalization.Current.Get(
                        key = "53981d8b-c53b-4b78-a8eb-e78b2399d6fc",
                        fallback = "Firma hakkında, iş modeli ve ticari bilgiler"
                    )
                )
            }
        }

        if (hasAbout) {
            item {
                CompanyAboutCard(company = company)
            }
        }

        if (hasDetailedInformation) {
            item {
                CompanyInfoGrid(
                    company = company,
                    onWebsiteClick = onWebsiteClick
                )
            }
        }

        if (companyCertificates.isNotEmpty()) {
            item {
                BbSectionHeader(
                    title = BBLocalization.Current.Get(
                        key = "fa8e443e-e497-4a9d-85f7-1e1e20c69892",
                        fallback = "Belgeler ve Sertifikalar"
                    ),
                    subtitle = BBLocalization.Current.Get(
                        key = "371c81db-38aa-41ab-bace-f03f8777d460",
                        fallback = "Firma güveni için doğrulama bilgileri"
                    )
                )
            }

            items(
                items = companyCertificates,
                key = { certificate ->
                    "certificate-${certificate.CompanyCertificateId}"
                }
            ) { certificate ->
                CompanyCertificateCard(
                    certificate = certificate,
                    onClick = {
                        if (certificate.CompanyCertificateId > 0) {
                            onCertificateClick(certificate.CompanyCertificateId)
                        }
                    }
                )
            }
        }

        item {
            CompanyTrustPanel(
                company = company,
                certificateCount = companyCertificates.size,
                pictureCount = companyPictures.size,
                onContactClick = {
                    if (company.CompanyId > 0) {
                        onContactClick(company.CompanyId)
                    }
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(BBSpacing.Space4))
        }
    }
}

@Composable
private fun CompanyDetailHero(
    company: CompanyDTO
) {
    val heroTags = buildList {
        company.CountryName.orEmpty()
            .trim()
            .takeIf { it.isNotEmpty() }
            ?.let(::add)

        company.CityName.orEmpty()
            .trim()
            .takeIf { it.isNotEmpty() }
            ?.let(::add)

        company.BusinessTypes.orEmpty()
            .trim()
            .takeIf { it.isNotEmpty() }
            ?.let(::add)
            ?: company.CompanyType.orEmpty()
                .trim()
                .takeIf { it.isNotEmpty() }
                ?.let(::add)

        if (company.Verified) {
            add(
                BBLocalization.Current.Get(
                    key = "c6a0ff62-8828-475f-b553-37effb42efe6",
                    fallback = "Doğrulanmış"
                )
            )
        }
    }

    val description = company.Slogan.orEmpty()
        .trim()
        .ifBlank {
            company.SeoDescription.orEmpty().trim()
        }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.onSurface,
        border = BorderStroke(
            width = BBSpacing.None,
            color = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.onSurface,
                            BBColors.Black
                        )
                    )
                )
                .padding(BBSpacing.Space5)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
                ) {
                    CompanyLogoBox(
                        companyName = company.CompanyName.orEmpty(),
                        logoPath = company.Logo.orEmpty()
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                BBSpacing.IconTextGapSmall
                            )
                        ) {
                            Text(
                                text = BBLocalization.Current.Get(
                                    key = "24346286-0656-43c8-ae61-fe89a658f495",
                                    fallback = "Firma Profili"
                                ),
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )

                            if (company.Verified) {
                                Icon(
                                    imageVector = Icons.Outlined.Verified,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Text(
                            text = company.CompanyName.orEmpty(),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = BBColors.White
                        )
                    }
                }

                if (description.isNotBlank()) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BBColors.White.copy(alpha = 0.78f)
                    )
                }

                if (heroTags.isNotEmpty()) {
                    CompanyDarkTagRow(tags = heroTags)
                }
            }
        }
    }
}

@Composable
private fun CompanyLogoBox(
    companyName: String,
    logoPath: String
) {
    val logoUrl = ImageUrlResolver.Resolve(logoPath)
    val logoLoadFailed = remember(logoUrl) {
        mutableStateOf(false)
    }

    Surface(
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.None,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Box(
            modifier = Modifier
                .size(BBSpacing.Space12)
                .padding(BBSpacing.Space2),
            contentAlignment = Alignment.Center
        ) {
            if (logoUrl.isNotBlank() && !logoLoadFailed.value) {
                AsyncImage(
                    model = logoUrl,
                    contentDescription = BBLocalization.Current.Get(
                        key = "d7ccb0f9-2233-4861-aeb9-b42312a98397",
                        fallback = "Company Logo"
                    ),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit,
                    onError = {
                        logoLoadFailed.value = true
                    }
                )
            } else {
                Text(
                    text = companyName.toCompanyLogoText(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun CompanyDetailMainActions(
    company: CompanyDTO,
    onProductListClick: (Int) -> Unit,
    onContactClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
        ) {
            CompanyActionCard(
                title = BBLocalization.Current.Get(
                    key = "5e09b1c8-93e6-4e9a-a055-2f556f57d6dc",
                    fallback = "Ürünleri Gör"
                ),
                description = BBLocalization.Current.Get(
                    key = "86524b7d-8604-431b-bb9c-e3aae1942af9",
                    fallback = "Firmanın toptan ürünlerini inceleyin"
                ),
                icon = Icons.Outlined.Inventory2,
                modifier = Modifier.weight(1f),
                onClick = {
                    if (company.CompanyId > 0) {
                        onProductListClick(company.CompanyId)
                    }
                }
            )

            CompanyActionCard(
                title = BBLocalization.Current.Get(
                    key = "a439130c-b2cf-496f-9868-93ef084d9aec",
                    fallback = "İletişime Geç"
                ),
                description = BBLocalization.Current.Get(
                    key = "dc33c096-fffb-4e72-8e0d-b7f62e6d3cbf",
                    fallback = "Yetkili kişiye ulaş"
                ),
                icon = Icons.Outlined.Mail,
                modifier = Modifier.weight(1f),
                onClick = {
                    if (company.CompanyId > 0) {
                        onContactClick(company.CompanyId)
                    }
                }
            )
        }

        BbButton(
            text = BBLocalization.Current.Get(
                key = "a439130c-b2cf-496f-9868-93ef084d9aec",
                fallback = "İletişime Geç"
            ),
            onClick = {
                if (company.CompanyId > 0) {
                    onContactClick(company.CompanyId)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            variant = BbButtonVariant.Primary,
            size = BbButtonSize.Medium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

@Composable
private fun CompanyDetailTabs(
    company: CompanyDTO,
    onHomeClick: (Int) -> Unit,
    onProductListClick: (Int) -> Unit,
    onContactClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            CompanyTabChip(
                text = BBLocalization.Current.Get(
                    key = "fe9c56ac-dbc2-4fc6-afe0-bb3f7cf1f8f7",
                    fallback = "Ana Sayfa"
                ),
                icon = Icons.Outlined.Home,
                selected = false,
                onClick = {
                    if (company.CompanyId > 0) {
                        onHomeClick(company.CompanyId)
                    }
                }
            )

            CompanyTabChip(
                text = BBLocalization.Current.Get(
                    key = "ab200e4f-1f9e-45f4-90a6-7d5d21d33953",
                    fallback = "Profil"
                ),
                icon = Icons.Outlined.Business,
                selected = true,
                onClick = {}
            )

            CompanyTabChip(
                text = BBLocalization.Current.Get(
                    key = "6cf7b92f-05e7-4ac7-be8c-ce98d8bf20c5",
                    fallback = "Ürünler"
                ),
                icon = Icons.Outlined.Inventory2,
                selected = false,
                onClick = {
                    if (company.CompanyId > 0) {
                        onProductListClick(company.CompanyId)
                    }
                }
            )

            CompanyTabChip(
                text = BBLocalization.Current.Get(
                    key = "0cf2cda1-7cf6-4d8b-ab56-8918e3a260fd",
                    fallback = ""
                ),
                icon = Icons.Outlined.Email,
                selected = false,
                onClick = {
                    if (company.CompanyId > 0) {
                        onContactClick(company.CompanyId)
                    }
                }
            )
        }
    }
}

@Composable
private fun CompanyTabChip(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clip(BBRadius.PillShape),
        onClick = onClick,
        shape = BBRadius.PillShape,
        color = if (selected) {
            BBColors.Blue.Blue50
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            width = BBSpacing.None,
            color = if (selected) {
                BBColors.Blue.Blue200
            } else {
                MaterialTheme.colorScheme.outlineVariant
            }
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) {
                    BBColors.Blue.Blue700
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                modifier = Modifier.height(BBIcon.SizeSm)
            )

            if (text.isNotBlank()) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun CompanyActionCard(
    title: String,
    description: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CompanyGalleryPreview(
    companyId: Int,
    pictures: List<CompanyPictureDTO>,
    onGalleryClick: (Int) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = {
            if (companyId > 0) {
                onGalleryClick(companyId)
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            CompanyIconTitleRow(
                icon = Icons.Outlined.PhotoLibrary,
                title = "${pictures.size} görsel"
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                items(
                    items = pictures,
                    key = { picture ->
                        "company-picture-${picture.CompanyPictureId}"
                    }
                ) { picture ->
                    CompanyGalleryImage(picture = picture)
                }
            }
        }
    }
}

@Composable
private fun CompanyGalleryImage(
    picture: CompanyPictureDTO
) {
    val imagePath = listOf(
        picture.DirectoryName.orEmpty().trim('/'),
        picture.PictureName.orEmpty().trim('/')
    )
        .filter { it.isNotBlank() }
        .joinToString("/")

    val imageUrl = ImageUrlResolver.Resolve(imagePath)

    if (imageUrl.isBlank()) {
        return
    }

    Surface(
        modifier = Modifier
            .width(220.dp)
            .height(140.dp),
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = picture.Description.orEmpty()
                .takeIf { it.isNotBlank() }
                ?: picture.PictureName.orEmpty(),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CompanyAboutCard(
    company: CompanyDTO
) {
    val about = company.About.orEmpty().trim()
    val whyUs = company.WhyUs.orEmpty().trim()

    if (about.isBlank() && whyUs.isBlank()) {
        return
    }

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            if (about.isNotBlank()) {
                Text(
                    text = BBLocalization.Current.Get(
                        key = "72467348-2eb8-484a-9253-27b28dbac2f3",
                        fallback = "Şirket Hakkında"
                    ),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = about,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (whyUs.isNotBlank()) {
                val whyUsTitle = BBLocalization.Current.Get(
                    key = "34ad3e1b-96a7-4933-83c5-88b55d3cee6e",
                    fallback = ""
                )

                if (whyUsTitle.isNotBlank()) {
                    Text(
                        text = whyUsTitle,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Text(
                    text = whyUs,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CompanyInfoGrid(
    company: CompanyDTO,
    onWebsiteClick: (String) -> Unit
) {
    val addressSummary = listOf(
        company.Address.orEmpty().trim(),
        company.DistrictName.orEmpty().trim(),
        company.CityName.orEmpty().trim()
    )
        .filter { it.isNotBlank() }
        .distinct()
        .joinToString(" / ")

    val website = company.Url.orEmpty().trim()

    val infoItems = buildList {
        company.YearEstablished.orEmpty()
            .trim()
            .takeIf { it.isNotEmpty() }
            ?.let { value ->
                add(
                    CompanyInfoItem(
                        title = BBLocalization.Current.Get(
                            key = "2439777a-0431-4929-9600-07df5586ad67",
                            fallback = ""
                        ),
                        value = value,
                        icon = Icons.Outlined.Factory
                    )
                )
            }

        company.SystemDescNumberOfEmployee.orEmpty()
            .trim()
            .takeIf { it.isNotEmpty() }
            ?.let { value ->
                add(
                    CompanyInfoItem(
                        title = BBLocalization.Current.Get(
                            key = "c763a88c-136e-44ed-9f5b-4f295ac6cb89",
                            fallback = "Çalışan"
                        ),
                        value = value,
                        icon = Icons.Outlined.Business
                    )
                )
            }

        company.ExportMarketsList.orEmpty()
            .trim()
            .takeIf { it.isNotEmpty() }
            ?.let { value ->
                add(
                    CompanyInfoItem(
                        title = BBLocalization.Current.Get(
                            key = "481f21a7-ac4c-4135-8b49-d3e022095f71",
                            fallback = ""
                        ),
                        value = value,
                        icon = Icons.Outlined.Language
                    )
                )
            }

        addressSummary
            .takeIf { it.isNotEmpty() }
            ?.let { value ->
                add(
                    CompanyInfoItem(
                        title = BBLocalization.Current.Get(
                            key = "af1da4df-7298-4cd9-b256-371d098b59f7",
                            fallback = "Adres"
                        ),
                        value = value,
                        icon = Icons.Outlined.LocationOn
                    )
                )
            }

        website
            .takeIf { it.isNotEmpty() }
            ?.let { value ->
                add(
                    CompanyInfoItem(
                        title = BBLocalization.Current.Get(
                            key = "a8fcc3ce-6d1a-40be-b752-974c9b774d7b",
                            fallback = "Web Sitesi"
                        ),
                        value = value,
                        icon = Icons.Outlined.Home,
                        onClick = {
                            onWebsiteClick(value)
                        }
                    )
                )
            }
    }

    if (infoItems.isEmpty()) {
        return
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGapCompact)
    ) {
        infoItems.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.CardGapCompact
                )
            ) {
                rowItems.forEach { item ->
                    CompanyInfoCard(
                        title = item.title,
                        value = item.value,
                        icon = item.icon,
                        modifier = Modifier.weight(1f),
                        onClick = item.onClick
                    )
                }

                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun CompanyInfoCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    BbCard(
        modifier = modifier,
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            if (title.isNotBlank()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

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
private fun CompanyCertificateCard(
    certificate: CompanyCertificateDTO,
    onClick: () -> Unit
) {
    val imageUrl = ImageUrlResolver.Resolve(
        certificate.Picture.orEmpty()
    )

    val certificateName = certificate.Certificate.orEmpty()
        .trim()
        .ifBlank {
            certificate.CertificateNumber.orEmpty().trim()
        }

    val description = certificate.Description.orEmpty().trim()
    val certificateNumber = certificate.CertificateNumber.orEmpty().trim()
    val expiryDate = certificate.ExpiryDate.orEmpty().trim()

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            if (imageUrl.isNotBlank()) {
                Surface(
                    modifier = Modifier.size(72.dp),
                    shape = BBRadius.LgShape,
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = certificateName,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Outlined.RequestQuote,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                if (certificateName.isNotBlank()) {
                    Text(
                        text = certificateName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (description.isNotBlank()) {
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (
                    certificateNumber.isNotBlank() &&
                    certificateNumber != certificateName
                ) {
                    Text(
                        text = certificateNumber,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (expiryDate.isNotBlank()) {
                    Text(
                        text = expiryDate,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun CompanyTrustPanel(
    company: CompanyDTO,
    certificateCount: Int,
    pictureCount: Int,
    onContactClick: () -> Unit
) {
    val trustTags = buildList {
        if (company.Verified) {
            add(
                BBLocalization.Current.Get(
                    key = "c6a0ff62-8828-475f-b553-37effb42efe6",
                    fallback = "Doğrulanmış"
                )
            )
        }

        if (certificateCount > 0) {
            add("$certificateCount sertifika")
        }

        if (pictureCount > 0) {
            add("$pictureCount görsel")
        }
    }

    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large,
        onClick = onContactClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            CompanyIconTitleRow(
                icon = Icons.Outlined.Security,
                title = BBLocalization.Current.Get(
                    key = "1ee2c81d-9d32-4a97-9334-f55d68c40b6d",
                    fallback = "Firma Güveni"
                )
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "2dfe6d94-be7c-4a5a-bbaf-1835ca4cd20d",
                    fallback = "Firma profili, ürünleri ve belgeleri üzerinden firmayı daha yakından değerlendirin."
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (trustTags.isNotEmpty()) {
                CompanyTagRow(tags = trustTags)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    BBSpacing.IconTextGap
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = BBLocalization.Current.Get(
                        key = "a439130c-b2cf-496f-9868-93ef084d9aec",
                        fallback = "İletişime Geç"
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun CompanyIconTitleRow(
    icon: ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.IconTextGap)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CompanyTagRow(
    tags: List<String>
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
    ) {
        tags
            .filter { it.isNotBlank() }
            .distinct()
            .forEach { tag ->
                BbChip(
                    text = tag,
                    selected = false,
                    onClick = {}
                )
            }
    }
}

@Composable
private fun CompanyDarkTagRow(
    tags: List<String>
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.ChipGap)
    ) {
        tags
            .filter { it.isNotBlank() }
            .distinct()
            .forEach { tag ->
                Surface(
                    shape = BBRadius.PillShape,
                    color = BBColors.White.copy(
                        alpha = BBAlpha.Overlay
                    ),
                    border = BorderStroke(
                        width = BBSpacing.None,
                        color = BBColors.White.copy(
                            alpha = BBAlpha.OverlayStrong
                        )
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = BBSpacing.Space3,
                            vertical = BBSpacing.Space2
                        ),
                        text = tag,
                        style = MaterialTheme.typography.labelMedium,
                        color = BBColors.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
    }
}

private data class CompanyInfoItem(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val onClick: (() -> Unit)? = null
)

private fun String.toCompanyLogoText(): String {
    return trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .mapNotNull { part ->
            part.firstOrNull()
                ?.uppercaseChar()
                ?.toString()
        }
        .joinToString("")
}

@Preview(showBackground = true)
@Composable
private fun CompanyDetailScreenPreview() {
    BbTheme {
        CompanyDetailScreen()
    }
}