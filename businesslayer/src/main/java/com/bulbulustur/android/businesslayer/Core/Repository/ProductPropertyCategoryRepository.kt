package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductPropertyCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductPropertyCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductPropertyCategoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductPropertyCategoryRepository {

    override suspend fun GetProductPropertyCategoryListAsync(): Result<List<ProductPropertyCategoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyCategoryListAsync"
        )
    }

    override suspend fun GetProductPropertyCategoryByIdAsync(
        productPropertyCategoryId: Int
    ): Result<ProductPropertyCategoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyCategoryByIdAsync",
            query = "productPropertyCategoryId=$productPropertyCategoryId"
        )
    }

    override suspend fun GetProductPropertyCategoryByIdExtendedAsync(
        productPropertyCategoryId: Int
    ): Result<ProductPropertyCategoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductPropertyCategoryByIdExtendedAsync",
            query = "productPropertyCategoryId=$productPropertyCategoryId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductPropertyCategoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductPropertyCategoryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productPropertyCategoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productPropertyCategoryId=$productPropertyCategoryId"
        )
    }
}