package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGenderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescGenderRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescGenderInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescGenderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescGenderRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescGenderRepository {

    override suspend fun GetSystemDescGenderListAsync(): Result<List<SystemDescGenderDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescGenderListAsync"
        )
    }

    override suspend fun GetSystemDescGenderByIdAsync(
        systemDescGenderId: Int
    ): Result<SystemDescGenderUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescGenderByIdAsync",
            query = "systemDescGenderId=$systemDescGenderId"
        )
    }

    override suspend fun GetSystemDescGenderByIdExtendedAsync(
        systemDescGenderId: Int
    ): Result<SystemDescGenderDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescGenderByIdExtendedAsync",
            query = "systemDescGenderId=$systemDescGenderId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescGenderInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescGenderUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescGenderId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescGenderId=$systemDescGenderId"
        )
    }
}