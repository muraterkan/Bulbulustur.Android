package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleMessageRepository {

    suspend fun GetWholesaleMessagesAsync(languageId: Int, memberId: Int, count: Int): Result<List<WholesaleMessageDTO>>

    suspend fun GetMessagesByThreadAsync(languageId: Int, memberId: Int, messageThreadId: Int, count: Int): Result<List<WholesaleMessageDTO>>

    suspend fun GetOtherUserInThreadAsync(languageId: Int, memberId: Int, messageThreadId: Int): Result<MemberDTO>

    suspend fun GetUnreadMessageCountAsync(memberId: Int): Result<Int>

    suspend fun ReplyAsync(memberId: Int, model: WholesaleMessageDTO): Result<Any?>

    suspend fun MarkAsRead(memberId: Int, messageId: Int): Result<Any?>
}
