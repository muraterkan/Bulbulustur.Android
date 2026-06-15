package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignDealsOfTheDayRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignDealsOfTheDayUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CampaignDealsOfTheDayRepository(
    private val apiClient: ApiClient
) : ICampaignDealsOfTheDayRepository {

    override suspend fun GetCampaignDealsOfTheDayListAsync(): Result<List<CampaignDealsOfTheDayDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCampaignDealsOfTheDayByIdAsync(
        campaignDealsOfTheDayId: Int
    ): Result<CampaignDealsOfTheDayUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCampaignDealsOfTheDayByIdExtendedAsync(
        campaignDealsOfTheDayId: Int
    ): Result<CampaignDealsOfTheDayDTO?> {
        TODO("Not implemented yet")
    }
}
