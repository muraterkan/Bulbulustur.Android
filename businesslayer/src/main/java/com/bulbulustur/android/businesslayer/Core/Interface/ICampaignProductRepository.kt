package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CampaignProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CampaignProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CampaignProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICampaignProductRepository {

    @GET("api/CampaignProduct/GetCampaignProductListAsync")
    suspend fun GetCampaignProductListAsync():
            Result<List<CampaignProductDTO>>

    @GET("api/CampaignProduct/GetCampaignProductByIdAsync")
    suspend fun GetCampaignProductByIdAsync(
        @Query("campaignProductId")
        campaignProductId: Int
    ): Result<CampaignProductUpdateModel?>

    @GET("api/CampaignProduct/GetCampaignProductByIdExtendedAsync")
    suspend fun GetCampaignProductByIdExtendedAsync(
        @Query("campaignProductId")
        campaignProductId: Int
    ): Result<CampaignProductDTO?>

    @POST("api/CampaignProduct/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CampaignProductInsertModel
    ): Result<Unit>

    @POST("api/CampaignProduct/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CampaignProductUpdateModel
    ): Result<Unit>

    @POST("api/CampaignProduct/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("campaignProductId")
        campaignProductId: Int
    ): Result<Unit>
}
