package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MessageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MessageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MessageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMessageRepository {

    suspend fun GetMessagesAsync(
        count: Int
    ): Result<List<MessageDTO>>

    suspend fun GetMessagesByConversationIdAsync(
        conversationId: Int,
        count: Int
    ): Result<List<MessageDTO>>

    suspend fun GetMessageByIdAsync(
        messageId: Int
    ): Result<MessageUpdateModel?>

    suspend fun GetMessageByIdExtendedAsync(
        messageId: Int
    ): Result<MessageDTO?>

    suspend fun InsertAsync(
        model: MessageInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: MessageUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        messageId: Int
    ): Result<Unit>
}