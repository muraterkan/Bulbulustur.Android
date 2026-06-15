package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CookieProviderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICookieProviderRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CookieProviderInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieProviderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CookieProviderRepository(
    private val apiClient: ApiClient = ApiClient
) : ICookieProviderRepository {

    override suspend fun GetCookieProviderListAsync(): Result<List<CookieProviderDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieProviderListAsync"
        )
    }

    override suspend fun GetCookieProviderByIdAsync(
        cookieProviderId: Int
    ): Result<CookieProviderUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieProviderByIdAsync",
            query = "cookieProviderId=$cookieProviderId"
        )
    }

    override suspend fun GetCookieProviderByIdExtendedAsync(
        cookieProviderId: Int
    ): Result<CookieProviderDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieProviderByIdExtendedAsync",
            query = "cookieProviderId=$cookieProviderId"
        )
    }

    override suspend fun InsertAsync(
        model: CookieProviderInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CookieProviderUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        cookieProviderId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "cookieProviderId=$cookieProviderId"
        )
    }
}