package com.othadd.ozi.ui.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.othadd.ozi.common.GROUP_CHAT_NAME
import com.othadd.ozi.common.getOtherParticipantId
import com.othadd.ozi.domain.useCases.chat.GetChatUseCase
import com.othadd.ozi.domain.useCases.message.GetMessagesUseCase
import com.othadd.ozi.domain.useCases.message.MarkMessagesReadUseCase
import com.othadd.ozi.domain.useCases.message.SendMessageUseCase
import com.othadd.ozi.domain.useCases.user.GetUsersUseCases
import com.othadd.ozi.domain.useCases.user.ThisUserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

const val CHAT_ID_KEY = "chatId"

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getUsersUseCases: GetUsersUseCases,
    private val sendMessageUseCase: SendMessageUseCase,
    private val markMessagesReadUseCase: MarkMessagesReadUseCase,
    private val getChatUseCase: GetChatUseCase,
    getMessagesUseCase: GetMessagesUseCase,
    thisUserUseCases: ThisUserUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var uiState = MutableStateFlow(ChatUiState.DEFAULT)
    private lateinit var chatMateId: String
    private lateinit var thisUserId: String
    val chatId: String = checkNotNull(savedStateHandle[CHAT_ID_KEY])

    fun sendMessage(messageBody: String) {
        viewModelScope.launch {
            sendMessageUseCase(messageBody, chatId)
        }
    }

    fun markMessagesRead() {
        viewModelScope.launch {
            markMessagesReadUseCase(chatId)
        }
    }

    /**
     * Returns participantsIds except this user
     */
    suspend fun getParticipantsIds(): List<String> {
        return getChatUseCase.invoke(chatId).first().participantIds.toMutableList().apply { remove(thisUserId) }
    }

    init {
        viewModelScope.launch {
            thisUserId = thisUserUseCases.get().first()!!.userId
            val chatItemsFlow = getMessagesUseCase(chatId)
            val isPairChat = chatId.length > 40

            val chatUiStateFlow = if (isPairChat) {
                chatMateId = getOtherParticipantId(chatId, thisUserId)
                val userFlow = getUsersUseCases.getUserFlow(chatMateId)
                combine(userFlow, chatItemsFlow) { chatMate, chatItems ->
                    ChatUiState(chatId, chatMate.username, chatMate.aviFg, chatMate.aviBg, chatMate.verified, chatItems)
                }
            } else {
                chatItemsFlow.map {
                    ChatUiState(chatId, GROUP_CHAT_NAME, 200, 200, false, it)
                }
            }

            chatUiStateFlow.collect {
                uiState.value = it
            }
        }
    }
}