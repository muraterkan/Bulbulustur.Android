package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AssignedToSellerDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AssignedToSellerInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AssignedToSellerUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAssignedToSellerRepository {

    @GET("api/AssignedToSeller/GetAssignedToSellerListAsync")
    suspend fun GetAssignedToSellerListAsync():
            Result<List<AssignedToSellerDTO>>

    @GET("api/AssignedToSeller/GetAssignedToSellerByIdAsync")
    suspend fun GetAssignedToSellerByIdAsync(
        @Query("assignedToSellerId")
        assignedToSellerId: Int
    ): Result<AssignedToSellerUpdateModel?>

    @GET("api/AssignedToSeller/GetAssignedToSellerByIdExtendedAsync")
    suspend fun GetAssignedToSellerByIdExtendedAsync(
        @Query("assignedToSellerId")
        assignedToSellerId: Int
    ): Result<AssignedToSellerDTO?>

    @POST("api/AssignedToSeller/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AssignedToSellerInsertModel
    ): Result<Unit>

    @POST("api/AssignedToSeller/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AssignedToSellerUpdateModel
    ): Result<Unit>

    @POST("api/AssignedToSeller/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("assignedToSellerId")
        assignedToSellerId: Int
    ): Result<Unit>
}
