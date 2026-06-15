package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyUserDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUserUpdateModel

interface ICompanyUserRepository {

    suspend fun GetCompanyUserListAsync(): Result<List<CompanyUserDTO>>

    suspend fun GetCompanyUserByIdAsync(
        companyUserId: Int
    ): Result<CompanyUserUpdateModel?>

    suspend fun GetCompanyUserByIdExtendedAsync(
        companyUserId: Int
    ): Result<CompanyUserDTO?>
}
