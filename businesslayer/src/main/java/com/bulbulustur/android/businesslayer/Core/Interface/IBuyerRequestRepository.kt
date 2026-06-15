package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BuyerRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IBuyerRequestRepository {

    @GET("api/BuyerRequest/GetBuyerRequestListAsync")
    suspend fun GetBuyerRequestListAsync():
            Result<List<BuyerRequestDTO>>

    @GET("api/BuyerRequest/GetBuyerRequestByIdAsync")
    suspend fun GetBuyerRequestByIdAsync(
        @Query("buyerRequestId")
        buyerRequestId: Int
    ): Result<BuyerRequestUpdateModel?>

    @GET("api/BuyerRequest/GetBuyerRequestByIdExtendedAsync")
    suspend fun GetBuyerRequestByIdExtendedAsync(
        @Query("buyerRequestId")
        buyerRequestId: Int
    ): Result<BuyerRequestDTO?>

    @POST("api/BuyerRequest/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: BuyerRequestInsertModel
    ): Result<Unit>

    @POST("api/BuyerRequest/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: BuyerRequestUpdateModel
    ): Result<Unit>

    @POST("api/BuyerRequest/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("buyerRequestId")
        buyerRequestId: Int
    ): Result<Unit>
}
