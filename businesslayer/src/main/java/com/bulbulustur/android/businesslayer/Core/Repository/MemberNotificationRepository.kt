package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberNotificationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberNotificationRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberNotificationRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberNotificationRepository {

    override suspend fun GetMemberNotificationsAsync(
        memberId: Int,
        applicationId: Int,
        count: Int
    ): Result<List<MemberNotificationDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Notification/GetMemberNotificationsAsync",
            query = "memberId=$memberId&applicationId=$applicationId&count=$count"
        )
    }

    override suspend fun GetUnreadMemberNotificationCountAsync(
        memberId: Int,
        applicationId: Int
    ): Result<Int> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Notification/GetUnreadMemberNotificationCountAsync",
            query = "memberId=$memberId&applicationId=$applicationId"
        )
    }

    override suspend fun GetMemberNotificationByIdAsync(
        memberId: Int,
        notificationId: Int
    ): Result<MemberNotificationDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Notification/GetMemberNotificationByIdAsync",
            query = "memberId=$memberId&notificationId=$notificationId"
        )
    }

    override suspend fun MarkMemberNotificationAsReadAsync(
        memberId: Int,
        notificationId: Int
    ): Result<Any?> {
        return apiClient.PutAsync<Unit, Any?>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Notification/MarkMemberNotificationAsReadAsync",
            query = "memberId=$memberId&notificationId=$notificationId",
            data = Unit
        )
    }

    override suspend fun DeleteMemberNotificationAsync(
        memberId: Int,
        notificationId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Notification/DeleteMemberNotificationAsync",
            query = "memberId=$memberId&notificationId=$notificationId"
        )
    }
}
