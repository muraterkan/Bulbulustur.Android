package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignUpdateModel

interface ICampaignRepository {

    suspend fun GetCampaignListAsync(): Result<List<CampaignDTO>>

    suspend fun GetCampaignByIdAsync(
        campaignId: Int
    ): Result<CampaignUpdateModel?>

    suspend fun GetCampaignByIdExtendedAsync(
        campaignId: Int
    ): Result<CampaignDTO?>
}
