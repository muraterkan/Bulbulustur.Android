package com.bulbulustur.android.businesslayer.Core.Enums

enum class EApplicationLanguage(
    val Code: String
) {
    Turkish("tr"),
    English("en");

    companion object {

        fun FromCode(
            code: String?
        ): EApplicationLanguage {
            return entries.firstOrNull {
                it.Code.equals(code, ignoreCase = true)
            } ?: Turkish
        }
    }
}