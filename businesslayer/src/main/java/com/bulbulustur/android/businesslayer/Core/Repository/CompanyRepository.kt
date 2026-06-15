package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyRepository {

    override suspend fun GetCompanyListAsync(): Result<List<CompanyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyListAsync"
        )
    }

    override suspend fun GetCompanyByIdAsync(
        companyId: Int
    ): Result<CompanyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyByIdAsync",
            query = "companyId=$companyId"
        )
    }

    override suspend fun GetCompanyByIdExtendedAsync(
        companyId: Int
    ): Result<CompanyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyByIdExtendedAsync",
            query = "companyId=$companyId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyId=$companyId"
        )
    }
}