package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescTradeTermDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescTradeTermUpdateModel

interface ISystemDescTradeTermRepository {

    suspend fun GetSystemDescTradeTermListAsync(): Result<List<SystemDescTradeTermDTO>>

    suspend fun GetSystemDescTradeTermByIdAsync(
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermUpdateModel?>

    suspend fun GetSystemDescTradeTermByIdExtendedAsync(
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermDTO?>
}
