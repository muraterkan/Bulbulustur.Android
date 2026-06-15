package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategorySliderDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategorySliderUpdateModel

interface IProductCategorySliderRepository {

    suspend fun GetProductCategorySliderListAsync(): Result<List<ProductCategorySliderDTO>>

    suspend fun GetProductCategorySliderByIdAsync(
        productCategorySliderId: Int
    ): Result<ProductCategorySliderUpdateModel?>

    suspend fun GetProductCategorySliderByIdExtendedAsync(
        productCategorySliderId: Int
    ): Result<ProductCategorySliderDTO?>
}
