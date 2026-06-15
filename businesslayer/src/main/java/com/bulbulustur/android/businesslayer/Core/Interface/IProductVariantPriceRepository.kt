package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPriceDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPriceUpdateModel

interface IProductVariantPriceRepository {

    suspend fun GetProductVariantPriceListAsync(): Result<List<ProductVariantPriceDTO>>

    suspend fun GetProductVariantPriceByIdAsync(
        productVariantPriceId: Int
    ): Result<ProductVariantPriceUpdateModel?>

    suspend fun GetProductVariantPriceByIdExtendedAsync(
        productVariantPriceId: Int
    ): Result<ProductVariantPriceDTO?>
}
