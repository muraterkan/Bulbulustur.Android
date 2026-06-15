package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRelatedRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductRelatedInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductRelatedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductRelatedRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductRelatedRepository {

    override suspend fun GetProductRelatedListAsync(): Result<List<ProductRelatedDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductRelatedListAsync"
        )
    }

    override suspend fun GetProductRelatedByIdAsync(
        productRelatedId: Int
    ): Result<ProductRelatedUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductRelatedByIdAsync",
            query = "productRelatedId=$productRelatedId"
        )
    }

    override suspend fun GetProductRelatedByIdExtendedAsync(
        productRelatedId: Int
    ): Result<ProductRelatedDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductRelatedByIdExtendedAsync",
            query = "productRelatedId=$productRelatedId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductRelatedInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductRelatedUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productRelatedId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productRelatedId=$productRelatedId"
        )
    }
}