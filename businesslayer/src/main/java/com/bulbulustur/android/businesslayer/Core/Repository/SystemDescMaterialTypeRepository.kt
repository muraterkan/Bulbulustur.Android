package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaterialTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMaterialTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMaterialTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescMaterialTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescMaterialTypeRepository {

    override suspend fun GetSystemDescMaterialTypeListAsync(): Result<List<SystemDescMaterialTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescMaterialTypeByIdAsync(
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescMaterialTypeByIdExtendedAsync(
        systemDescMaterialTypeId: Int
    ): Result<SystemDescMaterialTypeDTO?> {
        TODO("Not implemented yet")
    }
}
