package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateParkingTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateParkingTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateParkingTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateParkingTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateParkingTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateParkingTypeRepository {

    override suspend fun GetSystemDescRealestateParkingTypesAsync(): Result<List<SystemDescRealestateParkingTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateParkingTypesAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateParkingTypeByIdAsync(systemDescRealestateParkingTypeId: Int): Result<SystemDescRealestateParkingTypeUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateParkingTypeByIdAsync",
            query = "systemDescRealestateParkingTypeId=$systemDescRealestateParkingTypeId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateParkingTypeInsertModel): Result<SystemDescRealestateParkingTypeInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateParkingTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateParkingTypeUpdateModel): Result<SystemDescRealestateParkingTypeUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateParkingTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateParkingTypeId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateParkingTypeAsync",
            query = "systemDescRealestateParkingTypeId=$systemDescRealestateParkingTypeId"
        )
    }
}