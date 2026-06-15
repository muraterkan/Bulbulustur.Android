package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignConditionDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignConditionUpdateModel

interface ICampaignConditionRepository {

    suspend fun GetCampaignConditionListAsync(): Result<List<CampaignConditionDTO>>

    suspend fun GetCampaignConditionByIdAsync(
        campaignConditionId: Int
    ): Result<CampaignConditionUpdateModel?>

    suspend fun GetCampaignConditionByIdExtendedAsync(
        campaignConditionId: Int
    ): Result<CampaignConditionDTO?>
}
