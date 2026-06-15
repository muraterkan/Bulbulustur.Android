package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplaintDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductComplaintInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplaintUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IProductComplaintRepository {

    @GET("api/ProductComplaint/GetProductComplaintListAsync")
    suspend fun GetProductComplaintListAsync():
            Result<List<ProductComplaintDTO>>

    @GET("api/ProductComplaint/GetProductComplaintByIdAsync")
    suspend fun GetProductComplaintByIdAsync(
        @Query("productComplaintId")
        productComplaintId: Int
    ): Result<ProductComplaintUpdateModel?>

    @GET("api/ProductComplaint/GetProductComplaintByIdExtendedAsync")
    suspend fun GetProductComplaintByIdExtendedAsync(
        @Query("productComplaintId")
        productComplaintId: Int
    ): Result<ProductComplaintDTO?>

    @POST("api/ProductComplaint/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: ProductComplaintInsertModel
    ): Result<Unit>

    @POST("api/ProductComplaint/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: ProductComplaintUpdateModel
    ): Result<Unit>

    @POST("api/ProductComplaint/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("productComplaintId")
        productComplaintId: Int
    ): Result<Unit>
}
