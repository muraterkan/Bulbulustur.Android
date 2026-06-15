package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPhoneDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyPhoneRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPhoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyPhoneRepository(
    private val apiClient: ApiClient
) : ICompanyPhoneRepository {

    override suspend fun GetCompanyPhoneListAsync(): Result<List<CompanyPhoneDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyPhoneByIdAsync(
        companyPhoneId: Int
    ): Result<CompanyPhoneUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyPhoneByIdExtendedAsync(
        companyPhoneId: Int
    ): Result<CompanyPhoneDTO?> {
        TODO("Not implemented yet")
    }
}
