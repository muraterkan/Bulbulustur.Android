package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberLoginActivityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberLoginActivityRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberLoginActivityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberLoginActivityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberLoginActivityRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberLoginActivityRepository {

    override suspend fun GetMemberLoginActivityListAsync(): Result<List<MemberLoginActivityDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberLoginActivityListAsync"
        )
    }

    override suspend fun GetMemberLoginActivityByIdAsync(
        memberLoginActivityId: Int
    ): Result<MemberLoginActivityUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberLoginActivityByIdAsync",
            query = "memberLoginActivityId=$memberLoginActivityId"
        )
    }

    override suspend fun GetMemberLoginActivityByIdExtendedAsync(
        memberLoginActivityId: Int
    ): Result<MemberLoginActivityDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberLoginActivityByIdExtendedAsync",
            query = "memberLoginActivityId=$memberLoginActivityId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberLoginActivityInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberLoginActivityUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberLoginActivityId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberLoginActivityId=$memberLoginActivityId"
        )
    }
}