package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberCouponInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberCouponUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberCouponRepository {

    suspend fun GetMemberCouponsAsync(memberId: Int, count: Int = 100): Result<List<MemberCouponDTO>>

    suspend fun GetMemberCouponByIdAsync(memberId: Int, couponId: Int): Result<MemberCouponUpdateModel?>

    suspend fun InsertAsync(memberId: Int, model: MemberCouponInsertModel): Result<Unit>

    suspend fun UpdateAsync(memberId: Int, model: MemberCouponUpdateModel): Result<Unit>

    suspend fun DeleteAsync(couponId: Int): Result<Unit>
}