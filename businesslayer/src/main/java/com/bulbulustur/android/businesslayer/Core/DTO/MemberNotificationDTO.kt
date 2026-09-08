package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberNotificationDTO(
    val MemberNotificationId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val ApplicationId: Int = 0,
    val NotificationTypeId: Int = 0,
    val Notification: String = "",
    val Link: String? = null,
    val IsRead: Boolean = false
)
