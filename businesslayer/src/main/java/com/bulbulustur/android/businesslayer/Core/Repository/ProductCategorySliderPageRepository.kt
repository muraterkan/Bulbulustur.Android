package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategorySliderPageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategorySliderPageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategorySliderPageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategorySliderPageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCategorySliderPageRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductCategorySliderPageRepository {

    override suspend fun GetProductCategorySliderPageListAsync(): Result<List<ProductCategorySliderPageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategorySliderPageListAsync"
        )
    }

    override suspend fun GetProductCategorySliderPageByIdAsync(
        productCategorySliderPageId: Int
    ): Result<ProductCategorySliderPageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategorySliderPageByIdAsync",
            query = "productCategorySliderPageId=$productCategorySliderPageId"
        )
    }

    override suspend fun GetProductCategorySliderPageByIdExtendedAsync(
        productCategorySliderPageId: Int
    ): Result<ProductCategorySliderPageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategorySliderPageByIdExtendedAsync",
            query = "productCategorySliderPageId=$productCategorySliderPageId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductCategorySliderPageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductCategorySliderPageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productCategorySliderPageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productCategorySliderPageId=$productCategorySliderPageId"
        )
    }
}