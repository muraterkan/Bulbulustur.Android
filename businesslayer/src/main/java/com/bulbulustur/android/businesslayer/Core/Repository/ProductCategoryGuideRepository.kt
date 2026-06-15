package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryGuideRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryGuideInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCategoryGuideRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductCategoryGuideRepository {

    override suspend fun GetProductCategoryGuideListAsync(): Result<List<ProductCategoryGuideDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideListAsync"
        )
    }

    override suspend fun GetProductCategoryGuideByIdAsync(
        productCategoryGuideId: Int
    ): Result<ProductCategoryGuideUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideByIdAsync",
            query = "productCategoryGuideId=$productCategoryGuideId"
        )
    }

    override suspend fun GetProductCategoryGuideByIdExtendedAsync(
        productCategoryGuideId: Int
    ): Result<ProductCategoryGuideDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategoryGuideByIdExtendedAsync",
            query = "productCategoryGuideId=$productCategoryGuideId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductCategoryGuideInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductCategoryGuideUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productCategoryGuideId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productCategoryGuideId=$productCategoryGuideId"
        )
    }
}