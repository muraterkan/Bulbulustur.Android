package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandSectionDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IProductBrandSectionRepository {

    suspend fun GetProductBrandSectionsAsync(
            languageId: Int,
            count: Int = 5
    ): Result<List<ProductBrandSectionDTO>>

    suspend fun GetProductBrandSectionsAsync(
            languageId: Int,
            brandId: Int,
            count: Int = 5
    ): Result<List<ProductBrandSectionDTO>>
}