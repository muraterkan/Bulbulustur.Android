package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPriceDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductPriceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPriceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductPriceRepository {

    @GET("api/WholesaleProductPrice/GetWholesaleProductPriceListAsync")
    suspend fun GetWholesaleProductPriceListAsync():
            Result<List<WholesaleProductPriceDTO>>

    @GET("api/WholesaleProductPrice/GetWholesaleProductPriceByIdAsync")
    suspend fun GetWholesaleProductPriceByIdAsync(
        @Query("wholesaleProductPriceId")
        wholesaleProductPriceId: Int
    ): Result<WholesaleProductPriceUpdateModel?>

    @GET("api/WholesaleProductPrice/GetWholesaleProductPriceByIdExtendedAsync")
    suspend fun GetWholesaleProductPriceByIdExtendedAsync(
        @Query("wholesaleProductPriceId")
        wholesaleProductPriceId: Int
    ): Result<WholesaleProductPriceDTO?>

    @POST("api/WholesaleProductPrice/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductPriceInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductPrice/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductPriceUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductPrice/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductPriceId")
        wholesaleProductPriceId: Int
    ): Result<Unit>
}
