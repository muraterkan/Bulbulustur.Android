package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescUnitDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescUnitRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescUnitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescUnitRepository(
    private val apiClient: ApiClient
) : ISystemDescUnitRepository {

    override suspend fun GetSystemDescUnitListAsync(): Result<List<SystemDescUnitDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescUnitByIdAsync(
        systemDescUnitId: Int
    ): Result<SystemDescUnitUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescUnitByIdExtendedAsync(
        systemDescUnitId: Int
    ): Result<SystemDescUnitDTO?> {
        TODO("Not implemented yet")
    }
}
