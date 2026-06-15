package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyExportMarketDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyExportMarketRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyExportMarketInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyExportMarketUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyExportMarketRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyExportMarketRepository {

    override suspend fun GetCompanyExportMarketListAsync(): Result<List<CompanyExportMarketDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyExportMarketListAsync"
        )
    }

    override suspend fun GetCompanyExportMarketByIdAsync(
        companyExportMarketId: Int
    ): Result<CompanyExportMarketUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyExportMarketByIdAsync",
            query = "companyExportMarketId=$companyExportMarketId"
        )
    }

    override suspend fun GetCompanyExportMarketByIdExtendedAsync(
        companyExportMarketId: Int
    ): Result<CompanyExportMarketDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyExportMarketByIdExtendedAsync",
            query = "companyExportMarketId=$companyExportMarketId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyExportMarketInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyExportMarketUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyExportMarketId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyExportMarketId=$companyExportMarketId"
        )
    }
}