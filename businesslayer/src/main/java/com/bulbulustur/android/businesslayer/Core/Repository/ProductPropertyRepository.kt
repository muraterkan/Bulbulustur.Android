package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductPropertyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductPropertyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductPropertyRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductPropertyRepository {

    override suspend fun GetProductPropertyListAsync(): Result<List<ProductPropertyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyListAsync"
        )
    }

    override suspend fun GetProductPropertyByIdAsync(
        productPropertyId: Int
    ): Result<ProductPropertyUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyByIdAsync",
            query = "productPropertyId=$productPropertyId"
        )
    }

    override suspend fun GetProductPropertyByIdExtendedAsync(
        productPropertyId: Int
    ): Result<ProductPropertyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyByIdExtendedAsync",
            query = "productPropertyId=$productPropertyId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductPropertyInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductPropertyUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productPropertyId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productPropertyId=$productPropertyId"
        )
    }
}