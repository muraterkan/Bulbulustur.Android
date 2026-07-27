package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateEnergyCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateEnergyCertificateRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateEnergyCertificateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateEnergyCertificateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateEnergyCertificateRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateEnergyCertificateRepository {

    override suspend fun GetSystemDescRealestateEnergyCertificatesAsync(): Result<List<SystemDescRealestateEnergyCertificateDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateEnergyCertificatesAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateEnergyCertificateByIdAsync(systemDescRealestateEnergyCertificateId: Int): Result<SystemDescRealestateEnergyCertificateUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateEnergyCertificateByIdAsync",
            query = "systemDescRealestateEnergyCertificateId=$systemDescRealestateEnergyCertificateId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateEnergyCertificateInsertModel): Result<SystemDescRealestateEnergyCertificateInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateEnergyCertificateAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateEnergyCertificateUpdateModel): Result<SystemDescRealestateEnergyCertificateUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateEnergyCertificateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateEnergyCertificateId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateEnergyCertificateAsync",
            query = "systemDescRealestateEnergyCertificateId=$systemDescRealestateEnergyCertificateId"
        )
    }
}