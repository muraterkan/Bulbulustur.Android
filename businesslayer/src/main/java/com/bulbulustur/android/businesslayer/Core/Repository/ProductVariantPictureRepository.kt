package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductVariantPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductVariantPictureRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductVariantPictureRepository {

    override suspend fun GetProductVariantPictureListAsync(): Result<List<ProductVariantPictureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPictureListAsync"
        )
    }

    override suspend fun GetProductVariantPictureByIdAsync(
        productVariantPictureId: Int
    ): Result<ProductVariantPictureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPictureByIdAsync",
            query = "productVariantPictureId=$productVariantPictureId"
        )
    }

    override suspend fun GetProductVariantPictureByIdExtendedAsync(
        productVariantPictureId: Int
    ): Result<ProductVariantPictureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetProductVariantPictureByIdExtendedAsync",
            query = "productVariantPictureId=$productVariantPictureId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductVariantPictureInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductVariantPictureUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        productVariantPictureId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "productVariantPictureId=$productVariantPictureId"
        )
    }
}