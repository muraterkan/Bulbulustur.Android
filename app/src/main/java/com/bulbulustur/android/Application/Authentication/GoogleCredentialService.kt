package com.bulbulustur.android.Application.Authentication

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
                    "Google Web Client ID bulunamadı."
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
                        "Google hesabından geçerli kimlik bilgisi alınamadı."
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
                        "Google ID token alınamadı."
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
                        ?: "Google hesap seçimi tamamlanamadı."
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
                        ?: "Google ile giriş sırasında hata oluştu."
            )
        }
    }
}