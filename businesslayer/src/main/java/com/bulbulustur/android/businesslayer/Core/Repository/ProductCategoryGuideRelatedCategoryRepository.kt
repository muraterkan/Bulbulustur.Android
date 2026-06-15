package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideRelatedCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryGuideRelatedCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryGuideRelatedCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideRelatedCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCategoryGuideRelatedCategoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductCategoryGuideRelatedCategoryRepository {

    override suspend fun GetProductCategoryGuideRelatedCategoryListAsync(): Result<List<ProductCategoryGuideRelatedCategoryDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideRelatedCategoryListAsync"
        )
    }

    override suspend fun GetProductCategoryGuideRelatedCategoryByIdAsync(
        productCategoryGuideRelatedCategoryId: Int
    ): Result<ProductCategoryGuideRelatedCategoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideRelatedCategoryByIdAsync",
            query = "productCategoryGuideRelatedCategoryId=$productCategoryGuideRelatedCategoryId"
        )
    }

    override suspend fun GetProductCategoryGuideRelatedCategoryByIdExtendedAsync(
        productCategoryGuideRelatedCategoryId: Int
    ): Result<ProductCategoryGuideRelatedCategoryDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideRelatedCategoryByIdExtendedAsync",
            query = "productCategoryGuideRelatedCategoryId=$productCategoryGuideRelatedCategoryId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductCategoryGuideRelatedCategoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductCategoryGuideRelatedCategoryUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productCategoryGuideRelatedCategoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productCategoryGuideRelatedCategoryId=$productCategoryGuideRelatedCategoryId"
        )
    }
}