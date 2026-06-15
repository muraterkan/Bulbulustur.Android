package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPhoneDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPhoneUpdateModel

interface ICompanyPhoneRepository {

    suspend fun GetCompanyPhoneListAsync(): Result<List<CompanyPhoneDTO>>

    suspend fun GetCompanyPhoneByIdAsync(
        companyPhoneId: Int
    ): Result<CompanyPhoneUpdateModel?>

    suspend fun GetCompanyPhoneByIdExtendedAsync(
        companyPhoneId: Int
    ): Result<CompanyPhoneDTO?>
}
