package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateMortgageStatusDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateMortgageStatusRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateMortgageStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateMortgageStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateMortgageStatusRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateMortgageStatusRepository {

    override suspend fun GetSystemDescRealestateMortgageStatussAsync(): Result<List<SystemDescRealestateMortgageStatusDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateMortgageStatussAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateMortgageStatusByIdAsync(systemDescRealestateMortgageStatusId: Int): Result<SystemDescRealestateMortgageStatusUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateMortgageStatusByIdAsync",
            query = "systemDescRealestateMortgageStatusId=$systemDescRealestateMortgageStatusId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateMortgageStatusInsertModel): Result<SystemDescRealestateMortgageStatusInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateMortgageStatusAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateMortgageStatusUpdateModel): Result<SystemDescRealestateMortgageStatusUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateMortgageStatusAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateMortgageStatusId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateMortgageStatusAsync",
            query = "systemDescRealestateMortgageStatusId=$systemDescRealestateMortgageStatusId"
        )
    }
}