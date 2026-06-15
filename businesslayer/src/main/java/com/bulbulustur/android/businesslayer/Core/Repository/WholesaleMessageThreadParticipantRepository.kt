package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageThreadParticipantDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageThreadParticipantRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleMessageThreadParticipantInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageThreadParticipantUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleMessageThreadParticipantRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleMessageThreadParticipantRepository {

    override suspend fun GetWholesaleMessageThreadParticipantListAsync(): Result<List<WholesaleMessageThreadParticipantDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageThreadParticipantListAsync"
        )
    }

    override suspend fun GetWholesaleMessageThreadParticipantByIdAsync(
        wholesaleMessageThreadParticipantId: Int
    ): Result<WholesaleMessageThreadParticipantUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageThreadParticipantByIdAsync",
            query = "wholesaleMessageThreadParticipantId=$wholesaleMessageThreadParticipantId"
        )
    }

    override suspend fun GetWholesaleMessageThreadParticipantByIdExtendedAsync(
        wholesaleMessageThreadParticipantId: Int
    ): Result<WholesaleMessageThreadParticipantDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageThreadParticipantByIdExtendedAsync",
            query = "wholesaleMessageThreadParticipantId=$wholesaleMessageThreadParticipantId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleMessageThreadParticipantInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleMessageThreadParticipantUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleMessageThreadParticipantId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleMessageThreadParticipantId=$wholesaleMessageThreadParticipantId"
        )
    }
}