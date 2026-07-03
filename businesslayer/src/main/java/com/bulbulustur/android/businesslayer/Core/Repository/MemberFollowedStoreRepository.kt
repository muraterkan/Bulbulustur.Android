package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedStoreDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberFollowedStoreRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedStoreInsertModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberFollowedStoreRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberFollowedStoreRepository {

    override suspend fun GetAccountFollowedStores(memberId: Int, count: Int): Result<List<MemberFollowedStoreDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountFollowedStores",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun InsertAccountFollowedStoreAsync(memberId: Int, model: MemberFollowedStoreInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "InsertAccountFollowedStoreAsync",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun DeleteAccountFollowedStoreAsync(memberId: Int, followedStoreId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "DeleteAccountFollowedStoreAsync",
            query = "memberId=$memberId&followedStoreId=$followedStoreId"
        )
    }
}