package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReturnRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReturnRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IReturnRequestRepository {

    @GET("api/ReturnRequest/GetReturnRequestListAsync")
    suspend fun GetReturnRequestListAsync():
            Result<List<ReturnRequestDTO>>

    @GET("api/ReturnRequest/GetReturnRequestByIdAsync")
    suspend fun GetReturnRequestByIdAsync(
        @Query("returnRequestId")
        returnRequestId: Int
    ): Result<ReturnRequestUpdateModel?>

    @GET("api/ReturnRequest/GetReturnRequestByIdExtendedAsync")
    suspend fun GetReturnRequestByIdExtendedAsync(
        @Query("returnRequestId")
        returnRequestId: Int
    ): Result<ReturnRequestDTO?>

    @POST("api/ReturnRequest/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ReturnRequestInsertModel
    ): Result<Unit>

    @POST("api/ReturnRequest/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ReturnRequestUpdateModel
    ): Result<Unit>

    @POST("api/ReturnRequest/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("returnRequestId")
        returnRequestId: Int
    ): Result<Unit>
}
