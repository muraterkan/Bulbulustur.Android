package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateKitchenTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateKitchenTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateKitchenTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateKitchenTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateKitchenTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateKitchenTypeRepository {

    override suspend fun GetSystemDescRealestateKitchenTypesAsync(): Result<List<SystemDescRealestateKitchenTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateKitchenTypesAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateKitchenTypeByIdAsync(systemDescRealestateKitchenTypeId: Int): Result<SystemDescRealestateKitchenTypeUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateKitchenTypeByIdAsync",
            query = "systemDescRealestateKitchenTypeId=$systemDescRealestateKitchenTypeId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateKitchenTypeInsertModel): Result<SystemDescRealestateKitchenTypeInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateKitchenTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateKitchenTypeUpdateModel): Result<SystemDescRealestateKitchenTypeUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateKitchenTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateKitchenTypeId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateKitchenTypeAsync",
            query = "systemDescRealestateKitchenTypeId=$systemDescRealestateKitchenTypeId"
        )
    }
}