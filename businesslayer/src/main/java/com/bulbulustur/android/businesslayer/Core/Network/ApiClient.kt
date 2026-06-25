package com.bulbulustur.android.businesslayer.Core.Network

import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

object ApiClient {

    private const val DEFAULT_BASE_URL =
        "http://37.60.239.76:30215/"

    @PublishedApi
    internal const val JSON_MEDIA_TYPE =
        "application/json; charset=utf-8"

    @PublishedApi
    internal val gson: Gson =
        GsonBuilder()
            .setFieldNamingStrategy { field ->
                field.name.replaceFirstChar { firstCharacter ->
                    firstCharacter.lowercase()
                }
            }
            .create()

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(DEFAULT_BASE_URL)
            .build()

    @PublishedApi
    internal val genericApi: GenericApi =
        retrofit.create(
            GenericApi::class.java
        )

    suspend inline fun <reified T> GetAsync(
        baseUrl: String,
        method: String,
        query: String? = null
    ): Result<T> {
        val url =
            BuildUrl(
                baseUrl = baseUrl,
                method = method,
                query = query
            )

        val response =
            genericApi.GetAsync(
                url = url
            )

        return ParseResult(
            response = response
        )
    }

    suspend inline fun <
            reified TRequest,
            reified TResponse
            > PostAsync(
        baseUrl: String,
        method: String,
        data: TRequest
    ): Result<TResponse> {
        val url =
            BuildUrl(
                baseUrl = baseUrl,
                method = method,
                query = null
            )

        val json =
            gson.toJson(data)

        val mediaType =
            MediaType.parse(
                JSON_MEDIA_TYPE
            )

        val requestBody =
            RequestBody.create(
                mediaType,
                json
            )

        val response =
            genericApi.PostAsync(
                url = url,
                body = requestBody
            )

        return ParseResult(
            response = response
        )
    }

    suspend inline fun <reified T> DeleteAsync(
        baseUrl: String,
        method: String,
        query: String? = null
    ): Result<T> {
        val url =
            BuildUrl(
                baseUrl = baseUrl,
                method = method,
                query = query
            )

        val response =
            genericApi.DeleteAsync(
                url = url
            )

        return ParseResult(
            response = response
        )
    }

    fun BuildUrl(
        baseUrl: String,
        method: String,
        query: String?
    ): String {
        val cleanBaseUrl =
            baseUrl.trimEnd('/')

        val cleanMethod =
            method.trimStart('/')

        return if (
            query.isNullOrBlank()
        ) {
            "$cleanBaseUrl/$cleanMethod"
        } else {
            "$cleanBaseUrl/$cleanMethod?$query"
        }
    }

    @PublishedApi
    internal inline fun <reified T> ParseResult(
        response: Response<ResponseBody>
    ): Result<T> {
        val responseBody =
            if (response.isSuccessful) {
                response.body()
            } else {
                response.errorBody()
            }

        val json =
            responseBody
                ?.string()
                .orEmpty()

        if (json.isBlank()) {
            return if (response.isSuccessful) {
                Result(
                    Success = false,
                    Message = "Boş yanıt alındı."
                )
            } else {
                Result(
                    Success = false,
                    Message =
                        "HTTP hata: ${response.code()} ${response.message()}"
                )
            }
        }

        return try {
            val type =
                object :
                    TypeToken<Result<T>>() {
                }.type

            val parsedResult =
                gson.fromJson<Result<T>>(
                    json,
                    type
                )

            if (response.isSuccessful) {
                parsedResult
            } else {
                parsedResult.copy(
                    Success = false,
                    Message =
                        parsedResult.Message.ifBlank {
                            "HTTP hata: ${response.code()} ${response.message()}"
                        }
                )
            }
        } catch (
            exception: JsonSyntaxException
        ) {
            Result(
                Success = false,
                Message = if (
                    response.isSuccessful
                ) {
                    "Sunucu yanıtı çözümlenemedi."
                } else {
                    "HTTP hata: ${response.code()} ${response.message()}"
                },
                Exception =
                    exception.message
            )
        } catch (
            exception: Exception
        ) {
            Result(
                Success = false,
                Message =
                    "Sunucu yanıtı işlenirken hata oluştu.",
                Exception =
                    exception.message
            )
        }
    }
}

interface GenericApi {

    @GET
    suspend fun GetAsync(
        @Url url: String
    ): Response<ResponseBody>

    @POST
    suspend fun PostAsync(
        @Url url: String,
        @Body body: RequestBody
    ): Response<ResponseBody>

    @DELETE
    suspend fun DeleteAsync(
        @Url url: String
    ): Response<ResponseBody>
}