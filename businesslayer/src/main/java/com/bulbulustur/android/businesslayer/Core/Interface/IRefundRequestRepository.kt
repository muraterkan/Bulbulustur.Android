package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.RefundRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RefundRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RefundRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IRefundRequestRepository {

    @GET("api/RefundRequest/GetRefundRequestListAsync")
    suspend fun GetRefundRequestListAsync():
            Result<List<RefundRequestDTO>>

    @GET("api/RefundRequest/GetRefundRequestByIdAsync")
    suspend fun GetRefundRequestByIdAsync(
        @Query("refundRequestId")
        refundRequestId: Int
    ): Result<RefundRequestUpdateModel?>

    @GET("api/RefundRequest/GetRefundRequestByIdExtendedAsync")
    suspend fun GetRefundRequestByIdExtendedAsync(
        @Query("refundRequestId")
        refundRequestId: Int
    ): Result<RefundRequestDTO?>

    @POST("api/RefundRequest/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: RefundRequestInsertModel
    ): Result<Unit>

    @POST("api/RefundRequest/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: RefundRequestUpdateModel
    ): Result<Unit>

    @POST("api/RefundRequest/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("refundRequestId")
        refundRequestId: Int
    ): Result<Unit>
}
