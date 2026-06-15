package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAdvertSponsoredRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AdvertSponsoredInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertSponsoredUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AdvertSponsoredRepository(
    private val apiClient: ApiClient = ApiClient
) : IAdvertSponsoredRepository {

    override suspend fun GetAdvertSponsoredListAsync(): Result<List<AdvertSponsoredDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertSponsoredListAsync"
        )
    }

    override suspend fun GetAdvertSponsoredByIdAsync(
        advertSponsoredId: Int
    ): Result<AdvertSponsoredUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertSponsoredByIdAsync",
            query = "advertSponsoredId=$advertSponsoredId"
        )
    }

    override suspend fun GetAdvertSponsoredByIdExtendedAsync(
        advertSponsoredId: Int
    ): Result<AdvertSponsoredDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetAdvertSponsoredByIdExtendedAsync",
            query = "advertSponsoredId=$advertSponsoredId"
        )
    }

    override suspend fun InsertAsync(
        model: AdvertSponsoredInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: AdvertSponsoredUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        advertSponsoredId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "advertSponsoredId=$advertSponsoredId"
        )
    }
}