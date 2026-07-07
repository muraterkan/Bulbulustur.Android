package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IDealsOfTheDayRepository {
    suspend fun GetDealsOfTheDaysAsync(languageId: Int, count: Int = 8): Result<List<DealsOfTheDayDTO>>
}
