package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBoxSize
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbIconBox
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO

@Composable
fun SearchScreen(
    initialSearchText: String = "",
    onBackClick: () -> Unit = {},
    onSearchSubmit: (String, RetailSearchType) -> Unit = { _, _ -> },
    onProductClick: (RetailSearchProductItem) -> Unit = {},
    onCategoryClick: (RetailSearchCategoryItem) -> Unit = {},
    onBrandClick: (RetailSearchBrandItem) -> Unit = {},
    onStoreClick: (RetailSearchStoreItem) -> Unit = {},
    productResults: List<ProductDTO> = emptyList(),
    hasProductSearch: Boolean = false
) {
    var searchText by remember {
        mutableStateOf(initialSearchText)
    }

    var selectedSearchType by remember {
        mutableStateOf(RetailSearchType.Product)
    }

    val searchData = remember {
        getRetailSearchScreenData()
    }

    val displayedProducts = if (hasProductSearch) {
        productResults.map { product ->
            product.toRetailSearchProductItem()
        }
    } else {
        searchData.products
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 14.dp,
                end = 16.dp,
                bottom = 28.dp
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            item {
                SearchTopBar(
                    onBackClick = onBackClick
                )
            }

            item {
                SearchInputArea(
                    searchText = searchText,
                    onSearchTextChange = {
                        searchText = it
                    },
                    onSearchSubmit = {
                        onSearchSubmit(searchText, selectedSearchType)
                    }
                )
            }

            item {
                SearchTypeChips(
                    selectedSearchType = selectedSearchType,
                    onSearchTypeChange = {
                        selectedSearchType = it
                    }
                )
            }

            if (searchText.isBlank()) {
                item {
                    RecentSearchSection(
                        recentSearches = searchData.recentSearches,
                        onRecentSearchClick = {
                            searchText = it
                            onSearchSubmit(it, selectedSearchType)
                        }
                    )
                }

                item {
                    PopularSearchSection(
                        popularSearches = searchData.popularSearches,
                        onPopularSearchClick = {
                            searchText = it
                            onSearchSubmit(it, selectedSearchType)
                        }
                    )
                }
            }

            when (selectedSearchType) {
                RetailSearchType.Product -> {
                    item {
                        SearchSectionTitle(
                            title = BBLocalization.Current.Get(key = "71aeca46-c5db-45dc-be29-b8041d0a30aa", fallback = "Ürün sonuçları"),
                            description = BBLocalization.Current.Get(key = "bd212e25-400e-41b6-bc99-51a1a0b8fc75", fallback = "Aramana yakın ürün önerileri.")
                        )
                    }

                    itemsIndexed(displayedProducts) { index, product ->
                        SearchProductCard(
                            product = product,
                            onClick = {
                                onProductClick(product)
                            }
                        )
                    }
                }

                RetailSearchType.Category -> {
                    item {
                        SearchSectionTitle(
                            title = BBLocalization.Current.Get(key = "d6c8fa15-ec8a-42d8-a3c9-9003b906dbc0", fallback = "Kategori sonuçları"),
                            description = BBLocalization.Current.Get(key = "99d44e85-9e40-4f16-bde3-9532f2576699", fallback = "İlgili kategori ve alt kategori önerileri.")
                        )
                    }

                    items(searchData.categories) { category ->
                        SearchCategoryCard(
                            category = category,
                            onClick = {
                                onCategoryClick(category)
                            }
                        )
                    }
                }

                RetailSearchType.Brand -> {
                    item {
                        SearchSectionTitle(
                            title = BBLocalization.Current.Get(key = "89a09255-46a7-46ca-8b88-6024aa56f2b3", fallback = "Marka sonuçları"),
                            description = BBLocalization.Current.Get(key = "61b0234b-c39d-40f1-857a-e2f57a0587df", fallback = "Bulbulustur içindeki marka eşleşmeleri.")
                        )
                    }

                    items(searchData.brands) { brand ->
                        SearchBrandCard(
                            brand = brand,
                            onClick = {
                                onBrandClick(brand)
                            }
                        )
                    }
                }

                RetailSearchType.Store -> {
                    item {
                        SearchSectionTitle(
                            title = BBLocalization.Current.Get(key = "3567ac84-e92a-4399-a3fa-07d85c69bdd1", fallback = "Mağaza sonuçları"),
                            description = "Ürün satan mağaza ve Vitrinler."
                        )
                    }

                    items(searchData.stores) { store ->
                        SearchStoreCard(
                            store = store,
                            onClick = {
                                onStoreClick(store)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchTopBar(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BbIconBox(
            modifier = Modifier.clickable {
                onBackClick()
            },
            size = BbIconBoxSize.Medium,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = BBLocalization.Current.Get(key = "9d1f3b0b-d99f-4bf0-8f30-e2f8a399000f", fallback = "Perakende arama"),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = BBLocalization.Current.Get(key = "d462dea8-e1d4-4693-a5ae-bef2950aa4cd", fallback = "Ürün, kategori, marka veya mağaza bul."),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SearchInputArea(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchSubmit: () -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp)),
        placeholder = {
            Text(text = BBLocalization.Current.Get(key = "118523bc-e7a6-44c0-8b4e-284c271500c7", fallback = "Ne arıyorsun?"))
        },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = BBColors.Transparent,
            unfocusedIndicatorColor = BBColors.Transparent,
            disabledIndicatorColor = BBColors.Transparent
        ),
        trailingIcon = {
            Text(
                text = BBLocalization.Current.Get(key = "b62c3162-72af-439f-a20b-c4ca58f51035", fallback = "Ara"),
                modifier = Modifier
                    .padding(end = BBSpacing.Space3)
                    .clickable {
                        onSearchSubmit()
                    },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SearchTypeChips(
    selectedSearchType: RetailSearchType,
    onSearchTypeChange: (RetailSearchType) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        RetailSearchType.entries.forEach { searchType ->
            FilterChip(
                selected = selectedSearchType == searchType,
                onClick = {
                    onSearchTypeChange(searchType)
                },
                label = {
                    Text(text = searchType.title)
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RecentSearchSection(
    recentSearches: List<String>,
    onRecentSearchClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SearchSectionTitle(
            title = BBLocalization.Current.Get(key = "ff16d4fd-2eec-49e2-b43b-cdb4a15f8ce2", fallback = "Son Aramalar"),
            description = BBLocalization.Current.Get(key = "0b1d55b7-f17f-4379-9619-6990256b26a7", fallback = "Tek dokunuşla tekrar ara.")
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            recentSearches.forEach { recentSearch ->
                FilterChip(
                    selected = false,
                    onClick = {
                        onRecentSearchClick(recentSearch)
                    },
                    label = {
                        Text(text = recentSearch)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PopularSearchSection(
    popularSearches: List<String>,
    onPopularSearchClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SearchSectionTitle(
            title = BBLocalization.Current.Get(key = "7b4d8fc2-76a4-4cdb-aacf-b52bb67c4661", fallback = "Popüler Aramalar"),
            description = BBLocalization.Current.Get(key = "563b5cd8-0942-433f-8506-4103b934ed12", fallback = "Şu an en çok bakılan Aramalar.")
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            popularSearches.forEach { popularSearch ->
                FilterChip(
                    selected = false,
                    onClick = {
                        onPopularSearchClick(popularSearch)
                    },
                    label = {
                        Text(text = popularSearch)
                    }
                )
            }
        }
    }
}

@Composable
private fun SearchSectionTitle(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SearchProductCard(
    product: RetailSearchProductItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchImagePlaceholder(
                text = product.imageText
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.priceText,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            SearchArrow()
        }
    }
}

@Composable
private fun SearchCategoryCard(
    category: RetailSearchCategoryItem,
    onClick: () -> Unit
) {
    SearchSimpleCard(
        iconText = category.iconText,
        title = category.name,
        description = "${category.productCount} ürün . ${category.subCategoryCount} alt kategori",
        onClick = onClick
    )
}

@Composable
private fun SearchBrandCard(
    brand: RetailSearchBrandItem,
    onClick: () -> Unit
) {
    SearchSimpleCard(
        iconText = brand.logoText,
        title = brand.name,
        description = "${brand.productCount} ürün . ${brand.storeCount} mağaza",
        onClick = onClick
    )
}

@Composable
private fun SearchStoreCard(
    store: RetailSearchStoreItem,
    onClick: () -> Unit
) {
    SearchSimpleCard(
        iconText = store.logoText,
        title = store.name,
        description = "${store.productCount} ürün . ${store.ratingText} puan",
        onClick = onClick
    )
}

@Composable
private fun SearchSimpleCard(
    iconText: String,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BbIconBox(
                size = BbIconBoxSize.Large,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Text(
                    text = iconText,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            SearchArrow()
        }
    }
}

@Composable
private fun SearchImagePlaceholder(
    text: String
) {
    Box(
        modifier = Modifier
            .size(68.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SearchArrow() {
    Text(
        text = "›",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

enum class RetailSearchType(
    val title: String
) {
    Product(BBLocalization.Current.Get(key = "37f5db70-845d-4498-96d4-fb3a2d29326c", fallback = "")),
    Category(BBLocalization.Current.Get(key = "1a132fdc-096f-42d7-835d-96b0a17b3675", fallback = "")),
    Brand(BBLocalization.Current.Get(key = "3f8b4f8b-6cba-43e5-bc5c-b9c07fb7208e", fallback = "")),
    Store(BBLocalization.Current.Get(key = "a4bd79dd-e7ee-4407-9e7d-00582840c43a", fallback = "Mağaza"))
}

data class RetailSearchScreenData(
    val recentSearches: List<String>,
    val popularSearches: List<String>,
    val products: List<RetailSearchProductItem>,
    val categories: List<RetailSearchCategoryItem>,
    val brands: List<RetailSearchBrandItem>,
    val stores: List<RetailSearchStoreItem>
)

data class RetailSearchProductItem(
    val id: Int,
    val name: String,
    val storeName: String,
    val priceText: String,
    val imageText: String
)

data class RetailSearchCategoryItem(
    val id: Int,
    val name: String,
    val iconText: String,
    val productCount: Int,
    val subCategoryCount: Int
)

data class RetailSearchBrandItem(
    val id: Int,
    val name: String,
    val logoText: String,
    val productCount: Int,
    val storeCount: Int
)

data class RetailSearchStoreItem(
    val id: Int,
    val name: String,
    val logoText: String,
    val productCount: Int,
    val ratingText: String
)

private fun ProductDTO.toRetailSearchProductItem(): RetailSearchProductItem {
    val price = if (Price > 0.0) {
        "${CurrencySymbol.ifBlank { "₺" }}${Price}"
    } else {
        ""
    }

    return RetailSearchProductItem(
        id = ProductId,
        name = ProductName.ifBlank { SeoTitle },
        storeName = Store.ifBlank { CategoryName },
        priceText = price,
        imageText = ProductName.take(2).uppercase().ifBlank { "P" }
    )
}

private fun getRetailSearchScreenData(): RetailSearchScreenData {
    return RetailSearchScreenData(
        recentSearches = listOf(
            "sneaker",
            "bebek bezi",
            "çelik termos",
            "oversize tişört"
        ),
        popularSearches = listOf(
            BBLocalization.Current.Get(key = "516f6e72-596b-4482-8aaf-3c766520da99", fallback = "kampanyalı ayakkabı"),
            BBLocalization.Current.Get(key = "e4da9fca-05ee-4489-8579-320e844aad7f", fallback = "telefon aksesuarı"),
            "mutfak düzenleyici",
            "cilt bakım"
        ),
        products = listOf(
            RetailSearchProductItem(
                id = 1,
                name = BBLocalization.Current.Get(key = "cf2f4de0-711c-4308-a055-3ef7eb00d9c7", fallback = "Kadın klasik sneaker ayakkabı"),
                storeName = "Ortobella",
                priceText = "₺899,90",
                imageText = "P1"
            ),
            RetailSearchProductItem(
                id = 2,
                name = BBLocalization.Current.Get(key = "4d8d3a59-db93-45f0-8d29-5871bc1176bc", fallback = "Kablosuz bluetooth kulaklık"),
                storeName = BBLocalization.Current.Get(key = "4974118f-ecd1-486b-a149-c967127479a9", fallback = "Tekno Sepet"),
                priceText = "₺649,90",
                imageText = "P2"
            ),
            RetailSearchProductItem(
                id = 3,
                name = "Pamuklu oversize basic tişört",
                storeName = "Moda Nova",
                priceText = "₺349,90",
                imageText = "P3"
            )
        ),
        categories = listOf(
            RetailSearchCategoryItem(
                id = 1,
                name = "Moda",
                iconText = "MO",
                productCount = 18420,
                subCategoryCount = 36
            ),
            RetailSearchCategoryItem(
                id = 2,
                name = BBLocalization.Current.Get(key = "18101507-39f0-482d-80e7-491992e2915b", fallback = "Elektronik"),
                iconText = "EL",
                productCount = 9350,
                subCategoryCount = 28
            ),
            RetailSearchCategoryItem(
                id = 3,
                name = BBLocalization.Current.Get(key = "24959041-b704-404d-9576-4a3674a4c3a4", fallback = "Ev & Yaşam"),
                iconText = "EV",
                productCount = 12680,
                subCategoryCount = 42
            )
        ),
        brands = listOf(
            RetailSearchBrandItem(
                id = 1,
                name = "Ortobella",
                logoText = "OR",
                productCount = 248,
                storeCount = 3
            ),
            RetailSearchBrandItem(
                id = 2,
                name = "Urban Touch",
                logoText = "UT",
                productCount = 184,
                storeCount = 2
            ),
            RetailSearchBrandItem(
                id = 3,
                name = BBLocalization.Current.Get(key = "92577d95-6c19-466b-8c10-6562318f8cca", fallback = "Tekno Viva"),
                logoText = "TV",
                productCount = 96,
                storeCount = 4
            )
        ),
        stores = listOf(
            RetailSearchStoreItem(
                id = 1,
                name = "Ortobella Store",
                logoText = "OS",
                productCount = 248,
                ratingText = "4.8"
            ),
            RetailSearchStoreItem(
                id = 2,
                name = "Moda Nova",
                logoText = "MN",
                productCount = 392,
                ratingText = "4.6"
            ),
            RetailSearchStoreItem(
                id = 3,
                name = BBLocalization.Current.Get(key = "4974118f-ecd1-486b-a149-c967127479a9", fallback = "Tekno Sepet"),
                logoText = "TS",
                productCount = 511,
                ratingText = "4.7"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    MaterialTheme {
        SearchScreen()
    }
}


