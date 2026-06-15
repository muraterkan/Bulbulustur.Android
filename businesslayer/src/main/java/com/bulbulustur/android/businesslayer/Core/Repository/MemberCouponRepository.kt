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

    override suspend fun GetMemberCouponListAsync(): Result<List<MemberCouponDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberCouponListAsync"
        )
    }

    override suspend fun GetMemberCouponByIdAsync(
        memberCouponId: Int
    ): Result<MemberCouponUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberCouponByIdAsync",
            query = "memberCouponId=$memberCouponId"
        )
    }

    override suspend fun GetMemberCouponByIdExtendedAsync(
        memberCouponId: Int
    ): Result<MemberCouponDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberCouponByIdExtendedAsync",
            query = "memberCouponId=$memberCouponId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberCouponInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberCouponUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberCouponId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberCouponId=$memberCouponId"
        )
    }
}