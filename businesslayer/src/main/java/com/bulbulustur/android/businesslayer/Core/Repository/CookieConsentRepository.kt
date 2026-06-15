package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CookieConsentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICookieConsentRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CookieConsentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieConsentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CookieConsentRepository(
    private val apiClient: ApiClient = ApiClient
) : ICookieConsentRepository {

    override suspend fun GetCookieConsentListAsync(): Result<List<CookieConsentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieConsentListAsync"
        )
    }

    override suspend fun GetCookieConsentByIdAsync(
        cookieConsentId: Int
    ): Result<CookieConsentUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieConsentByIdAsync",
            query = "cookieConsentId=$cookieConsentId"
        )
    }

    override suspend fun GetCookieConsentByIdExtendedAsync(
        cookieConsentId: Int
    ): Result<CookieConsentDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieConsentByIdExtendedAsync",
            query = "cookieConsentId=$cookieConsentId"
        )
    }

    override suspend fun InsertAsync(
        model: CookieConsentInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CookieConsentUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        cookieConsentId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "cookieConsentId=$cookieConsentId"
        )
    }
}