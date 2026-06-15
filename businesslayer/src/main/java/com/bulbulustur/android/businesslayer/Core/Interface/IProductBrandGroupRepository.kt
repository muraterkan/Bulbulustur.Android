package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandGroupDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrandGroupInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductBrandGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductBrandGroupRepository {

    @GET("api/ProductBrandGroup/GetProductBrandGroupListAsync")
    suspend fun GetProductBrandGroupListAsync():
            Result<List<ProductBrandGroupDTO>>

    @GET("api/ProductBrandGroup/GetProductBrandGroupByIdAsync")
    suspend fun GetProductBrandGroupByIdAsync(
        @Query("productBrandGroupId")
        productBrandGroupId: Int
    ): Result<ProductBrandGroupUpdateModel?>

    @GET("api/ProductBrandGroup/GetProductBrandGroupByIdExtendedAsync")
    suspend fun GetProductBrandGroupByIdExtendedAsync(
        @Query("productBrandGroupId")
        productBrandGroupId: Int
    ): Result<ProductBrandGroupDTO?>

    @POST("api/ProductBrandGroup/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductBrandGroupInsertModel
    ): Result<Unit>

    @POST("api/ProductBrandGroup/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductBrandGroupUpdateModel
    ): Result<Unit>

    @POST("api/ProductBrandGroup/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productBrandGroupId")
        productBrandGroupId: Int
    ): Result<Unit>
}
