package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTermUpdateModel

interface ISystemDescPaymentTermRepository {

    suspend fun GetSystemDescPaymentTermListAsync(): Result<List<SystemDescPaymentTermDTO>>

    suspend fun GetSystemDescPaymentTermByIdAsync(
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermUpdateModel?>

    suspend fun GetSystemDescPaymentTermByIdExtendedAsync(
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermDTO?>
}
