package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleMessageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleMessageRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleMessageRepository {

    override suspend fun GetWholesaleMessageListAsync(): Result<List<WholesaleMessageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageListAsync"
        )
    }

    override suspend fun GetWholesaleMessageByIdAsync(
        wholesaleMessageId: Int
    ): Result<WholesaleMessageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageByIdAsync",
            query = "wholesaleMessageId=$wholesaleMessageId"
        )
    }

    override suspend fun GetWholesaleMessageByIdExtendedAsync(
        wholesaleMessageId: Int
    ): Result<WholesaleMessageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageByIdExtendedAsync",
            query = "wholesaleMessageId=$wholesaleMessageId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleMessageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleMessageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleMessageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleMessageId=$wholesaleMessageId"
        )
    }
}