package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyMainProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyMainProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyMainProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyMainProductRepository(
    private val apiClient: ApiClient
) : ICompanyMainProductRepository {

    override suspend fun GetCompanyMainProductListAsync(): Result<List<CompanyMainProductDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyMainProductByIdAsync(
        companyMainProductId: Int
    ): Result<CompanyMainProductUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyMainProductByIdExtendedAsync(
        companyMainProductId: Int
    ): Result<CompanyMainProductDTO?> {
        TODO("Not implemented yet")
    }
}
