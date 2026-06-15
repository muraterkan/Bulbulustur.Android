package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CampaignRepository(
    private val apiClient: ApiClient = ApiClient
) : ICampaignRepository {

    override suspend fun GetCampaignListAsync(): Result<List<CampaignDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignListAsync"
        )
    }

    override suspend fun GetCampaignByIdAsync(
        campaignId: Int
    ): Result<CampaignUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignByIdAsync",
            query = "campaignId=$campaignId"
        )
    }

    override suspend fun GetCampaignByIdExtendedAsync(
        campaignId: Int
    ): Result<CampaignDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignByIdExtendedAsync",
            query = "campaignId=$campaignId"
        )
    }

    override suspend fun InsertAsync(
        model: CampaignInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CampaignUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        campaignId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "campaignId=$campaignId"
        )
    }
}