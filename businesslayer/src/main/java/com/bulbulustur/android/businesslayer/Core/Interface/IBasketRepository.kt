package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BasketInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BasketUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IBasketRepository {

    @GET("api/Basket/GetBasketListAsync")
    suspend fun GetBasketListAsync():
            Result<List<BasketDTO>>

    @GET("api/Basket/GetBasketByIdAsync")
    suspend fun GetBasketByIdAsync(
        @Query("basketId")
        basketId: Int
    ): Result<BasketUpdateModel?>

    @GET("api/Basket/GetBasketByIdExtendedAsync")
    suspend fun GetBasketByIdExtendedAsync(
        @Query("basketId")
        basketId: Int
    ): Result<BasketDTO?>

    @POST("api/Basket/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: BasketInsertModel
    ): Result<Unit>

    @POST("api/Basket/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: BasketUpdateModel
    ): Result<Unit>

    @POST("api/Basket/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("basketId")
        basketId: Int
    ): Result<Unit>
}
