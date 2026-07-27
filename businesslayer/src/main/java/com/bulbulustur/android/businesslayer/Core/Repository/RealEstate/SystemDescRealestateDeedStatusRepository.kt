package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateDeedStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateDeedStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateDeedStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateDeedStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateDeedStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateDeedStatusRepository {

    override suspend fun GetSystemDescRealestateDeedStatussAsync(): Result<List<SystemDescRealestateDeedStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateDeedStatussAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateDeedStatusByIdAsync(systemDescRealestateDeedStatusId: Int): Result<SystemDescRealestateDeedStatusUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateDeedStatusByIdAsync",
            query = "systemDescRealestateDeedStatusId=$systemDescRealestateDeedStatusId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateDeedStatusInsertModel): Result<SystemDescRealestateDeedStatusInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateDeedStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateDeedStatusUpdateModel): Result<SystemDescRealestateDeedStatusUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateDeedStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateDeedStatusId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateDeedStatusAsync",
            query = "systemDescRealestateDeedStatusId=$systemDescRealestateDeedStatusId"
        )
    }
}