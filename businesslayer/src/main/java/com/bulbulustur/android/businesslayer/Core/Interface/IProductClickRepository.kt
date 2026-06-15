package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductClickDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductClickInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductClickUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductClickRepository {

    @GET("api/ProductClick/GetProductClickListAsync")
    suspend fun GetProductClickListAsync():
            Result<List<ProductClickDTO>>

    @GET("api/ProductClick/GetProductClickByIdAsync")
    suspend fun GetProductClickByIdAsync(
        @Query("productClickId")
        productClickId: Int
    ): Result<ProductClickUpdateModel?>

    @GET("api/ProductClick/GetProductClickByIdExtendedAsync")
    suspend fun GetProductClickByIdExtendedAsync(
        @Query("productClickId")
        productClickId: Int
    ): Result<ProductClickDTO?>

    @POST("api/ProductClick/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductClickInsertModel
    ): Result<Unit>

    @POST("api/ProductClick/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductClickUpdateModel
    ): Result<Unit>

    @POST("api/ProductClick/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productClickId")
        productClickId: Int
    ): Result<Unit>
}
