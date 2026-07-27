package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateUsageStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateUsageStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateUsageStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateUsageStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateUsageStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateUsageStatusRepository {

    override suspend fun GetSystemDescRealestateUsageStatussAsync(): Result<List<SystemDescRealestateUsageStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateUsageStatussAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateUsageStatusByIdAsync(systemDescRealestateUsageStatusId: Int): Result<SystemDescRealestateUsageStatusUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateUsageStatusByIdAsync",
            query = "systemDescRealestateUsageStatusId=$systemDescRealestateUsageStatusId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateUsageStatusInsertModel): Result<SystemDescRealestateUsageStatusInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateUsageStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateUsageStatusUpdateModel): Result<SystemDescRealestateUsageStatusUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateUsageStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateUsageStatusId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateUsageStatusAsync",
            query = "systemDescRealestateUsageStatusId=$systemDescRealestateUsageStatusId"
        )
    }
}