package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberRegisterModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberRepository {

    override suspend fun GetMemberListAsync(): Result<List<MemberDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "GetMembers",
            query = "languageId=1&count=100"
        )
    }

    override suspend fun GetMemberByIdAsync(languageId: Int, memberId: Int): Result<MemberUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "GetMemberByIdAsync",
            query = "languageId=$languageId&memberId=$memberId"
        )
    }

    override suspend fun GetMemberByIdExtendedAsync(languageId: Int, memberId: Int): Result<MemberDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "GetMemberByIdExtendedAsync",
            query = "languageId=$languageId&memberId=$memberId"
        )
    }

    override suspend fun InsertAsync(model: MemberInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberInsert",
            data = model
        )
    }

    override suspend fun InsertAsync(languageId: Int, model: MemberRegisterModel): Result<MemberInsertModel> {
        val registerModel = model.copy(LanguageId = languageId)

        return apiClient.PostAsync(
            baseUrl = ApiRoutes.AUTHENTICATION_BASE_URL,
            method = "member-insert",
            data = registerModel
        )
    }

    override suspend fun UpdateAsync(model: MemberUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberUpdate",
            data = model
        )
    }

    override suspend fun SetContactPreferenceAsync(model: MemberUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "SetContactPreference",
            data = model
        )
    }

    override suspend fun DeleteAsync(memberId: Int): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberDelete",
            data = memberId
        )
    }
}