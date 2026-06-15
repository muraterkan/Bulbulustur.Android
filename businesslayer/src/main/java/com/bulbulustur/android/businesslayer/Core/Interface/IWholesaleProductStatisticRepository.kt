package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductStatisticDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductStatisticInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductStatisticUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductStatisticRepository {

    @GET("api/WholesaleProductStatistic/GetWholesaleProductStatisticListAsync")
    suspend fun GetWholesaleProductStatisticListAsync():
            Result<List<WholesaleProductStatisticDTO>>

    @GET("api/WholesaleProductStatistic/GetWholesaleProductStatisticByIdAsync")
    suspend fun GetWholesaleProductStatisticByIdAsync(
        @Query("wholesaleProductStatisticId")
        wholesaleProductStatisticId: Int
    ): Result<WholesaleProductStatisticUpdateModel?>

    @GET("api/WholesaleProductStatistic/GetWholesaleProductStatisticByIdExtendedAsync")
    suspend fun GetWholesaleProductStatisticByIdExtendedAsync(
        @Query("wholesaleProductStatisticId")
        wholesaleProductStatisticId: Int
    ): Result<WholesaleProductStatisticDTO?>

    @POST("api/WholesaleProductStatistic/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductStatisticInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductStatistic/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductStatisticUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductStatistic/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductStatisticId")
        wholesaleProductStatisticId: Int
    ): Result<Unit>
}
