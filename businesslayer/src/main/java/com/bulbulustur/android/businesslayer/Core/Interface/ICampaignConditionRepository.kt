package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignConditionDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignConditionInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignConditionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICampaignConditionRepository {

    @GET("api/CampaignCondition/GetCampaignConditionListAsync")
    suspend fun GetCampaignConditionListAsync():
            Result<List<CampaignConditionDTO>>

    @GET("api/CampaignCondition/GetCampaignConditionByIdAsync")
    suspend fun GetCampaignConditionByIdAsync(
        @Query("campaignConditionId")
        campaignConditionId: Int
    ): Result<CampaignConditionUpdateModel?>

    @GET("api/CampaignCondition/GetCampaignConditionByIdExtendedAsync")
    suspend fun GetCampaignConditionByIdExtendedAsync(
        @Query("campaignConditionId")
        campaignConditionId: Int
    ): Result<CampaignConditionDTO?>

    @POST("api/CampaignCondition/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CampaignConditionInsertModel
    ): Result<Unit>

    @POST("api/CampaignCondition/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CampaignConditionUpdateModel
    ): Result<Unit>

    @POST("api/CampaignCondition/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("campaignConditionId")
        campaignConditionId: Int
    ): Result<Unit>
}
