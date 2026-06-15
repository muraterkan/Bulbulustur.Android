package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel

interface ICompanyRepository {

    suspend fun GetCompanyListAsync(): Result<List<CompanyDTO>>

    suspend fun GetCompanyByIdAsync(
        companyId: Int
    ): Result<CompanyUpdateModel?>

    suspend fun GetCompanyByIdExtendedAsync(
        companyId: Int
    ): Result<CompanyDTO?>
}
