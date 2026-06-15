package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberBankAccountRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberBankAccountRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberBankAccountRepository {

    override suspend fun GetMemberBankAccountListAsync(): Result<List<MemberBankAccountDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberBankAccountListAsync"
        )
    }

    override suspend fun GetMemberBankAccountByIdAsync(
        memberBankAccountId: Int
    ): Result<MemberBankAccountUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberBankAccountByIdAsync",
            query = "memberBankAccountId=$memberBankAccountId"
        )
    }

    override suspend fun GetMemberBankAccountByIdExtendedAsync(
        memberBankAccountId: Int
    ): Result<MemberBankAccountDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberBankAccountByIdExtendedAsync",
            query = "memberBankAccountId=$memberBankAccountId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberBankAccountInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberBankAccountUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberBankAccountId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberBankAccountId=$memberBankAccountId"
        )
    }
}