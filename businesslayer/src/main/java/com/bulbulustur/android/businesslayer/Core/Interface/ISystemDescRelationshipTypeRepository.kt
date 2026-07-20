package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRelationshipTypeDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRelationshipTypeRepository {

    suspend fun GetRelationshipTypesAsync(languageId: Int, count: Int): Result<List<SystemDescRelationshipTypeDTO>>
}
