package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescInvoiceTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescInvoiceTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescInvoiceTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescInvoiceTypeRepository {

    suspend fun GetSystemDescInvoiceTypesAsync(
        count: Int
    ): Result<List<SystemDescInvoiceTypeDTO>>

    suspend fun GetSystemDescInvoiceTypeByIdAsync(
        systemDescInvoiceTypeId: Int
    ): Result<SystemDescInvoiceTypeUpdateModel?>

    suspend fun GetSystemDescInvoiceTypeByIdExtendedAsync(
        systemDescInvoiceTypeId: Int
    ): Result<SystemDescInvoiceTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescInvoiceTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescInvoiceTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescInvoiceTypeId: Int
    ): Result<Unit>
}