package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberCouponRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberCouponUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class MemberCouponRepository(
    private val apiClient: ApiClient
) : IMemberCouponRepository {

    override suspend fun GetMemberCouponListAsync(): Result<List<MemberCouponDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberCouponByIdAsync(
        memberCouponId: Int
    ): Result<MemberCouponUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetMemberCouponByIdExtendedAsync(
        memberCouponId: Int
    ): Result<MemberCouponDTO?> {
        TODO("Not implemented yet")
    }
}
