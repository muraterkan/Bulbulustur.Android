package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IDealsOfTheDayRepository {
    suspend fun GetDealsOfTheDaysAsync(languageId: Int, count: Int = 8): Result<List<DealsOfTheDayDTO>>
    suspend fun GetDealsOfTheDaysByProductCategoryListAsync(languageId: Int, productCategoryIds: List<Int>, count: Int = 8): Result<List<DealsOfTheDayDTO>>
    suspend fun GetDealsOfTheDaysByProductCategoryListPagedAsync(languageId: Int, productCategoryIds: List<Int>, page: Int, pageSize: Int): Result<PaginatedList<DealsOfTheDayDTO>>
}
