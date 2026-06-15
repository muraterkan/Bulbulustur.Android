package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberCouponUpdateModel

interface IMemberCouponRepository {

    suspend fun GetMemberCouponListAsync(): Result<List<MemberCouponDTO>>

    suspend fun GetMemberCouponByIdAsync(
        memberCouponId: Int
    ): Result<MemberCouponUpdateModel?>

    suspend fun GetMemberCouponByIdExtendedAsync(
        memberCouponId: Int
    ): Result<MemberCouponDTO?>
}
