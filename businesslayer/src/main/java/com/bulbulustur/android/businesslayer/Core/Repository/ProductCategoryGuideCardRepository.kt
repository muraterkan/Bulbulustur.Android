package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideCardDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryGuideCardRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryGuideCardInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideCardUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCategoryGuideCardRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductCategoryGuideCardRepository {

    override suspend fun GetProductCategoryGuideCardListAsync(): Result<List<ProductCategoryGuideCardDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideCardListAsync"
        )
    }

    override suspend fun GetProductCategoryGuideCardByIdAsync(
        productCategoryGuideCardId: Int
    ): Result<ProductCategoryGuideCardUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideCardByIdAsync",
            query = "productCategoryGuideCardId=$productCategoryGuideCardId"
        )
    }

    override suspend fun GetProductCategoryGuideCardByIdExtendedAsync(
        productCategoryGuideCardId: Int
    ): Result<ProductCategoryGuideCardDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideCardByIdExtendedAsync",
            query = "productCategoryGuideCardId=$productCategoryGuideCardId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductCategoryGuideCardInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductCategoryGuideCardUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productCategoryGuideCardId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productCategoryGuideCardId=$productCategoryGuideCardId"
        )
    }
}