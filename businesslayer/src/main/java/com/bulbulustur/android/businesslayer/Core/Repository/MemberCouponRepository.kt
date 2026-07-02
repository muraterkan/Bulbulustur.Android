package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberCouponRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberCouponInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberCouponUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberCouponRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberCouponRepository {

    override suspend fun GetMemberCouponsAsync(memberId: Int, count: Int): Result<List<MemberCouponDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Account/GetAccountCouponsAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetMemberCouponByIdAsync(memberId: Int, couponId: Int): Result<MemberCouponUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Account/GetAccountCouponsByIdAsync",
            query = "memberId=$memberId&couponId=$couponId"
        )
    }

    override suspend fun InsertAsync(memberId: Int, model: MemberCouponInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Account/InsertAccountCouponsAsync?memberId=$memberId",
            data = model
        )
    }

    override suspend fun UpdateAsync(memberId: Int, model: MemberCouponUpdateModel): Result<Unit> {
        return apiClient.PutAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Account/UpdateAccountCouponsAsync?memberId=$memberId",
            data = model
        )
    }

    override suspend fun DeleteAsync(couponId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Account/DeleteAccountCouponsAsync",
            query = "couponId=$couponId"
        )
    }
}