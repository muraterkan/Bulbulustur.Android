package com.bulbulustur.android.businesslayer.Core.DTO

data class CookieConsentDTO(
    val CookieConsentId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Guid: String = "",
    val ConsentKey: String = "",
    val VisitorId: String? = null,
    val MemberId: Int? = null,
    val LanguageId: Int = 0,
    val CookiePolicyVersionId: Int = 0,
    val ConsentSource: String = "",
    val ConsentAction: String = "",
    val IpAddressHash: String = "",
    val UserAgentHash: String = "",
    val ExpiresDate: String? = null,
    val LastUpdated: String = ""
)
