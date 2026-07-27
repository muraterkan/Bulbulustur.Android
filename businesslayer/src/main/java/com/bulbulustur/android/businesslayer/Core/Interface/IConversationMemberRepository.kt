package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ConversationMemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ConversationMemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ConversationMemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IConversationMemberRepository {

    suspend fun GetConversationMembersAsync(
        count: Int
    ): Result<List<ConversationMemberDTO>>

    suspend fun GetConversationMembersByConversationIdAsync(
        conversationId: Int,
        count: Int
    ): Result<List<ConversationMemberDTO>>

    suspend fun GetConversationMemberByIdAsync(
        conversationMemberId: Int
    ): Result<ConversationMemberUpdateModel?>

    suspend fun GetConversationMemberByIdExtendedAsync(
        conversationMemberId: Int
    ): Result<ConversationMemberDTO?>

    suspend fun InsertAsync(
        model: ConversationMemberInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: ConversationMemberUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        conversationMemberId: Int
    ): Result<Unit>
}