package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMemberTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMemberTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMemberTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescMemberTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescMemberTypeRepository {

    override suspend fun GetSystemDescMemberTypeListAsync(): Result<List<SystemDescMemberTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescMemberTypeByIdAsync(
        systemDescMemberTypeId: Int
    ): Result<SystemDescMemberTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescMemberTypeByIdExtendedAsync(
        systemDescMemberTypeId: Int
    ): Result<SystemDescMemberTypeDTO?> {
        TODO("Not implemented yet")
    }
}
