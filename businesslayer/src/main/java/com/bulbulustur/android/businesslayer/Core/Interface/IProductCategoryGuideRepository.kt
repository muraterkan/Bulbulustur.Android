package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryGuideDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryGuideUpdateModel

interface IProductCategoryGuideRepository {

    suspend fun GetProductCategoryGuideListAsync(): Result<List<ProductCategoryGuideDTO>>

    suspend fun GetProductCategoryGuideByIdAsync(
        productCategoryGuideId: Int
    ): Result<ProductCategoryGuideUpdateModel?>

    suspend fun GetProductCategoryGuideByIdExtendedAsync(
        productCategoryGuideId: Int
    ): Result<ProductCategoryGuideDTO?>
}
