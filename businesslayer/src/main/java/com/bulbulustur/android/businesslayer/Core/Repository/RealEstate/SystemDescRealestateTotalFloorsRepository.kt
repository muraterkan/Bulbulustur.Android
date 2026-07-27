package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateTotalFloorsDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateTotalFloorsRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateTotalFloorsInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateTotalFloorsUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateTotalFloorsRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateTotalFloorsRepository {

    override suspend fun GetSystemDescRealestateTotalFloorssAsync(): Result<List<SystemDescRealestateTotalFloorsDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateTotalFloorssAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateTotalFloorsByIdAsync(systemDescRealestateTotalFloorsId: Int): Result<SystemDescRealestateTotalFloorsUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateTotalFloorsByIdAsync",
            query = "systemDescRealestateTotalFloorsId=$systemDescRealestateTotalFloorsId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateTotalFloorsInsertModel): Result<SystemDescRealestateTotalFloorsInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateTotalFloorsAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateTotalFloorsUpdateModel): Result<SystemDescRealestateTotalFloorsUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateTotalFloorsAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateTotalFloorsId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateTotalFloorsAsync",
            query = "systemDescRealestateTotalFloorsId=$systemDescRealestateTotalFloorsId"
        )
    }
}