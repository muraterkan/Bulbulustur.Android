package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IProductCategoryContentRepository {
    suspend fun GetProductCategoryContentsAsync(languageId: Int, productCategoryId: Int, groupCount: Int = 3, productCount: Int = 4): Result<ProductCategoryContentDTO>
    suspend fun GetProductCategoryContentsPagedAsync(productCategoryContentGroupId: Int, page: Int = 1, pageSize: Int = 20): Result<PaginatedList<ProductCategoryContentDTO>>
}
