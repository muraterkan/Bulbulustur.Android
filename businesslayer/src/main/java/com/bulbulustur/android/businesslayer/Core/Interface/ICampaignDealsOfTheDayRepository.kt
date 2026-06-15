package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignDealsOfTheDayInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignDealsOfTheDayUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICampaignDealsOfTheDayRepository {

    @GET("api/CampaignDealsOfTheDay/GetCampaignDealsOfTheDayListAsync")
    suspend fun GetCampaignDealsOfTheDayListAsync():
            Result<List<CampaignDealsOfTheDayDTO>>

    @GET("api/CampaignDealsOfTheDay/GetCampaignDealsOfTheDayByIdAsync")
    suspend fun GetCampaignDealsOfTheDayByIdAsync(
        @Query("campaignDealsOfTheDayId")
        campaignDealsOfTheDayId: Int
    ): Result<CampaignDealsOfTheDayUpdateModel?>

    @GET("api/CampaignDealsOfTheDay/GetCampaignDealsOfTheDayByIdExtendedAsync")
    suspend fun GetCampaignDealsOfTheDayByIdExtendedAsync(
        @Query("campaignDealsOfTheDayId")
        campaignDealsOfTheDayId: Int
    ): Result<CampaignDealsOfTheDayDTO?>

    @POST("api/CampaignDealsOfTheDay/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CampaignDealsOfTheDayInsertModel
    ): Result<Unit>

    @POST("api/CampaignDealsOfTheDay/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CampaignDealsOfTheDayUpdateModel
    ): Result<Unit>

    @POST("api/CampaignDealsOfTheDay/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("campaignDealsOfTheDayId")
        campaignDealsOfTheDayId: Int
    ): Result<Unit>
}
