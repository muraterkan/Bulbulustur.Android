package com.bulbulustur.android.Application.Controllers

import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAppDefaultRepository {

    suspend fun GetAsync(
        actionName: String,
        parameters: Map<String, Any?> = emptyMap()
    ): Result<Any?>

    suspend fun PostAsync(
        actionName: String,
        body: Any? = null
    ): Result<Any?>
}
