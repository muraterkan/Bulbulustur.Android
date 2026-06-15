package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyVerificationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyVerificationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyVerificationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyVerificationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyVerificationRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyVerificationRepository {

    override suspend fun GetCompanyVerificationListAsync(): Result<List<CompanyVerificationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyVerificationListAsync"
        )
    }

    override suspend fun GetCompanyVerificationByIdAsync(
        companyVerificationId: Int
    ): Result<CompanyVerificationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyVerificationByIdAsync",
            query = "companyVerificationId=$companyVerificationId"
        )
    }

    override suspend fun GetCompanyVerificationByIdExtendedAsync(
        companyVerificationId: Int
    ): Result<CompanyVerificationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyVerificationByIdExtendedAsync",
            query = "companyVerificationId=$companyVerificationId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyVerificationInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyVerificationUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyVerificationId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyVerificationId=$companyVerificationId"
        )
    }
}