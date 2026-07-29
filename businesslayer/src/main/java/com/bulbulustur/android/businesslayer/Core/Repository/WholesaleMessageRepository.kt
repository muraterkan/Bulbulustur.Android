package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleMessageRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleMessageRepository {

    override suspend fun GetWholesaleMessagesAsync(languageId: Int, memberId: Int, count: Int): Result<List<WholesaleMessageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_MESSAGE_BASE_URL,
            method = "GetWholesaleMessagesAsync",
            query = "languageId=$languageId&memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetMessagesByThreadAsync(languageId: Int, memberId: Int, messageThreadId: Int, count: Int): Result<List<WholesaleMessageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_MESSAGE_BASE_URL,
            method = "GetMessagesByThreadAsync",
            query = "languageId=$languageId&memberId=$memberId&messageThreadId=$messageThreadId&count=$count"
        )
    }

    override suspend fun GetOtherUserInThreadAsync(languageId: Int, memberId: Int, messageThreadId: Int): Result<MemberDTO> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_MESSAGE_BASE_URL,
            method = "GetOtherUserInThreadAsync",
            query = "languageId=$languageId&memberId=$memberId&messageThreadId=$messageThreadId"
        )
    }

    override suspend fun GetUnreadMessageCountAsync(memberId: Int): Result<Int> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_MESSAGE_BASE_URL,
            method = "GetUnreadMessageCountAsync",
            query = "memberId=$memberId"
        )
    }

    override suspend fun InsertAsync(memberId: Int, model: WholesaleMessageDTO): Result<Any?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_MESSAGE_BASE_URL,
            method = "InsertAsync",
            data = model,
            query = "memberId="
        )
    }

    override suspend fun ReplyAsync(memberId: Int, model: WholesaleMessageDTO): Result<Any?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_MESSAGE_BASE_URL,
            method = "ReplyAsync",
            data = model,
            query = "memberId=$memberId"
        )
    }

    override suspend fun MarkAsRead(memberId: Int, messageId: Int): Result<Any?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_MESSAGE_BASE_URL,
            method = "MarkAsRead",
            data = Unit,
            query = "memberId=$memberId&messageId=$messageId"
        )
    }
}
