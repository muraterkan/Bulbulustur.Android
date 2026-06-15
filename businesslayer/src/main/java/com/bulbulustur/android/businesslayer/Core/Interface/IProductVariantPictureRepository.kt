package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPictureUpdateModel

interface IProductVariantPictureRepository {

    suspend fun GetProductVariantPictureListAsync(): Result<List<ProductVariantPictureDTO>>

    suspend fun GetProductVariantPictureByIdAsync(
        productVariantPictureId: Int
    ): Result<ProductVariantPictureUpdateModel?>

    suspend fun GetProductVariantPictureByIdExtendedAsync(
        productVariantPictureId: Int
    ): Result<ProductVariantPictureDTO?>
}
