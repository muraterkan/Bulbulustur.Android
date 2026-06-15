package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryPopularDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryPopularRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryPopularInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryPopularUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCategoryPopularRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductCategoryPopularRepository {

    override suspend fun GetProductCategoryPopularListAsync(): Result<List<ProductCategoryPopularDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryPopularListAsync"
        )
    }

    override suspend fun GetProductCategoryPopularByIdAsync(
        productCategoryPopularId: Int
    ): Result<ProductCategoryPopularUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryPopularByIdAsync",
            query = "productCategoryPopularId=$productCategoryPopularId"
        )
    }

    override suspend fun GetProductCategoryPopularByIdExtendedAsync(
        productCategoryPopularId: Int
    ): Result<ProductCategoryPopularDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryPopularByIdExtendedAsync",
            query = "productCategoryPopularId=$productCategoryPopularId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductCategoryPopularInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductCategoryPopularUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productCategoryPopularId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productCategoryPopularId=$productCategoryPopularId"
        )
    }
}