package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandSectionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandSectionRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductBrandSectionRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductBrandSectionRepository {

    override suspend fun GetProductBrandSectionsAsync(
        languageId: Int,
        count: Int
    ): Result<List<ProductBrandSectionDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.BRAND_PRODUCT_SECTION_BASE_URL,
            method =
                "GetProductBrandSectionsAsync",
            query =
                "languageId=$languageId" +
                        "&count=$count"
        )
    }

    override suspend fun GetProductBrandSectionsAsync(
        languageId: Int,
        brandId: Int,
        count: Int
    ): Result<List<ProductBrandSectionDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.BRAND_PRODUCT_SECTION_BASE_URL,
            method =
                "GetProductBrandSectionsWithBrandIdAsync",
            query =
                "languageId=$languageId" +
                        "&brandId=$brandId" +
                        "&count=$count"
        )
    }
}