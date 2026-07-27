package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ConversationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ConversationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ConversationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IConversationRepository {

    suspend fun GetConversationsAsync(
        count: Int
    ): Result<List<ConversationDTO>>

    suspend fun GetConversationsByCreatedByMemberIdAsync(
        createdByMemberId: Int,
        count: Int
    ): Result<List<ConversationDTO>>

    suspend fun GetConversationByIdAsync(
        conversationId: Int
    ): Result<ConversationUpdateModel?>

    suspend fun GetConversationByIdExtendedAsync(
        conversationId: Int
    ): Result<ConversationDTO?>

    suspend fun InsertAsync(
        model: ConversationInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: ConversationUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        conversationId: Int
    ): Result<Unit>
}