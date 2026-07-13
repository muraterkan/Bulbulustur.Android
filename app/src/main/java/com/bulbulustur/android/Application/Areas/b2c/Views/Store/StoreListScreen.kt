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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bulbulustur.android.Application.Views.Shared.Components.BbInnerPageHeader
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCard
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardPadding
import com.bulbulustur.android.Application.wwwroot.DesignObjects.BbCardVariant
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.DTO.StoreDTO
import java.util.Locale

@Composable
fun StoreListScreen(
    stores: List<StoreDTO> = emptyList(),
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onBackClick: () -> Unit = {},
    onStoreClick: (Int) -> Unit = {},
    onSellerInfoClick: () -> Unit = {},
    onHowItWorksClick: () -> Unit = onSellerInfoClick
) {
    val items = remember(stores) {
        stores.map { store ->
            store.ToStoreListItem()
        }
    }

    val alphabetFilters = remember {
        listOf(
            "Tümü",
            "0-9",
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "İ",
            "K",
            "M",
            "O",
            "P",
            "S",
            "T",
            "U",
            "V",
            "Z"
        )
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedAlphabetFilter by remember {
        mutableStateOf("Tümü")
    }

    val filteredStores = remember(searchText, selectedAlphabetFilter, items) {
        val searchFilteredStores = if (searchText.isBlank()) {
            items
        } else {
            items.filter { store ->
                store.name.contains(searchText, ignoreCase = true) ||
                        store.description.contains(searchText, ignoreCase = true) ||
                        store.categoryName.contains(searchText, ignoreCase = true)
            }
        }

        when (selectedAlphabetFilter) {
            "Tümü" -> searchFilteredStores
            "0-9" -> searchFilteredStores.filter { store ->
                store.name.firstOrNull()?.isDigit() == true
            }
            else -> searchFilteredStores.filter { store ->
                store.name.startsWith(selectedAlphabetFilter, ignoreCase = true)
            }
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
                    onSearchTextChange = {
                        searchText = it
                    }
                )
            }

            item {
                StoreAlphabetFilterRow(
                    filters = alphabetFilters,
                    selectedFilter = selectedAlphabetFilter,
                    onFilterClick = {
                        selectedAlphabetFilter = it
                    }
                )
            }

            item {
                StoreListResultHeader(
                    storeCount = filteredStores.size,
                    selectedFilter = selectedAlphabetFilter
                )
            }

            when {
                isLoading && items.isEmpty() -> {
                    item {
                        StoreListLoadingState()
                    }
                }

                !errorMessage.isNullOrBlank() && items.isEmpty() -> {
                    item {
                        StoreListErrorState(
                            message = errorMessage
                        )
                    }
                }

                filteredStores.isEmpty() -> {
                    item {
                        StoreListEmptyState(
                            onSellerInfoClick = onSellerInfoClick
                        )
                    }
                }

                else -> {
                    items(
                        items = filteredStores,
                        key = { store ->
                            store.id
                        }
                    ) { store ->
                        StoreListCard(
                            store = store,
                            onClick = {
                                onStoreClick(store.id)
                            }
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
}

@Composable
private fun StoreListHeaderActionButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable {
            onClick()
        },
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space2
            ),
            horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space1),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(BBIcon.SizeSm)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary,
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
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            StoreListStatusPill(
                text = "Bulbulustur Mağazaları"
            )

            Text(
                text = "Güvenilir Mağazaları Keşfedin",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Perakende alışveriş için ürün, mağaza puanı ve mağaza detaylarını tek akışta inceleyin.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            StoreListPrimaryButton(
                text = "Nasıl Çalışır?",
                icon = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                modifier = Modifier.fillMaxWidth(),
                onClick = onHowItWorksClick
            )
        }
    }
}

@Composable
private fun StoreListSearchCard(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    BbCard(
        modifier = Modifier.fillMaxWidth(),
        variant = BbCardVariant.Outlined,
        padding = BbCardPadding.Medium
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = onSearchTextChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = "Mağaza Adı Ara..."
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                )
            },
            shape = BBRadius.LgShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
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
            Surface(
                modifier = Modifier.clickable {
                    onFilterClick(filter)
                },
                shape = BBRadius.PillShape,
                color = if (selectedFilter == filter) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surface
                },
                border = BorderStroke(
                    width = 1.dp,
                    color = if (selectedFilter == filter) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.outlineVariant
                    }
                )
            ) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = BBSpacing.Space3,
                        vertical = BBSpacing.Space2
                    ),
                    text = filter,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedFilter == filter) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun StoreListResultHeader(
    storeCount: Int,
    selectedFilter: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
    ) {
        Text(
            text = "$storeCount mağaza listeleniyor",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = if (selectedFilter == "Tümü") {
                "Tüm mağazalar gösteriliyor."
            } else {
                "$selectedFilter filtresi uygulanıyor."
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
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(BBSpacing.Space3)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(BBIcon.BoxLg),
                    shape = BBRadius.XlShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = store.logoText,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(BBSpacing.Space1)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(BBSpacing.Space2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = store.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )

                        if (store.isVerified) {
                            Icon(
                                imageVector = Icons.Outlined.Verified,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(BBIcon.SizeSm)
                            )
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
                StoreListMetaPill(text = store.ratingText)
                StoreListMetaPill(text = store.categoryName)
                StoreListMetaPill(text = store.cargoText)
            }

            StoreListPrimaryButton(
                text = "Mağazayı İncele",
                icon = Icons.Outlined.Storefront,
                modifier = Modifier.fillMaxWidth(),
                onClick = onClick
            )
        }
    }
}

@Composable
private fun StoreListLoadingState() {
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
            CircularProgressIndicator()

            Text(
                text = "Mağazalar yükleniyor...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun StoreListErrorState(
    message: String
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
            StoreListStatusPill(text = "Hata")

            Text(
                text = "Mağazalar alınamadı",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
            StoreListStatusPill(text = "Mağaza Bulunamadı")

            Text(
                text = "Listelenecek Mağaza Bulunmuyor",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Bu filtrede mağaza bulunamadı. Satıcı olmak istiyorsanız başvuru süreci web panelinde tamamlanır.",
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
                        tint = MaterialTheme.colorScheme.onPrimary,
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
                    text = "Başvuru ve mağaza yönetimi web paneli üzerinden tamamlanır.",
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
private fun StoreListStatusPill(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space3,
                vertical = BBSpacing.Space1
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun StoreListMetaPill(
    text: String
) {
    Surface(
        shape = BBRadius.PillShape,
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = BBSpacing.Space2,
                vertical = BBSpacing.Space1
            ),
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.SemiBold
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
        modifier = modifier.clickable {
            onClick()
        },
        shape = BBRadius.LgShape,
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = BBSpacing.Space4,
                    vertical = BBSpacing.Space3
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(BBIcon.SizeMd)
            )

            Spacer(
                modifier = Modifier.size(BBSpacing.Space2)
            )

            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Immutable
private data class StoreListItem(
    val id: Int,
    val name: String,
    val logoText: String,
    val description: String,
    val categoryName: String,
    val ratingText: String,
    val cargoText: String,
    val isVerified: Boolean
)

private fun StoreDTO.ToStoreListItem(): StoreListItem {
    val resolvedName = StoreName.ifBlank {
        "Mağaza"
    }

    return StoreListItem(
        id = StoreId,
        name = resolvedName,
        logoText = resolvedName.take(2).uppercase(),
        description = StoreDescription.ifBlank {
            "Mağaza vitrini"
        },
        categoryName = if (CompanyId > 0) {
            "Kurumsal Mağaza"
        } else {
            "Perakende Mağaza"
        },
        ratingText = if (Rating > 0.0) {
            String.format(Locale.US, "%.1f", Rating)
        } else {
            "-"
        },
        cargoText = if (DefaultEstimatedShippingTime > 0) {
            "${DefaultEstimatedShippingTime} gün"
        } else {
            "Standart"
        },
        isVerified = !StoreKey.isNullOrBlank() || CompanyId > 0
    )
}

@Preview(showBackground = true)
@Composable
private fun StoreListScreenPreview() {
    BbTheme {
        StoreListScreen()
    }
}
