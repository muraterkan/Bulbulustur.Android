package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBrowsingHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBrowsingHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleBrowsingHistoryRepository {

    @GET("api/WholesaleBrowsingHistory/GetWholesaleBrowsingHistoryListAsync")
    suspend fun GetWholesaleBrowsingHistoryListAsync():
            Result<List<WholesaleBrowsingHistoryDTO>>

    @GET("api/WholesaleBrowsingHistory/GetWholesaleBrowsingHistoryByIdAsync")
    suspend fun GetWholesaleBrowsingHistoryByIdAsync(
        @Query("wholesaleBrowsingHistoryId")
        wholesaleBrowsingHistoryId: Int
    ): Result<WholesaleBrowsingHistoryUpdateModel?>

    @GET("api/WholesaleBrowsingHistory/GetWholesaleBrowsingHistoryByIdExtendedAsync")
    suspend fun GetWholesaleBrowsingHistoryByIdExtendedAsync(
        @Query("wholesaleBrowsingHistoryId")
        wholesaleBrowsingHistoryId: Int
    ): Result<WholesaleBrowsingHistoryDTO?>

    @POST("api/WholesaleBrowsingHistory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleBrowsingHistoryInsertModel
    ): Result<Unit>

    @POST("api/WholesaleBrowsingHistory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleBrowsingHistoryUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleBrowsingHistory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleBrowsingHistoryId")
        wholesaleBrowsingHistoryId: Int
    ): Result<Unit>
}
