package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBankDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBankRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBankUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescBankRepository(
    private val apiClient: ApiClient
) : ISystemDescBankRepository {

    override suspend fun GetSystemDescBankListAsync(): Result<List<SystemDescBankDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescBankByIdAsync(
        systemDescBankId: Int
    ): Result<SystemDescBankUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescBankByIdExtendedAsync(
        systemDescBankId: Int
    ): Result<SystemDescBankDTO?> {
        TODO("Not implemented yet")
    }
}
