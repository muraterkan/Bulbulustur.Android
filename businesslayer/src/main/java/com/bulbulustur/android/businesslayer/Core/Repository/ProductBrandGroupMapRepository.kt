package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandGroupMapDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandGroupMapRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrandGroupMapInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandGroupMapUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductBrandGroupMapRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductBrandGroupMapRepository {

    override suspend fun GetProductBrandGroupMapListAsync(): Result<List<ProductBrandGroupMapDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandGroupMapListAsync"
        )
    }

    override suspend fun GetProductBrandGroupMapByIdAsync(
        productBrandGroupMapId: Int
    ): Result<ProductBrandGroupMapUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandGroupMapByIdAsync",
            query = "productBrandGroupMapId=$productBrandGroupMapId"
        )
    }

    override suspend fun GetProductBrandGroupMapByIdExtendedAsync(
        productBrandGroupMapId: Int
    ): Result<ProductBrandGroupMapDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandGroupMapByIdExtendedAsync",
            query = "productBrandGroupMapId=$productBrandGroupMapId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductBrandGroupMapInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductBrandGroupMapUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productBrandGroupMapId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productBrandGroupMapId=$productBrandGroupMapId"
        )
    }
}