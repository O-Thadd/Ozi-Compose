package com.othadd.ozi.di

import com.othadd.ozi.domain.useCases.implementations.AppStateUseCasesImpl
import com.othadd.ozi.domain.useCases.implementations.GetFormattedDateTimeUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.NotificationUseCasesImpl
import com.othadd.ozi.domain.useCases.implementations.ProcessSignalsUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.chat.CreatePairChatUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.chat.DeleteChatsUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.chat.GetChatsUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.chat.GetSingleChatUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.message.GetMessagesUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.message.MarkMessagesReadUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.message.SendMessageUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.user.GetUsersUseCasesImpl
import com.othadd.ozi.domain.useCases.implementations.user.LoginUserUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.user.LogoutUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.user.RegisterUserUseCaseImpl
import com.othadd.ozi.domain.useCases.implementations.user.ThisUserUseCasesImpl
import com.othadd.ozi.domain.useCases.interfaces.AppStateUseCases
import com.othadd.ozi.domain.useCases.interfaces.chat.CreatePairChatUseCase
import com.othadd.ozi.domain.useCases.interfaces.chat.DeleteChatsUseCase
import com.othadd.ozi.domain.useCases.interfaces.chat.GetChatsUseCase
import com.othadd.ozi.domain.useCases.interfaces.GetFormattedDateTimeUseCase
import com.othadd.ozi.domain.useCases.interfaces.message.GetMessagesUseCase
import com.othadd.ozi.domain.useCases.interfaces.chat.GetSingleChatUseCase
import com.othadd.ozi.domain.useCases.interfaces.user.GetUsersUseCases
import com.othadd.ozi.domain.useCases.interfaces.user.LoginUserUseCase
import com.othadd.ozi.domain.useCases.interfaces.user.LogoutUseCase
import com.othadd.ozi.domain.useCases.interfaces.message.MarkMessagesReadUseCase
import com.othadd.ozi.domain.useCases.interfaces.NotificationUseCases
import com.othadd.ozi.domain.useCases.interfaces.ProcessSignalsUseCase
import com.othadd.ozi.domain.useCases.interfaces.user.RegisterUserUseCase
import com.othadd.ozi.domain.useCases.interfaces.message.SendMessageUseCase
import com.othadd.ozi.domain.useCases.interfaces.user.ThisUserUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    @Binds
    abstract fun bindAppStateUseCases(implementation: AppStateUseCasesImpl): AppStateUseCases

    @Binds
    abstract fun bindCreatePairChatUseCase(implementation: CreatePairChatUseCaseImpl): CreatePairChatUseCase

    @Binds
    abstract fun bindDeleteChatsUseCase(implementation: DeleteChatsUseCaseImpl): DeleteChatsUseCase

    @Binds
    abstract fun bindGetChatsUseCase(implementation: GetChatsUseCaseImpl): GetChatsUseCase

    @Binds
    abstract fun bindFormattedDateTimeUseCase(implementation: GetFormattedDateTimeUseCaseImpl): GetFormattedDateTimeUseCase

    @Binds
    abstract fun bindGetMessagesUseCase(implementation: GetMessagesUseCaseImpl): GetMessagesUseCase

    @Binds
    abstract fun bindGetSingleChatUseCase(implementation: GetSingleChatUseCaseImpl): GetSingleChatUseCase

    @Binds
    abstract fun bindGetUsersUseCase(implementation: GetUsersUseCasesImpl): GetUsersUseCases

    @Binds
    abstract fun bindLoginUseCase(implementation: LoginUserUseCaseImpl): LoginUserUseCase

    @Binds
    abstract fun bindLogoutUseCase(implementation: LogoutUseCaseImpl): LogoutUseCase

    @Binds
    abstract fun bindMarkMessagesReadUseCase(implementation: MarkMessagesReadUseCaseImpl): MarkMessagesReadUseCase

    @Binds
    abstract fun bindNotificationUseCase(implementation: NotificationUseCasesImpl): NotificationUseCases

    @Binds
    abstract fun bindProcessSignalsUseCase(implementation: ProcessSignalsUseCaseImpl): ProcessSignalsUseCase

    @Binds
    abstract fun bindRegisterUserUseCase(implementation: RegisterUserUseCaseImpl): RegisterUserUseCase

    @Binds
    abstract fun bindSendMessageUseCase(implementation: SendMessageUseCaseImpl): SendMessageUseCase

    @Binds
    abstract fun bindThisUserUseCases(implementation: ThisUserUseCasesImpl): ThisUserUseCases
}