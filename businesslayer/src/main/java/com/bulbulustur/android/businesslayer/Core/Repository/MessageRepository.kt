package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MessageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMessageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MessageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MessageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MessageRepository(
    private val apiClient: ApiClient = ApiClient
) : IMessageRepository {

    override suspend fun GetMessagesAsync(
        count: Int
    ): Result<List<MessageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetMessagesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetMessagesByConversationIdAsync(
        conversationId: Int,
        count: Int
    ): Result<List<MessageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetMessagesByConversationIdAsync",
            query = "conversationId=$conversationId&count=$count"
        )
    }

    override suspend fun GetMessageByIdAsync(
        messageId: Int
    ): Result<MessageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetMessageByIdAsync",
            query = "messageId=$messageId"
        )
    }

    override suspend fun GetMessageByIdExtendedAsync(
        messageId: Int
    ): Result<MessageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "GetMessageByIdExtendedAsync",
            query = "messageId=$messageId"
        )
    }

    override suspend fun InsertAsync(
        model: MessageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "InsertMessageAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MessageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "UpdateMessageAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        messageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.FLATMATE_BASE_URL,
            method = "DeleteMessageAsync",
            query = "messageId=$messageId"
        )
    }
}