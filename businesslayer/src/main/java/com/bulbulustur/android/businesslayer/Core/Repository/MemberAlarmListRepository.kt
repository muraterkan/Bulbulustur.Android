package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAlarmListDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAlarmListRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAlarmListInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAlarmListUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberAlarmListRepository(private val apiClient: ApiClient = ApiClient) : IMemberAlarmListRepository {

    override suspend fun GetAccountAlarmLists(memberId: Int, count: Int): Result<List<MemberAlarmListDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountAlarmLists",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetAccountAlarmListByIdAsync(memberId: Int, memberAlarmListId: Int): Result<MemberAlarmListUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountAlarmListByIdAsync",
            query = "memberId=$memberId&memberAlarmListId=$memberAlarmListId"
        )
    }

    override suspend fun GetAccountAlarmListByIdExtendedAsync(memberId: Int, memberAlarmListId: Int): Result<MemberAlarmListDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountAlarmListByIdExtendedAsync",
            query = "memberId=$memberId&memberAlarmListId=$memberAlarmListId"
        )
    }

    override suspend fun InsertAccountAlarmAsync(memberId: Int, model: MemberAlarmListInsertModel): Result<Unit> {
        return apiClient.PostAsync<MemberAlarmListInsertModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "InsertAccountAlarmAsync",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun UpdateAccountAlarmAsync(memberId: Int, model: MemberAlarmListUpdateModel): Result<Unit> {
        return apiClient.PutAsync<MemberAlarmListUpdateModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "UpdateAccountAlarmAsync",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun DeleteAccountAlarmAsync(memberId: Int, memberAlarmListId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "DeleteAccountAlarmAsync",
            query = "memberId=$memberId&memberAlarmListId=$memberAlarmListId"
        )
    }
}