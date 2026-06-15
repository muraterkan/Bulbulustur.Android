package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyCategoryUpdateModel

interface IProductPropertyCategoryRepository {

    suspend fun GetProductPropertyCategoryListAsync(): Result<List<ProductPropertyCategoryDTO>>

    suspend fun GetProductPropertyCategoryByIdAsync(
        propertyCategoryId: Int
    ): Result<ProductPropertyCategoryUpdateModel?>

    suspend fun GetProductPropertyCategoryByIdExtendedAsync(
        propertyCategoryId: Int
    ): Result<ProductPropertyCategoryDTO?>
}
