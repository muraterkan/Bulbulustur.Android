package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.TooltipDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TooltipUpdateModel

interface ITooltipRepository {

    suspend fun GetTooltipListAsync(): Result<List<TooltipDTO>>

    suspend fun GetTooltipByIdAsync(
        tooltipId: Int
    ): Result<TooltipUpdateModel?>

    suspend fun GetTooltipByIdExtendedAsync(
        tooltipId: Int
    ): Result<TooltipDTO?>
}
