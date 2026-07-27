package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateBuildingTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateBuildingTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateBuildingTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateBuildingTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateBuildingTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateBuildingTypeRepository {

    override suspend fun GetSystemDescRealestateBuildingTypesAsync(): Result<List<SystemDescRealestateBuildingTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateBuildingTypesAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateBuildingTypeByIdAsync(systemDescRealestateBuildingTypeId: Int): Result<SystemDescRealestateBuildingTypeUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateBuildingTypeByIdAsync",
            query = "systemDescRealestateBuildingTypeId=$systemDescRealestateBuildingTypeId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateBuildingTypeInsertModel): Result<SystemDescRealestateBuildingTypeInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateBuildingTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateBuildingTypeUpdateModel): Result<SystemDescRealestateBuildingTypeUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateBuildingTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateBuildingTypeId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateBuildingTypeAsync",
            query = "systemDescRealestateBuildingTypeId=$systemDescRealestateBuildingTypeId"
        )
    }
}