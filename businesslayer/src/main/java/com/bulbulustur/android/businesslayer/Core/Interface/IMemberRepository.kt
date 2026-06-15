package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberRepository {

    @GET("api/Member/GetMemberListAsync")
    suspend fun GetMemberListAsync():
            Result<List<MemberDTO>>

    @GET("api/Member/GetMemberByIdAsync")
    suspend fun GetMemberByIdAsync(
        @Query("memberId")
        memberId: Int
    ): Result<MemberUpdateModel?>

    @GET("api/Member/GetMemberByIdExtendedAsync")
    suspend fun GetMemberByIdExtendedAsync(
        @Query("memberId")
        memberId: Int
    ): Result<MemberDTO?>

    @POST("api/Member/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberInsertModel
    ): Result<Unit>

    @POST("api/Member/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberUpdateModel
    ): Result<Unit>

    @POST("api/Member/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberId")
        memberId: Int
    ): Result<Unit>
}
