package com.bulbulustur.android.Core.DTO

data class ResultDTO<T>(
    val data: T? = null,
    val success: Boolean? = null,
    val message: String? = null
)