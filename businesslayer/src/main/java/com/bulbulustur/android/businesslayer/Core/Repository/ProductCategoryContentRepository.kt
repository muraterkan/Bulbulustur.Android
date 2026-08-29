package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryContentRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCategoryContentRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductCategoryContentRepository {

    override suspend fun GetProductCategoryContentsAsync(languageId: Int, productCategoryId: Int, groupCount: Int, productCount: Int): Result<ProductCategoryContentDTO> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_CATEGORY_CONTENT_BASE_URL,
            method = "GetProductCategoryContentsAsync",
            query = "languageId=$languageId&productCategoryId=$productCategoryId&groupCount=$groupCount&productCount=$productCount"
        )
    }

    override suspend fun GetProductCategoryContentsPagedAsync(productCategoryContentGroupId: Int, page: Int, pageSize: Int): Result<PaginatedList<ProductCategoryContentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_CATEGORY_CONTENT_BASE_URL,
            method = "GetProductCategoryContentsPagedAsync",
            query = "productCategoryContentGroupId=$productCategoryContentGroupId&page=$page&pageSize=$pageSize"
        )
    }
}
