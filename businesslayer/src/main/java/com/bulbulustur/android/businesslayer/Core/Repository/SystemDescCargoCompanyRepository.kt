package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoCompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCargoCompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoCompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescCargoCompanyRepository(
    private val apiClient: ApiClient
) : ISystemDescCargoCompanyRepository {

    override suspend fun GetSystemDescCargoCompanyListAsync(): Result<List<SystemDescCargoCompanyDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCargoCompanyByIdAsync(
        systemDescCargoCompanyId: Int
    ): Result<SystemDescCargoCompanyUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescCargoCompanyByIdExtendedAsync(
        systemDescCargoCompanyId: Int
    ): Result<SystemDescCargoCompanyDTO?> {
        TODO("Not implemented yet")
    }
}
