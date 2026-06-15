package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductRepository {

    override suspend fun GetWholesaleProductListAsync(): Result<List<WholesaleProductDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductListAsync"
        )
    }

    override suspend fun GetWholesaleProductByIdAsync(
        wholesaleProductId: Int
    ): Result<WholesaleProductUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductByIdAsync",
            query = "wholesaleProductId=$wholesaleProductId"
        )
    }

    override suspend fun GetWholesaleProductByIdExtendedAsync(
        wholesaleProductId: Int
    ): Result<WholesaleProductDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductByIdExtendedAsync",
            query = "wholesaleProductId=$wholesaleProductId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductId=$wholesaleProductId"
        )
    }
}