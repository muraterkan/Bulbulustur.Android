package com.bulbulustur.android.Application.Authentication

import com.bulbulustur.android.Application.Localization.BBLocalization

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

sealed interface GoogleCredentialResult {

    data class Success(
        val IdToken: String
    ) : GoogleCredentialResult

    data class Failure(
        val Message: String
    ) : GoogleCredentialResult

    data object Cancelled :
        GoogleCredentialResult
}

class GoogleCredentialService(
    private val context: Context
) {

    private val credentialManager =
        CredentialManager.create(
            context
        )

    suspend fun SignIn(
        serverClientId: String
    ): GoogleCredentialResult {
        if (serverClientId.isBlank()) {
            return GoogleCredentialResult.Failure(
                Message =
                    BBLocalization.Current.Get(key = "7ec50dbe-a2e4-4fdc-9ba5-512fac58da90", fallback = "Google Web Client ID bulunamadı.")
            )
        }

        val googleOption =
            GetSignInWithGoogleOption
                .Builder(
                    serverClientId =
                        serverClientId
                )
                .build()

        val request =
            GetCredentialRequest
                .Builder()
                .addCredentialOption(
                    googleOption
                )
                .build()

        return try {
            val response =
                credentialManager.getCredential(
                    context =
                        context,
                    request =
                        request
                )

            val credential =
                response.credential

            if (
                credential !is CustomCredential ||
                credential.type !=
                GoogleIdTokenCredential
                    .TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                return GoogleCredentialResult.Failure(
                    Message =
                        BBLocalization.Current.Get(key = "0c33b2d4-55f3-44b6-8fdf-b066a580708d", fallback = "Google hesabından geçerli kimlik bilgisi alınamadı.")
                )
            }

            val googleCredential =
                GoogleIdTokenCredential
                    .createFrom(
                        credential.data
                    )

            val idToken =
                googleCredential.idToken
                    .trim()

            if (idToken.isBlank()) {
                return GoogleCredentialResult.Failure(
                    Message =
                        BBLocalization.Current.Get(key = "099d48b3-bf9c-45d7-94fb-e73666c4c4d0", fallback = "Google ID token alınamadı.")
                )
            }

            GoogleCredentialResult.Success(
                IdToken =
                    idToken
            )
        } catch (
            exception: GetCredentialCancellationException
        ) {
            GoogleCredentialResult.Cancelled
        } catch (
            exception: GetCredentialException
        ) {
            GoogleCredentialResult.Failure(
                Message =
                    exception.message
                        ?.takeIf {
                            it.isNotBlank()
                        }
                        ?: BBLocalization.Current.Get(key = "52454665-eabf-4165-8e2f-9a0d22625311", fallback = "Google hesap seçimi tamamlanamadı.")
            )
        } catch (
            exception: Exception
        ) {
            GoogleCredentialResult.Failure(
                Message =
                    exception.message
                        ?.takeIf {
                            it.isNotBlank()
                        }
                        ?: BBLocalization.Current.Get(key = "fe83ee3b-0e2f-40c5-9447-c60e53b89b52", fallback = "Google ile giriş sırasında hata oluştu.")
            )
        }
    }
}