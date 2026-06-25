package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberTempDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberTempRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberTempInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberTempFistdoorModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberTempRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberTempRepository {

    override suspend fun FirstDoorAsync(
        languageId: Int,
        model: MemberTempFistdoorModel
    ): Result<MemberTempInsertModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.AUTHENTICATION_BASE_URL,
            method = "firsdoor?languageId=$languageId",
            data = model
        )
    }

    override suspend fun GetMemberTempByActivationCodeAsync(
        languageId: Int,
        uuid: String
    ): Result<MemberTempDTO> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.AUTHENTICATION_BASE_URL,
            method = "get-member-temp-by-activation-code",
            query = "languageId=$languageId&uuid=$uuid"
        )
    }
}