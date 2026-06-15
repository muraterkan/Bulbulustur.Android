package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IResourceRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ResourceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ResourceRepository(
    private val apiClient: ApiClient
) : IResourceRepository {

    override suspend fun GetResourceListAsync(): Result<List<ResourceDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetResourceByIdAsync(
        resourceId: Int
    ): Result<ResourceUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetResourceByIdExtendedAsync(
        resourceId: Int
    ): Result<ResourceDTO?> {
        TODO("Not implemented yet")
    }
}
