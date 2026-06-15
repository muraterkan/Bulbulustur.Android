package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyExportMarketDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyExportMarketRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyExportMarketUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyExportMarketRepository(
    private val apiClient: ApiClient
) : ICompanyExportMarketRepository {

    override suspend fun GetCompanyExportMarketListAsync(): Result<List<CompanyExportMarketDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyExportMarketByIdAsync(
        companyExportMarketId: Int
    ): Result<CompanyExportMarketUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyExportMarketByIdExtendedAsync(
        companyExportMarketId: Int
    ): Result<CompanyExportMarketDTO?> {
        TODO("Not implemented yet")
    }
}
