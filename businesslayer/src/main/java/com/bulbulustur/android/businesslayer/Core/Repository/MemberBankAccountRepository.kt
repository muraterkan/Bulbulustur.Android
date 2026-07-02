package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberBankAccountRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberBankAccountRepository(private val apiClient: ApiClient = ApiClient) : IMemberBankAccountRepository {

    override suspend fun GetAccountBankAccountsAsync(memberId: Int, count: Int): Result<List<MemberBankAccountDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountBankAccountsAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetAccountBankAccountByIdAsync(memberId: Int, bankAccountId: Int): Result<MemberBankAccountUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountBankAccountByIdAsync",
            query = "memberId=$memberId&bankAccountId=$bankAccountId"
        )
    }

    override suspend fun InsertAccountBankAccountAsync(memberId: Int, model: MemberBankAccountInsertModel): Result<Unit> {
        return apiClient.PostAsync<MemberBankAccountInsertModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "InsertAccountBankAccount",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun UpdateAccountBankAccountAsync(memberId: Int, model: MemberBankAccountUpdateModel): Result<Unit> {
        return apiClient.PutAsync<MemberBankAccountUpdateModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "UpdateAccountBankAccount",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun DeleteAccountBankAccountAsync(memberId: Int, bankAccountId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "DeleteAccountBankAccount",
            query = "memberId=$memberId&bankAccountId=$bankAccountId"
        )
    }
}