package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBuyTogetherDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBuyTogetherRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBuyTogetherInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBuyTogetherUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductBuyTogetherRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductBuyTogetherRepository {

    override suspend fun GetProductBuyTogetherListAsync(): Result<List<ProductBuyTogetherDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBuyTogetherListAsync"
        )
    }

    override suspend fun GetProductBuyTogetherByIdAsync(
        productBuyTogetherId: Int
    ): Result<ProductBuyTogetherUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBuyTogetherByIdAsync",
            query = "productBuyTogetherId=$productBuyTogetherId"
        )
    }

    override suspend fun GetProductBuyTogetherByIdExtendedAsync(
        productBuyTogetherId: Int
    ): Result<ProductBuyTogetherDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductBuyTogetherByIdExtendedAsync",
            query = "productBuyTogetherId=$productBuyTogetherId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductBuyTogetherInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductBuyTogetherUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productBuyTogetherId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productBuyTogetherId=$productBuyTogetherId"
        )
    }
}