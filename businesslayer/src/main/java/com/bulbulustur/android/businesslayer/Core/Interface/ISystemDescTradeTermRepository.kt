package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescTradeTermDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescTradeTermInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescTradeTermUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescTradeTermRepository {

    suspend fun GetSystemDescTradeTermsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescTradeTermDTO>>

    suspend fun GetSystemDescTradeTermByIdAsync(
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermUpdateModel?>

    suspend fun GetSystemDescTradeTermByIdExtendedAsync(
        systemDescTradeTermId: Int
    ): Result<SystemDescTradeTermDTO?>

    suspend fun InsertAsync(
        model: SystemDescTradeTermInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescTradeTermUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescTradeTermId: Int
    ): Result<Unit>
}