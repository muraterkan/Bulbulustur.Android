package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPriceHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductVariantPriceHistoryUpdateModel

interface IProductVariantPriceHistoryRepository {

    suspend fun GetProductVariantPriceHistoryListAsync(): Result<List<ProductVariantPriceHistoryDTO>>

    suspend fun GetProductVariantPriceHistoryByIdAsync(
        productVariantPriceHistoryId: Int
    ): Result<ProductVariantPriceHistoryUpdateModel?>

    suspend fun GetProductVariantPriceHistoryByIdExtendedAsync(
        productVariantPriceHistoryId: Int
    ): Result<ProductVariantPriceHistoryDTO?>
}
