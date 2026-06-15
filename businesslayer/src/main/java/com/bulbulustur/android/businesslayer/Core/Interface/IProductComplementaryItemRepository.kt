package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplementaryItemDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplementaryItemUpdateModel

interface IProductComplementaryItemRepository {

    suspend fun GetProductComplementaryItemListAsync(): Result<List<ProductComplementaryItemDTO>>

    suspend fun GetProductComplementaryItemByIdAsync(
        complementaryItemId: Int
    ): Result<ProductComplementaryItemUpdateModel?>

    suspend fun GetProductComplementaryItemByIdExtendedAsync(
        complementaryItemId: Int
    ): Result<ProductComplementaryItemDTO?>
}
