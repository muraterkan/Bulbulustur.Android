package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrowsingHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrowsingHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList

class ProductBrowsingHistoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductBrowsingHistoryRepository {

    override suspend fun GetProductBrowsingHistoriesAsync(
        memberId: Int,
        page: Int,
        pageSize: Int
    ):  Result<PaginatedList<ProductBrowsingHistoryDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.B2C_PRODUCT_BROWSING_HISTORY_BASE_URL,
            method =
                "GetProductBrowsingHistoriesAsync",
            query =
                "memberId=$memberId" +
                        "&page=$page" +
                        "&pageSize=$pageSize"
        )
    }

    override suspend fun InsertProductBrowsingHistoryAsync(
        memberId: Int,
        model: ProductBrowsingHistoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.B2C_PRODUCT_BROWSING_HISTORY_BASE_URL,
            method =
                "InsertProductBrowsingHistoryAsync",
            query =
                "memberId=$memberId",
            data =
                model
        )
    }

    override suspend fun DeleteProductBrowsingHistoryAsync(
        memberId: Int,
        browsingHistoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl =
                ApiRoutes.B2C_PRODUCT_BROWSING_HISTORY_BASE_URL,
            method =
                "DeleteProductBrowsingHistory",
            query =
                "memberId=$memberId" +
                        "&browsingHistoryId=$browsingHistoryId"
        )
    }

    override suspend fun DeleteAllProductBrowsingHistoriesAsync(
        memberId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl =
                ApiRoutes.B2C_PRODUCT_BROWSING_HISTORY_BASE_URL,
            method =
                "DeleteAllProductBrowsingHistories",
            query =
                "memberId=$memberId"
        )
    }
}