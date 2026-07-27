package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescHomeClothingComfortDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescHomeClothingComfortInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescHomeClothingComfortUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescHomeClothingComfortRepository {

    suspend fun GetSystemDescHomeClothingComfortsAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescHomeClothingComfortDTO>>

    suspend fun GetSystemDescHomeClothingComfortByIdAsync(
        systemDescHomeClothingComfortId: Int
    ): Result<SystemDescHomeClothingComfortUpdateModel?>

    suspend fun GetSystemDescHomeClothingComfortByIdExtendedAsync(
        languageId: Int,
        systemDescHomeClothingComfortId: Int
    ): Result<SystemDescHomeClothingComfortDTO?>

    suspend fun InsertAsync(
        model: SystemDescHomeClothingComfortInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescHomeClothingComfortUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescHomeClothingComfortId: Int
    ): Result<Unit>
}