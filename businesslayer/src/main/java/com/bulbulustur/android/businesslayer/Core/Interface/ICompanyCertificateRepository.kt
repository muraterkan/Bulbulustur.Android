package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyCertificateUpdateModel

interface ICompanyCertificateRepository {

    suspend fun GetCompanyCertificateListAsync(): Result<List<CompanyCertificateDTO>>

    suspend fun GetCompanyCertificateByIdAsync(
        companyCertificateId: Int
    ): Result<CompanyCertificateUpdateModel?>

    suspend fun GetCompanyCertificateByIdExtendedAsync(
        companyCertificateId: Int
    ): Result<CompanyCertificateDTO?>
}
