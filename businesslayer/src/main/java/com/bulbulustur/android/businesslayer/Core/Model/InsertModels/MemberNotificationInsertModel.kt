package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberNotificationInsertModel(
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
