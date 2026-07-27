package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateRoomCountDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateRoomCountRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateRoomCountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateRoomCountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateRoomCountRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateRoomCountRepository {

    override suspend fun GetSystemDescRealestateRoomCountsAsync(): Result<List<SystemDescRealestateRoomCountDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateRoomCountsAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateRoomCountByIdAsync(systemDescRealestateRoomCountId: Int): Result<SystemDescRealestateRoomCountUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateRoomCountByIdAsync",
            query = "systemDescRealestateRoomCountId=$systemDescRealestateRoomCountId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateRoomCountInsertModel): Result<SystemDescRealestateRoomCountInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateRoomCountAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateRoomCountUpdateModel): Result<SystemDescRealestateRoomCountUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateRoomCountAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateRoomCountId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateRoomCountAsync",
            query = "systemDescRealestateRoomCountId=$systemDescRealestateRoomCountId"
        )
    }
}