package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandCategoryMapDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandCategoryMapRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductBrandCategoryMapRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductBrandCategoryMapRepository {

    override suspend fun GetProductBrandCategoryMaps(
        productCategoryId: Int,
        count: Int
    ): Result<List<ProductBrandCategoryMapDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.PRODUCT_BRAND_BASE_URL,
            method = "ProductBrandCategoryMap/GetProductBrandCategoryMaps",
            query = "productCategoryId=$productCategoryId&count=$count"
        )
    }
}