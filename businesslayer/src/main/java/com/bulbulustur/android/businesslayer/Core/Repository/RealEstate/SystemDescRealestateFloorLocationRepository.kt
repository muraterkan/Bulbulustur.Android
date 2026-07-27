package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateFloorLocationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateFloorLocationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateFloorLocationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateFloorLocationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateFloorLocationRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateFloorLocationRepository {

    override suspend fun GetSystemDescRealestateFloorLocationsAsync(): Result<List<SystemDescRealestateFloorLocationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateFloorLocationsAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateFloorLocationByIdAsync(systemDescRealestateFloorLocationId: Int): Result<SystemDescRealestateFloorLocationUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateFloorLocationByIdAsync",
            query = "systemDescRealestateFloorLocationId=$systemDescRealestateFloorLocationId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateFloorLocationInsertModel): Result<SystemDescRealestateFloorLocationInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateFloorLocationAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateFloorLocationUpdateModel): Result<SystemDescRealestateFloorLocationUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateFloorLocationAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateFloorLocationId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateFloorLocationAsync",
            query = "systemDescRealestateFloorLocationId=$systemDescRealestateFloorLocationId"
        )
    }
}