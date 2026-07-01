package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IProductCategoryRepository {

    suspend fun GetProductCategoryListAsync():
            Result<List<ProductCategoryDTO>>

    suspend fun GetProductCategoryByIdAsync(
        productCategoryId: Int
    ): Result<ProductCategoryUpdateModel?>

    suspend fun GetProductCategoryByIdExtendedAsync(
        productCategoryId: Int
    ): Result<ProductCategoryDTO?>

    suspend fun GetProductChildCategoriesAsync(
        languageId: Int,
        productCategoryId: Int
    ): Result<List<ProductCategoryDTO>>

    suspend fun InsertAsync(
        model: ProductCategoryInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: ProductCategoryUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        productCategoryId: Int
    ): Result<Unit>
}