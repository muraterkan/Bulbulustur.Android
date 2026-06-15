package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductRelatedRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductRelatedInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductRelatedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductRelatedRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductRelatedRepository {

    override suspend fun GetWholesaleProductRelatedListAsync(): Result<List<WholesaleProductRelatedDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductRelatedListAsync"
        )
    }

    override suspend fun GetWholesaleProductRelatedByIdAsync(
        wholesaleProductRelatedId: Int
    ): Result<WholesaleProductRelatedUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductRelatedByIdAsync",
            query = "wholesaleProductRelatedId=$wholesaleProductRelatedId"
        )
    }

    override suspend fun GetWholesaleProductRelatedByIdExtendedAsync(
        wholesaleProductRelatedId: Int
    ): Result<WholesaleProductRelatedDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductRelatedByIdExtendedAsync",
            query = "wholesaleProductRelatedId=$wholesaleProductRelatedId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductRelatedInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductRelatedUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductRelatedId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductRelatedId=$wholesaleProductRelatedId"
        )
    }
}