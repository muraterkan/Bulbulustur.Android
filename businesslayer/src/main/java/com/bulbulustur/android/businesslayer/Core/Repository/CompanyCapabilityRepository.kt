package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCapabilityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyCapabilityRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyCapabilityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyCapabilityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyCapabilityRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyCapabilityRepository {

    override suspend fun GetCompanyCapabilityListAsync(): Result<List<CompanyCapabilityDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyCapabilityListAsync"
        )
    }

    override suspend fun GetCompanyCapabilityByIdAsync(
        companyCapabilityId: Int
    ): Result<CompanyCapabilityUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyCapabilityByIdAsync",
            query = "companyCapabilityId=$companyCapabilityId"
        )
    }

    override suspend fun GetCompanyCapabilityByIdExtendedAsync(
        companyCapabilityId: Int
    ): Result<CompanyCapabilityDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyCapabilityByIdExtendedAsync",
            query = "companyCapabilityId=$companyCapabilityId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyCapabilityInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyCapabilityUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyCapabilityId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyCapabilityId=$companyCapabilityId"
        )
    }
}