package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyAddressRepository(
    private val apiClient: ApiClient
) : ICompanyAddressRepository {

    override suspend fun GetCompanyAddressListAsync(): Result<List<CompanyAddressDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyAddressByIdAsync(
        companyAddressId: Int
    ): Result<CompanyAddressUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyAddressByIdExtendedAsync(
        companyAddressId: Int
    ): Result<CompanyAddressDTO?> {
        TODO("Not implemented yet")
    }
}
