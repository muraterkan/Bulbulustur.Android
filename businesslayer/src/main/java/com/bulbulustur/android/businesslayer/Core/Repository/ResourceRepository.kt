package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IResourceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ResourceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ResourceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ResourceRepository(
    private val apiClient: ApiClient = ApiClient
) : IResourceRepository {

    override suspend fun GetResourceListAsync(): Result<List<ResourceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetResourceListAsync"
        )
    }

    override suspend fun GetResourceByIdAsync(
        resourceId: Int
    ): Result<ResourceUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetResourceByIdAsync",
            query = "resourceId=$resourceId"
        )
    }

    override suspend fun GetResourceByIdExtendedAsync(
        resourceId: Int
    ): Result<ResourceDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetResourceByIdExtendedAsync",
            query = "resourceId=$resourceId"
        )
    }

    override suspend fun InsertAsync(
        model: ResourceInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ResourceUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        resourceId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "resourceId=$resourceId"
        )
    }
}