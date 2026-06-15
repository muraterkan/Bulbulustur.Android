package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AdvertProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAdvertProductRepository {

    @GET("api/AdvertProduct/GetAdvertProductListAsync")
    suspend fun GetAdvertProductListAsync():
            Result<List<AdvertProductDTO>>

    @GET("api/AdvertProduct/GetAdvertProductByIdAsync")
    suspend fun GetAdvertProductByIdAsync(
        @Query("advertProductId")
        advertProductId: Int
    ): Result<AdvertProductUpdateModel?>

    @GET("api/AdvertProduct/GetAdvertProductByIdExtendedAsync")
    suspend fun GetAdvertProductByIdExtendedAsync(
        @Query("advertProductId")
        advertProductId: Int
    ): Result<AdvertProductDTO?>

    @POST("api/AdvertProduct/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AdvertProductInsertModel
    ): Result<Unit>

    @POST("api/AdvertProduct/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AdvertProductUpdateModel
    ): Result<Unit>

    @POST("api/AdvertProduct/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("advertProductId")
        advertProductId: Int
    ): Result<Unit>
}
