package com.bulbulustur.android.Application.Areas.b2c.Views.Category

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Areas.b2c.Views.Category.Components.CategoryProductContentShowcaseContent
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigation
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailBottomNavigationItem
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeader
import com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components.RetailSearchHeaderLeadingAction
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Views.Shared.Components.BbMaterialSymbol
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCard
import com.bulbulustur.android.Application.Views.Shared.Components.BbProductCardModel
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductData
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryContentGroupDTO
import com.bulbulustur.android.businesslayer.Core.Network.ImageUrlResolver
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CategoryLevel1Screen(
    categoryId: Int = 0,
    categoryInfo: ProductCategoryDTO? = null,
    childCategories: List<ProductCategoryDTO> = emptyList(),
    categoryContents: List<ProductCategoryContentGroupDTO> = emptyList(),
    products: List<B2CProductData> = emptyList(),
    campaigns: List<CampaignDTO> = emptyList(),
    sponsoredAdverts: List<AdvertSponsoredDTO> = emptyList(),
    isProductLoading: Boolean = false,
    isCategoryContentsLoading: Boolean = false,

    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onModeSwitchClick: () -> Unit = {},
    onBasketClick: () -> Unit = {},
    onAccountClick: () -> Unit = {},
    onSubCategoryClick: (Int) -> Unit = {},

    onCategoryProductClick: (ProductCategoryContentDTO) -> Unit = {},
    onCategoryProductFavoriteClick: (ProductCategoryContentDTO) -> Unit = {},
    onCategoryAddToBasketClick: (Int) -> Unit = {},
    onCategoryViewAllClick: (ProductCategoryContentGroupDTO) -> Unit = {},
    onProductClick: (B2CProductData) -> Unit = {},
    onProductFavoriteClick: (B2CProductData) -> Unit = {},
    onAddToBasketClick: (Int) -> Unit = {},
    onSponsoredProductClick: (AdvertSponsoredDTO) -> Unit = {},
    onSponsoredFavoriteClick: (AdvertSponsoredDTO) -> Unit = {},
    onSponsoredAddToBasketClick: (Int) -> Unit = {},
    onCampaignClick: (CampaignDTO) -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    var favoriteSponsoredIds by remember { mutableStateOf<Set<Int>>(emptySet()) }

    val validChildCategories = remember(childCategories) {
        childCategories
            .filter { it.ProductCategoryId > 0 && it.CategoryName.isNotBlank() }
            .distinctBy { it.ProductCategoryId }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            RetailSearchHeader(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                placeholder = BBLocalization.Current.Get(
                    key = "e4f653c3-8828-4934-aa3b-959cede38feb",
                    fallback = "Ürün, kategori veya marka ara"
                ),
                leadingAction = RetailSearchHeaderLeadingAction.Back,
                onBackClick = onBackClick,
                onMenuClick = onMenuClick,
                onFavoriteClick = onFavoriteClick,
                onMessageClick = onMessageClick,
                onSearchClick = { onSearchClick(searchText) },
                onClearClick = { searchText = "" }
            )
        },
        bottomBar = {
            RetailBottomNavigation(
                selectedItem = RetailBottomNavigationItem.Menu,
                onItemClick = { item ->
                    when (item) {
                        RetailBottomNavigationItem.Home -> onHomeClick()
                        RetailBottomNavigationItem.Menu -> onMenuClick()
                        RetailBottomNavigationItem.ModeSwitch -> onModeSwitchClick()
                        RetailBottomNavigationItem.Basket -> onBasketClick()
                        RetailBottomNavigationItem.Account -> onAccountClick()
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.PageTopCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.SectionGapCompact)
        ) {
            item {
                CategoryDetailHero(
                    categoryId = categoryId,
                    categoryInfo = categoryInfo,
                    childCategoryCount = validChildCategories.size,
                    hasShowcase = categoryContents.isNotEmpty() ||
                            isCategoryContentsLoading ||
                            campaigns.isNotEmpty() ||
                            sponsoredAdverts.isNotEmpty()
                )
            }

            if (validChildCategories.isNotEmpty()) {
                item {
                    CategorySubCategorySectionHeader()
                }

                items(
                    items = validChildCategories,
                    key = { it.ProductCategoryId }
                ) { category ->
                    CategorySubCategoryRow(
                        category = category,
                        onClick = { onSubCategoryClick(category.ProductCategoryId) }
                    )
                }
            }

            item {
                CategoryShowcaseSection(
                    categoryContents = categoryContents,
                    isCategoryContentsLoading = isCategoryContentsLoading,
                    campaigns = campaigns,
                    onCategoryProductClick = onCategoryProductClick,
                    onCategoryProductFavoriteClick = onCategoryProductFavoriteClick,
                    onCategoryAddToBasketClick = onCategoryAddToBasketClick,
                    onCategoryViewAllClick = onCategoryViewAllClick,
                    onCampaignClick = onCampaignClick
                )
            }

            item {
                CategorySponsoredFeaturedSection(
                    sponsoredAdverts = sponsoredAdverts,
                    favoriteSponsoredIds = favoriteSponsoredIds,
                    onSponsoredProductClick = onSponsoredProductClick,
                    onSponsoredFavoriteClick = { advert ->
                        favoriteSponsoredIds =
                            if (favoriteSponsoredIds.contains(advert.ProductId)) {
                                favoriteSponsoredIds - advert.ProductId
                            } else {
                                favoriteSponsoredIds + advert.ProductId
                            }

                        onSponsoredFavoriteClick(advert)
                    },
                    onSponsoredAddToBasketClick = onSponsoredAddToBasketClick
                )
            }
        }
    }
}

@Composable
private fun CategoryDetailHero(
    categoryId: Int,
    categoryInfo: ProductCategoryDTO?,
    childCategoryCount: Int,
    hasShowcase: Boolean
) {
    val categoryName = categoryInfo
        ?.CategoryName
        ?.takeIf { it.isNotBlank() }
        ?: BBLocalization.Current.Get(
            key = "1a132fdc-096f-42d7-835d-96b0a17b3675",
            fallback = ""
        )

    val description = categoryInfo
        ?.Breadcrumb
        .orEmpty()
        .takeIf {
            it.isNotBlank() &&
                    it != categoryId.toString() &&
                    it.any { character -> !character.isDigit() }
        }
        .orEmpty()

    val childLabel = BBLocalization.Current.Get(
        key = "19e928cc-d4e4-426f-a1e8-fb8d9adf872f",
        fallback = ""
    )

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XxlShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
            ) {
                BbIconBox(
                    size = BbIconBoxSize.Xl,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    radius = BBRadius.xl
                ) {
                    BbMaterialSymbol(
                        iconClass = categoryInfo?.IconClass,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Text(
                        text = categoryName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (description.isNotBlank()) {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            if (childCategoryCount > 0 || hasShowcase) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                ) {
                    if (childCategoryCount > 0) {
                        item {
                            CategoryInfoChip(
                                text = if (childLabel.isNotBlank()) {
                                    "$childCategoryCount $childLabel"
                                } else {
                                    childCategoryCount.toString()
                                }
                            )
                        }
                    }

                    if (hasShowcase) {
                        item {
                            CategoryInfoChip(
                                text = BBLocalization.Current.Get(
                                    key = "21f6b0ee-67eb-40fb-899d-640fb99a7397",
                                    fallback = "Kategori Vitrinleri"
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryInfoChip(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun CategorySubCategorySectionHeader() {
    RetailCategorySectionTitle(
        title = BBLocalization.Current.Get(
            key = "19e928cc-d4e4-426f-a1e8-fb8d9adf872f",
            fallback = ""
        ),
        description = BBLocalization.Current.Get(
            key = "74a46e5a-2695-4867-8660-b0fe2b4f8528",
            fallback = "Doğrudan ürün akışına inmek için hızlı seçim."
        )
    )
}

@Composable
private fun CategorySubCategoryRow(
    category: ProductCategoryDTO,
    onClick: () -> Unit
) {
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
            BbIconBox(
                size = BbIconBoxSize.Medium,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                radius = BBRadius.lg
            ) {
                BbMaterialSymbol(
                    iconClass = category.IconClass,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = category.CategoryName,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CategoryShowcaseSection(
    categoryContents: List<ProductCategoryContentGroupDTO>,
    isCategoryContentsLoading: Boolean,
    campaigns: List<CampaignDTO>,
    onCategoryProductClick: (ProductCategoryContentDTO) -> Unit,
    onCategoryProductFavoriteClick: (ProductCategoryContentDTO) -> Unit,
    onCategoryAddToBasketClick: (Int) -> Unit,
    onCategoryViewAllClick: (ProductCategoryContentGroupDTO) -> Unit,
    onCampaignClick: (CampaignDTO) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        RetailCategorySectionTitle(
            title = BBLocalization.Current.Get(
                key = "21f6b0ee-67eb-40fb-899d-640fb99a7397",
                fallback = "Kategori Vitrinleri"
            ),
            description = BBLocalization.Current.Get(
                key = "9beff001-6dd9-4017-9f1f-72ef6606495a",
                fallback = "Ürün, mağaza ve kampanya akışlarına hızlı geç."
            )
        )

        CategoryProductContentShowcaseContent(
            categoryContents = categoryContents,
            isLoading = isCategoryContentsLoading,
            onProductClick = onCategoryProductClick,
            onFavoriteClick = onCategoryProductFavoriteClick,
            onAddToBasketClick = onCategoryAddToBasketClick,
            onViewAllClick = onCategoryViewAllClick
        )

        if (campaigns.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                contentPadding = PaddingValues(end = BBSpacing.Space3)
            ) {
                items(
                    items = campaigns.filter { it.CampaignId > 0 },
                    key = { it.CampaignId }
                ) { campaign ->
                    CategoryCampaignCard(
                        campaign = campaign,
                        onClick = { onCampaignClick(campaign) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategorySponsoredFeaturedSection(
    sponsoredAdverts: List<AdvertSponsoredDTO>,
    favoriteSponsoredIds: Set<Int>,
    onSponsoredProductClick: (AdvertSponsoredDTO) -> Unit,
    onSponsoredFavoriteClick: (AdvertSponsoredDTO) -> Unit,
    onSponsoredAddToBasketClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
    ) {
        RetailCategorySectionTitle(
            title = BBLocalization.Current.Get(
                key = "1c7c6ac9-2b6d-46ec-90f0-3f88b65beb11",
                fallback = "Öne Çıkan Ürünler"
            ),
            description = BBLocalization.Current.Get(
                key = "4340b4b1-5348-4601-ad94-18ea0ca8f5cc",
                fallback = "Bu kategoride dikkat çeken seçili ürünlerden kısa bir seçki."
            )
        )

        if (sponsoredAdverts.isEmpty()) {
            CategoryProductEmpty()
        } else {
            sponsoredAdverts
                .filter { it.ProductId > 0 }
                .take(6)
                .chunked(2)
                .forEach { rowProducts ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                        verticalAlignment = Alignment.Top
                    ) {
                        rowProducts.forEach { advert ->
                            val isFavorite = favoriteSponsoredIds.contains(advert.ProductId)

                            BbProductCard(
                                modifier = Modifier.weight(1f),
                                product = advert.ToSponsoredProductCardModel(
                                    isFavorite = isFavorite
                                ),
                                onClick = {
                                    onSponsoredProductClick(advert)
                                },
                                onFavoriteClick = {
                                    onSponsoredFavoriteClick(advert)
                                },
                                onAddToBasketClick = {
                                    if (advert.ProductVariantPriceId > 0) {
                                        onSponsoredAddToBasketClick(
                                            advert.ProductVariantPriceId
                                        )
                                    }
                                }
                            )
                        }

                        if (rowProducts.size == 1) {
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
        }
    }
}

@Composable
private fun CategoryCampaignCard(
    campaign: CampaignDTO,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.width(BBSpacing.Space24 + BBSpacing.Space16),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            BbIconBox(
                size = BbIconBoxSize.Medium,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                radius = BBRadius.lg
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalOffer,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = campaign.CampaignName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (campaign.Description.isNotBlank()) {
                    Text(
                        text = campaign.Description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryProductEmpty() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(BBSpacing.Space5),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Text(
                text = BBLocalization.Current.Get(
                    key = "9afc052e-e2bf-413d-81c6-461bfc3c9174",
                    fallback = "Ürün bulunamadı"
                ),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = BBLocalization.Current.Get(
                    key = "59f50847-365f-4959-b050-641d7c1e18cc",
                    fallback = "Arama veya filtre seçimini değiştirerek tekrar deneyebilirsin."
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RetailCategorySectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        if (title.isNotBlank()) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        if (description.isNotBlank()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun AdvertSponsoredDTO.ToSponsoredProductCardModel(
    isFavorite: Boolean
): BbProductCardModel {
    return BbProductCardModel(
        Id = ProductId,
        Name = ProductName.orEmpty(),
        StoreName = "",
        ImageUrl = ImageUrlResolver.Resolve(DefaultPicture.orEmpty()),
        PriceText = FormatCategoryProductPrice(
            price = Price,
            currencySymbol = CurrencySymbol
        ),
        OldPriceText = "",
        BadgeText = "",
        RatingText = "",
        CargoText = "",
        IsFavorite = isFavorite
    )
}

private fun FormatCategoryProductPrice(
    price: Double,
    currencySymbol: String?
): String {
    val formatter = NumberFormat
        .getNumberInstance(Locale.forLanguageTag("tr-TR"))
        .apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }

    return buildString {
        append(currencySymbol.orEmpty())
        if (!currencySymbol.isNullOrBlank()) {
            append(" ")
        }
        append(formatter.format(price))
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryLevel1ScreenPreview() {
    BbTheme {
        CategoryLevel1Screen()
    }
}