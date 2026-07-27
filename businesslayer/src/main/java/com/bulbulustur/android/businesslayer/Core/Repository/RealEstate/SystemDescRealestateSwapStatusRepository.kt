package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateSwapStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateSwapStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateSwapStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateSwapStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateSwapStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateSwapStatusRepository {

    override suspend fun GetSystemDescRealestateSwapStatussAsync(): Result<List<SystemDescRealestateSwapStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateSwapStatussAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateSwapStatusByIdAsync(systemDescRealestateSwapStatusId: Int): Result<SystemDescRealestateSwapStatusUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateSwapStatusByIdAsync",
            query = "systemDescRealestateSwapStatusId=$systemDescRealestateSwapStatusId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateSwapStatusInsertModel): Result<SystemDescRealestateSwapStatusInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateSwapStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateSwapStatusUpdateModel): Result<SystemDescRealestateSwapStatusUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateSwapStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateSwapStatusId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateSwapStatusAsync",
            query = "systemDescRealestateSwapStatusId=$systemDescRealestateSwapStatusId"
        )
    }
}