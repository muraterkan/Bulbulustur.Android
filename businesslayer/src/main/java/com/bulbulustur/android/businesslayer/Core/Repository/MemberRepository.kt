package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberRepository
import com.bulbulustur.android.businesslayer.Core.Model.ChangeMailModel
import com.bulbulustur.android.businesslayer.Core.Model.ChangePasswordAsyncModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberRegisterModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateAddressModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateBirthDateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateGenderModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateProfessionModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateTcknModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberRepository {

    override suspend fun GetMemberListAsync(): Result<List<MemberDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "GetMembersAsync",
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

    override suspend fun UpdateAsync(model: MemberUpdateModel): Result<MemberUpdateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberUpdateAsync",
            data = model
        )
    }

    override suspend fun MemberUpdateGenderAsync(model: MemberUpdateGenderModel): Result<MemberUpdateGenderModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberUpdateGenderAsync",
            data = model
        )
    }

    override suspend fun MemberUpdateBirthDateAsync(model: MemberUpdateBirthDateModel): Result<MemberUpdateBirthDateModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberUpdateBirthDateAsync",
            data = model
        )
    }

    override suspend fun MemberUpdateAddressAsync(model: MemberUpdateAddressModel): Result<MemberUpdateAddressModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberUpdateAddressAsync",
            data = model
        )
    }

    override suspend fun MemberUpdateTcknAsync(model: MemberUpdateTcknModel): Result<MemberUpdateTcknModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberUpdateTcknAsync",
            data = model
        )
    }

    override suspend fun MemberUpdateProfessionAsync(model: MemberUpdateProfessionModel): Result<MemberUpdateProfessionModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberUpdateProfessionAsync",
            data = model
        )
    }

    override suspend fun SendEmailChangingRequestAsync(model: ChangeMailModel): Result<ChangeMailModel> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "SendEmailChangingRequest",
            data = model
        )
    }

    override suspend fun ChangePasswordAsync(languageId: Int, model: ChangePasswordAsyncModel): Result<Unit> {
        val requestModel = model.copy(LanguageId = languageId)

        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "ChangePasswordAsync?languageId=$languageId",
            data = requestModel
        )
    }

    override suspend fun DeleteAsync(memberId: Int): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.MEMBER_BASE_URL,
            method = "MemberDeleteAsync",
            data = memberId
        )
    }
}