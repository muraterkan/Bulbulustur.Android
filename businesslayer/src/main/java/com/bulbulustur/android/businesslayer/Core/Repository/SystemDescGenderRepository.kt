package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGenderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescGenderRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescGenderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescGenderRepository(
    private val apiClient: ApiClient
) : ISystemDescGenderRepository {

    override suspend fun GetSystemDescGenderListAsync(): Result<List<SystemDescGenderDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescGenderByIdAsync(
        systemDescGenderId: Int
    ): Result<SystemDescGenderUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescGenderByIdExtendedAsync(
        systemDescGenderId: Int
    ): Result<SystemDescGenderDTO?> {
        TODO("Not implemented yet")
    }
}
