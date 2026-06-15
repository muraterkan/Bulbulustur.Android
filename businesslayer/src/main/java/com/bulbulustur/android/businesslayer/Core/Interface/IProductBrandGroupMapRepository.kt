package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandGroupMapDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrandGroupMapInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandGroupMapUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductBrandGroupMapRepository {

    @GET("api/ProductBrandGroupMap/GetProductBrandGroupMapListAsync")
    suspend fun GetProductBrandGroupMapListAsync():
            Result<List<ProductBrandGroupMapDTO>>

    @GET("api/ProductBrandGroupMap/GetProductBrandGroupMapByIdAsync")
    suspend fun GetProductBrandGroupMapByIdAsync(
        @Query("productBrandGroupMapId")
        productBrandGroupMapId: Int
    ): Result<ProductBrandGroupMapUpdateModel?>

    @GET("api/ProductBrandGroupMap/GetProductBrandGroupMapByIdExtendedAsync")
    suspend fun GetProductBrandGroupMapByIdExtendedAsync(
        @Query("productBrandGroupMapId")
        productBrandGroupMapId: Int
    ): Result<ProductBrandGroupMapDTO?>

    @POST("api/ProductBrandGroupMap/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductBrandGroupMapInsertModel
    ): Result<Unit>

    @POST("api/ProductBrandGroupMap/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductBrandGroupMapUpdateModel
    ): Result<Unit>

    @POST("api/ProductBrandGroupMap/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productBrandGroupMapId")
        productBrandGroupMapId: Int
    ): Result<Unit>
}
