package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBusinessTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyBusinessTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBusinessTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyBusinessTypeRepository(
    private val apiClient: ApiClient
) : ICompanyBusinessTypeRepository {

    override suspend fun GetCompanyBusinessTypeListAsync(): Result<List<CompanyBusinessTypeDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyBusinessTypeByIdAsync(
        companyBusinessTypeId: Int
    ): Result<CompanyBusinessTypeUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyBusinessTypeByIdExtendedAsync(
        companyBusinessTypeId: Int
    ): Result<CompanyBusinessTypeDTO?> {
        TODO("Not implemented yet")
    }
}
