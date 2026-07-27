package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescComplaintTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescComplaintTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescComplaintTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescComplaintTypeRepository {

    suspend fun GetSystemDescComplaintTypesAsync(
        count: Int
    ): Result<List<SystemDescComplaintTypeDTO>>

    suspend fun GetSystemDescComplaintTypeByIdAsync(
        systemDescComplaintTypeId: Int
    ): Result<SystemDescComplaintTypeUpdateModel?>

    suspend fun GetSystemDescComplaintTypeByIdExtendedAsync(
        systemDescComplaintTypeId: Int
    ): Result<SystemDescComplaintTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescComplaintTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescComplaintTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescComplaintTypeId: Int
    ): Result<Unit>
}