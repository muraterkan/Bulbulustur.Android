package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplementaryItemDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductComplementaryItemInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplementaryItemUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductComplementaryItemRepository {

    @GET("api/ProductComplementaryItem/GetProductComplementaryItemListAsync")
    suspend fun GetProductComplementaryItemListAsync():
            Result<List<ProductComplementaryItemDTO>>

    @GET("api/ProductComplementaryItem/GetProductComplementaryItemByIdAsync")
    suspend fun GetProductComplementaryItemByIdAsync(
        @Query("productComplementaryItemId")
        productComplementaryItemId: Int
    ): Result<ProductComplementaryItemUpdateModel?>

    @GET("api/ProductComplementaryItem/GetProductComplementaryItemByIdExtendedAsync")
    suspend fun GetProductComplementaryItemByIdExtendedAsync(
        @Query("productComplementaryItemId")
        productComplementaryItemId: Int
    ): Result<ProductComplementaryItemDTO?>

    @POST("api/ProductComplementaryItem/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductComplementaryItemInsertModel
    ): Result<Unit>

    @POST("api/ProductComplementaryItem/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductComplementaryItemUpdateModel
    ): Result<Unit>

    @POST("api/ProductComplementaryItem/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productComplementaryItemId")
        productComplementaryItemId: Int
    ): Result<Unit>
}
