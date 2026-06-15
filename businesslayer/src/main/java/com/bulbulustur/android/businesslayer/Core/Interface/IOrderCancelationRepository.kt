package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.OrderCancelationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.OrderCancelationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.OrderCancelationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IOrderCancelationRepository {

    @GET("api/OrderCancelation/GetOrderCancelationListAsync")
    suspend fun GetOrderCancelationListAsync():
            Result<List<OrderCancelationDTO>>

    @GET("api/OrderCancelation/GetOrderCancelationByIdAsync")
    suspend fun GetOrderCancelationByIdAsync(
        @Query("orderCancelationId")
        orderCancelationId: Int
    ): Result<OrderCancelationUpdateModel?>

    @GET("api/OrderCancelation/GetOrderCancelationByIdExtendedAsync")
    suspend fun GetOrderCancelationByIdExtendedAsync(
        @Query("orderCancelationId")
        orderCancelationId: Int
    ): Result<OrderCancelationDTO?>

    @POST("api/OrderCancelation/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: OrderCancelationInsertModel
    ): Result<Unit>

    @POST("api/OrderCancelation/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: OrderCancelationUpdateModel
    ): Result<Unit>

    @POST("api/OrderCancelation/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("orderCancelationId")
        orderCancelationId: Int
    ): Result<Unit>
}
