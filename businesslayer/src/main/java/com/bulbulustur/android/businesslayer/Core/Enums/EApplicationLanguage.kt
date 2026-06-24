package com.bulbulustur.android.businesslayer.Core.Enums

enum class EApplicationLanguage(
    val Id: Int,
    val Code: String
) {
    Turkish(
        Id = 1,
        Code = "tr"
    ),

    English(
        Id = 2,
        Code = "en"
    );

    companion object {

        fun FromCode(
            code: String?
        ): EApplicationLanguage {
            return entries.firstOrNull { language ->
                language.Code.equals(
                    other = code,
                    ignoreCase = true
                )
            } ?: Turkish
        }

        fun FromId(
            id: Int?
        ): EApplicationLanguage {
            return entries.firstOrNull { language ->
                language.Id == id
            } ?: Turkish
        }
    }
}