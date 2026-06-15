package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBranchDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyBranchRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyBranchInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBranchUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyBranchRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyBranchRepository {

    override suspend fun GetCompanyBranchListAsync(): Result<List<CompanyBranchDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBranchListAsync"
        )
    }

    override suspend fun GetCompanyBranchByIdAsync(
        companyBranchId: Int
    ): Result<CompanyBranchUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBranchByIdAsync",
            query = "companyBranchId=$companyBranchId"
        )
    }

    override suspend fun GetCompanyBranchByIdExtendedAsync(
        companyBranchId: Int
    ): Result<CompanyBranchDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBranchByIdExtendedAsync",
            query = "companyBranchId=$companyBranchId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyBranchInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyBranchUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyBranchId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyBranchId=$companyBranchId"
        )
    }
}