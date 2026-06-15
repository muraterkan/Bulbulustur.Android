package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductStoreBasedFeatureDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductStoreBasedFeatureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductStoreBasedFeatureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductStoreBasedFeatureRepository {

    @GET("api/ProductStoreBasedFeature/GetProductStoreBasedFeatureListAsync")
    suspend fun GetProductStoreBasedFeatureListAsync():
            Result<List<ProductStoreBasedFeatureDTO>>

    @GET("api/ProductStoreBasedFeature/GetProductStoreBasedFeatureByIdAsync")
    suspend fun GetProductStoreBasedFeatureByIdAsync(
        @Query("productStoreBasedFeatureId")
        productStoreBasedFeatureId: Int
    ): Result<ProductStoreBasedFeatureUpdateModel?>

    @GET("api/ProductStoreBasedFeature/GetProductStoreBasedFeatureByIdExtendedAsync")
    suspend fun GetProductStoreBasedFeatureByIdExtendedAsync(
        @Query("productStoreBasedFeatureId")
        productStoreBasedFeatureId: Int
    ): Result<ProductStoreBasedFeatureDTO?>

    @POST("api/ProductStoreBasedFeature/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductStoreBasedFeatureInsertModel
    ): Result<Unit>

    @POST("api/ProductStoreBasedFeature/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductStoreBasedFeatureUpdateModel
    ): Result<Unit>

    @POST("api/ProductStoreBasedFeature/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productStoreBasedFeatureId")
        productStoreBasedFeatureId: Int
    ): Result<Unit>
}
