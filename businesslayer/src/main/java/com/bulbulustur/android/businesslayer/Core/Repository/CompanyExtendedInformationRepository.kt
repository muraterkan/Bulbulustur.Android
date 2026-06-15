package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyExtendedInformationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyExtendedInformationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyExtendedInformationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyExtendedInformationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyExtendedInformationRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyExtendedInformationRepository {

    override suspend fun GetCompanyExtendedInformationListAsync(): Result<List<CompanyExtendedInformationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyExtendedInformationListAsync"
        )
    }

    override suspend fun GetCompanyExtendedInformationByIdAsync(
        companyExtendedInformationId: Int
    ): Result<CompanyExtendedInformationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyExtendedInformationByIdAsync",
            query = "companyExtendedInformationId=$companyExtendedInformationId"
        )
    }

    override suspend fun GetCompanyExtendedInformationByIdExtendedAsync(
        companyExtendedInformationId: Int
    ): Result<CompanyExtendedInformationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyExtendedInformationByIdExtendedAsync",
            query = "companyExtendedInformationId=$companyExtendedInformationId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyExtendedInformationInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyExtendedInformationUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyExtendedInformationId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyExtendedInformationId=$companyExtendedInformationId"
        )
    }
}