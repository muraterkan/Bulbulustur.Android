package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyMainProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyMainProductUpdateModel

interface ICompanyMainProductRepository {

    suspend fun GetCompanyMainProductListAsync(): Result<List<CompanyMainProductDTO>>

    suspend fun GetCompanyMainProductByIdAsync(
        companyMainProductId: Int
    ): Result<CompanyMainProductUpdateModel?>

    suspend fun GetCompanyMainProductByIdExtendedAsync(
        companyMainProductId: Int
    ): Result<CompanyMainProductDTO?>
}
