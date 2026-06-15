package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignProductRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CampaignProductRepository(
    private val apiClient: ApiClient
) : ICampaignProductRepository {

    override suspend fun GetCampaignProductListAsync(): Result<List<CampaignProductDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCampaignProductByIdAsync(
        campaignProductId: Int
    ): Result<CampaignProductUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCampaignProductByIdExtendedAsync(
        campaignProductId: Int
    ): Result<CampaignProductDTO?> {
        TODO("Not implemented yet")
    }
}
