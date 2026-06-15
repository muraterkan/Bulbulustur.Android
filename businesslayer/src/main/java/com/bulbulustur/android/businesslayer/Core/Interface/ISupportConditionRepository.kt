package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SupportConditionDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SupportConditionUpdateModel

interface ISupportConditionRepository {

    suspend fun GetSupportConditionListAsync(): Result<List<SupportConditionDTO>>

    suspend fun GetSupportConditionByIdAsync(
        supportConditionId: Int
    ): Result<SupportConditionUpdateModel?>

    suspend fun GetSupportConditionByIdExtendedAsync(
        supportConditionId: Int
    ): Result<SupportConditionDTO?>
}
