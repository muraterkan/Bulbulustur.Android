package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryUpdateModel

interface IProductCategoryRepository {

    suspend fun GetProductCategoryListAsync(): Result<List<ProductCategoryDTO>>

    suspend fun GetProductCategoryByIdAsync(
        productCategoryId: Int
    ): Result<ProductCategoryUpdateModel?>

    suspend fun GetProductCategoryByIdExtendedAsync(
        productCategoryId: Int
    ): Result<ProductCategoryDTO?>
}
