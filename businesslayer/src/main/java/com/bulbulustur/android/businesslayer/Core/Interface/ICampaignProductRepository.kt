package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignProductUpdateModel

interface ICampaignProductRepository {

    suspend fun GetCampaignProductListAsync(): Result<List<CampaignProductDTO>>

    suspend fun GetCampaignProductByIdAsync(
        campaignProductId: Int
    ): Result<CampaignProductUpdateModel?>

    suspend fun GetCampaignProductByIdExtendedAsync(
        campaignProductId: Int
    ): Result<CampaignProductDTO?>
}
