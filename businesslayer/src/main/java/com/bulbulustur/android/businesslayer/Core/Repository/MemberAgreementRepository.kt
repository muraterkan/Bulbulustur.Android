package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAgreementDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAgreementRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberAgreementRepository(private val apiClient: ApiClient = ApiClient) : IMemberAgreementRepository {

    override suspend fun GetLatestAccountAgreementAsync(memberId: Int): Result<MemberAgreementDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ACCOUNT_BASE_URL,
            method = "GetLatestAccountAgreementAsync",
            query = "memberId=$memberId"
        )
    }
}