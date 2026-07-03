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

    override suspend fun GetWholesaleFavoriteListAsync(memberId: Int, count: Int): Result<List<WholesaleFavoriteDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_WHOLESALE_FAVORITE_BASE_URL,
            method = "GetWholesaleFavoriteListAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetWholesaleFavoriteByIdAsync(wholesaleFavoriteId: Int): Result<WholesaleFavoriteUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_WHOLESALE_FAVORITE_BASE_URL,
            method = "GetWholesaleFavoriteByIdAsync",
            query = "wholesaleFavoriteId=$wholesaleFavoriteId"
        )
    }

    override suspend fun GetWholesaleFavoriteByIdExtendedAsync(wholesaleFavoriteId: Int): Result<WholesaleFavoriteDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_WHOLESALE_FAVORITE_BASE_URL,
            method = "GetWholesaleFavoriteByIdExtendedAsync",
            query = "wholesaleFavoriteId=$wholesaleFavoriteId"
        )
    }

    override suspend fun InsertAsync(memberId: Int, model: WholesaleFavoriteInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_WHOLESALE_FAVORITE_BASE_URL,
            method = "InsertAsync",
            query = "memberId=$memberId",
            data = model
        )
    }

    override suspend fun DeleteAsync(memberId: Int, wholesaleFavoriteId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.B2B_WHOLESALE_FAVORITE_BASE_URL,
            method = "DeleteAsync",
            query = "memberId=$memberId&wholesaleFavoriteId=$wholesaleFavoriteId"
        )
    }
}