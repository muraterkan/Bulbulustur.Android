package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBuyTogetherDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBuyTogetherInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBuyTogetherUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductBuyTogetherRepository {

    @GET("api/ProductBuyTogether/GetProductBuyTogetherListAsync")
    suspend fun GetProductBuyTogetherListAsync():
            Result<List<ProductBuyTogetherDTO>>

    @GET("api/ProductBuyTogether/GetProductBuyTogetherByIdAsync")
    suspend fun GetProductBuyTogetherByIdAsync(
        @Query("productBuyTogetherId")
        productBuyTogetherId: Int
    ): Result<ProductBuyTogetherUpdateModel?>

    @GET("api/ProductBuyTogether/GetProductBuyTogetherByIdExtendedAsync")
    suspend fun GetProductBuyTogetherByIdExtendedAsync(
        @Query("productBuyTogetherId")
        productBuyTogetherId: Int
    ): Result<ProductBuyTogetherDTO?>

    @POST("api/ProductBuyTogether/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductBuyTogetherInsertModel
    ): Result<Unit>

    @POST("api/ProductBuyTogether/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductBuyTogetherUpdateModel
    ): Result<Unit>

    @POST("api/ProductBuyTogether/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productBuyTogetherId")
        productBuyTogetherId: Int
    ): Result<Unit>
}
