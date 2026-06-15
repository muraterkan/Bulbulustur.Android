package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignConditionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignConditionRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignConditionInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignConditionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CampaignConditionRepository(
    private val apiClient: ApiClient = ApiClient
) : ICampaignConditionRepository {

    override suspend fun GetCampaignConditionListAsync(): Result<List<CampaignConditionDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignConditionListAsync"
        )
    }

    override suspend fun GetCampaignConditionByIdAsync(
        campaignConditionId: Int
    ): Result<CampaignConditionUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignConditionByIdAsync",
            query = "campaignConditionId=$campaignConditionId"
        )
    }

    override suspend fun GetCampaignConditionByIdExtendedAsync(
        campaignConditionId: Int
    ): Result<CampaignConditionDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignConditionByIdExtendedAsync",
            query = "campaignConditionId=$campaignConditionId"
        )
    }

    override suspend fun InsertAsync(
        model: CampaignConditionInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CampaignConditionUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        campaignConditionId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "campaignConditionId=$campaignConditionId"
        )
    }
}