package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestFileDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBuyerRequestFileRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BuyerRequestFileInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestFileUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class BuyerRequestFileRepository(
    private val apiClient: ApiClient = ApiClient
) : IBuyerRequestFileRepository {

    override suspend fun GetBuyerRequestFileListAsync(): Result<List<BuyerRequestFileDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBuyerRequestFileListAsync"
        )
    }

    override suspend fun GetBuyerRequestFileByIdAsync(
        buyerRequestFileId: Int
    ): Result<BuyerRequestFileUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBuyerRequestFileByIdAsync",
            query = "buyerRequestFileId=$buyerRequestFileId"
        )
    }

    override suspend fun GetBuyerRequestFileByIdExtendedAsync(
        buyerRequestFileId: Int
    ): Result<BuyerRequestFileDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetBuyerRequestFileByIdExtendedAsync",
            query = "buyerRequestFileId=$buyerRequestFileId"
        )
    }

    override suspend fun InsertAsync(
        model: BuyerRequestFileInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: BuyerRequestFileUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        buyerRequestFileId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "buyerRequestFileId=$buyerRequestFileId"
        )
    }
}