package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleHomepageSpecialContentRepository {
    suspend fun GetHomepageSpecialContents(languageId: Int, count: Int = 6): Result<List<WholesaleHomepageSpecialContentDTO>>
}