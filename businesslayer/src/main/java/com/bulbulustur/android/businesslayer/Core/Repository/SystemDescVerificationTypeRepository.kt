package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVerificationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescVerificationTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVerificationTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescVerificationTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescVerificationTypeRepository {

    override suspend fun GetSystemDescVerificationTypeListAsync(): Result<List<SystemDescVerificationTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescVerificationTypeByIdAsync(
        systemDescVerificationTypeId: Int
    ): Result<SystemDescVerificationTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescVerificationTypeByIdExtendedAsync(
        systemDescVerificationTypeId: Int
    ): Result<SystemDescVerificationTypeDTO?> {
        TODO("Not implemented yet")
    }
}
