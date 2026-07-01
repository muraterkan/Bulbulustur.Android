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

    override suspend fun GetAdvertSponsoredListAsync():
            Result<List<AdvertSponsoredDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.ADVERT_BASE_URL,
            method =
                "Advert/GetSponsoredAdvertsAsync",
            query =
                "productCategoryId=0&count=100"
        )
    }

    override suspend fun GetAdvertSponsoredByIdAsync(
        advertSponsoredId: Int
    ): Result<AdvertSponsoredUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.ADVERT_BASE_URL,
            method =
                "Advert/GetAdvertSponsoredByIdAsync",
            query =
                "advertSponsoredId=$advertSponsoredId"
        )
    }

    override suspend fun GetAdvertSponsoredByIdExtendedAsync(
        advertSponsoredId: Int
    ): Result<AdvertSponsoredDTO?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.ADVERT_BASE_URL,
            method =
                "Advert/GetAdvertSponsoredByIdExtendedAsync",
            query =
                "advertSponsoredId=$advertSponsoredId"
        )
    }

    override suspend fun GetSponsoredAdvertsAsync(
        languageId: Int,
        productCategoryId: Int,
        count: Int
    ): Result<List<AdvertSponsoredDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.ADVERT_BASE_URL,
            method =
                "Advert/GetSponsoredAdvertsAsync",
            query =
                "productCategoryId=$productCategoryId" +
                        "&count=$count"
        )
    }

    override suspend fun InsertAsync(
        model: AdvertSponsoredInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.ADVERT_BASE_URL,
            method =
                "Advert/InsertAdvertSponsoredAsync",
            data =
                model
        )
    }

    override suspend fun UpdateAsync(
        model: AdvertSponsoredUpdateModel
    ): Result<Unit> {
        return apiClient.PutAsync(
            baseUrl =
                ApiRoutes.ADVERT_BASE_URL,
            method =
                "Advert/UpdateAdvertSponsoredAsync",
            data =
                model
        )
    }

    override suspend fun DeleteAsync(
        advertSponsoredId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl =
                ApiRoutes.ADVERT_BASE_URL,
            method =
                "Advert/DeleteAdvertSponsoredAsync",
            query =
                "advertSponsoredId=$advertSponsoredId"
        )
    }
}