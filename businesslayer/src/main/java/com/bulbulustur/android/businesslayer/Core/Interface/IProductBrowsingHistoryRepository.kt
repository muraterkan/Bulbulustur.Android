package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrowsingHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrowsingHistoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductBrowsingHistoryRepository {

    @GET("api/ProductBrowsingHistory/GetProductBrowsingHistoryListAsync")
    suspend fun GetProductBrowsingHistoryListAsync():
            Result<List<ProductBrowsingHistoryDTO>>

    @GET("api/ProductBrowsingHistory/GetProductBrowsingHistoryByIdAsync")
    suspend fun GetProductBrowsingHistoryByIdAsync(
        @Query("productBrowsingHistoryId")
        productBrowsingHistoryId: Int
    ): Result<ProductBrowsingHistoryUpdateModel?>

    @GET("api/ProductBrowsingHistory/GetProductBrowsingHistoryByIdExtendedAsync")
    suspend fun GetProductBrowsingHistoryByIdExtendedAsync(
        @Query("productBrowsingHistoryId")
        productBrowsingHistoryId: Int
    ): Result<ProductBrowsingHistoryDTO?>

    @POST("api/ProductBrowsingHistory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductBrowsingHistoryInsertModel
    ): Result<Unit>

    @POST("api/ProductBrowsingHistory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductBrowsingHistoryUpdateModel
    ): Result<Unit>

    @POST("api/ProductBrowsingHistory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productBrowsingHistoryId")
        productBrowsingHistoryId: Int
    ): Result<Unit>
}
