package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCapabilityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyCapabilityRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyCapabilityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyCapabilityRepository(
    private val apiClient: ApiClient
) : ICompanyCapabilityRepository {

    override suspend fun GetCompanyCapabilityListAsync(): Result<List<CompanyCapabilityDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyCapabilityByIdAsync(
        companyCapabilityId: Int
    ): Result<CompanyCapabilityUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyCapabilityByIdExtendedAsync(
        companyCapabilityId: Int
    ): Result<CompanyCapabilityDTO?> {
        TODO("Not implemented yet")
    }
}
