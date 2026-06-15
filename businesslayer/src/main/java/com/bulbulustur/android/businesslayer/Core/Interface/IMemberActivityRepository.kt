package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberActivityDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberActivityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberActivityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberActivityRepository {

    @GET("api/MemberActivity/GetMemberActivityListAsync")
    suspend fun GetMemberActivityListAsync():
            Result<List<MemberActivityDTO>>

    @GET("api/MemberActivity/GetMemberActivityByIdAsync")
    suspend fun GetMemberActivityByIdAsync(
        @Query("memberActivityId")
        memberActivityId: Int
    ): Result<MemberActivityUpdateModel?>

    @GET("api/MemberActivity/GetMemberActivityByIdExtendedAsync")
    suspend fun GetMemberActivityByIdExtendedAsync(
        @Query("memberActivityId")
        memberActivityId: Int
    ): Result<MemberActivityDTO?>

    @POST("api/MemberActivity/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberActivityInsertModel
    ): Result<Unit>

    @POST("api/MemberActivity/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberActivityUpdateModel
    ): Result<Unit>

    @POST("api/MemberActivity/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberActivityId")
        memberActivityId: Int
    ): Result<Unit>
}
