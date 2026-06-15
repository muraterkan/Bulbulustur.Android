package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyValueDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductPropertyValueRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductPropertyValueInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyValueUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductPropertyValueRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductPropertyValueRepository {

    override suspend fun GetProductPropertyValueListAsync(): Result<List<ProductPropertyValueDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyValueListAsync"
        )
    }

    override suspend fun GetProductPropertyValueByIdAsync(
        productPropertyValueId: Int
    ): Result<ProductPropertyValueUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyValueByIdAsync",
            query = "productPropertyValueId=$productPropertyValueId"
        )
    }

    override suspend fun GetProductPropertyValueByIdExtendedAsync(
        productPropertyValueId: Int
    ): Result<ProductPropertyValueDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyValueByIdExtendedAsync",
            query = "productPropertyValueId=$productPropertyValueId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductPropertyValueInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductPropertyValueUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productPropertyValueId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productPropertyValueId=$productPropertyValueId"
        )
    }
}