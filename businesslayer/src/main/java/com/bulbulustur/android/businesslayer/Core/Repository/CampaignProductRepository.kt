package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CampaignProductRepository(
    private val apiClient: ApiClient = ApiClient
) : ICampaignProductRepository {

    override suspend fun GetCampaignProductListAsync(): Result<List<CampaignProductDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignProductListAsync"
        )
    }

    override suspend fun GetCampaignProductByIdAsync(
        campaignProductId: Int
    ): Result<CampaignProductUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignProductByIdAsync",
            query = "campaignProductId=$campaignProductId"
        )
    }

    override suspend fun GetCampaignProductByIdExtendedAsync(
        campaignProductId: Int
    ): Result<CampaignProductDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCampaignProductByIdExtendedAsync",
            query = "campaignProductId=$campaignProductId"
        )
    }

    override suspend fun InsertAsync(
        model: CampaignProductInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CampaignProductUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        campaignProductId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "campaignProductId=$campaignProductId"
        )
    }
}