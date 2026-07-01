package com.bulbulustur.android.businesslayer.Core.Security

import android.util.Base64
import org.json.JSONObject
import java.nio.charset.StandardCharsets

object JwtMemberIdParser {

    private val MemberIdClaimNames =
        listOf(
            "MemberId",
            "memberId",
            "memberid",
            "nameid",
            "sub",
            "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier"
        )

    fun Parse(
        token: String
    ): Int {
        if (token.isBlank()) {
            return 0
        }

        return runCatching {
            val tokenParts =
                token.split('.')

            if (tokenParts.size < 2) {
                return@runCatching 0
            }

            val payloadPart =
                tokenParts[1]

            val decodedPayload =
                Base64.decode(
                    NormalizeBase64Payload(
                        value = payloadPart
                    ),
                    Base64.URL_SAFE or
                            Base64.NO_WRAP or
                            Base64.NO_PADDING
                )

            val payloadJson =
                String(
                    decodedPayload,
                    StandardCharsets.UTF_8
                )

            val jsonObject =
                JSONObject(
                    payloadJson
                )

            ResolveMemberId(
                jsonObject = jsonObject
            )
        }.getOrDefault(
            0
        )
    }

    private fun ResolveMemberId(
        jsonObject: JSONObject
    ): Int {
        MemberIdClaimNames.forEach { claimName ->
            if (!jsonObject.has(claimName)) {
                return@forEach
            }

            val claimValue =
                jsonObject.opt(
                    claimName
                )

            val memberId =
                when (claimValue) {
                    is Int ->
                        claimValue

                    is Long ->
                        claimValue.toInt()

                    is Double ->
                        claimValue.toInt()

                    is Number ->
                        claimValue.toInt()

                    is String ->
                        claimValue.toIntOrNull()
                            ?: 0

                    else ->
                        0
                }

            if (memberId > 0) {
                return memberId
            }
        }

        return 0
    }

    private fun NormalizeBase64Payload(
        value: String
    ): String {
        val remainder =
            value.length % 4

        return when (remainder) {
            2 ->
                "$value=="

            3 ->
                "$value="

            else ->
                value
        }
    }
}