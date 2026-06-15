package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductRelatedUpdateModel

interface IProductRelatedRepository {

    suspend fun GetProductRelatedListAsync(): Result<List<ProductRelatedDTO>>

    suspend fun GetProductRelatedByIdAsync(
        productRelatedId: Int
    ): Result<ProductRelatedUpdateModel?>

    suspend fun GetProductRelatedByIdExtendedAsync(
        productRelatedId: Int
    ): Result<ProductRelatedDTO?>
}
