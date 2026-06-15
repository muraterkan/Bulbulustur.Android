package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SupportConditionSubClauseDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISupportConditionSubClauseRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SupportConditionSubClauseUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class SupportConditionSubClauseRepository(
    private val apiClient: ApiClient
) : ISupportConditionSubClauseRepository {

    override suspend fun GetSupportConditionSubClauseListAsync(): Result<List<SupportConditionSubClauseDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSupportConditionSubClauseByIdAsync(
        supportConditionSubClauseId: Int
    ): Result<SupportConditionSubClauseUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetSupportConditionSubClauseByIdExtendedAsync(
        supportConditionSubClauseId: Int
    ): Result<SupportConditionSubClauseDTO?> {
        TODO("Not implemented yet")
    }
}
