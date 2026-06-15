package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SupportConditionInsertModel(
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
