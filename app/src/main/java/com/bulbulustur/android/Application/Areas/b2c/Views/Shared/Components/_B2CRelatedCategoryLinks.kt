package com.bulbulustur.android.Application.Areas.b2c.Views.Shared.Components

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBRadius
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO

@Composable
fun _B2CRelatedCategoryLinks(
    Categories: List<ProductCategoryDTO>,
    onCategoryClick: (ProductCategoryDTO) -> Unit
) {
    if (
        Categories.isEmpty()
    ) {
        return
    }

    Column(
        modifier =
            Modifier.padding(
                top =
                    BBSpacing.Space3
            ),
        verticalArrangement =
            Arrangement.spacedBy(
                BBSpacing.Space2
            )
    ) {
        _RetailSectionTitle(
            title =
                BBLocalization.Current.Get(key = "e675f152-9fda-4647-aca2-0376e996a3f5", fallback = "İlgili Kategoriler")
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        horizontal =
                            BBSpacing.PageHorizontal
                    ),
            horizontalArrangement =
                Arrangement.spacedBy(
                    BBSpacing.Space2
                )
        ) {
            Categories.forEach { category ->
                val categoryName =
                    category.CategoryName
                        .takeIf {
                            it.isNotBlank()
                        }
                        ?: return@forEach

                Surface(
                    modifier =
                        Modifier
                            .clickable {
                                onCategoryClick(
                                    category
                                )
                            },
                    shape =
                        BBRadius.PillShape,
                    color =
                        MaterialTheme.colorScheme.surface
                ) {
                    Text(
                        modifier =
                            Modifier.padding(
                                horizontal =
                                    BBSpacing.Space3,
                                vertical =
                                    BBSpacing.Space2
                            ),
                        text =
                            categoryName,
                        style =
                            MaterialTheme.typography.labelMedium,
                        color =
                            MaterialTheme.colorScheme.onSurface,
                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun _RetailSectionTitle(
    title: String
) {
    Text(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal =
                        BBSpacing.PageHorizontal
                ),
        text =
            title,
        style =
            MaterialTheme.typography.titleMedium,
        color =
            MaterialTheme.colorScheme.onSurface,
        fontWeight =
            FontWeight.Bold
    )
}