package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateInSiteStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateInSiteStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateInSiteStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateInSiteStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateInSiteStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateInSiteStatusRepository {

    override suspend fun GetSystemDescRealestateInSiteStatussAsync(): Result<List<SystemDescRealestateInSiteStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateInSiteStatussAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateInSiteStatusByIdAsync(systemDescRealestateInSiteStatusId: Int): Result<SystemDescRealestateInSiteStatusUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateInSiteStatusByIdAsync",
            query = "systemDescRealestateInSiteStatusId=$systemDescRealestateInSiteStatusId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateInSiteStatusInsertModel): Result<SystemDescRealestateInSiteStatusInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateInSiteStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateInSiteStatusUpdateModel): Result<SystemDescRealestateInSiteStatusUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateInSiteStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateInSiteStatusId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateInSiteStatusAsync",
            query = "systemDescRealestateInSiteStatusId=$systemDescRealestateInSiteStatusId"
        )
    }
}