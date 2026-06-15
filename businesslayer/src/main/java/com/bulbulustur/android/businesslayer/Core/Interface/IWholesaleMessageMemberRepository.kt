package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageMemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleMessageMemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageMemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleMessageMemberRepository {

    @GET("api/WholesaleMessageMember/GetWholesaleMessageMemberListAsync")
    suspend fun GetWholesaleMessageMemberListAsync():
            Result<List<WholesaleMessageMemberDTO>>

    @GET("api/WholesaleMessageMember/GetWholesaleMessageMemberByIdAsync")
    suspend fun GetWholesaleMessageMemberByIdAsync(
        @Query("wholesaleMessageMemberId")
        wholesaleMessageMemberId: Int
    ): Result<WholesaleMessageMemberUpdateModel?>

    @GET("api/WholesaleMessageMember/GetWholesaleMessageMemberByIdExtendedAsync")
    suspend fun GetWholesaleMessageMemberByIdExtendedAsync(
        @Query("wholesaleMessageMemberId")
        wholesaleMessageMemberId: Int
    ): Result<WholesaleMessageMemberDTO?>

    @POST("api/WholesaleMessageMember/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleMessageMemberInsertModel
    ): Result<Unit>

    @POST("api/WholesaleMessageMember/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleMessageMemberUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleMessageMember/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleMessageMemberId")
        wholesaleMessageMemberId: Int
    ): Result<Unit>
}
