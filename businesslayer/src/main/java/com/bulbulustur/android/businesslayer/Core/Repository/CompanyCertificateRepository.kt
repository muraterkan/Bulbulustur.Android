package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyCertificateRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyCertificateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyCertificateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyCertificateRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyCertificateRepository {

    override suspend fun GetCompanyCertificateListAsync(): Result<List<CompanyCertificateDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyCertificateListAsync"
        )
    }

    override suspend fun GetCompanyCertificateByIdAsync(
        companyCertificateId: Int
    ): Result<CompanyCertificateUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyCertificateByIdAsync",
            query = "companyCertificateId=$companyCertificateId"
        )
    }

    override suspend fun GetCompanyCertificateByIdExtendedAsync(
        companyCertificateId: Int
    ): Result<CompanyCertificateDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyCertificateByIdExtendedAsync",
            query = "companyCertificateId=$companyCertificateId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyCertificateInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyCertificateUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyCertificateId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyCertificateId=$companyCertificateId"
        )
    }
}