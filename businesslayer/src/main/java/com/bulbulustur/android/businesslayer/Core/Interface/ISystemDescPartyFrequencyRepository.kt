package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPartyFrequencyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPartyFrequencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPartyFrequencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescPartyFrequencyRepository {

    suspend fun GetSystemDescPartyFrequenciesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPartyFrequencyDTO>>

    suspend fun GetSystemDescPartyFrequencyByIdAsync(
        systemDescPartyFrequencyId: Int
    ): Result<SystemDescPartyFrequencyUpdateModel?>

    suspend fun GetSystemDescPartyFrequencyByIdExtendedAsync(
        languageId: Int,
        systemDescPartyFrequencyId: Int
    ): Result<SystemDescPartyFrequencyDTO?>

    suspend fun InsertAsync(
        model: SystemDescPartyFrequencyInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescPartyFrequencyUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescPartyFrequencyId: Int
    ): Result<Unit>
}