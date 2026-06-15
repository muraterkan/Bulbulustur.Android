package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageThreadDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageThreadRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleMessageThreadInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageThreadUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleMessageThreadRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleMessageThreadRepository {

    override suspend fun GetWholesaleMessageThreadListAsync(): Result<List<WholesaleMessageThreadDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageThreadListAsync"
        )
    }

    override suspend fun GetWholesaleMessageThreadByIdAsync(
        wholesaleMessageThreadId: Int
    ): Result<WholesaleMessageThreadUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageThreadByIdAsync",
            query = "wholesaleMessageThreadId=$wholesaleMessageThreadId"
        )
    }

    override suspend fun GetWholesaleMessageThreadByIdExtendedAsync(
        wholesaleMessageThreadId: Int
    ): Result<WholesaleMessageThreadDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageThreadByIdExtendedAsync",
            query = "wholesaleMessageThreadId=$wholesaleMessageThreadId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleMessageThreadInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleMessageThreadUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleMessageThreadId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleMessageThreadId=$wholesaleMessageThreadId"
        )
    }
}