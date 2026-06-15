package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyVerificationDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyVerificationUpdateModel

interface ICompanyVerificationRepository {

    suspend fun GetCompanyVerificationListAsync(): Result<List<CompanyVerificationDTO>>

    suspend fun GetCompanyVerificationByIdAsync(
        verificationId: Int
    ): Result<CompanyVerificationUpdateModel?>

    suspend fun GetCompanyVerificationByIdExtendedAsync(
        verificationId: Int
    ): Result<CompanyVerificationDTO?>
}
