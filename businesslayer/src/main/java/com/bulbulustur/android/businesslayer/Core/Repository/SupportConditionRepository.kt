package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SupportConditionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISupportConditionRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SupportConditionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SupportConditionRepository(
    private val apiClient: ApiClient
) : ISupportConditionRepository {

    override suspend fun GetSupportConditionListAsync(): Result<List<SupportConditionDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSupportConditionByIdAsync(
        supportConditionId: Int
    ): Result<SupportConditionUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSupportConditionByIdExtendedAsync(
        supportConditionId: Int
    ): Result<SupportConditionDTO?> {
        TODO("Not implemented yet")
    }
}
