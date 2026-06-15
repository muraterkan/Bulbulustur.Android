package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.BannerPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBannerPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BannerPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BannerPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class BannerPictureRepository(
    private val apiClient: ApiClient = ApiClient
) : IBannerPictureRepository {

    override suspend fun GetBannerPictureListAsync(): Result<List<BannerPictureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBannerPictureListAsync"
        )
    }

    override suspend fun GetBannerPictureByIdAsync(
        bannerPictureId: Int
    ): Result<BannerPictureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBannerPictureByIdAsync",
            query = "bannerPictureId=$bannerPictureId"
        )
    }

    override suspend fun GetBannerPictureByIdExtendedAsync(
        bannerPictureId: Int
    ): Result<BannerPictureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBannerPictureByIdExtendedAsync",
            query = "bannerPictureId=$bannerPictureId"
        )
    }

    override suspend fun InsertAsync(
        model: BannerPictureInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: BannerPictureUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        bannerPictureId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "bannerPictureId=$bannerPictureId"
        )
    }
}