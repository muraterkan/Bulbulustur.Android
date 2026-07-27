package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGuestFrequencyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescGuestFrequencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescGuestFrequencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescGuestFrequencyRepository {

    suspend fun GetSystemDescGuestFrequenciesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescGuestFrequencyDTO>>

    suspend fun GetSystemDescGuestFrequencyByIdAsync(
        systemDescGuestFrequencyId: Int
    ): Result<SystemDescGuestFrequencyUpdateModel?>

    suspend fun GetSystemDescGuestFrequencyByIdExtendedAsync(
        languageId: Int,
        systemDescGuestFrequencyId: Int
    ): Result<SystemDescGuestFrequencyDTO?>

    suspend fun InsertAsync(
        model: SystemDescGuestFrequencyInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescGuestFrequencyUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescGuestFrequencyId: Int
    ): Result<Unit>
}