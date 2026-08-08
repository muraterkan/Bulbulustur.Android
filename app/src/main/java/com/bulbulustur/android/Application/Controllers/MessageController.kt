package com.bulbulustur.android.Application.Controllers

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MessageControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val MessageListResult: Result<List<WholesaleMessageDTO>>? = null,
    val ThreadMessagesResult: Result<List<WholesaleMessageDTO>>? = null,
    val OtherUserResult: Result<MemberDTO>? = null,
    val UnreadCountResult: Result<Int>? = null,
    val InsertResult: Result<Any?>? = null,
    val ReplyResult: Result<Any?>? = null,
    val MarkAsReadResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
) {
    val Messages: List<WholesaleMessageDTO>
        get() = MessageListResult?.Data.orEmpty()

    val ThreadMessages: List<WholesaleMessageDTO>
        get() = ThreadMessagesResult?.Data.orEmpty()

    val OtherUser: MemberDTO?
        get() = OtherUserResult?.Data

    val UnreadCount: Int
        get() = UnreadCountResult?.Data ?: 0
}

class MessageController(
    private val executeService: IExecuteService,
    private val wholesaleMessageRepository: IWholesaleMessageRepository
) : BaseController() {

    private val _state = MutableStateFlow(MessageControllerState())
    val State: StateFlow<MessageControllerState> = _state.asStateFlow()

    fun Inbox(languageId: Int, memberId: Int, count: Int = 100) {
        if (!ValidateSession(languageId, memberId)) return

        viewModelScope.launch {
            SetLoading("Inbox")

            val response = executeService.GetAsync(cacheKey = "") {
                wholesaleMessageRepository.GetWholesaleMessagesAsync(languageId, memberId, count)
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    MessageListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun Thread(languageId: Int, memberId: Int, messageThreadId: Int, count: Int = 100) {
        if (!ValidateSession(languageId, memberId)) return

        if (messageThreadId <= 0) {
            SetError(BBLocalization.Current.Get(key = "13132b71-d5fc-4364-8b44-dbb07c93a8a8", fallback = "Geçerli bir mesaj konusu bulunamadı."))
            return
        }

        viewModelScope.launch {
            SetLoading("Thread")

            val response = executeService.GetAsync(cacheKey = "") {
                wholesaleMessageRepository.GetMessagesByThreadAsync(languageId, memberId, messageThreadId, count)
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    ThreadMessagesResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun OtherUser(languageId: Int, memberId: Int, messageThreadId: Int) {
        if (!ValidateSession(languageId, memberId)) return

        if (messageThreadId <= 0) {
            SetError(BBLocalization.Current.Get(key = "13132b71-d5fc-4364-8b44-dbb07c93a8a8", fallback = "Geçerli bir mesaj konusu bulunamadı."))
            return
        }

        viewModelScope.launch {
            SetLoading("OtherUser")

            val response = executeService.GetAsync(cacheKey = "") {
                wholesaleMessageRepository.GetOtherUserInThreadAsync(languageId, memberId, messageThreadId)
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    OtherUserResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun UnreadCount(memberId: Int) {
        if (memberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "44ec4745-86b3-4549-9d2b-5204ba073fdc", fallback = "Oturum açmanız gerekiyor."))
            return
        }

        viewModelScope.launch {
            SetLoading("UnreadCount")

            val response = executeService.GetAsync(cacheKey = "") {
                wholesaleMessageRepository.GetUnreadMessageCountAsync(memberId)
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    UnreadCountResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun SendFirstMessage(
        memberId: Int,
        recipientId: Int,
        body: String,
        onSuccess: ((Int) -> Unit)? = null
    ) {
        if (memberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "44ec4745-86b3-4549-9d2b-5204ba073fdc", fallback = "Oturum açmanız gerekiyor."))
            return
        }

        if (recipientId <= 0 || recipientId == memberId) {
            SetError("Geçerli bir alıcı bilgisi bulunamadı.")
            return
        }

        if (body.isBlank()) {
            SetError(BBLocalization.Current.Get(key = "35334200-711e-4e03-8b46-8810d2dc660c", fallback = "Mesaj içeriği boş olamaz."))
            return
        }

        viewModelScope.launch {
            SetLoading("Insert")

            val response = executeService.PostAsync(operationType = "b2b.Message.Insert") {
                wholesaleMessageRepository.InsertAsync(
                    memberId = memberId,
                    model = WholesaleMessageDTO(
                        InsertedBy = memberId,
                        InsertedDate = java.time.Instant.now().toString(),
                        Body = body.trim(),
                        SenderId = memberId,
                        RecipientId = recipientId
                    )
                )
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    InsertResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                onSuccess?.invoke(response.ResponseId)
            }
        }
    }

    fun Reply(
        memberId: Int,
        messageThreadId: Int,
        body: String,
        onSuccess: (() -> Unit)? = null
    ) {
        if (memberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "44ec4745-86b3-4549-9d2b-5204ba073fdc", fallback = "Oturum açmanız gerekiyor."))
            return
        }

        if (messageThreadId <= 0) {
            SetError(BBLocalization.Current.Get(key = "13132b71-d5fc-4364-8b44-dbb07c93a8a8", fallback = "Geçerli bir mesaj konusu bulunamadı."))
            return
        }

        if (body.isBlank()) {
            SetError(BBLocalization.Current.Get(key = "35334200-711e-4e03-8b46-8810d2dc660c", fallback = "Mesaj içeriği boş olamaz."))
            return
        }

        viewModelScope.launch {
            SetLoading("Reply")

            val response = executeService.PostAsync(operationType = "b2b.Message.Reply") {
                wholesaleMessageRepository.ReplyAsync(
                    memberId = memberId,
                    model = WholesaleMessageDTO(
                        InsertedBy = memberId,
                        InsertedDate = java.time.Instant.now().toString(),
                        MessageThreadId = messageThreadId,
                        Body = body.trim(),
                        SenderId = memberId
                    )
                )
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    ReplyResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                onSuccess?.invoke()
            }
        }
    }

    fun MarkAsRead(memberId: Int, messageId: Int) {
        if (memberId <= 0 || messageId <= 0) return

        viewModelScope.launch {
            val response = executeService.PostAsync(operationType = "b2b.Message.MarkAsRead") {
                wholesaleMessageRepository.MarkAsRead(memberId, messageId)
            }

            _state.update { currentState ->
                currentState.copy(
                    MarkAsReadResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    private fun ValidateSession(languageId: Int, memberId: Int): Boolean {
        if (languageId <= 0) {
            SetError(BBLocalization.Current.Get(key = "7f0d8334-1cf8-470b-a8f4-69f237488cde", fallback = "Geçerli bir dil bilgisi bulunamadı."))
            return false
        }

        if (memberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "44ec4745-86b3-4549-9d2b-5204ba073fdc", fallback = "Oturum açmanız gerekiyor."))
            return false
        }

        return true
    }

    private fun SetLoading(currentAction: String) {
        _state.update { currentState ->
            currentState.copy(IsLoading = true, CurrentAction = currentAction, ErrorMessage = null)
        }
    }

    private fun SetError(message: String) {
        _state.update { currentState ->
            currentState.copy(IsLoading = false, ErrorMessage = message)
        }
    }
}
