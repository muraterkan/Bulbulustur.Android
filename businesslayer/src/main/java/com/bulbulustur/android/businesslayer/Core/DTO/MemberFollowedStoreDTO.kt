package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberFollowedStoreDTO(
    val MemberFollowedStoreId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val StoreId: Int = 0,
    val FollowedStoresType: String = "",
    val MemberName: String = "",
    val Company: String = "",
    val Store: String = "",
    val Logo: String = ""
)