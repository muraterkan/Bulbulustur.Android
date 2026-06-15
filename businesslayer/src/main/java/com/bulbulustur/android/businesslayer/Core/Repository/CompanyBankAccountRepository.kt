package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyBankAccountRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyBankAccountRepository(
    private val apiClient: ApiClient
) : ICompanyBankAccountRepository {

    override suspend fun GetCompanyBankAccountListAsync(): Result<List<CompanyBankAccountDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyBankAccountByIdAsync(
        companyBankAccountId: Int
    ): Result<CompanyBankAccountUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyBankAccountByIdExtendedAsync(
        companyBankAccountId: Int
    ): Result<CompanyBankAccountDTO?> {
        TODO("Not implemented yet")
    }
}
