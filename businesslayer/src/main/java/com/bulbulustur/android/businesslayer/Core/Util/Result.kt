package com.bulbulustur.android.businesslayer.Core.Util

enum class ErrorType {
    None,
    BussinessRuleViolation,
    Exception
}

data class Result<T>(
    val Success: Boolean = true,
    val Message: String = "İşleminiz Gerçekleşti",
    val ErrorType: ErrorType = com.bulbulustur.android.businesslayer.Core.Util.ErrorType.None,
    val Exception: String? = null,
    val MessageReplacements: List<String> = emptyList(),
    val ResponseId: Int = 0,
    val ResponseKey: String? = null,
    val Data: T? = null,
    val Count: Int = 0
)