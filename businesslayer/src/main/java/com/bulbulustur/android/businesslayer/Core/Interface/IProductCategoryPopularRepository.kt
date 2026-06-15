package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryPopularDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryPopularUpdateModel

interface IProductCategoryPopularRepository {

    suspend fun GetProductCategoryPopularListAsync(): Result<List<ProductCategoryPopularDTO>>

    suspend fun GetProductCategoryPopularByIdAsync(
        productCategoryPopularId: Int
    ): Result<ProductCategoryPopularUpdateModel?>

    suspend fun GetProductCategoryPopularByIdExtendedAsync(
        productCategoryPopularId: Int
    ): Result<ProductCategoryPopularDTO?>
}
