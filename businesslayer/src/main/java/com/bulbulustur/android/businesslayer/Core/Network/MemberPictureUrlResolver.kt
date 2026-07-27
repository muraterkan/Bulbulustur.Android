package com.bulbulustur.android.businesslayer.Core.Network

object MemberPictureUrlResolver {

    private const val WebApplicationOrigin =
        "https://www.bulbulustur.com"

    fun Resolve(
        picture: String?
    ): String {
        val normalizedPicture =
            picture
                ?.trim()
                .orEmpty()

        if (normalizedPicture.isBlank()) {
            return ""
        }

        if (
            normalizedPicture.startsWith(
                "http://",
                ignoreCase = true
            ) ||
            normalizedPicture.startsWith(
                "https://",
                ignoreCase = true
            )
        ) {
            return normalizedPicture
        }

        return "$WebApplicationOrigin/${normalizedPicture.trimStart('/')}"
    }
}
