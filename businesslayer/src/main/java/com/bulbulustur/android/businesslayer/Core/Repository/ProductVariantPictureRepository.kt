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

    override suspend fun GetProductVariantPicturesAsync(
        variantId: Int,
        count: Int
    ): Result<List<ProductVariantPictureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_PICTURE_BASE_URL,
            method = "GetProductVariantPicturesAsync",
            query = "variantId=$variantId&count=$count"
        )
    }

    override suspend fun GetProductVariantPictureByIdAsync(
        productVariantPictureId: Int
    ): Result<ProductVariantPictureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_PICTURE_BASE_URL,
            method = "GetProductVariantPictureByIdAsync",
            query = "productVariantPictureId=$productVariantPictureId"
        )
    }

    override suspend fun GetProductVariantPictureByIdExtendedAsync(
        productVariantPictureId: Int
    ): Result<ProductVariantPictureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2C_PRODUCT_VARIANT_PICTURE_BASE_URL,
            method = "GetProductVariantPictureByIdExtendedAsync",
            query = "productVariantPictureId=$productVariantPictureId"
        )
    }
}