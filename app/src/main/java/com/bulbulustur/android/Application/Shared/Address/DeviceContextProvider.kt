package com.bulbulustur.android.Application.Shared

import android.content.Context
import android.os.Build
import java.security.MessageDigest
import java.util.UUID

data class DeviceContext(
    val DeviceFingerprintHash: String,
    val Device: String,
    val Os: String,
    val Browser: String
)

class DeviceContextProvider(private val context: Context) {

    companion object {
        private const val PreferencesName = "bulbulustur_device"
        private const val InstallationIdKey = "installation_id"
    }

    fun Get(): DeviceContext {
        return DeviceContext(
            DeviceFingerprintHash = GetDeviceFingerprintHash(),
            Device = GetDeviceName(),
            Os = "Android ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})",
            Browser = "Bulbulustur Android"
        )
    }

    private fun GetDeviceFingerprintHash(): String {
        val preferences = context.getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)

        var installationId = preferences.getString(InstallationIdKey, null)

        if (installationId.isNullOrBlank()) {
            installationId = UUID.randomUUID().toString()

            preferences.edit()
                .putString(InstallationIdKey, installationId)
                .apply()
        }

        return Sha256(installationId)
    }

    private fun GetDeviceName(): String {
        val manufacturer = Build.MANUFACTURER.orEmpty().trim()
        val model = Build.MODEL.orEmpty().trim()

        return when {
            manufacturer.isBlank() && model.isBlank() -> "Android Device"
            manufacturer.isBlank() -> model
            model.isBlank() -> manufacturer
            model.startsWith(manufacturer, ignoreCase = true) -> model
            else -> "$manufacturer $model"
        }
    }

    private fun Sha256(value: String): String {
        return MessageDigest.getInstance("SHA-256")
            .digest(value.toByteArray(Charsets.UTF_8))
            .joinToString("") { "%02x".format(it) }
    }
}