package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplementaryItemDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductComplementaryItemRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductComplementaryItemInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplementaryItemUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductComplementaryItemRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductComplementaryItemRepository {

    override suspend fun GetProductComplementaryItemListAsync(): Result<List<ProductComplementaryItemDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplementaryItemListAsync"
        )
    }

    override suspend fun GetProductComplementaryItemByIdAsync(
        productComplementaryItemId: Int
    ): Result<ProductComplementaryItemUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplementaryItemByIdAsync",
            query = "productComplementaryItemId=$productComplementaryItemId"
        )
    }

    override suspend fun GetProductComplementaryItemByIdExtendedAsync(
        productComplementaryItemId: Int
    ): Result<ProductComplementaryItemDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductComplementaryItemByIdExtendedAsync",
            query = "productComplementaryItemId=$productComplementaryItemId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductComplementaryItemInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductComplementaryItemUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productComplementaryItemId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productComplementaryItemId=$productComplementaryItemId"
        )
    }
}