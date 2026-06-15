package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPropertyValueDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductPropertyValueRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductPropertyValueInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPropertyValueUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductPropertyValueRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductPropertyValueRepository {

    override suspend fun GetWholesaleProductPropertyValueListAsync(): Result<List<WholesaleProductPropertyValueDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPropertyValueListAsync"
        )
    }

    override suspend fun GetWholesaleProductPropertyValueByIdAsync(
        wholesaleProductPropertyValueId: Int
    ): Result<WholesaleProductPropertyValueUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPropertyValueByIdAsync",
            query = "wholesaleProductPropertyValueId=$wholesaleProductPropertyValueId"
        )
    }

    override suspend fun GetWholesaleProductPropertyValueByIdExtendedAsync(
        wholesaleProductPropertyValueId: Int
    ): Result<WholesaleProductPropertyValueDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPropertyValueByIdExtendedAsync",
            query = "wholesaleProductPropertyValueId=$wholesaleProductPropertyValueId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductPropertyValueInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductPropertyValueUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductPropertyValueId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductPropertyValueId=$wholesaleProductPropertyValueId"
        )
    }
}