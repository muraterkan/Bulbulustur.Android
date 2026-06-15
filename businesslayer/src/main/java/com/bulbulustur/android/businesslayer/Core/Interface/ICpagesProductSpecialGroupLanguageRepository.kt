package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialGroupLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialGroupLanguageUpdateModel

interface ICpagesProductSpecialGroupLanguageRepository {

    suspend fun GetCpagesProductSpecialGroupLanguageListAsync(): Result<List<CpagesProductSpecialGroupLanguageDTO>>

    suspend fun GetCpagesProductSpecialGroupLanguageByIdAsync(
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<CpagesProductSpecialGroupLanguageUpdateModel?>

    suspend fun GetCpagesProductSpecialGroupLanguageByIdExtendedAsync(
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<CpagesProductSpecialGroupLanguageDTO?>
}
