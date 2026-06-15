package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyBankAccountRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyBankAccountRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyBankAccountRepository {

    override suspend fun GetCompanyBankAccountListAsync(): Result<List<CompanyBankAccountDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBankAccountListAsync"
        )
    }

    override suspend fun GetCompanyBankAccountByIdAsync(
        companyBankAccountId: Int
    ): Result<CompanyBankAccountUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBankAccountByIdAsync",
            query = "companyBankAccountId=$companyBankAccountId"
        )
    }

    override suspend fun GetCompanyBankAccountByIdExtendedAsync(
        companyBankAccountId: Int
    ): Result<CompanyBankAccountDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBankAccountByIdExtendedAsync",
            query = "companyBankAccountId=$companyBankAccountId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyBankAccountInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyBankAccountUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyBankAccountId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyBankAccountId=$companyBankAccountId"
        )
    }
}