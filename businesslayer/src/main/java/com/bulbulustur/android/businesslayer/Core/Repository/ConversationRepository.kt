package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ConversationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IConversationRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ConversationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ConversationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ConversationRepository(
    private val apiClient: ApiClient = ApiClient
) : IConversationRepository {

    override suspend fun GetConversationsAsync(
        count: Int
    ): Result<List<ConversationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetConversationsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetConversationsByCreatedByMemberIdAsync(
        createdByMemberId: Int,
        count: Int
    ): Result<List<ConversationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetConversationsByCreatedByMemberIdAsync",
            query = "createdByMemberId=$createdByMemberId&count=$count"
        )
    }

    override suspend fun GetConversationByIdAsync(
        conversationId: Int
    ): Result<ConversationUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetConversationByIdAsync",
            query = "conversationId=$conversationId"
        )
    }

    override suspend fun GetConversationByIdExtendedAsync(
        conversationId: Int
    ): Result<ConversationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetConversationByIdExtendedAsync",
            query = "conversationId=$conversationId"
        )
    }

    override suspend fun InsertAsync(
        model: ConversationInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertConversationAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ConversationUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateConversationAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        conversationId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteConversationAsync",
            query = "conversationId=$conversationId"
        )
    }
}