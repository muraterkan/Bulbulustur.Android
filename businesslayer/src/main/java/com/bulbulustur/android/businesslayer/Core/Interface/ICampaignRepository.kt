package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ICampaignRepository {
    suspend fun GetCampaignsAsync(languageId: Int, count: Int = 3): Result<List<CampaignDTO>>
    suspend fun GetCampaignByIdAsync(campaignId: Int): Result<CampaignUpdateModel?>
    suspend fun GetCampaignByIdExtendedAsync(languageId: Int, campaignId: Int): Result<CampaignDTO?>
    suspend fun GetCampaignsByCategoryAsync(languageId: Int, categoryId: Int, count: Int): Result<List<CampaignDTO>>
    suspend fun InsertAsync(model: CampaignInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: CampaignUpdateModel): Result<Unit>
    suspend fun DeleteAsync(campaignId: Int): Result<Unit>
}
