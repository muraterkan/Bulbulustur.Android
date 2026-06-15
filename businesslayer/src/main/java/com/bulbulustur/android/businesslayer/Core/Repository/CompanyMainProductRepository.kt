package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyMainProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyMainProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyMainProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyMainProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyMainProductRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyMainProductRepository {

    override suspend fun GetCompanyMainProductListAsync(): Result<List<CompanyMainProductDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyMainProductListAsync"
        )
    }

    override suspend fun GetCompanyMainProductByIdAsync(
        companyMainProductId: Int
    ): Result<CompanyMainProductUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyMainProductByIdAsync",
            query = "companyMainProductId=$companyMainProductId"
        )
    }

    override suspend fun GetCompanyMainProductByIdExtendedAsync(
        companyMainProductId: Int
    ): Result<CompanyMainProductDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyMainProductByIdExtendedAsync",
            query = "companyMainProductId=$companyMainProductId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyMainProductInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyMainProductUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyMainProductId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyMainProductId=$companyMainProductId"
        )
    }
}