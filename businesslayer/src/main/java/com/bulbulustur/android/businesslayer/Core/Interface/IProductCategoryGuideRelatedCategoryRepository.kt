package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideRelatedCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideRelatedCategoryUpdateModel

interface IProductCategoryGuideRelatedCategoryRepository {

    suspend fun GetProductCategoryGuideRelatedCategoryListAsync(): Result<List<ProductCategoryGuideRelatedCategoryDTO>>

    suspend fun GetProductCategoryGuideRelatedCategoryByIdAsync(
        productCategoryGuideRelatedCategoryId: Int
    ): Result<ProductCategoryGuideRelatedCategoryUpdateModel?>

    suspend fun GetProductCategoryGuideRelatedCategoryByIdExtendedAsync(
        productCategoryGuideRelatedCategoryId: Int
    ): Result<ProductCategoryGuideRelatedCategoryDTO?>
}
