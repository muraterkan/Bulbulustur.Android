package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPaymentTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SystemDescPaymentTypeRepository(
    private val apiClient: ApiClient
) : ISystemDescPaymentTypeRepository {

    override suspend fun GetSystemDescPaymentTypeListAsync(): Result<List<SystemDescPaymentTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescPaymentTypeByIdAsync(
        systemDescPaymentTypeId: Int
    ): Result<SystemDescPaymentTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSystemDescPaymentTypeByIdExtendedAsync(
        systemDescPaymentTypeId: Int
    ): Result<SystemDescPaymentTypeDTO?> {
        TODO("Not implemented yet")
    }
}
