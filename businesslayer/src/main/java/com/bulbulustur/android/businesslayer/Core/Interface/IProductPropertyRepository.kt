package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductPropertyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductPropertyRepository {

    @GET("api/ProductProperty/GetProductPropertyListAsync")
    suspend fun GetProductPropertyListAsync():
            Result<List<ProductPropertyDTO>>

    @GET("api/ProductProperty/GetProductPropertyByIdAsync")
    suspend fun GetProductPropertyByIdAsync(
        @Query("productPropertyId")
        productPropertyId: Int
    ): Result<ProductPropertyUpdateModel?>

    @GET("api/ProductProperty/GetProductPropertyByIdExtendedAsync")
    suspend fun GetProductPropertyByIdExtendedAsync(
        @Query("productPropertyId")
        productPropertyId: Int
    ): Result<ProductPropertyDTO?>

    @POST("api/ProductProperty/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductPropertyInsertModel
    ): Result<Unit>

    @POST("api/ProductProperty/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductPropertyUpdateModel
    ): Result<Unit>

    @POST("api/ProductProperty/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productPropertyId")
        productPropertyId: Int
    ): Result<Unit>
}
