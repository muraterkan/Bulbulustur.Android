package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCoupleAcceptanceTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCoupleAcceptanceTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCoupleAcceptanceTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescCoupleAcceptanceTypeRepository {

    suspend fun GetSystemDescCoupleAcceptanceTypesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescCoupleAcceptanceTypeDTO>>

    suspend fun GetSystemDescCoupleAcceptanceTypeByIdAsync(
        systemDescCoupleAcceptanceTypeId: Int
    ): Result<SystemDescCoupleAcceptanceTypeUpdateModel?>

    suspend fun GetSystemDescCoupleAcceptanceTypeByIdExtendedAsync(
        languageId: Int,
        systemDescCoupleAcceptanceTypeId: Int
    ): Result<SystemDescCoupleAcceptanceTypeDTO?>

    suspend fun InsertAsync(
        model: SystemDescCoupleAcceptanceTypeInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescCoupleAcceptanceTypeUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescCoupleAcceptanceTypeId: Int
    ): Result<Unit>
}