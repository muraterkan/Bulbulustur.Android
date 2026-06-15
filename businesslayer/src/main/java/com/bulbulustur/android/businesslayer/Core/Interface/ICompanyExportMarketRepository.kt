package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyExportMarketDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyExportMarketUpdateModel

interface ICompanyExportMarketRepository {

    suspend fun GetCompanyExportMarketListAsync(): Result<List<CompanyExportMarketDTO>>

    suspend fun GetCompanyExportMarketByIdAsync(
        companyExportMarketId: Int
    ): Result<CompanyExportMarketUpdateModel?>

    suspend fun GetCompanyExportMarketByIdExtendedAsync(
        companyExportMarketId: Int
    ): Result<CompanyExportMarketDTO?>
}
