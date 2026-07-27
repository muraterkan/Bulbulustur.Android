package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateCreditEligibilityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateCreditEligibilityRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateCreditEligibilityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateCreditEligibilityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateCreditEligibilityRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateCreditEligibilityRepository {

    override suspend fun GetSystemDescRealestateCreditEligibilitysAsync(): Result<List<SystemDescRealestateCreditEligibilityDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateCreditEligibilitysAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateCreditEligibilityByIdAsync(systemDescRealestateCreditEligibilityId: Int): Result<SystemDescRealestateCreditEligibilityUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateCreditEligibilityByIdAsync",
            query = "systemDescRealestateCreditEligibilityId=$systemDescRealestateCreditEligibilityId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateCreditEligibilityInsertModel): Result<SystemDescRealestateCreditEligibilityInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateCreditEligibilityAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateCreditEligibilityUpdateModel): Result<SystemDescRealestateCreditEligibilityUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateCreditEligibilityAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateCreditEligibilityId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateCreditEligibilityAsync",
            query = "systemDescRealestateCreditEligibilityId=$systemDescRealestateCreditEligibilityId"
        )
    }
}