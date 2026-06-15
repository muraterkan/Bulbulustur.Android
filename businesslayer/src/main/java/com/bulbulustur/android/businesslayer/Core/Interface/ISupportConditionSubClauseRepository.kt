package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SupportConditionSubClauseDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SupportConditionSubClauseUpdateModel

interface ISupportConditionSubClauseRepository {

    suspend fun GetSupportConditionSubClauseListAsync(): Result<List<SupportConditionSubClauseDTO>>

    suspend fun GetSupportConditionSubClauseByIdAsync(
        supportConditionSubClauseId: Int
    ): Result<SupportConditionSubClauseUpdateModel?>

    suspend fun GetSupportConditionSubClauseByIdExtendedAsync(
        supportConditionSubClauseId: Int
    ): Result<SupportConditionSubClauseDTO?>
}
