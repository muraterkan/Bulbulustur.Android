package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerLastPriceRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerLastPriceRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerLastPriceRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleBuyerLastPriceRequestRepository {

    @GET("api/WholesaleBuyerLastPriceRequest/GetWholesaleBuyerLastPriceRequestListAsync")
    suspend fun GetWholesaleBuyerLastPriceRequestListAsync():
            Result<List<WholesaleBuyerLastPriceRequestDTO>>

    @GET("api/WholesaleBuyerLastPriceRequest/GetWholesaleBuyerLastPriceRequestByIdAsync")
    suspend fun GetWholesaleBuyerLastPriceRequestByIdAsync(
        @Query("wholesaleBuyerLastPriceRequestId")
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<WholesaleBuyerLastPriceRequestUpdateModel?>

    @GET("api/WholesaleBuyerLastPriceRequest/GetWholesaleBuyerLastPriceRequestByIdExtendedAsync")
    suspend fun GetWholesaleBuyerLastPriceRequestByIdExtendedAsync(
        @Query("wholesaleBuyerLastPriceRequestId")
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<WholesaleBuyerLastPriceRequestDTO?>

    @POST("api/WholesaleBuyerLastPriceRequest/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleBuyerLastPriceRequestInsertModel
    ): Result<Unit>

    @POST("api/WholesaleBuyerLastPriceRequest/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleBuyerLastPriceRequestUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleBuyerLastPriceRequest/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleBuyerLastPriceRequestId")
        wholesaleBuyerLastPriceRequestId: Int
    ): Result<Unit>
}
