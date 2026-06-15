package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCertificateTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCertificateTypeUpdateModel

interface ISystemDescCertificateTypeRepository {

    suspend fun GetSystemDescCertificateTypeListAsync(): Result<List<SystemDescCertificateTypeDTO>>

    suspend fun GetSystemDescCertificateTypeByIdAsync(
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeUpdateModel?>

    suspend fun GetSystemDescCertificateTypeByIdExtendedAsync(
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeDTO?>
}
