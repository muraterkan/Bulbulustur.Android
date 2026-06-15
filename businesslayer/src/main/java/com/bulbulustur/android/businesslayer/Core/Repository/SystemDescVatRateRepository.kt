package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescVatRateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescVatRateRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescVatRateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescVatRateRepository(
    private val apiClient: ApiClient
) : ISystemDescVatRateRepository {

    override suspend fun GetSystemDescVatRateListAsync(): Result<List<SystemDescVatRateDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescVatRateByIdAsync(
        systemDescVatRateId: Int
    ): Result<SystemDescVatRateUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescVatRateByIdExtendedAsync(
        systemDescVatRateId: Int
    ): Result<SystemDescVatRateDTO?> {
        TODO("Not implemented yet")
    }
}
