package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialGroupDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICpagesProductSpecialGroupRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CpagesProductSpecialGroupRepository(
    private val apiClient: ApiClient
) : ICpagesProductSpecialGroupRepository {

    override suspend fun GetCpagesProductSpecialGroupListAsync(): Result<List<CpagesProductSpecialGroupDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCpagesProductSpecialGroupByIdAsync(
        cpagesProductSpecialGroupId: Int
    ): Result<CpagesProductSpecialGroupUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCpagesProductSpecialGroupByIdExtendedAsync(
        cpagesProductSpecialGroupId: Int
    ): Result<CpagesProductSpecialGroupDTO?> {
        TODO("Not implemented yet")
    }
}
