package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategorySliderPageDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategorySliderPageUpdateModel

interface IProductCategorySliderPageRepository {

    suspend fun GetProductCategorySliderPageListAsync(): Result<List<ProductCategorySliderPageDTO>>

    suspend fun GetProductCategorySliderPageByIdAsync(
        productCategorySliderPageId: Int
    ): Result<ProductCategorySliderPageUpdateModel?>

    suspend fun GetProductCategorySliderPageByIdExtendedAsync(
        productCategorySliderPageId: Int
    ): Result<ProductCategorySliderPageDTO?>
}
