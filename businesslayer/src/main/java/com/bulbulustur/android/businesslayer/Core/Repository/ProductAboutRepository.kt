package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductAboutDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductAboutRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductAboutInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductAboutUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductAboutRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductAboutRepository {

    override suspend fun GetProductAboutListAsync(): Result<List<ProductAboutDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductAboutListAsync"
        )
    }

    override suspend fun GetProductAboutByIdAsync(
        productAboutId: Int
    ): Result<ProductAboutUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductAboutByIdAsync",
            query = "productAboutId=$productAboutId"
        )
    }

    override suspend fun GetProductAboutByIdExtendedAsync(
        productAboutId: Int
    ): Result<ProductAboutDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductAboutByIdExtendedAsync",
            query = "productAboutId=$productAboutId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductAboutInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductAboutUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productAboutId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productAboutId=$productAboutId"
        )
    }
}