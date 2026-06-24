package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ResourceDTO
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ILocalizationRepository {

    suspend fun GetResourcesAsync(
        language: EApplicationLanguage,
        count: Int = 10000
    ): Result<List<ResourceDTO>>
}