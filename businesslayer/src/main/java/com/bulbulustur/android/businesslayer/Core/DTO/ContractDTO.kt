package com.bulbulustur.android.businesslayer.Core.DTO

data class ContractDTO(
    val ContractId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val OrderKey: String = "",
    val StoreKey: String = "",
    val MemberId: Int = 0,
    val ContractTypeId: Int = 0,
    val ContractText: String = "",
    val Version: Int? = null
)
