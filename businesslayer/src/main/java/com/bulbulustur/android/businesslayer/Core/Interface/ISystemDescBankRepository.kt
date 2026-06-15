package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBankDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBankUpdateModel

interface ISystemDescBankRepository {

    suspend fun GetSystemDescBankListAsync(): Result<List<SystemDescBankDTO>>

    suspend fun GetSystemDescBankByIdAsync(
        systemDescBankId: Int
    ): Result<SystemDescBankUpdateModel?>

    suspend fun GetSystemDescBankByIdExtendedAsync(
        systemDescBankId: Int
    ): Result<SystemDescBankDTO?>
}
