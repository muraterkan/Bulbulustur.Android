package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.OrderDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.OrderInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.OrderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IOrderRepository {

    @GET("api/Order/GetOrderListAsync")
    suspend fun GetOrderListAsync():
            Result<List<OrderDTO>>

    @GET("api/Order/GetOrderByIdAsync")
    suspend fun GetOrderByIdAsync(
        @Query("orderId")
        orderId: Int
    ): Result<OrderUpdateModel?>

    @GET("api/Order/GetOrderByIdExtendedAsync")
    suspend fun GetOrderByIdExtendedAsync(
        @Query("orderId")
        orderId: Int
    ): Result<OrderDTO?>

    @POST("api/Order/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: OrderInsertModel
    ): Result<Unit>

    @POST("api/Order/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: OrderUpdateModel
    ): Result<Unit>

    @POST("api/Order/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("orderId")
        orderId: Int
    ): Result<Unit>
}
