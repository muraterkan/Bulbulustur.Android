package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPaymentTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPaymentTypeRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPaymentTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPaymentTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SystemDescPaymentTypeRepository(
    private val apiClient: ApiClient = ApiClient
) : ISystemDescPaymentTypeRepository {

    override suspend fun GetSystemDescPaymentTypeListAsync(): Result<List<SystemDescPaymentTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPaymentTypeListAsync"
        )
    }

    override suspend fun GetSystemDescPaymentTypeByIdAsync(
        systemDescPaymentTypeId: Int
    ): Result<SystemDescPaymentTypeUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPaymentTypeByIdAsync",
            query = "systemDescPaymentTypeId=$systemDescPaymentTypeId"
        )
    }

    override suspend fun GetSystemDescPaymentTypeByIdExtendedAsync(
        systemDescPaymentTypeId: Int
    ): Result<SystemDescPaymentTypeDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSystemDescPaymentTypeByIdExtendedAsync",
            query = "systemDescPaymentTypeId=$systemDescPaymentTypeId"
        )
    }

    override suspend fun InsertAsync(
        model: SystemDescPaymentTypeInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SystemDescPaymentTypeUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        systemDescPaymentTypeId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "systemDescPaymentTypeId=$systemDescPaymentTypeId"
        )
    }
}