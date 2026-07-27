package com.bulbulustur.android.businesslayer.Core.Repository.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateSellerTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.RealEstate.ISystemDescRealestateSellerTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateSellerTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateSellerTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescRealestateSellerTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescRealestateSellerTypeRepository {

    override suspend fun GetSystemDescRealestateSellerTypesAsync(): Result<List<SystemDescRealestateSellerTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateSellerTypesAsync",
            query = ""
        )
    }

    override suspend fun GetSystemDescRealestateSellerTypeByIdAsync(systemDescRealestateSellerTypeId: Int): Result<SystemDescRealestateSellerTypeUpdateModel> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "GetSystemDescRealestateSellerTypeByIdAsync",
            query = "systemDescRealestateSellerTypeId=$systemDescRealestateSellerTypeId"
        )
    }

    override suspend fun InsertAsync(model: SystemDescRealestateSellerTypeInsertModel): Result<SystemDescRealestateSellerTypeInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "InsertSystemDescRealestateSellerTypeAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SystemDescRealestateSellerTypeUpdateModel): Result<SystemDescRealestateSellerTypeUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "UpdateSystemDescRealestateSellerTypeAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(systemDescRealestateSellerTypeId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.GLOBALIZATION_MASTER_DATA_BASE_URL,
            method = "DeleteSystemDescRealestateSellerTypeAsync",
            query = "systemDescRealestateSellerTypeId=$systemDescRealestateSellerTypeId"
        )
    }
}