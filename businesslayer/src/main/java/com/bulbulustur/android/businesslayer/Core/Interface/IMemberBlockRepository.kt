package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBlockDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBlockInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBlockUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberBlockRepository {

    @GET("api/MemberBlock/GetMemberBlockListAsync")
    suspend fun GetMemberBlockListAsync():
            Result<List<MemberBlockDTO>>

    @GET("api/MemberBlock/GetMemberBlockByIdAsync")
    suspend fun GetMemberBlockByIdAsync(
        @Query("memberBlockId")
        memberBlockId: Int
    ): Result<MemberBlockUpdateModel?>

    @GET("api/MemberBlock/GetMemberBlockByIdExtendedAsync")
    suspend fun GetMemberBlockByIdExtendedAsync(
        @Query("memberBlockId")
        memberBlockId: Int
    ): Result<MemberBlockDTO?>

    @POST("api/MemberBlock/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberBlockInsertModel
    ): Result<Unit>

    @POST("api/MemberBlock/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberBlockUpdateModel
    ): Result<Unit>

    @POST("api/MemberBlock/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberBlockId")
        memberBlockId: Int
    ): Result<Unit>
}
