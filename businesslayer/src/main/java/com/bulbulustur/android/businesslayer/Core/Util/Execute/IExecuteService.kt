package com.bulbulustur.android.businesslayer.Core.Util.Execute

import com.bulbulustur.android.businesslayer.Core.Cache.CacheStrategy
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IExecuteService {

    suspend fun <T> GetAsync(
        cacheKey: String = "",
        cacheDurationMillis: Long = ExecuteService.DefaultCacheDurationMillis,
        cacheStrategy: CacheStrategy = CacheStrategy.MEMORY_ONLY,
        hideException: Boolean = true,
        serviceMethod: suspend () -> Result<T>
    ): Result<T>

    suspend fun <T> PostAsync(
        operationType: String = "UNKNOWN",
        hideException: Boolean = true,
        serviceMethod: suspend () -> Result<T>
    ): Result<T>
}