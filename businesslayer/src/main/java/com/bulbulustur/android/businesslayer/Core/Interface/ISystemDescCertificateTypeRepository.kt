package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCertificateTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCertificateTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCertificateTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescCertificateTypeRepository {

    suspend fun GetSystemDescCertificateTypesAsync(
        count: Int
    ): Result<List<SystemDescCertificateTypeDTO>>

    suspend fun GetSystemDescCertificateTypeByIdAsync(
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeDTO?>

    suspend fun GetSystemDescCertificateTypeByIdExtendedAsync(
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescCertificateTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescCertificateTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescCertificateTypeId: Int
    ): Result<Unit>
}