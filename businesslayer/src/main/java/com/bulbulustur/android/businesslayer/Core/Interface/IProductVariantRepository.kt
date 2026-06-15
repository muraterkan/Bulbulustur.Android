package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantUpdateModel

interface IProductVariantRepository {

    suspend fun GetProductVariantListAsync(): Result<List<ProductVariantDTO>>

    suspend fun GetProductVariantByIdAsync(
        variantId: Int
    ): Result<ProductVariantUpdateModel?>

    suspend fun GetProductVariantByIdExtendedAsync(
        variantId: Int
    ): Result<ProductVariantDTO?>
}
