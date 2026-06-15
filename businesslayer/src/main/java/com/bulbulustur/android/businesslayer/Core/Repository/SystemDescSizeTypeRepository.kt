package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSizeTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSizeTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescSizeTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescSizeTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescSizeTypeRepository {

    override suspend fun GetSystemDescSizeTypeListAsync(): Result<List<SystemDescSizeTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescSizeTypeByIdAsync(
        systemDescSizeTypeId: Int
    ): Result<SystemDescSizeTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescSizeTypeByIdExtendedAsync(
        systemDescSizeTypeId: Int
    ): Result<SystemDescSizeTypeDTO?> {
        TODO("Not implemented yet")
    }
}
