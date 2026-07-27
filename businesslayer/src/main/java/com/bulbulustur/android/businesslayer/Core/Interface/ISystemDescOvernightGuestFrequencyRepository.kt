package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOvernightGuestFrequencyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescOvernightGuestFrequencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOvernightGuestFrequencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescOvernightGuestFrequencyRepository {

    suspend fun GetSystemDescOvernightGuestFrequenciesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescOvernightGuestFrequencyDTO>>

    suspend fun GetSystemDescOvernightGuestFrequencyByIdAsync(
        systemDescOvernightGuestFrequencyId: Int
    ): Result<SystemDescOvernightGuestFrequencyUpdateModel?>

    suspend fun GetSystemDescOvernightGuestFrequencyByIdExtendedAsync(
        languageId: Int,
        systemDescOvernightGuestFrequencyId: Int
    ): Result<SystemDescOvernightGuestFrequencyDTO?>

    suspend fun InsertAsync(
        model: SystemDescOvernightGuestFrequencyInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescOvernightGuestFrequencyUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescOvernightGuestFrequencyId: Int
    ): Result<Unit>
}