package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductRelatedInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductRelatedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductRelatedRepository {

    @GET("api/WholesaleProductRelated/GetWholesaleProductRelatedListAsync")
    suspend fun GetWholesaleProductRelatedListAsync():
            Result<List<WholesaleProductRelatedDTO>>

    @GET("api/WholesaleProductRelated/GetWholesaleProductRelatedByIdAsync")
    suspend fun GetWholesaleProductRelatedByIdAsync(
        @Query("wholesaleProductRelatedId")
        wholesaleProductRelatedId: Int
    ): Result<WholesaleProductRelatedUpdateModel?>

    @GET("api/WholesaleProductRelated/GetWholesaleProductRelatedByIdExtendedAsync")
    suspend fun GetWholesaleProductRelatedByIdExtendedAsync(
        @Query("wholesaleProductRelatedId")
        wholesaleProductRelatedId: Int
    ): Result<WholesaleProductRelatedDTO?>

    @POST("api/WholesaleProductRelated/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductRelatedInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductRelated/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductRelatedUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductRelated/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductRelatedId")
        wholesaleProductRelatedId: Int
    ): Result<Unit>
}
