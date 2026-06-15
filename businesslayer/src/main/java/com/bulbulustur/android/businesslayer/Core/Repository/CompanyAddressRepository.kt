package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyAddressRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyAddressRepository {

    override suspend fun GetCompanyAddressListAsync(): Result<List<CompanyAddressDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyAddressListAsync"
        )
    }

    override suspend fun GetCompanyAddressByIdAsync(
        companyAddressId: Int
    ): Result<CompanyAddressUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyAddressByIdAsync",
            query = "companyAddressId=$companyAddressId"
        )
    }

    override suspend fun GetCompanyAddressByIdExtendedAsync(
        companyAddressId: Int
    ): Result<CompanyAddressDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyAddressByIdExtendedAsync",
            query = "companyAddressId=$companyAddressId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyAddressInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyAddressUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyAddressId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyAddressId=$companyAddressId"
        )
    }
}