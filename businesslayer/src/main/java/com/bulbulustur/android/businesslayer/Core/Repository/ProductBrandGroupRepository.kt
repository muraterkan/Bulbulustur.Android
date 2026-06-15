package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandGroupDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandGroupRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrandGroupInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductBrandGroupRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductBrandGroupRepository {

    override suspend fun GetProductBrandGroupListAsync(): Result<List<ProductBrandGroupDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandGroupListAsync"
        )
    }

    override suspend fun GetProductBrandGroupByIdAsync(
        productBrandGroupId: Int
    ): Result<ProductBrandGroupUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandGroupByIdAsync",
            query = "productBrandGroupId=$productBrandGroupId"
        )
    }

    override suspend fun GetProductBrandGroupByIdExtendedAsync(
        productBrandGroupId: Int
    ): Result<ProductBrandGroupDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBrandGroupByIdExtendedAsync",
            query = "productBrandGroupId=$productBrandGroupId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductBrandGroupInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductBrandGroupUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productBrandGroupId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productBrandGroupId=$productBrandGroupId"
        )
    }
}