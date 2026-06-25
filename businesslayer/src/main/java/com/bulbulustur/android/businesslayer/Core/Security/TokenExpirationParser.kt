package com.bulbulustur.android.businesslayer.Core.Security

import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object TokenExpirationParser {

    fun Parse(
        value: String
    ): Instant? {
        if (value.isBlank()) {
            return null
        }

        ParseInstant(value)?.let {
            return it
        }

        ParseOffsetDateTime(value)?.let {
            return it
        }

        ParseLocalDateTime(value)?.let {
            return it
        }

        return null
    }

    fun IsExpired(
        value: String,
        now: Instant = Instant.now()
    ): Boolean {
        val expiration = Parse(value)
            ?: return true

        return !expiration.isAfter(now)
    }

    fun IsValid(
        value: String,
        now: Instant = Instant.now()
    ): Boolean {
        return !IsExpired(
            value = value,
            now = now
        )
    }

    private fun ParseInstant(
        value: String
    ): Instant? {
        return try {
            Instant.parse(value)
        } catch (_: DateTimeParseException) {
            null
        }
    }

    private fun ParseOffsetDateTime(
        value: String
    ): Instant? {
        return try {
            OffsetDateTime
                .parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .toInstant()
        } catch (_: DateTimeParseException) {
            null
        }
    }

    private fun ParseLocalDateTime(
        value: String
    ): Instant? {
        return try {
            /*
             * Backend DateTime değeri timezone bilgisi taşımıyorsa
             * UTC kabul ediyoruz.
             *
             * Authentication API Local time döndürüyorsa daha sonra
             * bu ZoneOffset.UTC kararını backend davranışına göre
             * güncelleyebiliriz.
             */
            LocalDateTime
                .parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .toInstant(ZoneOffset.UTC)
        } catch (_: DateTimeParseException) {
            ParseLocalDateTimeWithDeviceZone(value)
        }
    }

    private fun ParseLocalDateTimeWithDeviceZone(
        value: String
    ): Instant? {
        return try {
            LocalDateTime
                .parse(value)
                .atZone(ZoneId.systemDefault())
                .toInstant()
        } catch (_: DateTimeParseException) {
            null
        }
    }
}