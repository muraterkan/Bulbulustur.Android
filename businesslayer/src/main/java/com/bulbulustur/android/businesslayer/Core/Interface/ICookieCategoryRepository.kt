package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CookieCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieCategoryUpdateModel

interface ICookieCategoryRepository {

    suspend fun GetCookieCategoryListAsync(): Result<List<CookieCategoryDTO>>

    suspend fun GetCookieCategoryByIdAsync(
        cookieCategoryId: Int
    ): Result<CookieCategoryUpdateModel?>

    suspend fun GetCookieCategoryByIdExtendedAsync(
        cookieCategoryId: Int
    ): Result<CookieCategoryDTO?>
}
