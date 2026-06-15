package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.PaymentAttemptDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PaymentAttemptInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PaymentAttemptUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IPaymentAttemptRepository {

    @GET("api/PaymentAttempt/GetPaymentAttemptListAsync")
    suspend fun GetPaymentAttemptListAsync():
            Result<List<PaymentAttemptDTO>>

    @GET("api/PaymentAttempt/GetPaymentAttemptByIdAsync")
    suspend fun GetPaymentAttemptByIdAsync(
        @Query("paymentAttemptId")
        paymentAttemptId: Int
    ): Result<PaymentAttemptUpdateModel?>

    @GET("api/PaymentAttempt/GetPaymentAttemptByIdExtendedAsync")
    suspend fun GetPaymentAttemptByIdExtendedAsync(
        @Query("paymentAttemptId")
        paymentAttemptId: Int
    ): Result<PaymentAttemptDTO?>

    @POST("api/PaymentAttempt/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: PaymentAttemptInsertModel
    ): Result<Unit>

    @POST("api/PaymentAttempt/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: PaymentAttemptUpdateModel
    ): Result<Unit>

    @POST("api/PaymentAttempt/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("paymentAttemptId")
        paymentAttemptId: Int
    ): Result<Unit>
}
