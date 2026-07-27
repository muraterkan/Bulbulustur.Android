package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPropertyFeatureTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescPropertyFeatureTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPropertyFeatureTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescPropertyFeatureTypeRepository {

    suspend fun GetSystemDescPropertyFeatureTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescPropertyFeatureTypeDTO>>

    suspend fun GetSystemDescPropertyFeatureTypeByIdAsync(
        systemDescPropertyFeatureTypeId: Int
    ): Result<SystemDescPropertyFeatureTypeUpdateModel?>

    suspend fun GetSystemDescPropertyFeatureTypeByIdExtendedAsync(
        languageId: Int,
        systemDescPropertyFeatureTypeId: Int
    ): Result<SystemDescPropertyFeatureTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescPropertyFeatureTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescPropertyFeatureTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescPropertyFeatureTypeId: Int
    ): Result<Unit>
}