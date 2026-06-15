package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignDealsOfTheDayUpdateModel

interface ICampaignDealsOfTheDayRepository {

    suspend fun GetCampaignDealsOfTheDayListAsync(): Result<List<CampaignDealsOfTheDayDTO>>

    suspend fun GetCampaignDealsOfTheDayByIdAsync(
        campaignDealsOfTheDayId: Int
    ): Result<CampaignDealsOfTheDayUpdateModel?>

    suspend fun GetCampaignDealsOfTheDayByIdExtendedAsync(
        campaignDealsOfTheDayId: Int
    ): Result<CampaignDealsOfTheDayDTO?>
}
