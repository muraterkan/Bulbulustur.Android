package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrowsingHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrowsingHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrowsingHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductBrowsingHistoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductBrowsingHistoryRepository {

    override suspend fun GetProductBrowsingHistoryListAsync(): Result<List<ProductBrowsingHistoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrowsingHistoryListAsync"
        )
    }

    override suspend fun GetProductBrowsingHistoryByIdAsync(
        productBrowsingHistoryId: Int
    ): Result<ProductBrowsingHistoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrowsingHistoryByIdAsync",
            query = "productBrowsingHistoryId=$productBrowsingHistoryId"
        )
    }

    override suspend fun GetProductBrowsingHistoryByIdExtendedAsync(
        productBrowsingHistoryId: Int
    ): Result<ProductBrowsingHistoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrowsingHistoryByIdExtendedAsync",
            query = "productBrowsingHistoryId=$productBrowsingHistoryId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductBrowsingHistoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductBrowsingHistoryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productBrowsingHistoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productBrowsingHistoryId=$productBrowsingHistoryId"
        )
    }
}