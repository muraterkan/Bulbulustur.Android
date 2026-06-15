package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.BannerDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBannerRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BannerInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BannerUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class BannerRepository(
    private val apiClient: ApiClient = ApiClient
) : IBannerRepository {

    override suspend fun GetBannerListAsync(): Result<List<BannerDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBannerListAsync"
        )
    }

    override suspend fun GetBannerByIdAsync(
        bannerId: Int
    ): Result<BannerUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBannerByIdAsync",
            query = "bannerId=$bannerId"
        )
    }

    override suspend fun GetBannerByIdExtendedAsync(
        bannerId: Int
    ): Result<BannerDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBannerByIdExtendedAsync",
            query = "bannerId=$bannerId"
        )
    }

    override suspend fun InsertAsync(
        model: BannerInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: BannerUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        bannerId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "bannerId=$bannerId"
        )
    }
}