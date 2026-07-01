package com.bulbulustur.android.businesslayer.Core.Security

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import com.google.gson.Gson
import java.nio.charset.StandardCharsets
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class SecureTokenStore(
    context: Context
) {

    private val applicationContext: Context =
        context.applicationContext

    private val preferences =
        applicationContext.getSharedPreferences(
            PreferenceFileName,
            Context.MODE_PRIVATE
        )

    private val gson =
        Gson()

    @Synchronized
    fun SaveTokens(
        accessToken: String,
        refreshToken: String,
        expiration: String,
        memberId: Int
    ): Boolean {
        require(accessToken.isNotBlank()) {
            "AccessToken boş olamaz."
        }

        require(refreshToken.isNotBlank()) {
            "RefreshToken boş olamaz."
        }

        require(expiration.isNotBlank()) {
            "Expiration boş olamaz."
        }

        require(memberId > 0) {
            "MemberId geçerli olmalıdır."
        }

        val tokenModel =
            SecureTokenModel(
                AccessToken =
                    accessToken,
                RefreshToken =
                    refreshToken,
                Expiration =
                    expiration,
                MemberId =
                    memberId
            )

        return try {
            val json =
                gson.toJson(
                    tokenModel
                )

            val encryptedPayload =
                Encrypt(
                    plainText = json
                )

            preferences
                .edit()
                .putString(
                    EncryptedTokenPayloadKey,
                    encryptedPayload
                )
                .commit()
        } catch (_: Exception) {
            false
        }
    }

    @Synchronized
    fun ReadTokens(): SecureTokenModel? {
        val encryptedPayload =
            preferences.getString(
                EncryptedTokenPayloadKey,
                null
            ) ?: return null

        return try {
            val json =
                Decrypt(
                    encryptedPayload = encryptedPayload
                )

            val tokenModel =
                gson.fromJson(
                    json,
                    SecureTokenModel::class.java
                )

            if (!tokenModel.HasTokens) {
                Clear()
                null
            } else {
                tokenModel
            }
        } catch (_: Exception) {
            Clear()
            null
        }
    }

    @Synchronized
    fun Clear(): Boolean {
        return preferences
            .edit()
            .remove(
                EncryptedTokenPayloadKey
            )
            .commit()
    }

    fun HasStoredTokens(): Boolean {
        return preferences.contains(
            EncryptedTokenPayloadKey
        )
    }

    private fun Encrypt(
        plainText: String
    ): String {
        val cipher =
            Cipher.getInstance(
                CipherTransformation
            )

        cipher.init(
            Cipher.ENCRYPT_MODE,
            GetOrCreateSecretKey()
        )

        val plainBytes =
            plainText.toByteArray(
                StandardCharsets.UTF_8
            )

        val cipherBytes =
            cipher.doFinal(
                plainBytes
            )

        val iv =
            cipher.iv

        val encodedIv =
            Base64.encodeToString(
                iv,
                Base64.NO_WRAP
            )

        val encodedCipherText =
            Base64.encodeToString(
                cipherBytes,
                Base64.NO_WRAP
            )

        return "$encodedIv$PayloadSeparator$encodedCipherText"
    }

    private fun Decrypt(
        encryptedPayload: String
    ): String {
        val payloadParts =
            encryptedPayload.split(
                PayloadSeparator,
                limit = 2
            )

        require(payloadParts.size == 2) {
            "Şifreli token payload formatı geçersiz."
        }

        val iv =
            Base64.decode(
                payloadParts[0],
                Base64.NO_WRAP
            )

        val cipherText =
            Base64.decode(
                payloadParts[1],
                Base64.NO_WRAP
            )

        val cipher =
            Cipher.getInstance(
                CipherTransformation
            )

        val parameterSpec =
            GCMParameterSpec(
                GcmTagLength,
                iv
            )

        cipher.init(
            Cipher.DECRYPT_MODE,
            GetOrCreateSecretKey(),
            parameterSpec
        )

        val plainBytes =
            cipher.doFinal(
                cipherText
            )

        return String(
            plainBytes,
            StandardCharsets.UTF_8
        )
    }

    private fun GetOrCreateSecretKey(): SecretKey {
        val keyStore =
            KeyStore.getInstance(
                AndroidKeyStoreProvider
            )

        keyStore.load(
            null
        )

        val existingKey =
            keyStore.getKey(
                KeyAlias,
                null
            )

        if (existingKey is SecretKey) {
            return existingKey
        }

        val keyGenerator =
            KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                AndroidKeyStoreProvider
            )

        val keySpecification =
            KeyGenParameterSpec.Builder(
                KeyAlias,
                KeyProperties.PURPOSE_ENCRYPT or
                        KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(
                    KeyProperties.BLOCK_MODE_GCM
                )
                .setEncryptionPaddings(
                    KeyProperties.ENCRYPTION_PADDING_NONE
                )
                .setKeySize(
                    AesKeySize
                )
                .setRandomizedEncryptionRequired(
                    true
                )
                .build()

        keyGenerator.init(
            keySpecification
        )

        return keyGenerator.generateKey()
    }

    private companion object {

        const val PreferenceFileName =
            "bulbulustur_secure_auth"

        const val EncryptedTokenPayloadKey =
            "encrypted_token_payload"

        const val AndroidKeyStoreProvider =
            "AndroidKeyStore"

        const val KeyAlias =
            "bulbulustur_auth_token_key"

        const val CipherTransformation =
            "AES/GCM/NoPadding"

        const val PayloadSeparator =
            ":"

        const val AesKeySize =
            256

        const val GcmTagLength =
            128
    }
}