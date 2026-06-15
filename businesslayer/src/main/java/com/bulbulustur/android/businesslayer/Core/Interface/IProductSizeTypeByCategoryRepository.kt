package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductSizeTypeByCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductSizeTypeByCategoryUpdateModel

interface IProductSizeTypeByCategoryRepository {

    suspend fun GetProductSizeTypeByCategoryListAsync(): Result<List<ProductSizeTypeByCategoryDTO>>

    suspend fun GetProductSizeTypeByCategoryByIdAsync(
        productSizeTypeByCategoryId: Int
    ): Result<ProductSizeTypeByCategoryUpdateModel?>

    suspend fun GetProductSizeTypeByCategoryByIdExtendedAsync(
        productSizeTypeByCategoryId: Int
    ): Result<ProductSizeTypeByCategoryDTO?>
}
