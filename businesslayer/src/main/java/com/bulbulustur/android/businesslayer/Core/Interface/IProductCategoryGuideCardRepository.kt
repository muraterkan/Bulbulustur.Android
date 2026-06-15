package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideCardDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideCardUpdateModel

interface IProductCategoryGuideCardRepository {

    suspend fun GetProductCategoryGuideCardListAsync(): Result<List<ProductCategoryGuideCardDTO>>

    suspend fun GetProductCategoryGuideCardByIdAsync(
        productCategoryGuideCardId: Int
    ): Result<ProductCategoryGuideCardUpdateModel?>

    suspend fun GetProductCategoryGuideCardByIdExtendedAsync(
        productCategoryGuideCardId: Int
    ): Result<ProductCategoryGuideCardDTO?>
}
