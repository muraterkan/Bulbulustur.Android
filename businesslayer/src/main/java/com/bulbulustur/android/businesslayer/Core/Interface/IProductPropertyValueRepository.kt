package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyValueDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductPropertyValueInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyValueUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductPropertyValueRepository {

    @GET("api/ProductPropertyValue/GetProductPropertyValueListAsync")
    suspend fun GetProductPropertyValueListAsync():
            Result<List<ProductPropertyValueDTO>>

    @GET("api/ProductPropertyValue/GetProductPropertyValueByIdAsync")
    suspend fun GetProductPropertyValueByIdAsync(
        @Query("productPropertyValueId")
        productPropertyValueId: Int
    ): Result<ProductPropertyValueUpdateModel?>

    @GET("api/ProductPropertyValue/GetProductPropertyValueByIdExtendedAsync")
    suspend fun GetProductPropertyValueByIdExtendedAsync(
        @Query("productPropertyValueId")
        productPropertyValueId: Int
    ): Result<ProductPropertyValueDTO?>

    @POST("api/ProductPropertyValue/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductPropertyValueInsertModel
    ): Result<Unit>

    @POST("api/ProductPropertyValue/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductPropertyValueUpdateModel
    ): Result<Unit>

    @POST("api/ProductPropertyValue/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productPropertyValueId")
        productPropertyValueId: Int
    ): Result<Unit>
}
