package com.bulbulustur.android.businesslayer.Core.DTO

data class CookieProviderDTO(
    val CookieProviderId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Guid: String = "",
    val ProviderCode: String = "",
    val ProviderName: String = "",
    val ProviderPolicyUrl: String = "",
    val IsFirstParty: Boolean = false,
    val DisplayOrder: Int = 0,
    val LastUpdated: String = ""
)
