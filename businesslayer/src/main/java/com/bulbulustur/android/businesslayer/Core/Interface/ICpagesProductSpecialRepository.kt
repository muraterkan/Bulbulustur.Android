package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialUpdateModel

interface ICpagesProductSpecialRepository {

    suspend fun GetCpagesProductSpecialListAsync(): Result<List<CpagesProductSpecialDTO>>

    suspend fun GetCpagesProductSpecialByIdAsync(
        cpagesProductSpecialId: Int
    ): Result<CpagesProductSpecialUpdateModel?>

    suspend fun GetCpagesProductSpecialByIdExtendedAsync(
        cpagesProductSpecialId: Int
    ): Result<CpagesProductSpecialDTO?>
}
