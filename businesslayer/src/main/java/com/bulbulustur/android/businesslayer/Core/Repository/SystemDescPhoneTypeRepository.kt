package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPhoneTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPhoneTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPhoneTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescPhoneTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescPhoneTypeRepository {

    override suspend fun GetSystemDescPhoneTypeListAsync(): Result<List<SystemDescPhoneTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescPhoneTypeByIdAsync(
        systemDescPhoneTypeId: Int
    ): Result<SystemDescPhoneTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescPhoneTypeByIdExtendedAsync(
        systemDescPhoneTypeId: Int
    ): Result<SystemDescPhoneTypeDTO?> {
        TODO("Not implemented yet")
    }
}
