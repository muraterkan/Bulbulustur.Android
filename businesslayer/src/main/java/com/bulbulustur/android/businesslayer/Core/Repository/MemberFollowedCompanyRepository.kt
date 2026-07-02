package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedCompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberFollowedCompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedCompanyInsertModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberFollowedCompanyRepository(private val apiClient: ApiClient = ApiClient) : IMemberFollowedCompanyRepository {

    override suspend fun GetAccountFollowedCompanies(memberId: Int, count: Int): Result<List<MemberFollowedCompanyDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountFollowedCompanies",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun InsertAccountFollowedCompany(memberId: Int, model: MemberFollowedCompanyInsertModel): Result<Unit> {
        return apiClient.PostAsync<MemberFollowedCompanyInsertModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "InsertAccountFollowedCompany",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun DeleteAccountFollowedCompany(memberId: Int, followedCompanyId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "DeleteAccountFollowedCompany",
            query = "memberId=$memberId&followedCompanyId=$followedCompanyId"
        )
    }
}