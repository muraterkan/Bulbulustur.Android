package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAlarmListDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAlarmListInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAlarmListUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberAlarmListRepository {

    @GET("api/MemberAlarmList/GetMemberAlarmListListAsync")
    suspend fun GetMemberAlarmListListAsync():
            Result<List<MemberAlarmListDTO>>

    @GET("api/MemberAlarmList/GetMemberAlarmListByIdAsync")
    suspend fun GetMemberAlarmListByIdAsync(
        @Query("memberAlarmListId")
        memberAlarmListId: Int
    ): Result<MemberAlarmListUpdateModel?>

    @GET("api/MemberAlarmList/GetMemberAlarmListByIdExtendedAsync")
    suspend fun GetMemberAlarmListByIdExtendedAsync(
        @Query("memberAlarmListId")
        memberAlarmListId: Int
    ): Result<MemberAlarmListDTO?>

    @POST("api/MemberAlarmList/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberAlarmListInsertModel
    ): Result<Unit>

    @POST("api/MemberAlarmList/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberAlarmListUpdateModel
    ): Result<Unit>

    @POST("api/MemberAlarmList/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberAlarmListId")
        memberAlarmListId: Int
    ): Result<Unit>
}
