package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBLayout
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandSectionDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandSectionPageDTO
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes

@Composable
fun _FromBrands(
    Sections: List<ProductBrandSectionDTO>,
    onPageClick: (ProductBrandSectionPageDTO) -> Unit
) {
    val visibleSections =
        Sections
            .filter { section ->
                section.Pages.isNotEmpty()
            }
            .sortedBy { section ->
                section.OrderNo
            }

    if (
        visibleSections.isEmpty()
    ) {
        return
    }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    top =
                        BBSpacing.Space5
                ),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space5
            )
    ) {
        visibleSections.forEach { section ->
            FromBrandsSection(
                Section =
                    section,
                onPageClick =
                    onPageClick
            )
        }
    }
}

@Composable
private fun FromBrandsSection(
    Section: ProductBrandSectionDTO,
    onPageClick: (ProductBrandSectionPageDTO) -> Unit
) {
    val pages =
        Section.Pages
            .sortedBy { page ->
                page.OrderNo
            }

    if (
        pages.isEmpty()
    ) {
        return
    }

    Column(
        modifier =
            Modifier.fillMaxWidth(),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        FromBrandsSectionHeader(
            Title =
                Section.Title,
            Content =
                Section.Content
        )

        LazyRow(
            modifier =
                Modifier.fillMaxWidth(),
            contentPadding =
                androidx.compose.foundation.layout.PaddingValues(
                    horizontal =
                        BBSpacing.PageHorizontal
                ),
            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {
            items(
                items =
                    pages,
                key = { page ->
                    page.ProductBrandSectionPageId
                }
            ) { page ->
                FromBrandsPageCard(
                    Page =
                        page,
                    onClick = {
                        onPageClick(
                            page
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun FromBrandsSectionHeader(
    Title: String,
    Content: String
) {
    val resolvedTitle =
        Title
            .takeIf {
                it.isNotBlank()
            }
            ?: "Markadan Seçimler"

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal =
                        BBSpacing.PageHorizontal
                ),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space1
            )
    ) {
        Text(
            text =
                resolvedTitle,
            style =
                MaterialTheme.typography.titleMedium,
            color =
                MaterialTheme.colorScheme.onSurface,
            fontWeight =
                FontWeight.Bold
        )

        if (
            Content.isNotBlank()
        ) {
            Text(
                text =
                    Content,
                style =
                    MaterialTheme.typography.bodySmall,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FromBrandsPageCard(
    Page: ProductBrandSectionPageDTO,
    onClick: () -> Unit
) {
    val imageUrl =
        ResolveFromBrandsImageUrl(
            imagePath =
                Page.MobilePicture
                    .takeIf {
                        it.isNotBlank()
                    }
                    ?: Page.Picture
        )

    Surface(
        modifier =
            Modifier
                .width(
                    BBLayout.ProductCardWidthSmall
                )
                .clip(
                    BBRadius.XlShape
                )
                .clickable {
                    onClick()
                },
        shape =
            BBRadius.XlShape,
        color =
            MaterialTheme.colorScheme.surface,
        border =
            BorderStroke(
                width =
                    BBSpacing.BorderThin,
                color =
                    MaterialTheme.colorScheme.outlineVariant
            )
    ) {
        Column(
            modifier =
                Modifier.fillMaxWidth()
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(
                            BBLayout.ProductCardMediaHeightLarge
                        ),
                contentAlignment =
                    Alignment.Center
            ) {
                if (
                    imageUrl.isNotBlank()
                ) {
                    AsyncImage(
                        model =
                            imageUrl,
                        contentDescription =
                            Page.Title,
                        modifier =
                            Modifier.fillMaxSize(),
                        contentScale =
                            ContentScale.Crop
                    )
                } else {
                    Text(
                        text =
                            Page.Title
                                .takeIf {
                                    it.isNotBlank()
                                }
                                ?.take(
                                    2
                                )
                                ?.uppercase()
                                ?: "BB",
                        style =
                            MaterialTheme.typography.titleLarge,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }

            if (
                Page.Title.isNotBlank() ||
                Page.SubTitle.isNotBlank()
            ) {
                Column(
                    modifier =
                        Modifier.padding(
                            BBSpacing.Space3
                        ),
                    verticalArrangement =
                        Arrangement.spacedBy(
                            BBSpacing.Space1
                        )
                ) {
                    if (
                        Page.Title.isNotBlank()
                    ) {
                        Text(
                            text =
                                Page.Title,
                            style =
                                MaterialTheme.typography.bodyMedium,
                            color =
                                MaterialTheme.colorScheme.onSurface,
                            fontWeight =
                                FontWeight.SemiBold,
                            maxLines =
                                2
                        )
                    }

                    if (
                        Page.SubTitle.isNotBlank()
                    ) {
                        Text(
                            text =
                                Page.SubTitle,
                            style =
                                MaterialTheme.typography.bodySmall,
                            color =
                                MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines =
                                2
                        )
                    }
                }
            }
        }
    }
}

private fun ResolveFromBrandsImageUrl(
    imagePath: String
): String {
    val normalizedPath =
        imagePath.trim()

    if (
        normalizedPath.isBlank()
    ) {
        return ""
    }

    if (
        normalizedPath.startsWith(
            "http://",
            ignoreCase =
                true
        ) ||
        normalizedPath.startsWith(
            "https://",
            ignoreCase =
                true
        )
    ) {
        return normalizedPath
    }

    val baseUrl =
        ApiRoutes.BRAND_BASE_URL
            .substringBefore(
                "/api/"
            )
            .trimEnd(
                '/'
            )

    return "$baseUrl/${normalizedPath.trimStart('/')}"
}