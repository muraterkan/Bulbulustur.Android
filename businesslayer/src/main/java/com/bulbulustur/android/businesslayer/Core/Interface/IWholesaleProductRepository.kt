package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductRepository {

    @GET("api/WholesaleProduct/GetWholesaleProductListAsync")
    suspend fun GetWholesaleProductListAsync():
            Result<List<WholesaleProductDTO>>

    @GET("api/WholesaleProduct/GetWholesaleProductByIdAsync")
    suspend fun GetWholesaleProductByIdAsync(
        @Query("wholesaleProductId")
        wholesaleProductId: Int
    ): Result<WholesaleProductUpdateModel?>

    @GET("api/WholesaleProduct/GetWholesaleProductByIdExtendedAsync")
    suspend fun GetWholesaleProductByIdExtendedAsync(
        @Query("wholesaleProductId")
        wholesaleProductId: Int
    ): Result<WholesaleProductDTO?>

    @POST("api/WholesaleProduct/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProduct/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProduct/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductId")
        wholesaleProductId: Int
    ): Result<Unit>
}
