package com.bulbulustur.android.businesslayer.Core.Repository

import java.net.URLEncoder

import com.bulbulustur.android.businesslayer.Core.DTO.B2BProductDataDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList

class WholesaleProductRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductRepository {

    override suspend fun GetProductDataAsync(languageId: Int, productCategoryId: Int, page: Int, pageSize: Int, sortOrder: String, brandIds: String): Result<B2BProductDataDTO> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_BASE_URL,
            method = "GetProductDataAsync",
            query = "languageId=$languageId&productCategoryId=$productCategoryId&page=$page&pageSize=$pageSize&sortOrder=$sortOrder&brandIds=$brandIds"
        )
    }

    override suspend fun GetProductByIdExtendedAsync(languageId: Int, wholesaleProductId: Int): Result<WholesaleProductDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_BASE_URL,
            method = "GetProductByIdExtendedAsync",
            query = "languageId=$languageId&wholesaleProductId=$wholesaleProductId"
        )
    }

    override suspend fun GetProductRelatedsAsync(languageId: Int, wholesaleProductId: Int, count: Int): Result<List<WholesaleProductRelatedDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_BASE_URL,
            method = "GetProductRelatedsAsync",
            query = "languageId=$languageId&wholesaleProductId=$wholesaleProductId&count=$count"
        )
    }

    override suspend fun GetSearchingProductsAsync(companyId: Int, key: String, page: Int, pageSize: Int, sortOrder: String): Result<PaginatedList<WholesaleProductDTO>> {
        val encodedKey = URLEncoder.encode(key.trim(), "UTF-8")
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_BASE_URL,
            method = "GetSearchingProductsAsync",
            query = "companyId=$companyId&key=$encodedKey&page=$page&pageSize=$pageSize&sortOrder=$sortOrder"
        )
    }
}
