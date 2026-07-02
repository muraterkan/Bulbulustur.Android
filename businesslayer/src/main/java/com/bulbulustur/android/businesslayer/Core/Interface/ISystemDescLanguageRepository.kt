package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescLanguageRepository {

    suspend fun GetSystemDescLanguagesAsync(languageId: Int, count: Int): Result<List<SystemDescLanguageDTO>>

    suspend fun GetSystemDescLanguageByIdAsync(systemDescLanguageId: Int): Result<SystemDescLanguageUpdateModel?>

    suspend fun GetSystemDescLanguageByIdExtendedAsync(languageId: Int, systemDescLanguageId: Int): Result<SystemDescLanguageDTO?>

    suspend fun InsertAsync(model: SystemDescLanguageInsertModel): Result<Unit>

    suspend fun UpdateAsync(model: SystemDescLanguageUpdateModel): Result<Unit>

    suspend fun DeleteAsync(systemDescLanguageId: Int): Result<Unit>
}