package com.bulbulustur.app.features.retail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryListScreen(
    onCategoryClick: (RetailCategoryListItem) -> Unit = {},
    onSearchClick: (String) -> Unit = {}
) {
    val categories = remember {
        getRetailCategoryListItems()
    }

    var searchText by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 18.dp,
                end = 16.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                CategoryListHeader()
            }

            item {
                CategorySearchBox(
                    searchText = searchText,
                    onSearchTextChange = {
                        searchText = it
                    },
                    onSearchClick = {
                        onSearchClick(searchText)
                    }
                )
            }

            item {
                FeaturedCategoryChips(
                    categories = categories.filter { it.isFeatured }
                )
            }

            item {
                Text(
                    text = "Tüm kategoriler",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            items(categories) { category ->
                CategoryListCard(
                    category = category,
                    onClick = {
                        onCategoryClick(category)
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryListHeader() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Perakende kategorileri",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Ürünleri kategori, marka ve kampanya akışına göre hızlıca keşfedin.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun CategorySearchBox(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp)),
        placeholder = {
            Text(text = "Kategori, ürün veya marka ara")
        },
        singleLine = true,
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            Text(
                text = "Ara",
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clickable {
                        onSearchClick()
                    },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }
    )
}

@Composable
private fun FeaturedCategoryChips(
    categories: List<RetailCategoryListItem>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Hızlı keşif",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                AssistChip(
                    onClick = {},
                    label = {
                        Text(text = category.name)
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryListCard(
    category: RetailCategoryListItem,
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
            CategoryIconBubble(
                iconText = category.iconText
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CategoryMetaPill(
                        text = "${category.productCount} ürün"
                    )

                    if (category.hasLandingPage) {
                        CategoryMetaPill(
                            text = "Vitrin"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CategoryIconBubble(
    iconText: String
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = iconText,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun CategoryMetaPill(
    text: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                horizontal = 9.dp,
                vertical = 4.dp
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

data class RetailCategoryListItem(
    val id: Int,
    val name: String,
    val description: String,
    val iconText: String,
    val productCount: Int,
    val isFeatured: Boolean,
    val hasLandingPage: Boolean
)

private fun getRetailCategoryListItems(): List<RetailCategoryListItem> {
    return listOf(
        RetailCategoryListItem(
            id = 1,
            name = "Moda",
            description = "Giyim, ayakkabı, çanta ve aksesuar ürünleri.",
            iconText = "MO",
            productCount = 18420,
            isFeatured = true,
            hasLandingPage = true
        ),
        RetailCategoryListItem(
            id = 2,
            name = "Elektronik",
            description = "Telefon, bilgisayar, aksesuar ve akıllı cihazlar.",
            iconText = "EL",
            productCount = 9350,
            isFeatured = true,
            hasLandingPage = true
        ),
        RetailCategoryListItem(
            id = 3,
            name = "Ev & Yaşam",
            description = "Ev tekstili, dekorasyon, mutfak ve yaşam ürünleri.",
            iconText = "EV",
            productCount = 12680,
            isFeatured = true,
            hasLandingPage = true
        ),
        RetailCategoryListItem(
            id = 4,
            name = "Anne & Bebek",
            description = "Bebek bakım, oyuncak, tekstil ve güvenlik ürünleri.",
            iconText = "AB",
            productCount = 4720,
            isFeatured = true,
            hasLandingPage = true
        ),
        RetailCategoryListItem(
            id = 5,
            name = "Kozmetik",
            description = "Cilt bakımı, saç bakımı, makyaj ve kişisel bakım.",
            iconText = "KO",
            productCount = 7820,
            isFeatured = true,
            hasLandingPage = true
        ),
        RetailCategoryListItem(
            id = 6,
            name = "Spor & Outdoor",
            description = "Spor ekipmanları, outdoor ürünleri ve aktif yaşam.",
            iconText = "SP",
            productCount = 3910,
            isFeatured = false,
            hasLandingPage = false
        ),
        RetailCategoryListItem(
            id = 7,
            name = "Kitap & Hobi",
            description = "Kitap, kırtasiye, oyuncak, koleksiyon ve hobi ürünleri.",
            iconText = "KH",
            productCount = 2160,
            isFeatured = false,
            hasLandingPage = false
        ),
        RetailCategoryListItem(
            id = 8,
            name = "Oto Aksesuar",
            description = "Araç içi aksesuar, bakım ve pratik kullanım ürünleri.",
            iconText = "OA",
            productCount = 1880,
            isFeatured = false,
            hasLandingPage = false
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CategoryListScreenPreview() {
    MaterialTheme {
        CategoryListScreen()
    }
}