package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyPaymentTermRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPaymentTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyPaymentTermRepository(
    private val apiClient: ApiClient
) : ICompanyPaymentTermRepository {

    override suspend fun GetCompanyPaymentTermListAsync(): Result<List<CompanyPaymentTermDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyPaymentTermByIdAsync(
        companyPaymentTermId: Int
    ): Result<CompanyPaymentTermUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyPaymentTermByIdExtendedAsync(
        companyPaymentTermId: Int
    ): Result<CompanyPaymentTermDTO?> {
        TODO("Not implemented yet")
    }
}
