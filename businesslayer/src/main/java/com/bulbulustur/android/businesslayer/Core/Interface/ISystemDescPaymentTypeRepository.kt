package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTypeUpdateModel

interface ISystemDescPaymentTypeRepository {

    suspend fun GetSystemDescPaymentTypeListAsync(): Result<List<SystemDescPaymentTypeDTO>>

    suspend fun GetSystemDescPaymentTypeByIdAsync(
        systemDescPaymentTypeId: Int
    ): Result<SystemDescPaymentTypeUpdateModel?>

    suspend fun GetSystemDescPaymentTypeByIdExtendedAsync(
        systemDescPaymentTypeId: Int
    ): Result<SystemDescPaymentTypeDTO?>
}
