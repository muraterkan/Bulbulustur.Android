package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescHomeClothingComfortDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescHomeClothingComfortRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescHomeClothingComfortInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescHomeClothingComfortUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescHomeClothingComfortRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescHomeClothingComfortRepository {

    override suspend fun GetSystemDescHomeClothingComfortsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescHomeClothingComfortDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeClothingComfortsAsync",
            query = "languageId=$languageId&count=$count"
        )
    }

    override suspend fun GetSystemDescHomeClothingComfortByIdAsync(
        systemDescHomeClothingComfortId: Int
    ): Result<SystemDescHomeClothingComfortUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeClothingComfortByIdAsync",
            query = "systemDescHomeClothingComfortId=$systemDescHomeClothingComfortId"
        )
    }

    override suspend fun GetSystemDescHomeClothingComfortByIdExtendedAsync(
        languageId: Int,
        systemDescHomeClothingComfortId: Int
    ): Result<SystemDescHomeClothingComfortDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescHomeClothingComfortByIdExtendedAsync",
            query = "languageId=$languageId&systemDescHomeClothingComfortId=$systemDescHomeClothingComfortId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescHomeClothingComfortInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertSystemDescHomeClothingComfortAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescHomeClothingComfortUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateSystemDescHomeClothingComfortAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescHomeClothingComfortId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteSystemDescHomeClothingComfortAsync",
            query = "systemDescHomeClothingComfortId=$systemDescHomeClothingComfortId"
        )
    }
}