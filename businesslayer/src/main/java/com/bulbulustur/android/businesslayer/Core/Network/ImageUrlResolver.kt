package com.bulbulustur.android.businesslayer.Core.Network

object ImageUrlResolver {

    private const val WebApplicationOrigin =
        "https://dww.bulbulustur.com"

    fun Resolve(
        imagePath: String?
    ): String {
        val normalizedPath =
            imagePath
                ?.trim()
                .orEmpty()

        if (normalizedPath.isBlank()) {
            return ""
        }

        if (
            normalizedPath.startsWith(
                "http://",
                ignoreCase = true
            ) ||
            normalizedPath.startsWith(
                "https://",
                ignoreCase = true
            )
        ) {
            return normalizedPath
        }

        return "$WebApplicationOrigin/${normalizedPath.trimStart('/')}"
    }
}
