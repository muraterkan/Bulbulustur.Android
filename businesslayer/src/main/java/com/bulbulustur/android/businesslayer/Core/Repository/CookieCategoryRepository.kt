package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CookieCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICookieCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CookieCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CookieCategoryRepository(
    private val apiClient: ApiClient = ApiClient
) : ICookieCategoryRepository {

    override suspend fun GetCookieCategoryListAsync(): Result<List<CookieCategoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieCategoryListAsync"
        )
    }

    override suspend fun GetCookieCategoryByIdAsync(
        cookieCategoryId: Int
    ): Result<CookieCategoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieCategoryByIdAsync",
            query = "cookieCategoryId=$cookieCategoryId"
        )
    }

    override suspend fun GetCookieCategoryByIdExtendedAsync(
        cookieCategoryId: Int
    ): Result<CookieCategoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCookieCategoryByIdExtendedAsync",
            query = "cookieCategoryId=$cookieCategoryId"
        )
    }

    override suspend fun InsertAsync(
        model: CookieCategoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CookieCategoryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        cookieCategoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "cookieCategoryId=$cookieCategoryId"
        )
    }
}