package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CampaignRepository(
    private val apiClient: ApiClient
) : ICampaignRepository {

    override suspend fun GetCampaignListAsync(): Result<List<CampaignDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCampaignByIdAsync(
        campaignId: Int
    ): Result<CampaignUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCampaignByIdExtendedAsync(
        campaignId: Int
    ): Result<CampaignDTO?> {
        TODO("Not implemented yet")
    }
}
