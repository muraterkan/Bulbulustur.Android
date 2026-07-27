package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateBuildingAgeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateBuildingAgeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateBuildingAgeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateBuildingAgeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateBuildingAgeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateBuildingAgeRepository {

    override suspend fun GetSystemDescRealestateBuildingAgesAsync(): Result<List<SystemDescRealestateBuildingAgeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateBuildingAgesAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateBuildingAgeByIdAsync(systemDescRealestateBuildingAgeId: Int): Result<SystemDescRealestateBuildingAgeUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateBuildingAgeByIdAsync",
            query = "systemDescRealestateBuildingAgeId=$systemDescRealestateBuildingAgeId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateBuildingAgeInsertModel): Result<SystemDescRealestateBuildingAgeInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateBuildingAgeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateBuildingAgeUpdateModel): Result<SystemDescRealestateBuildingAgeUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateBuildingAgeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateBuildingAgeId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateBuildingAgeAsync",
            query = "systemDescRealestateBuildingAgeId=$systemDescRealestateBuildingAgeId"
        )
    }
}