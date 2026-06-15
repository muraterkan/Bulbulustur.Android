package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategorySliderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategorySliderRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategorySliderInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategorySliderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCategorySliderRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductCategorySliderRepository {

    override suspend fun GetProductCategorySliderListAsync(): Result<List<ProductCategorySliderDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategorySliderListAsync"
        )
    }

    override suspend fun GetProductCategorySliderByIdAsync(
        productCategorySliderId: Int
    ): Result<ProductCategorySliderUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategorySliderByIdAsync",
            query = "productCategorySliderId=$productCategorySliderId"
        )
    }

    override suspend fun GetProductCategorySliderByIdExtendedAsync(
        productCategorySliderId: Int
    ): Result<ProductCategorySliderDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductCategorySliderByIdExtendedAsync",
            query = "productCategorySliderId=$productCategorySliderId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductCategorySliderInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductCategorySliderUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productCategorySliderId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productCategorySliderId=$productCategorySliderId"
        )
    }
}