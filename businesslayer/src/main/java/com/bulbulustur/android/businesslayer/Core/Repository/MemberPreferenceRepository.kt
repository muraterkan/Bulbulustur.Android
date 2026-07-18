package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberPreferenceRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberPreferenceInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberPreferenceRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberPreferenceRepository {

    override suspend fun GetAccountPreferencesAsync(languageId: Int, memberId: Int, count: Int): Result<List<MemberPreferenceDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_MEMBER_BASE_URL,
            method = "GetAccountPreferencesAsync",
            query = "languageId=$languageId&memberId=$memberId&count=$count"
        )
    }

    override suspend fun InsertAccountPreferenceAsync(memberId: Int, model: MemberPreferenceInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_MEMBER_BASE_URL,
            method = "InsertAccountPreferenceAsync?memberId=$memberId",
            data = model
        )
    }

    override suspend fun UpdateAccountPreferenceAsync(memberId: Int, model: MemberPreferenceUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_MEMBER_BASE_URL,
            method = "UpdateAccountPreferenceAsync?memberId=$memberId",
            data = model
        )
    }
}
