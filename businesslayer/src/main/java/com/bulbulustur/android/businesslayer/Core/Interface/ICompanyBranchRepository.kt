package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBranchDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBranchUpdateModel

interface ICompanyBranchRepository {

    suspend fun GetCompanyBranchListAsync(): Result<List<CompanyBranchDTO>>

    suspend fun GetCompanyBranchByIdAsync(
        companyBranchId: Int
    ): Result<CompanyBranchUpdateModel?>

    suspend fun GetCompanyBranchByIdExtendedAsync(
        companyBranchId: Int
    ): Result<CompanyBranchDTO?>
}
