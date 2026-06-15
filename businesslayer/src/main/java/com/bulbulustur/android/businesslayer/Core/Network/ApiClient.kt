package com.bulbulustur.android.businesslayer.Core.Network

import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

object ApiClient {

    private const val DEFAULT_BASE_URL = "http://37.60.239.76:30215/"

    @PublishedApi
    internal val gson: Gson = Gson()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(DEFAULT_BASE_URL)
        .build()

    @PublishedApi
    internal val genericApi: GenericApi = retrofit.create(GenericApi::class.java)

    suspend inline fun <reified T> GetAsync(
        baseUrl: String,
        method: String,
        query: String? = null
    ): Result<T> {
        val url = BuildUrl(baseUrl, method, query)
        val response = genericApi.GetAsync(url)
        return ParseResult(response)
    }

    suspend inline fun <reified TRequest, reified TResponse> PostAsync(
        baseUrl: String,
        method: String,
        data: TRequest
    ): Result<TResponse> {
        val url = BuildUrl(baseUrl, method, null)
        val response = genericApi.PostAsync(url, data as Any)
        return ParseResult(response)
    }

    suspend inline fun <reified T> DeleteAsync(
        baseUrl: String,
        method: String,
        query: String? = null
    ): Result<T> {
        val url = BuildUrl(baseUrl, method, query)
        val response = genericApi.DeleteAsync(url)
        return ParseResult(response)
    }

    fun BuildUrl(
        baseUrl: String,
        method: String,
        query: String?
    ): String {
        val cleanBaseUrl = baseUrl.trimEnd('/')
        val cleanMethod = method.trimStart('/')

        return if (query.isNullOrBlank()) {
            "$cleanBaseUrl/$cleanMethod"
        } else {
            "$cleanBaseUrl/$cleanMethod?$query"
        }
    }

    @PublishedApi
    internal inline fun <reified T> ParseResult(
        response: Response<ResponseBody>
    ): Result<T> {
        if (!response.isSuccessful) {
            return Result(
                Success = false,
                Message = "HTTP hata: ${response.code()} ${response.message()}"
            )
        }

        val json = response.body()?.string().orEmpty()

        if (json.isBlank()) {
            return Result(
                Success = false,
                Message = "Boş yanıt alındı."
            )
        }

        val type = object : TypeToken<Result<T>>() {}.type
        return gson.fromJson(json, type)
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
        @Body body: Any
    ): Response<ResponseBody>

    @DELETE
    suspend fun DeleteAsync(
        @Url url: String
    ): Response<ResponseBody>
}