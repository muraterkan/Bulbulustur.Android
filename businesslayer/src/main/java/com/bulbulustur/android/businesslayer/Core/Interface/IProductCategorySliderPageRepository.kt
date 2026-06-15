package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategorySliderPageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategorySliderPageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategorySliderPageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductCategorySliderPageRepository {

    @GET("api/ProductCategorySliderPage/GetProductCategorySliderPageListAsync")
    suspend fun GetProductCategorySliderPageListAsync():
            Result<List<ProductCategorySliderPageDTO>>

    @GET("api/ProductCategorySliderPage/GetProductCategorySliderPageByIdAsync")
    suspend fun GetProductCategorySliderPageByIdAsync(
        @Query("productCategorySliderPageId")
        productCategorySliderPageId: Int
    ): Result<ProductCategorySliderPageUpdateModel?>

    @GET("api/ProductCategorySliderPage/GetProductCategorySliderPageByIdExtendedAsync")
    suspend fun GetProductCategorySliderPageByIdExtendedAsync(
        @Query("productCategorySliderPageId")
        productCategorySliderPageId: Int
    ): Result<ProductCategorySliderPageDTO?>

    @POST("api/ProductCategorySliderPage/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductCategorySliderPageInsertModel
    ): Result<Unit>

    @POST("api/ProductCategorySliderPage/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductCategorySliderPageUpdateModel
    ): Result<Unit>

    @POST("api/ProductCategorySliderPage/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productCategorySliderPageId")
        productCategorySliderPageId: Int
    ): Result<Unit>
}
