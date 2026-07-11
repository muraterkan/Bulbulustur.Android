package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.StoreDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IStoreRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.StoreInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.StoreUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result

class StoreRepository(
    private val apiClient: ApiClient = ApiClient
) : IStoreRepository {

    override suspend fun GetStoresAsync(
        page: Int,
        pageSize: Int
    ): Result<PaginatedList<StoreDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_STORE_BASE_URL,
            method = "GetStoresAsync",
            query = "page=$page&pageSize=$pageSize"
        )
    }

    override suspend fun GetStoreByIdAsync(
        storeId: Int
    ): Result<StoreUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetStoreByIdAsync",
            query = "storeId=$storeId"
        )
    }

    override suspend fun GetStoreByIdExtendedAsync(
        storeId: Int
    ): Result<StoreDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_STORE_BASE_URL,
            method = "GetStoreByIdExtendedAsync",
            query = "storeId=$storeId"
        )
    }

    override suspend fun InsertAsync(
        model: StoreInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: StoreUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        storeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "storeId=$storeId"
        )
    }
}