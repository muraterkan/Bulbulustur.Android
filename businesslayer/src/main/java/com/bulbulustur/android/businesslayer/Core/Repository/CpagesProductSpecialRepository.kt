package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICpagesProductSpecialRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CpagesProductSpecialRepository(
    private val apiClient: ApiClient
) : ICpagesProductSpecialRepository {

    override suspend fun GetCpagesProductSpecialListAsync(): Result<List<CpagesProductSpecialDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCpagesProductSpecialByIdAsync(
        cpagesProductSpecialId: Int
    ): Result<CpagesProductSpecialUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCpagesProductSpecialByIdExtendedAsync(
        cpagesProductSpecialId: Int
    ): Result<CpagesProductSpecialDTO?> {
        TODO("Not implemented yet")
    }
}
