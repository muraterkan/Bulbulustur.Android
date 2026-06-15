package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleFavoriteUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleFavoriteRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleFavoriteRepository {

    override suspend fun GetWholesaleFavoriteListAsync(): Result<List<WholesaleFavoriteDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleFavoriteListAsync"
        )
    }

    override suspend fun GetWholesaleFavoriteByIdAsync(
        wholesaleFavoriteId: Int
    ): Result<WholesaleFavoriteUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleFavoriteByIdAsync",
            query = "wholesaleFavoriteId=$wholesaleFavoriteId"
        )
    }

    override suspend fun GetWholesaleFavoriteByIdExtendedAsync(
        wholesaleFavoriteId: Int
    ): Result<WholesaleFavoriteDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleFavoriteByIdExtendedAsync",
            query = "wholesaleFavoriteId=$wholesaleFavoriteId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleFavoriteInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleFavoriteUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleFavoriteId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleFavoriteId=$wholesaleFavoriteId"
        )
    }
}