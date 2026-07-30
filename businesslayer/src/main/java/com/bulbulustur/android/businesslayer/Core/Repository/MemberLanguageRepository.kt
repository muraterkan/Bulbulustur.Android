package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberLanguageRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberLanguageRepository {

    override suspend fun GetAccountLanguagesAsync(
        memberId: Int,
        count: Int
    ): Result<List<MemberLanguageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountLanguagesAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetAccountLanguageByIdAsync(
        memberId: Int,
        memberLanguageId: Int
    ): Result<MemberLanguageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetAccountLanguagesByIdAsync",
            query = "memberId=$memberId&memberLanguageId=$memberLanguageId"
        )
    }

    override suspend fun InsertAccountLanguageAsync(
        memberId: Int,
        model: MemberLanguageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync<MemberLanguageInsertModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "InsertAccountLanguagesAsync",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun UpdateAccountLanguageAsync(
        memberId: Int,
        model: MemberLanguageUpdateModel
    ): Result<Unit> {
        return apiClient.PutAsync<MemberLanguageUpdateModel, Unit>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "UpdateAccountLanguagesAsync",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun DeleteAccountLanguageAsync(
        memberId : Int,
        memberLanguageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "DeleteAccountLanguagesAsync",
            query = "memberId=$memberId&memberLanguageId=$memberLanguageId"
        )
    }
}
