package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPriceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductPriceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductPriceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductPriceRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductPriceRepository {

    override suspend fun GetWholesaleProductPriceListAsync(): Result<List<WholesaleProductPriceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPriceListAsync"
        )
    }

    override suspend fun GetWholesaleProductPriceByIdAsync(
        wholesaleProductPriceId: Int
    ): Result<WholesaleProductPriceUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPriceByIdAsync",
            query = "wholesaleProductPriceId=$wholesaleProductPriceId"
        )
    }

    override suspend fun GetWholesaleProductPriceByIdExtendedAsync(
        wholesaleProductPriceId: Int
    ): Result<WholesaleProductPriceDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPriceByIdExtendedAsync",
            query = "wholesaleProductPriceId=$wholesaleProductPriceId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductPriceInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductPriceUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductPriceId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductPriceId=$wholesaleProductPriceId"
        )
    }
}