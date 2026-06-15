package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CookieCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICookieCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CookieCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CookieCategoryRepository(
    private val apiClient: ApiClient
) : ICookieCategoryRepository {

    override suspend fun GetCookieCategoryListAsync(): Result<List<CookieCategoryDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCookieCategoryByIdAsync(
        cookieCategoryId: Int
    ): Result<CookieCategoryUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCookieCategoryByIdExtendedAsync(
        cookieCategoryId: Int
    ): Result<CookieCategoryDTO?> {
        TODO("Not implemented yet")
    }
}
