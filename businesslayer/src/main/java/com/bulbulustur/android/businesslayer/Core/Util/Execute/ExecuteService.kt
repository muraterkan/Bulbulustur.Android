package com.bulbulustur.android.businesslayer.Core.Util.Execute

import android.util.Log
import com.bulbulustur.android.businesslayer.Core.Cache.CacheStrategy
import com.bulbulustur.android.businesslayer.Core.Cache.MemoryCacheService
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ExecuteService(
    private val memoryCacheService: MemoryCacheService = MemoryCacheService()
) : IExecuteService {

    companion object {
        const val DefaultCacheDurationMillis: Long = 15L * 24L * 60L * 60L * 1000L
        private const val LogTag = "ExecuteService"
    }

    override suspend fun <T> GetAsync(
        cacheKey: String,
        cacheDurationMillis: Long,
        cacheStrategy: CacheStrategy,
        hideException: Boolean,
        serviceMethod: suspend () -> Result<T>
    ): Result<T> {
        val startTime = System.currentTimeMillis()

        return try {
            if (
                cacheStrategy == CacheStrategy.MEMORY_ONLY &&
                cacheKey.isNotBlank()
            ) {
                val cachedResult = memoryCacheService.Get<Result<T>>(cacheKey)

                if (cachedResult != null) {
                    Log.d(LogTag, "Cache HIT: $cacheKey")
                    return cachedResult
                }
            }

            val result = serviceMethod()

            if (
                cacheStrategy == CacheStrategy.MEMORY_ONLY &&
                cacheKey.isNotBlank() &&
                result.Data != null
            ) {
                memoryCacheService.Set(
                    cacheKey = cacheKey,
                    value = result,
                    durationMillis = cacheDurationMillis
                )
            }

            val elapsed = System.currentTimeMillis() - startTime
            Log.d(LogTag, "GetAsync completed in ${elapsed}ms. cacheKey=$cacheKey")

            result
        } catch (exception: Exception) {
            val elapsed = System.currentTimeMillis() - startTime

            Log.e(
                LogTag,
                "GetAsync failed in ${elapsed}ms. cacheKey=$cacheKey",
                exception
            )

            if (hideException) {
                Result(
                    Success = false,
                    Message = "An error occurred while executing the service.",
                    Exception = exception.message,
                    Data = null
                )
            } else {
                throw exception
            }
        }
    }

    override suspend fun <T> PostAsync(
        operationType: String,
        hideException: Boolean,
        serviceMethod: suspend () -> Result<T>
    ): Result<T> {
        val startTime = System.currentTimeMillis()

        return try {
            val result = serviceMethod()

            val elapsed = System.currentTimeMillis() - startTime
            Log.d(LogTag, "PostAsync completed in ${elapsed}ms. operation=$operationType")

            result
        } catch (exception: Exception) {
            val elapsed = System.currentTimeMillis() - startTime

            Log.e(
                LogTag,
                "PostAsync failed in ${elapsed}ms. operation=$operationType",
                exception
            )

            if (hideException) {
                Result(
                    Success = false,
                    Message = "An unexpected error occurred.",
                    Exception = exception.message,
                    Data = null
                )
            } else {
                throw exception
            }
        }
    }

    override fun ClearMemoryCache() {
        memoryCacheService.ClearAll()
    }
}