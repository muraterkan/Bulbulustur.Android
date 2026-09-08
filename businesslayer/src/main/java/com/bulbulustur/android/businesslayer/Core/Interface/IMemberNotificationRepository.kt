package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberNotificationDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberNotificationRepository {

    suspend fun GetMemberNotificationsAsync(
        memberId: Int,
        applicationId: Int,
        count: Int = 100
    ): Result<List<MemberNotificationDTO>>

    suspend fun GetUnreadMemberNotificationCountAsync(
        memberId: Int,
        applicationId: Int
    ): Result<Int>

    suspend fun GetMemberNotificationByIdAsync(
        memberId: Int,
        notificationId: Int
    ): Result<MemberNotificationDTO?>

    suspend fun MarkMemberNotificationAsReadAsync(
        memberId: Int,
        notificationId: Int
    ): Result<Any?>

    suspend fun DeleteMemberNotificationAsync(
        memberId: Int,
        notificationId: Int
    ): Result<Unit>
}
