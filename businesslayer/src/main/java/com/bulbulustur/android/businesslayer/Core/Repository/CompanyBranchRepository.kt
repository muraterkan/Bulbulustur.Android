package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBranchDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyBranchRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBranchUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyBranchRepository(
    private val apiClient: ApiClient
) : ICompanyBranchRepository {

    override suspend fun GetCompanyBranchListAsync(): Result<List<CompanyBranchDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyBranchByIdAsync(
        companyBranchId: Int
    ): Result<CompanyBranchUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyBranchByIdExtendedAsync(
        companyBranchId: Int
    ): Result<CompanyBranchDTO?> {
        TODO("Not implemented yet")
    }
}
