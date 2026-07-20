package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescChildrenPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescChildrenPreferenceRepository {

    suspend fun GetChildrenPreferencesAsync(languageId: Int, count: Int): Result<List<SystemDescChildrenPreferenceDTO>>
}
