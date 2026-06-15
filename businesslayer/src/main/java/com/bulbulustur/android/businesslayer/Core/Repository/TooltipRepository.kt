package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.TooltipDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITooltipRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TooltipUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class TooltipRepository(
    private val apiClient: ApiClient
) : ITooltipRepository {

    override suspend fun GetTooltipListAsync(): Result<List<TooltipDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetTooltipByIdAsync(
        tooltipId: Int
    ): Result<TooltipUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetTooltipByIdExtendedAsync(
        tooltipId: Int
    ): Result<TooltipDTO?> {
        TODO("Not implemented yet")
    }
}
