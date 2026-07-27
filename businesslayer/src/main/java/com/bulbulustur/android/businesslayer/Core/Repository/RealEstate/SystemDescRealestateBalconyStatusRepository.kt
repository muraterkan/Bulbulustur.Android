package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateBalconyStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateBalconyStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateBalconyStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateBalconyStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateBalconyStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateBalconyStatusRepository {

    override suspend fun GetSystemDescRealestateBalconyStatussAsync(): Result<List<SystemDescRealestateBalconyStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateBalconyStatussAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateBalconyStatusByIdAsync(systemDescRealestateBalconyStatusId: Int): Result<SystemDescRealestateBalconyStatusUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateBalconyStatusByIdAsync",
            query = "systemDescRealestateBalconyStatusId=$systemDescRealestateBalconyStatusId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateBalconyStatusInsertModel): Result<SystemDescRealestateBalconyStatusInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateBalconyStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateBalconyStatusUpdateModel): Result<SystemDescRealestateBalconyStatusUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateBalconyStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateBalconyStatusId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateBalconyStatusAsync",
            query = "systemDescRealestateBalconyStatusId=$systemDescRealestateBalconyStatusId"
        )
    }
}