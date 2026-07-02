package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberAgreementDTO(
    val MemberAgreementId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ServiceType: String = "",
    val MemberId: Int = 0,
    val MemberKey: String = "",
    val StoreRequestKey: String = "",
    val Status: String = "",
    val AgreementHtml: String = "",
    val ApprovedDate: String? = null,
    val PhoneNumber: String = "",
    val SmsCode: String = "",
    val SmsVerifiedDate: String? = null,
    val IpAddress: String = "",
    val UserAgent: String = ""
)