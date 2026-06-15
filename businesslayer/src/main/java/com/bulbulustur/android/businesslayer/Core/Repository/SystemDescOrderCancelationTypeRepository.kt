package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderCancelationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescOrderCancelationTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderCancelationTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescOrderCancelationTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescOrderCancelationTypeRepository {

    override suspend fun GetSystemDescOrderCancelationTypeListAsync(): Result<List<SystemDescOrderCancelationTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescOrderCancelationTypeByIdAsync(
        systemDescOrderCancelationTypeId: Int
    ): Result<SystemDescOrderCancelationTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescOrderCancelationTypeByIdExtendedAsync(
        systemDescOrderCancelationTypeId: Int
    ): Result<SystemDescOrderCancelationTypeDTO?> {
        TODO("Not implemented yet")
    }
}
