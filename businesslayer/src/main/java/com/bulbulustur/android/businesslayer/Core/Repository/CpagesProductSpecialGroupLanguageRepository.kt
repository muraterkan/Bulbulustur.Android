package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialGroupLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICpagesProductSpecialGroupLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialGroupLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CpagesProductSpecialGroupLanguageRepository(
    private val apiClient: ApiClient
) : ICpagesProductSpecialGroupLanguageRepository {

    override suspend fun GetCpagesProductSpecialGroupLanguageListAsync(): Result<List<CpagesProductSpecialGroupLanguageDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCpagesProductSpecialGroupLanguageByIdAsync(
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<CpagesProductSpecialGroupLanguageUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCpagesProductSpecialGroupLanguageByIdExtendedAsync(
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<CpagesProductSpecialGroupLanguageDTO?> {
        TODO("Not implemented yet")
    }
}
