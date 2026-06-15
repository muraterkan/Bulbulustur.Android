package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberRepository {

    override suspend fun GetMemberListAsync(): Result<List<MemberDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberListAsync"
        )
    }

    override suspend fun GetMemberByIdAsync(
        memberId: Int
    ): Result<MemberUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberByIdAsync",
            query = "memberId=$memberId"
        )
    }

    override suspend fun GetMemberByIdExtendedAsync(
        memberId: Int
    ): Result<MemberDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberByIdExtendedAsync",
            query = "memberId=$memberId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberId=$memberId"
        )
    }
}