package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.BuyerRequestFileDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BuyerRequestFileInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BuyerRequestFileUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IBuyerRequestFileRepository {

    @GET("api/BuyerRequestFile/GetBuyerRequestFileListAsync")
    suspend fun GetBuyerRequestFileListAsync():
            Result<List<BuyerRequestFileDTO>>

    @GET("api/BuyerRequestFile/GetBuyerRequestFileByIdAsync")
    suspend fun GetBuyerRequestFileByIdAsync(
        @Query("buyerRequestFileId")
        buyerRequestFileId: Int
    ): Result<BuyerRequestFileUpdateModel?>

    @GET("api/BuyerRequestFile/GetBuyerRequestFileByIdExtendedAsync")
    suspend fun GetBuyerRequestFileByIdExtendedAsync(
        @Query("buyerRequestFileId")
        buyerRequestFileId: Int
    ): Result<BuyerRequestFileDTO?>

    @POST("api/BuyerRequestFile/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: BuyerRequestFileInsertModel
    ): Result<Unit>

    @POST("api/BuyerRequestFile/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: BuyerRequestFileUpdateModel
    ): Result<Unit>

    @POST("api/BuyerRequestFile/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("buyerRequestFileId")
        buyerRequestFileId: Int
    ): Result<Unit>
}
