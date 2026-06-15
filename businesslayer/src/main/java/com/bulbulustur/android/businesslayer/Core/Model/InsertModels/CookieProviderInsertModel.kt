package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class CookieProviderInsertModel(
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
