package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyRepository(
    private val apiClient: ApiClient
) : ICompanyRepository {

    override suspend fun GetCompanyListAsync(): Result<List<CompanyDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyByIdAsync(
        companyId: Int
    ): Result<CompanyUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyByIdExtendedAsync(
        companyId: Int
    ): Result<CompanyDTO?> {
        TODO("Not implemented yet")
    }
}
