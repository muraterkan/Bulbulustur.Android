package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCommunicationStyleDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCommunicationStyleInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCommunicationStyleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescCommunicationStyleRepository {

    suspend fun GetSystemDescCommunicationStylesAsync(
        languageId: Int,
        count: Int
    ): Result<List<SystemDescCommunicationStyleDTO>>

    suspend fun GetSystemDescCommunicationStyleByIdAsync(
        systemDescCommunicationStyleId: Int
    ): Result<SystemDescCommunicationStyleUpdateModel?>

    suspend fun GetSystemDescCommunicationStyleByIdExtendedAsync(
        languageId: Int,
        systemDescCommunicationStyleId: Int
    ): Result<SystemDescCommunicationStyleDTO?>

    suspend fun InsertAsync(
        model: SystemDescCommunicationStyleInsertModel
    ): Result<Unit>

    suspend fun UpdateAsync(
        model: SystemDescCommunicationStyleUpdateModel
    ): Result<Unit>

    suspend fun DeleteAsync(
        systemDescCommunicationStyleId: Int
    ): Result<Unit>
}