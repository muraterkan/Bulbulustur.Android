package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateHeatingTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateHeatingTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateHeatingTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateHeatingTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateHeatingTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateHeatingTypeRepository {

    override suspend fun GetSystemDescRealestateHeatingTypesAsync(): Result<List<SystemDescRealestateHeatingTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateHeatingTypesAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateHeatingTypeByIdAsync(systemDescRealestateHeatingTypeId: Int): Result<SystemDescRealestateHeatingTypeUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateHeatingTypeByIdAsync",
            query = "systemDescRealestateHeatingTypeId=$systemDescRealestateHeatingTypeId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateHeatingTypeInsertModel): Result<SystemDescRealestateHeatingTypeInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateHeatingTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateHeatingTypeUpdateModel): Result<SystemDescRealestateHeatingTypeUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateHeatingTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateHeatingTypeId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateHeatingTypeAsync",
            query = "systemDescRealestateHeatingTypeId=$systemDescRealestateHeatingTypeId"
        )
    }
}