package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCurrencyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCurrencyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCurrencyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescCurrencyRepository {

    suspend fun GetSystemDescCurrenciesAsync(languageId: Int, count: Int): Result<List<SystemDescCurrencyDTO>>

    suspend fun GetSystemDescCurrencyByIdAsync(systemDescCurrencyId: Int): Result<SystemDescCurrencyUpdateModel?>

    suspend fun GetSystemDescCurrencyByIdExtendedAsync(languageId: Int, systemDescCurrencyId: Int): Result<SystemDescCurrencyDTO?>

    suspend fun InsertAsync(model: SystemDescCurrencyInsertModel): Result<Unit>

    suspend fun UpdateAsync(model: SystemDescCurrencyUpdateModel): Result<Unit>

    suspend fun DeleteAsync(systemDescCurrencyId: Int): Result<Unit>
}