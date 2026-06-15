package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ResourceUpdateModel

interface IResourceRepository {

    suspend fun GetResourceListAsync(): Result<List<ResourceDTO>>

    suspend fun GetResourceByIdAsync(
        resourceId: Int
    ): Result<ResourceUpdateModel?>

    suspend fun GetResourceByIdExtendedAsync(
        resourceId: Int
    ): Result<ResourceDTO?>
}
