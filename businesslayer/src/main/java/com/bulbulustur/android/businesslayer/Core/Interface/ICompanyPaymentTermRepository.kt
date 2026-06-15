package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPaymentTermUpdateModel

interface ICompanyPaymentTermRepository {

    suspend fun GetCompanyPaymentTermListAsync(): Result<List<CompanyPaymentTermDTO>>

    suspend fun GetCompanyPaymentTermByIdAsync(
        companyPaymentTermId: Int
    ): Result<CompanyPaymentTermUpdateModel?>

    suspend fun GetCompanyPaymentTermByIdExtendedAsync(
        companyPaymentTermId: Int
    ): Result<CompanyPaymentTermDTO?>
}
