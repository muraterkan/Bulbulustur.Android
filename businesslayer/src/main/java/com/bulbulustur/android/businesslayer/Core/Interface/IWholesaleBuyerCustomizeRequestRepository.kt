package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerCustomizeRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerCustomizeRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerCustomizeRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleBuyerCustomizeRequestRepository {

    @GET("api/WholesaleBuyerCustomizeRequest/GetWholesaleBuyerCustomizeRequestListAsync")
    suspend fun GetWholesaleBuyerCustomizeRequestListAsync():
            Result<List<WholesaleBuyerCustomizeRequestDTO>>

    @GET("api/WholesaleBuyerCustomizeRequest/GetWholesaleBuyerCustomizeRequestByIdAsync")
    suspend fun GetWholesaleBuyerCustomizeRequestByIdAsync(
        @Query("wholesaleBuyerCustomizeRequestId")
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<WholesaleBuyerCustomizeRequestUpdateModel?>

    @GET("api/WholesaleBuyerCustomizeRequest/GetWholesaleBuyerCustomizeRequestByIdExtendedAsync")
    suspend fun GetWholesaleBuyerCustomizeRequestByIdExtendedAsync(
        @Query("wholesaleBuyerCustomizeRequestId")
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<WholesaleBuyerCustomizeRequestDTO?>

    @POST("api/WholesaleBuyerCustomizeRequest/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleBuyerCustomizeRequestInsertModel
    ): Result<Unit>

    @POST("api/WholesaleBuyerCustomizeRequest/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleBuyerCustomizeRequestUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleBuyerCustomizeRequest/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleBuyerCustomizeRequestId")
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<Unit>
}
