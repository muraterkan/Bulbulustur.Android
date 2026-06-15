package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCargoDesiRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescCargoDesiRepository(
    private val apiClient: ApiClient
) : ISystemDescCargoDesiRepository {

    override suspend fun GetSystemDescCargoDesiListAsync(): Result<List<SystemDescCargoDesiDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCargoDesiByIdAsync(
        systemDescCargoDesiId: Int
    ): Result<SystemDescCargoDesiUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCargoDesiByIdExtendedAsync(
        systemDescCargoDesiId: Int
    ): Result<SystemDescCargoDesiDTO?> {
        TODO("Not implemented yet")
    }
}
