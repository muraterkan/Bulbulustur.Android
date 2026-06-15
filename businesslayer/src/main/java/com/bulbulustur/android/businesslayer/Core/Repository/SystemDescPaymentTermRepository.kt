package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPaymentTermRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescPaymentTermRepository(
    private val apiClient: ApiClient
) : ISystemDescPaymentTermRepository {

    override suspend fun GetSystemDescPaymentTermListAsync(): Result<List<SystemDescPaymentTermDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescPaymentTermByIdAsync(
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescPaymentTermByIdExtendedAsync(
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermDTO?> {
        TODO("Not implemented yet")
    }
}
