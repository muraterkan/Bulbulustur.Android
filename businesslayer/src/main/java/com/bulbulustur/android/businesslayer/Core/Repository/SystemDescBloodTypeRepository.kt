package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBloodTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBloodTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBloodTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescBloodTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescBloodTypeRepository {

    override suspend fun GetSystemDescBloodTypeListAsync(): Result<List<SystemDescBloodTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescBloodTypeByIdAsync(
        systemDescBloodTypeId: Int
    ): Result<SystemDescBloodTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescBloodTypeByIdExtendedAsync(
        systemDescBloodTypeId: Int
    ): Result<SystemDescBloodTypeDTO?> {
        TODO("Not implemented yet")
    }
}
