package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAlarmListDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAlarmListRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAlarmListInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAlarmListUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberAlarmListRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberAlarmListRepository {

    override suspend fun GetMemberAlarmListListAsync(): Result<List<MemberAlarmListDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberAlarmListListAsync"
        )
    }

    override suspend fun GetMemberAlarmListByIdAsync(
        memberAlarmListId: Int
    ): Result<MemberAlarmListUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberAlarmListByIdAsync",
            query = "memberAlarmListId=$memberAlarmListId"
        )
    }

    override suspend fun GetMemberAlarmListByIdExtendedAsync(
        memberAlarmListId: Int
    ): Result<MemberAlarmListDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberAlarmListByIdExtendedAsync",
            query = "memberAlarmListId=$memberAlarmListId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberAlarmListInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberAlarmListUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberAlarmListId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberAlarmListId=$memberAlarmListId"
        )
    }
}