package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrandInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductBrandRepository {

    @GET("api/ProductBrand/GetProductBrandListAsync")
    suspend fun GetProductBrandListAsync():
            Result<List<ProductBrandDTO>>

    @GET("api/ProductBrand/GetProductBrandByIdAsync")
    suspend fun GetProductBrandByIdAsync(
        @Query("productBrandId")
        productBrandId: Int
    ): Result<ProductBrandUpdateModel?>

    @GET("api/ProductBrand/GetProductBrandByIdExtendedAsync")
    suspend fun GetProductBrandByIdExtendedAsync(
        @Query("productBrandId")
        productBrandId: Int
    ): Result<ProductBrandDTO?>

    @POST("api/ProductBrand/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductBrandInsertModel
    ): Result<Unit>

    @POST("api/ProductBrand/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductBrandUpdateModel
    ): Result<Unit>

    @POST("api/ProductBrand/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productBrandId")
        productBrandId: Int
    ): Result<Unit>
}
