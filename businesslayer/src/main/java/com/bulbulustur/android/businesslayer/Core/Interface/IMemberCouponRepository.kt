package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberCouponInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberCouponUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMemberCouponRepository {

    @GET("api/MemberCoupon/GetMemberCouponListAsync")
    suspend fun GetMemberCouponListAsync():
            Result<List<MemberCouponDTO>>

    @GET("api/MemberCoupon/GetMemberCouponByIdAsync")
    suspend fun GetMemberCouponByIdAsync(
        @Query("memberCouponId")
        memberCouponId: Int
    ): Result<MemberCouponUpdateModel?>

    @GET("api/MemberCoupon/GetMemberCouponByIdExtendedAsync")
    suspend fun GetMemberCouponByIdExtendedAsync(
        @Query("memberCouponId")
        memberCouponId: Int
    ): Result<MemberCouponDTO?>

    @POST("api/MemberCoupon/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: MemberCouponInsertModel
    ): Result<Unit>

    @POST("api/MemberCoupon/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: MemberCouponUpdateModel
    ): Result<Unit>

    @POST("api/MemberCoupon/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("memberCouponId")
        memberCouponId: Int
    ): Result<Unit>
}
