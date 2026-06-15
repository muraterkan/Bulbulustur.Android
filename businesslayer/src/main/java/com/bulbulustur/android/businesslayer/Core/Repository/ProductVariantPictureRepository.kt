package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class ProductVariantPictureRepository(
    private val apiClient: ApiClient
) : IProductVariantPictureRepository {

    override suspend fun GetProductVariantPictureListAsync(): Result<List<ProductVariantPictureDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductVariantPictureByIdAsync(
        productVariantPictureId: Int
    ): Result<ProductVariantPictureUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetProductVariantPictureByIdExtendedAsync(
        productVariantPictureId: Int
    ): Result<ProductVariantPictureDTO?> {
        TODO("Not implemented yet")
    }
}
