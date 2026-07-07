package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IProductHomepageSpecialContentRepository {
    suspend fun GetHomepageSpecialContentsAsync(languageId: Int, count: Int = 5): Result<List<ProductHomepageSpecialContentDTO>>
}
