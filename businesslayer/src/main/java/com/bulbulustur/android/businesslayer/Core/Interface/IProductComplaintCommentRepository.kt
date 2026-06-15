package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplaintCommentDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductComplaintCommentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplaintCommentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductComplaintCommentRepository {

    @GET("api/ProductComplaintComment/GetProductComplaintCommentListAsync")
    suspend fun GetProductComplaintCommentListAsync():
            Result<List<ProductComplaintCommentDTO>>

    @GET("api/ProductComplaintComment/GetProductComplaintCommentByIdAsync")
    suspend fun GetProductComplaintCommentByIdAsync(
        @Query("productComplaintCommentId")
        productComplaintCommentId: Int
    ): Result<ProductComplaintCommentUpdateModel?>

    @GET("api/ProductComplaintComment/GetProductComplaintCommentByIdExtendedAsync")
    suspend fun GetProductComplaintCommentByIdExtendedAsync(
        @Query("productComplaintCommentId")
        productComplaintCommentId: Int
    ): Result<ProductComplaintCommentDTO?>

    @POST("api/ProductComplaintComment/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductComplaintCommentInsertModel
    ): Result<Unit>

    @POST("api/ProductComplaintComment/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductComplaintCommentUpdateModel
    ): Result<Unit>

    @POST("api/ProductComplaintComment/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productComplaintCommentId")
        productComplaintCommentId: Int
    ): Result<Unit>
}
