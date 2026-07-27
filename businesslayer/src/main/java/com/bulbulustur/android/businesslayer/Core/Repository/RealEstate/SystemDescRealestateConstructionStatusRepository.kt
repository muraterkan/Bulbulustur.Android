package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateConstructionStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateConstructionStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateConstructionStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateConstructionStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateConstructionStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateConstructionStatusRepository {

    override suspend fun GetSystemDescRealestateConstructionStatussAsync(): Result<List<SystemDescRealestateConstructionStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateConstructionStatussAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateConstructionStatusByIdAsync(systemDescRealestateConstructionStatusId: Int): Result<SystemDescRealestateConstructionStatusUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateConstructionStatusByIdAsync",
            query = "systemDescRealestateConstructionStatusId=$systemDescRealestateConstructionStatusId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateConstructionStatusInsertModel): Result<SystemDescRealestateConstructionStatusInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateConstructionStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateConstructionStatusUpdateModel): Result<SystemDescRealestateConstructionStatusUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateConstructionStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateConstructionStatusId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateConstructionStatusAsync",
            query = "systemDescRealestateConstructionStatusId=$systemDescRealestateConstructionStatusId"
        )
    }
}