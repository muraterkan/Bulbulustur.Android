package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICampaignRepository {

    @GET("api/Campaign/GetCampaignListAsync")
    suspend fun GetCampaignListAsync():
            Result<List<CampaignDTO>>

    @GET("api/Campaign/GetCampaignByIdAsync")
    suspend fun GetCampaignByIdAsync(
        @Query("campaignId")
        campaignId: Int
    ): Result<CampaignUpdateModel?>

    @GET("api/Campaign/GetCampaignByIdExtendedAsync")
    suspend fun GetCampaignByIdExtendedAsync(
        @Query("campaignId")
        campaignId: Int
    ): Result<CampaignDTO?>

    @POST("api/Campaign/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CampaignInsertModel
    ): Result<Unit>

    @POST("api/Campaign/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CampaignUpdateModel
    ): Result<Unit>

    @POST("api/Campaign/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("campaignId")
        campaignId: Int
    ): Result<Unit>
}
