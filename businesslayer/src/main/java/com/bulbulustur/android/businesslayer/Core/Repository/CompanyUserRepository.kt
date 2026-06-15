package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyUserDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyUserRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUserUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyUserRepository(
    private val apiClient: ApiClient
) : ICompanyUserRepository {

    override suspend fun GetCompanyUserListAsync(): Result<List<CompanyUserDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyUserByIdAsync(
        companyUserId: Int
    ): Result<CompanyUserUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyUserByIdExtendedAsync(
        companyUserId: Int
    ): Result<CompanyUserDTO?> {
        TODO("Not implemented yet")
    }
}
