package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberLoginActivityDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberLoginActivityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberLoginActivityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberLoginActivityRepository {

    @GET("api/MemberLoginActivity/GetMemberLoginActivityListAsync")
    suspend fun GetMemberLoginActivityListAsync():
            Result<List<MemberLoginActivityDTO>>

    @GET("api/MemberLoginActivity/GetMemberLoginActivityByIdAsync")
    suspend fun GetMemberLoginActivityByIdAsync(
        @Query("memberLoginActivityId")
        memberLoginActivityId: Int
    ): Result<MemberLoginActivityUpdateModel?>

    @GET("api/MemberLoginActivity/GetMemberLoginActivityByIdExtendedAsync")
    suspend fun GetMemberLoginActivityByIdExtendedAsync(
        @Query("memberLoginActivityId")
        memberLoginActivityId: Int
    ): Result<MemberLoginActivityDTO?>

    @POST("api/MemberLoginActivity/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberLoginActivityInsertModel
    ): Result<Unit>

    @POST("api/MemberLoginActivity/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberLoginActivityUpdateModel
    ): Result<Unit>

    @POST("api/MemberLoginActivity/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberLoginActivityId")
        memberLoginActivityId: Int
    ): Result<Unit>
}
