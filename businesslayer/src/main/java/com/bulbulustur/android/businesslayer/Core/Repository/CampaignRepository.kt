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

    override suspend fun GetCampaignsAsync(languageId: Int, count: Int): Result<List<CampaignDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.CAMPAIGN_BASE_URL,
            method = "GetCampaignsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetCampaignByIdAsync(campaignId: Int): Result<CampaignUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.CAMPAIGN_BASE_URL,
            method = "GetCampaignByIdAsync",
            query = "campaignId=$campaignId"
        )
    }

    override suspend fun GetCampaignByIdExtendedAsync(languageId: Int, campaignId: Int): Result<CampaignDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.CAMPAIGN_BASE_URL,
            method = "GetCampaignByIdExtendedAsync",
            query = "languageId=$languageId&campaignId=$campaignId"
        )
    }

    override suspend fun GetCampaignsByCategoryAsync(languageId: Int, categoryId: Int, count: Int): Result<List<CampaignDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.CAMPAIGN_BASE_URL,
            method = "GetCampaignsByCategoryAsync",
            query = "languageId=$languageId&categoryId=$categoryId&count=$count"
        )
    }

    override suspend fun InsertAsync(model: CampaignInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.CAMPAIGN_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: CampaignUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.CAMPAIGN_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(campaignId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.CAMPAIGN_BASE_URL,
            method = "DeleteAsync",
            query = "campaignId=$campaignId"
        )
    }
}
