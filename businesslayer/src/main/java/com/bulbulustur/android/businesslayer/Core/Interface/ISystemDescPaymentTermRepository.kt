package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPaymentTermInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescPaymentTermRepository {

    suspend fun GetSystemDescPaymentTermsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPaymentTermDTO>>

    suspend fun GetSystemDescPaymentTermByIdAsync(
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermUpdateModel?>

    suspend fun GetSystemDescPaymentTermByIdExtendedAsync(
        systemDescPaymentTermId: Int
    ): Result<SystemDescPaymentTermDTO?>

    suspend fun InsertAsync(
        model: SystemDescPaymentTermInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescPaymentTermUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescPaymentTermId: Int
    ): Result<Unit>
}