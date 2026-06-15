package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategorySliderDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategorySliderInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategorySliderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductCategorySliderRepository {

    @GET("api/ProductCategorySlider/GetProductCategorySliderListAsync")
    suspend fun GetProductCategorySliderListAsync():
            Result<List<ProductCategorySliderDTO>>

    @GET("api/ProductCategorySlider/GetProductCategorySliderByIdAsync")
    suspend fun GetProductCategorySliderByIdAsync(
        @Query("productCategorySliderId")
        productCategorySliderId: Int
    ): Result<ProductCategorySliderUpdateModel?>

    @GET("api/ProductCategorySlider/GetProductCategorySliderByIdExtendedAsync")
    suspend fun GetProductCategorySliderByIdExtendedAsync(
        @Query("productCategorySliderId")
        productCategorySliderId: Int
    ): Result<ProductCategorySliderDTO?>

    @POST("api/ProductCategorySlider/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductCategorySliderInsertModel
    ): Result<Unit>

    @POST("api/ProductCategorySlider/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductCategorySliderUpdateModel
    ): Result<Unit>

    @POST("api/ProductCategorySlider/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productCategorySliderId")
        productCategorySliderId: Int
    ): Result<Unit>
}
