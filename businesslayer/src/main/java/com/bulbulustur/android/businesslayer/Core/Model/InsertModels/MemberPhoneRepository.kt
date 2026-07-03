package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberPhoneDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberPhoneRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberPhoneInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberPhoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberPhoneRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberPhoneRepository {

    override suspend fun GetMemberPhonesAsync(languageId: Int, memberId: Int, count: Int): Result<List<MemberPhoneDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "GetMemberPhones",
            query = "languageId=$languageId&memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetMemberPhoneByIdAsync(
        languageId: Int,
        memberPhoneId: Int,
        memberId: Int
    ): Result<MemberPhoneUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "GetMemberPhoneByIdAsync",
            query = "languageId=$languageId&memberPhoneId=$memberPhoneId&memberId=$memberId"
        )
    }

    override suspend fun GetMemberPhoneByIdExtendedAsync(
        languageId: Int,
        memberPhoneId: Int,
        memberId: Int
    ): Result<MemberPhoneDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "GetMemberPhoneByIdExtendedAsync",
            query = "languageId=$languageId&memberPhoneId=$memberPhoneId&memberId=$memberId"
        )
    }

    override suspend fun InsertAsync(languageId: Int, model: MemberPhoneInsertModel): Result<Int> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberPhoneInsert?languageId=$languageId",
            data = model
        )
    }

    override suspend fun UpdateAsync(languageId: Int, model: MemberPhoneUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberPhoneUpdate?languageId=$languageId",
            data = model
        )
    }

    override suspend fun DeleteAsync(languageId: Int, phoneId: Int, memberId: Int): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberPhoneDelete?languageId=$languageId&memberId=$memberId",
            data = phoneId
        )
    }

    override suspend fun GetMemberPrimaryPhoneByIdAsync(
        languageId: Int,
        memberId: Int
    ): Result<MemberPhoneUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "GetMemberPrimaryPhoneByIdAsync",
            query = "languageId=$languageId&memberId=$memberId"
        )
    }

    override suspend fun SendVerificationSmsAsync(
        languageId: Int,
        memberPhoneId: Int,
        memberId: Int
    ): Result<Unit> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "SendVerificationSmsAsync",
            query = "languageId=$languageId&memberPhoneId=$memberPhoneId&memberId=$memberId"
        )
    }

    override suspend fun VerifyPhoneAsync(languageId: Int, model: MemberPhoneUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "VerifyPhoneAsync?languageId=$languageId",
            data = model
        )
    }
}