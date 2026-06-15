package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyUserDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyUserRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyUserInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUserUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyUserRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyUserRepository {

    override suspend fun GetCompanyUserListAsync(): Result<List<CompanyUserDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyUserListAsync"
        )
    }

    override suspend fun GetCompanyUserByIdAsync(
        companyUserId: Int
    ): Result<CompanyUserUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyUserByIdAsync",
            query = "companyUserId=$companyUserId"
        )
    }

    override suspend fun GetCompanyUserByIdExtendedAsync(
        companyUserId: Int
    ): Result<CompanyUserDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyUserByIdExtendedAsync",
            query = "companyUserId=$companyUserId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyUserInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyUserUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyUserId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyUserId=$companyUserId"
        )
    }
}