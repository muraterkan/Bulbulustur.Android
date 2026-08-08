package com.bulbulustur.android.businesslayer.Core.Model

data class ApplicationLanguage(
    val Id: Int = DefaultLanguageId,
    val Code: String = DefaultLanguageCode
) {
    companion object {
        const val DefaultLanguageId = 1
        const val DefaultLanguageCode = "tr"

        fun Create(id: Int?, code: String?): ApplicationLanguage {
            return ApplicationLanguage(
                Id = id?.takeIf { it > 0 } ?: DefaultLanguageId,
                Code = code?.trim()?.takeIf { it.isNotBlank() } ?: DefaultLanguageCode
            )
        }
    }
}