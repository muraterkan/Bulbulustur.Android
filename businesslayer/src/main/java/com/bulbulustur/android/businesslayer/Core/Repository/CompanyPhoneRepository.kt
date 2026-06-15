package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPhoneDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyPhoneRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyPhoneInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPhoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyPhoneRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyPhoneRepository {

    override suspend fun GetCompanyPhoneListAsync(): Result<List<CompanyPhoneDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPhoneListAsync"
        )
    }

    override suspend fun GetCompanyPhoneByIdAsync(
        companyPhoneId: Int
    ): Result<CompanyPhoneUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPhoneByIdAsync",
            query = "companyPhoneId=$companyPhoneId"
        )
    }

    override suspend fun GetCompanyPhoneByIdExtendedAsync(
        companyPhoneId: Int
    ): Result<CompanyPhoneDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPhoneByIdExtendedAsync",
            query = "companyPhoneId=$companyPhoneId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyPhoneInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyPhoneUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyPhoneId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyPhoneId=$companyPhoneId"
        )
    }
}