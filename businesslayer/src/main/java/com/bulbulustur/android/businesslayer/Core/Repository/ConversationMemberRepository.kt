package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ConversationMemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IConversationMemberRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ConversationMemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ConversationMemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ConversationMemberRepository(
    private val apiClient: ApiClient = ApiClient
) : IConversationMemberRepository {

    override suspend fun GetConversationMembersAsync(
        count: Int
    ): Result<List<ConversationMemberDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetConversationMembersAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetConversationMembersByConversationIdAsync(
        conversationId: Int,
        count: Int
    ): Result<List<ConversationMemberDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetConversationMembersByConversationIdAsync",
            query = "conversationId=$conversationId&count=$count"
        )
    }

    override suspend fun GetConversationMemberByIdAsync(
        conversationMemberId: Int
    ): Result<ConversationMemberUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetConversationMemberByIdAsync",
            query = "conversationMemberId=$conversationMemberId"
        )
    }

    override suspend fun GetConversationMemberByIdExtendedAsync(
        conversationMemberId: Int
    ): Result<ConversationMemberDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetConversationMemberByIdExtendedAsync",
            query = "conversationMemberId=$conversationMemberId"
        )
    }

    override suspend fun InsertAsync(
        model: ConversationMemberInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertConversationMemberAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ConversationMemberUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateConversationMemberAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        conversationMemberId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteConversationMemberAsync",
            query = "conversationMemberId=$conversationMemberId"
        )
    }
}