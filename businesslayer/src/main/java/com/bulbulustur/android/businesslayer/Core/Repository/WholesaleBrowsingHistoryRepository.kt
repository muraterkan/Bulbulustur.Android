package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBrowsingHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBrowsingHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBrowsingHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleBrowsingHistoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleBrowsingHistoryRepository {

    override suspend fun GetWholesaleBrowsingHistoryListAsync(): Result<List<WholesaleBrowsingHistoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleBrowsingHistoryListAsync"
        )
    }

    override suspend fun GetWholesaleBrowsingHistoryByIdAsync(
        wholesaleBrowsingHistoryId: Int
    ): Result<WholesaleBrowsingHistoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleBrowsingHistoryByIdAsync",
            query = "wholesaleBrowsingHistoryId=$wholesaleBrowsingHistoryId"
        )
    }

    override suspend fun GetWholesaleBrowsingHistoryByIdExtendedAsync(
        wholesaleBrowsingHistoryId: Int
    ): Result<WholesaleBrowsingHistoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleBrowsingHistoryByIdExtendedAsync",
            query = "wholesaleBrowsingHistoryId=$wholesaleBrowsingHistoryId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleBrowsingHistoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleBrowsingHistoryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleBrowsingHistoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleBrowsingHistoryId=$wholesaleBrowsingHistoryId"
        )
    }
}