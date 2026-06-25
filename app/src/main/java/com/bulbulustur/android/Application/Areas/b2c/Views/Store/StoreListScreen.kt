package com.bulbulustur.android.Application.Areas.b2c.Views.Store

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBColors
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme

@Composable
fun StoreListScreen(
    onBackClick: () -> Unit = {},
    onStoreClick: (StoreListItem) -> Unit = {},
    onSellerInfoClick: () -> Unit = {},
    onHowItWorksClick: () -> Unit = onSellerInfoClick
) {
    val stores = remember { getStoreListItems() }

    val alphabetFilters = remember {
        listOf("Tümü", "0-9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "İ", "K", "M", "O", "P", "S", "T", "U", "V", "Z")
    }

    var searchText by remember { mutableStateOf("") }
    var selectedAlphabetFilter by remember { mutableStateOf("Tümü") }

    val filteredStores = remember(searchText, selectedAlphabetFilter, stores) {
        val searchFilteredStores = if (searchText.isBlank()) {
            stores
        } else {
            stores.filter {
                it.name.contains(searchText, ignoreCase = true) ||
                        it.description.contains(searchText, ignoreCase = true) ||
                        it.categoryName.contains(searchText, ignoreCase = true)
            }
        }

        when (selectedAlphabetFilter) {
            "Tümü" -> searchFilteredStores
            "0-9" -> searchFilteredStores.filter { it.name.firstOrNull()?.isDigit() == true }
            else -> searchFilteredStores.filter { it.name.startsWith(selectedAlphabetFilter, ignoreCase = true) }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            BbInnerPageHeader(
                title = "Mağazalar",
                onBackClick = onBackClick,
                actionContent = {
                    StoreListHeaderActionButton(
                        text = "Satıcı Ol",
                        icon = Icons.Outlined.Storefront,
                        onClick = onSellerInfoClick
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(innerPadding)
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                start = BBSpacing.PageHorizontal,
                top = BBSpacing.SectionGapCompact,
                end = BBSpacing.PageHorizontal,
                bottom = BBSpacing.PageBottom
            ),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.CardGap)
        ) {
            item {
                StoreListHeroCard(
                    onHowItWorksClick = onHowItWorksClick
                )
            }

            item {
                StoreListSearchCard(
                    searchText = searchText,
                    onSearchTextChange = { searchText = it }
                )
            }

            item {
                StoreAlphabetFilterRow(
                    filters = alphabetFilters,
                    selectedFilter = selectedAlphabetFilter,
                    onFilterClick = { selectedAlphabetFilter = it }
                )
            }

            item {
                StoreListResultHeader(
                    storeCount = filteredStores.size,
                    selectedFilter = selectedAlphabetFilter
                )
            }

            if (filteredStores.isEmpty()) {
                item {
                    StoreListEmptyState(
                        onSellerInfoClick = onSellerInfoClick
                    )
                }
            } else {
                items(
                    items = filteredStores,
                    key = { store -> store.id }
                ) { store ->
                    StoreListCard(
                        store = store,
                        onClick = { onStoreClick(store) }
                    )
                }

                item {
                    StoreListSellerInfoBanner(
                        onSellerInfoClick = onSellerInfoClick
                    )
                }
            }
        }
    }
}

@Composable
private fun StoreListHeaderActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(BBSpacing.Space10)
            .clip(BBRadius.PillShape)
            .clickable { onClick() },
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primary,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun StoreListHeroCard(
    onHowItWorksClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            StoreListStatusPill(text = "MaĞaza Rehberi")

            Text(
                text = "Bulbulustur Mağazalarını Keşfet",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Doğrulanmış satıcıları, favori mağazaları ve maĞaza vitrinlerindeki ürünleri tek yerden inceleyin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            StoreListSecondaryButton(
                text = "Satıcı Başvurusu Nasıl Çalışır?",
                modifier = Modifier.fillMaxWidth(),
                onClick = onHowItWorksClick
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                StoreListHeroMiniCard(
                    modifier = Modifier.weight(1f),
                    title = "Güvenilir Satıcılar",
                    subtitle = "Doğrulanmış maĞaza vitrinlerini inceleyin.",
                    icon = Icons.Outlined.Verified
                )

                StoreListHeroMiniCard(
                    modifier = Modifier.weight(1f),
                    title = "Favori Mağazalar",
                    subtitle = "BeĞendiĞiniz mağazaları takip edin.",
                    icon = Icons.Outlined.FavoriteBorder
                )
            }
        }
    }
}

@Composable
private fun StoreListHeroMiniCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector
) {
    Surface(
        modifier = modifier,
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(BBSpacing.Space3),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
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

@Composable
private fun StoreListSearchCard(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(BBSpacing.Space2),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            placeholder = {
                Text(
                    text = "MaĞaza, kategori veya ürün ara",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            shape = BBRadius.Input,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

@Composable
private fun StoreAlphabetFilterRow(
    filters: List<String>,
    selectedFilter: String,
    onFilterClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
    ) {
        filters.forEach { filter ->
            StoreAlphabetChip(
                text = filter,
                selected = selectedFilter == filter,
                onClick = { onFilterClick(filter) }
            )
        }
    }
}

@Composable
private fun StoreAlphabetChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(BBRadius.PillShape)
            .clickable { onClick() },
        shape = BBRadius.PillShape,
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun StoreListResultHeader(
    storeCount: Int,
    selectedFilter: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = "MaĞaza Listesi",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = if (selectedFilter == "Tümü") {
                "$storeCount maĞaza listeleniyor"
            } else {
                "$selectedFilter filtresinde $storeCount maĞaza"
            },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StoreListCard(
    store: StoreListItem,
    onClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space4)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
            ) {
                Surface(
                    modifier = Modifier.size(BBIcon.Box2Xl),
                    shape = BBRadius.XlShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    border = BorderStroke(
                        width = BBSpacing.Divider,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = store.logoText,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
                    ) {
                        Text(
                            text = store.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1
                        )

                        if (store.isVerified) {
                            StoreListMiniBadge(text = "Doğrulanmış")
                        }
                    }

                    Text(
                        text = store.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(BBIcon.SizeMd)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2)
            ) {
                StoreListMetaPill(text = "${store.productCount} Ürün")
                StoreListMetaPill(text = store.ratingText)
                StoreListMetaPill(text = store.categoryName)
            }

            StoreListPrimaryButton(
                text = "MaĞazayı İncele",
                icon = Icons.Outlined.Storefront,
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick
            )
        }
    }
}

@Composable
private fun StoreListEmptyState(
    onSellerInfoClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxLg),
                shape = BBRadius.XlShape,
                color = MaterialTheme.colorScheme.surfaceVariant,
                border = BorderStroke(
                    width = BBSpacing.Divider,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "âˆ…",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            StoreListStatusPill(text = "MaĞaza Bulunamadı")

            Text(
                text = "Listelenecek MaĞaza Bulunmuyor",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bu filtrede maĞaza bulunamadı. Satıcı olmak istiyorsanız başvuru süreci web panelinde tamamlanır.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            StoreListPrimaryButton(
                text = "Satıcı Başvurusu Hakkında",
                icon = Icons.Outlined.Storefront,
                modifier = Modifier.fillMaxWidth(),
                onClick = onSellerInfoClick
            )
        }
    }
}

@Composable
private fun StoreListSellerInfoBanner(
    onSellerInfoClick: () -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium,
        onClick = onSellerInfoClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Surface(
                modifier = Modifier.size(BBIcon.BoxMd),
                shape = BBRadius.LgShape,
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Storefront,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(BBIcon.SizeMd)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
            ) {
                Text(
                    text = "Satıcı olmak ister misiniz?",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Başvuru ve maĞaza yönetimi web paneli üzerinden tamamlanır.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(BBIcon.SizeMd)
            )
        }
    }
}

@Composable
private fun StoreListStatusPill(text: String) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun StoreListMiniBadge(text: String) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
        ) {
            Icon(
                imageVector = Icons.Outlined.Verified,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(BBIcon.Size2Xs)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun StoreListMetaPill(text: String) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun StoreListPrimaryButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(BBSpacing.Space11)
            .clip(BBRadius.PillShape)
            .clickable { onClick() },
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primary,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Spacer(modifier = Modifier.width(BBSpacing.Space1))

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun StoreListSecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(BBSpacing.Space11)
            .clip(BBRadius.PillShape)
            .clickable { onClick() },
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = BBSpacing.Divider,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Immutable
data class StoreListItem(
    val id: Int,
    val name: String,
    val description: String,
    val logoText: String,
    val categoryName: String,
    val productCount: Int,
    val ratingText: String,
    val isVerified: Boolean
)

private fun getStoreListItems(): List<StoreListItem> {
    return listOf(
        StoreListItem(
            id = 1,
            name = "Ortobella Comfort",
            description = "Ayakkabı ve günlük konfor ürünleri maĞazası.",
            logoText = "OC",
            categoryName = "Ayakkabı",
            productCount = 48,
            ratingText = "â˜… 4.8",
            isVerified = true
        ),
        StoreListItem(
            id = 2,
            name = "Moda Nova",
            description = "Giyim, basic ürünler ve sezonluk parçalar.",
            logoText = "MN",
            categoryName = "Giyim",
            productCount = 124,
            ratingText = "â˜… 4.6",
            isVerified = true
        ),
        StoreListItem(
            id = 3,
            name = "Urban Touch",
            description = "Çanta, aksesuar ve şehir yaşamı ürünleri.",
            logoText = "UT",
            categoryName = "Aksesuar",
            productCount = 72,
            ratingText = "â˜… 4.5",
            isVerified = false
        ),
        StoreListItem(
            id = 4,
            name = "Casa Liva",
            description = "Ev, yaşam ve mutfak düzenleyici ürünleri.",
            logoText = "CL",
            categoryName = "Ev & Yaşam",
            productCount = 96,
            ratingText = "â˜… 4.7",
            isVerified = true
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun StoreListScreenPreview() {
    BbTheme {
        StoreListScreen()
    }
}
