package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberFollowedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberFollowedRepository {

    @GET("api/MemberFollowed/GetMemberFollowedListAsync")
    suspend fun GetMemberFollowedListAsync():
            Result<List<MemberFollowedDTO>>

    @GET("api/MemberFollowed/GetMemberFollowedByIdAsync")
    suspend fun GetMemberFollowedByIdAsync(
        @Query("memberFollowedId")
        memberFollowedId: Int
    ): Result<MemberFollowedUpdateModel?>

    @GET("api/MemberFollowed/GetMemberFollowedByIdExtendedAsync")
    suspend fun GetMemberFollowedByIdExtendedAsync(
        @Query("memberFollowedId")
        memberFollowedId: Int
    ): Result<MemberFollowedDTO?>

    @POST("api/MemberFollowed/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberFollowedInsertModel
    ): Result<Unit>

    @POST("api/MemberFollowed/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberFollowedUpdateModel
    ): Result<Unit>

    @POST("api/MemberFollowed/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberFollowedId")
        memberFollowedId: Int
    ): Result<Unit>
}
