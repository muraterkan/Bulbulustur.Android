package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductStatisticDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductStatisticRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductStatisticInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductStatisticUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductStatisticRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductStatisticRepository {

    override suspend fun GetWholesaleProductStatisticListAsync(): Result<List<WholesaleProductStatisticDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductStatisticListAsync"
        )
    }

    override suspend fun GetWholesaleProductStatisticByIdAsync(
        wholesaleProductStatisticId: Int
    ): Result<WholesaleProductStatisticUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductStatisticByIdAsync",
            query = "wholesaleProductStatisticId=$wholesaleProductStatisticId"
        )
    }

    override suspend fun GetWholesaleProductStatisticByIdExtendedAsync(
        wholesaleProductStatisticId: Int
    ): Result<WholesaleProductStatisticDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductStatisticByIdExtendedAsync",
            query = "wholesaleProductStatisticId=$wholesaleProductStatisticId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductStatisticInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductStatisticUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductStatisticId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductStatisticId=$wholesaleProductStatisticId"
        )
    }
}