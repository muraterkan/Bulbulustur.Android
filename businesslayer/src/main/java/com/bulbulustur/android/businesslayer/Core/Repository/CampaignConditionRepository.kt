package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignConditionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignConditionRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignConditionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CampaignConditionRepository(
    private val apiClient: ApiClient
) : ICampaignConditionRepository {

    override suspend fun GetCampaignConditionListAsync(): Result<List<CampaignConditionDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCampaignConditionByIdAsync(
        campaignConditionId: Int
    ): Result<CampaignConditionUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCampaignConditionByIdExtendedAsync(
        campaignConditionId: Int
    ): Result<CampaignConditionDTO?> {
        TODO("Not implemented yet")
    }
}
