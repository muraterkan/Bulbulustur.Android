package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductPropertyCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductPropertyCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductPropertyCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductPropertyCategoryRepository {

    @GET("api/ProductPropertyCategory/GetProductPropertyCategoryListAsync")
    suspend fun GetProductPropertyCategoryListAsync():
            Result<List<ProductPropertyCategoryDTO>>

    @GET("api/ProductPropertyCategory/GetProductPropertyCategoryByIdAsync")
    suspend fun GetProductPropertyCategoryByIdAsync(
        @Query("productPropertyCategoryId")
        productPropertyCategoryId: Int
    ): Result<ProductPropertyCategoryUpdateModel?>

    @GET("api/ProductPropertyCategory/GetProductPropertyCategoryByIdExtendedAsync")
    suspend fun GetProductPropertyCategoryByIdExtendedAsync(
        @Query("productPropertyCategoryId")
        productPropertyCategoryId: Int
    ): Result<ProductPropertyCategoryDTO?>

    @POST("api/ProductPropertyCategory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductPropertyCategoryInsertModel
    ): Result<Unit>

    @POST("api/ProductPropertyCategory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductPropertyCategoryUpdateModel
    ): Result<Unit>

    @POST("api/ProductPropertyCategory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productPropertyCategoryId")
        productPropertyCategoryId: Int
    ): Result<Unit>
}
