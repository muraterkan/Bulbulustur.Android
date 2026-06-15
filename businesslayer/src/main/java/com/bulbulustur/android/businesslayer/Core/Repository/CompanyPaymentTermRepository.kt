package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPaymentTermDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyPaymentTermRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyPaymentTermInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPaymentTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyPaymentTermRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyPaymentTermRepository {

    override suspend fun GetCompanyPaymentTermListAsync(): Result<List<CompanyPaymentTermDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPaymentTermListAsync"
        )
    }

    override suspend fun GetCompanyPaymentTermByIdAsync(
        companyPaymentTermId: Int
    ): Result<CompanyPaymentTermUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPaymentTermByIdAsync",
            query = "companyPaymentTermId=$companyPaymentTermId"
        )
    }

    override suspend fun GetCompanyPaymentTermByIdExtendedAsync(
        companyPaymentTermId: Int
    ): Result<CompanyPaymentTermDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPaymentTermByIdExtendedAsync",
            query = "companyPaymentTermId=$companyPaymentTermId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyPaymentTermInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyPaymentTermUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyPaymentTermId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyPaymentTermId=$companyPaymentTermId"
        )
    }
}