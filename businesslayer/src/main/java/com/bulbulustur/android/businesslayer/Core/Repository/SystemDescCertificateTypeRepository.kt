package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCertificateTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescCertificateTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCertificateTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCertificateTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescCertificateTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescCertificateTypeRepository {

    override suspend fun GetSystemDescCertificateTypeListAsync(): Result<List<SystemDescCertificateTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCertificateTypeListAsync"
        )
    }

    override suspend fun GetSystemDescCertificateTypeByIdAsync(
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCertificateTypeByIdAsync",
            query = "systemDescCertificateTypeId=$systemDescCertificateTypeId"
        )
    }

    override suspend fun GetSystemDescCertificateTypeByIdExtendedAsync(
        systemDescCertificateTypeId: Int
    ): Result<SystemDescCertificateTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescCertificateTypeByIdExtendedAsync",
            query = "systemDescCertificateTypeId=$systemDescCertificateTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescCertificateTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescCertificateTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescCertificateTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescCertificateTypeId=$systemDescCertificateTypeId"
        )
    }
}