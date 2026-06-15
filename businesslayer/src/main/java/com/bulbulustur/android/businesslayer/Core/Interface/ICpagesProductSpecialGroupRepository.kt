package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialGroupDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialGroupUpdateModel

interface ICpagesProductSpecialGroupRepository {

    suspend fun GetCpagesProductSpecialGroupListAsync(): Result<List<CpagesProductSpecialGroupDTO>>

    suspend fun GetCpagesProductSpecialGroupByIdAsync(
        cpagesProductSpecialGroupId: Int
    ): Result<CpagesProductSpecialGroupUpdateModel?>

    suspend fun GetCpagesProductSpecialGroupByIdExtendedAsync(
        cpagesProductSpecialGroupId: Int
    ): Result<CpagesProductSpecialGroupDTO?>
}
