package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBillDisciplineDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescBillDisciplineInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescBillDisciplineUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescBillDisciplineRepository {

    suspend fun GetSystemDescBillDisciplinesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescBillDisciplineDTO>>

    suspend fun GetSystemDescBillDisciplineByIdAsync(
        systemDescBillDisciplineId: Int
    ): Result<SystemDescBillDisciplineUpdateModel?>

    suspend fun GetSystemDescBillDisciplineByIdExtendedAsync(
        languageId: Int,
        systemDescBillDisciplineId: Int
    ): Result<SystemDescBillDisciplineDTO?>

    suspend fun InsertAsync(
        model: SystemDescBillDisciplineInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescBillDisciplineUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescBillDisciplineId: Int
    ): Result<Unit>
}