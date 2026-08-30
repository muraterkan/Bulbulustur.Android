package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandCategoryMapDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IProductBrandCategoryMapRepository {

    suspend fun GetProductBrandCategoryMaps(
        productCategoryId: Int,
        count: Int = 30
    ): Result<List<ProductBrandCategoryMapDTO>>
}