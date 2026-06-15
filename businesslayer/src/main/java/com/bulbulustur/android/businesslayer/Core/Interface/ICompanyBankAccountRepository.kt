package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBankAccountUpdateModel

interface ICompanyBankAccountRepository {

    suspend fun GetCompanyBankAccountListAsync(): Result<List<CompanyBankAccountDTO>>

    suspend fun GetCompanyBankAccountByIdAsync(
        companyBankAccountId: Int
    ): Result<CompanyBankAccountUpdateModel?>

    suspend fun GetCompanyBankAccountByIdExtendedAsync(
        companyBankAccountId: Int
    ): Result<CompanyBankAccountDTO?>
}
