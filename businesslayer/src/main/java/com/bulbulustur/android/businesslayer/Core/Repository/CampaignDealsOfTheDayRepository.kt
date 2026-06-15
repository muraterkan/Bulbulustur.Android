package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignDealsOfTheDayRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignDealsOfTheDayInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignDealsOfTheDayUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CampaignDealsOfTheDayRepository(
    private val apiClient: ApiClient = ApiClient
) : ICampaignDealsOfTheDayRepository {

    override suspend fun GetCampaignDealsOfTheDayListAsync(): Result<List<CampaignDealsOfTheDayDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignDealsOfTheDayListAsync"
        )
    }

    override suspend fun GetCampaignDealsOfTheDayByIdAsync(
        campaignDealsOfTheDayId: Int
    ): Result<CampaignDealsOfTheDayUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignDealsOfTheDayByIdAsync",
            query = "campaignDealsOfTheDayId=$campaignDealsOfTheDayId"
        )
    }

    override suspend fun GetCampaignDealsOfTheDayByIdExtendedAsync(
        campaignDealsOfTheDayId: Int
    ): Result<CampaignDealsOfTheDayDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignDealsOfTheDayByIdExtendedAsync",
            query = "campaignDealsOfTheDayId=$campaignDealsOfTheDayId"
        )
    }

    override suspend fun InsertAsync(
        model: CampaignDealsOfTheDayInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CampaignDealsOfTheDayUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        campaignDealsOfTheDayId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "campaignDealsOfTheDayId=$campaignDealsOfTheDayId"
        )
    }
}