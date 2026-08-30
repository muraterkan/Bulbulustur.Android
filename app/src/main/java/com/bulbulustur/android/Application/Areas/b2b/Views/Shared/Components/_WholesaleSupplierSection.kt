package com.bulbulustur.android.Application.Areas.b2b.Views.Shared.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBIcon
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySupplierDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySupplierSomeProductDTO

@Composable
fun WholesaleSupplierSection(
    suppliers: List<WholesaleProductCategorySupplierDTO>,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    onSupplierClick: (WholesaleProductCategorySupplierDTO) -> Unit = {},
    onProductClick: (WholesaleProductCategorySupplierSomeProductDTO) -> Unit = {}
) {
    val validSuppliers = suppliers
        .filter {
            it.WholesaleProductCategorySupplierId > 0 &&
                    it.CompanyId > 0
        }
        .distinctBy {
            it.WholesaleProductCategorySupplierId
        }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.Space4
        )
    ) {
        WholesaleSupplierSectionHeader()

        when {
            isLoading && validSuppliers.isEmpty() -> {
                WholesaleSupplierSectionLoading()
            }

            validSuppliers.isEmpty() -> {
                WholesaleSupplierSectionEmpty()
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(
                        BBSpacing.CardGap
                    )
                ) {
                    validSuppliers.forEach { supplier ->
                        WholesaleSupplierCard(
                            supplier = supplier,
                            onSupplierClick = onSupplierClick,
                            onProductClick = onProductClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WholesaleSupplierSectionHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            BBSpacing.SectionHeaderGap
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.IconTextGap
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(
                    end = BBSpacing.Space1
                )
            )

            Text(
                text = "Öne Çıkan Tedarikçiler",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Bu kategoride öne çıkan tedarikçileri ve ürünlerini keşfedin.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun WholesaleSupplierSectionLoading() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.Space6
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                strokeWidth = BBSpacing.ProgressStroke
            )
        }
    }
}

@Composable
private fun WholesaleSupplierSectionEmpty() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = BBRadius.XlShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = BBSpacing.Hairline,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    BBSpacing.Space5
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                BBSpacing.IconTextGap
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Bu kategori için öne çıkan tedarikçi bulunamadı.",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}