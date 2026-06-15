package com.bulbulustur.android.businesslayer.Core.DTO

data class SupportConditionDTO(
    val SupportConditionId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ConditionTypeId: Int = 0,
    val ConditionTitle: String = "",
    val ConditionDefinition: String = "",
    val UpdatedDate: String = "",
    val ProjectId: Int = 0,
    val ConditionAlert: String = ""
)
