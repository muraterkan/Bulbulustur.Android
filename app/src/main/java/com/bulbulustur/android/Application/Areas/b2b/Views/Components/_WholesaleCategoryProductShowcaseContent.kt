package com.bulbulustur.android.Application.Areas.b2b.Views.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCard
import com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components.WholesaleProductCardModel
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbChip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver

@Composable
fun WholesaleCategoryProductShowcaseContent(
    specialContents: List<WholesaleHomepageSpecialContentDTO>,
    isLoading: Boolean = false,
    onProductClick: (Int) -> Unit = {},
    onFavoriteClick: (Int) -> Unit = {},
    onRfqClick: (Int) -> Unit = {},
    onViewAllClick: (WholesaleHomepageSpecialContentDTO) -> Unit = {}
) {
    var selectedContentIndex by remember(specialContents) {
        mutableIntStateOf(0)
    }

    var favoriteProductIds by remember {
        mutableStateOf<Set<Int>>(emptySet())
    }

    when {
        isLoading && specialContents.isEmpty() -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BBSpacing.Space5),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator()
            }
        }

        specialContents.isEmpty() -> {
            WholesaleCategoryProductShowcaseEmpty()
        }

        else -> {
            val selectedContent =
                specialContents.getOrNull(selectedContentIndex)
                    ?: specialContents.first()

            val selectedProducts = selectedContent.Products
                .filter {
                    it.WholesaleProductId > 0
                }
                .distinctBy {
                    it.WholesaleProductId
                }
                .take(12)

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    BBSpacing.Space3
                )
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(
                        BBSpacing.Space2
                    ),
                    contentPadding = PaddingValues(
                        end = BBSpacing.Space3
                    )
                ) {
                    items(
                        count = specialContents.size,
                        key = { index ->
                            val item = specialContents[index]

                            "${item.WholesaleHomepageSpecialContentId}-${item.ProductSpecialGroupId}-$index"
                        }
                    ) { index ->
                        val content =
                            specialContents[index]

                        BbChip(
                            text = safeWholesaleShowcaseText(
                                content.GroupName
                            ).ifBlank {
                                safeWholesaleShowcaseText(
                                    content.ContentName
                                )
                            },
                            selected =
                                selectedContentIndex == index,
                            onClick = {
                                selectedContentIndex = index
                            }
                        )
                    }
                }

                WholesaleCategoryProductShowcaseHeader(
                    title = safeWholesaleShowcaseText(
                        selectedContent.GroupName
                    ).ifBlank {
                        safeWholesaleShowcaseText(
                            selectedContent.ContentName
                        )
                    },
                    onViewAllClick = {
                        onViewAllClick(
                            selectedContent
                        )
                    }
                )

                if (selectedProducts.isEmpty()) {
                    WholesaleCategoryProductShowcaseEmpty()
                } else {
                    selectedProducts
                        .chunked(2)
                        .forEach { rowProducts ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement =
                                    Arrangement.spacedBy(
                                        BBSpacing.Space3
                                    ),
                                verticalAlignment =
                                    Alignment.Top
                            ) {
                                rowProducts.forEach { product ->
                                    val productId =
                                        product.WholesaleProductId

                                    val isFavorite =
                                        favoriteProductIds.contains(
                                            productId
                                        )

                                    WholesaleProductCard(
                                        modifier = Modifier.weight(
                                            1f
                                        ),
                                        product = product.ToWholesaleCategoryShowcaseCardModel(
                                            isFavorite = isFavorite
                                        ),
                                        onClick = {
                                            if (productId > 0) {
                                                onProductClick(
                                                    productId
                                                )
                                            }
                                        },
                                        onFavoriteClick = {
                                            favoriteProductIds =
                                                if (isFavorite) {
                                                    favoriteProductIds -
                                                            productId
                                                } else {
                                                    favoriteProductIds +
                                                            productId
                                                }

                                            if (productId > 0) {
                                                onFavoriteClick(
                                                    productId
                                                )
                                            }
                                        },
                                        onRfqClick = {
                                            if (productId > 0) {
                                                onRfqClick(
                                                    productId
                                                )
                                            }
                                        }
                                    )
                                }

                                if (rowProducts.size == 1) {
                                    Spacer(
                                        modifier = Modifier.weight(
                                            1f
                                        )
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}

@Composable
private fun WholesaleCategoryProductShowcaseHeader(
    title: String,
    onViewAllClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            modifier = Modifier.clickable {
                onViewAllClick()
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.Space1
            )
        ) {
            Text(
                text = BBLocalization.Current.Get(
                    key = "7fa2dfd8-809f-4a8d-8fde-f33e7f652b45",
                    fallback = "Tümünü Gör"
                ),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Icon(
                imageVector =
                    Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.padding(
                    start = BBSpacing.Space1
                ),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun WholesaleCategoryProductShowcaseEmpty() {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Text(
            text = BBLocalization.Current.Get(
                key = "9afc052e-e2bf-413d-81c6-461bfc3c9174",
                fallback = "Ürün bulunamadı"
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun WholesaleHomepageSpecialDTO.ToWholesaleCategoryShowcaseCardModel(
    isFavorite: Boolean
): WholesaleProductCardModel {
    return WholesaleProductCardModel(
        Id = WholesaleProductId,
        Title = safeWholesaleShowcaseText(
            ProductName
        ),
        PriceText = BBLocalization.Current.Get(
            key = "ba6fe1b6-4d68-487c-b98a-eed9fe59bb2c",
            fallback = "Teklif İle"
        ),
        MoqText =
            if (MinimumOrderQuantity > 0) {
                "MOQ $MinimumOrderQuantity"
            } else {
                ""
            },
        BadgeText = "",
        ImageUrl = ImageUrlResolver.Resolve(
            safeWholesaleShowcaseText(
                DefaultPicture
            )
        ),
        IsFavorite = isFavorite
    )
}

private fun safeWholesaleShowcaseText(
    value: String?
): String {
    return value
        ?.trim()
        .orEmpty()
}