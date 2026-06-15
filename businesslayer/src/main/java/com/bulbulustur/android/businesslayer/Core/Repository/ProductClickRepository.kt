package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductClickDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductClickRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductClickInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductClickUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductClickRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductClickRepository {

    override suspend fun GetProductClickListAsync(): Result<List<ProductClickDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductClickListAsync"
        )
    }

    override suspend fun GetProductClickByIdAsync(
        productClickId: Int
    ): Result<ProductClickUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductClickByIdAsync",
            query = "productClickId=$productClickId"
        )
    }

    override suspend fun GetProductClickByIdExtendedAsync(
        productClickId: Int
    ): Result<ProductClickDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductClickByIdExtendedAsync",
            query = "productClickId=$productClickId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductClickInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductClickUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productClickId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productClickId=$productClickId"
        )
    }
}