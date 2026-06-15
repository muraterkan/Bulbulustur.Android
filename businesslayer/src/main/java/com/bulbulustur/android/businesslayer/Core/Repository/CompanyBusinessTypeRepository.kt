package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBusinessTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyBusinessTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyBusinessTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBusinessTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyBusinessTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyBusinessTypeRepository {

    override suspend fun GetCompanyBusinessTypeListAsync(): Result<List<CompanyBusinessTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBusinessTypeListAsync"
        )
    }

    override suspend fun GetCompanyBusinessTypeByIdAsync(
        companyBusinessTypeId: Int
    ): Result<CompanyBusinessTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBusinessTypeByIdAsync",
            query = "companyBusinessTypeId=$companyBusinessTypeId"
        )
    }

    override suspend fun GetCompanyBusinessTypeByIdExtendedAsync(
        companyBusinessTypeId: Int
    ): Result<CompanyBusinessTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyBusinessTypeByIdExtendedAsync",
            query = "companyBusinessTypeId=$companyBusinessTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyBusinessTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyBusinessTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyBusinessTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyBusinessTypeId=$companyBusinessTypeId"
        )
    }
}