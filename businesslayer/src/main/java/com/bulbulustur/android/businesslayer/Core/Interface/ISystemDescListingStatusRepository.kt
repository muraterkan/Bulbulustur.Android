package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescListingStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescListingStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescListingStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescListingStatusRepository {

    suspend fun GetSystemDescListingStatusesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescListingStatusDTO>>

    suspend fun GetSystemDescListingStatusByIdAsync(
        systemDescListingStatusId: Int
    ): Result<SystemDescListingStatusUpdateModel?>

    suspend fun GetSystemDescListingStatusByIdExtendedAsync(
        languageId: Int,
        systemDescListingStatusId: Int
    ): Result<SystemDescListingStatusDTO?>

    suspend fun InsertAsync(
        model: SystemDescListingStatusInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescListingStatusUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescListingStatusId: Int
    ): Result<Unit>
}