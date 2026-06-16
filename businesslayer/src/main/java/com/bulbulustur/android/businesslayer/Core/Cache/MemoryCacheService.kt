package com.bulbulustur.android.businesslayer.Core.Cache

class MemoryCacheService {

    private data class CacheItem(
        val value: Any,
        val expireAt: Long
    )

    private val cache = mutableMapOf<String, CacheItem>()

    @Suppress("UNCHECKED_CAST")
    fun <T> Get(cacheKey: String): T? {
        val item = cache[cacheKey] ?: return null

        if (System.currentTimeMillis() > item.expireAt) {
            cache.remove(cacheKey)
            return null
        }

        return item.value as? T
    }

    fun <T> Set(
        cacheKey: String,
        value: T,
        durationMillis: Long
    ) {
        cache[cacheKey] = CacheItem(
            value = value as Any,
            expireAt = System.currentTimeMillis() + durationMillis
        )
    }

    fun Clear(cacheKey: String) {
        cache.remove(cacheKey)
    }

    fun ClearAll() {
        cache.clear()
    }
}