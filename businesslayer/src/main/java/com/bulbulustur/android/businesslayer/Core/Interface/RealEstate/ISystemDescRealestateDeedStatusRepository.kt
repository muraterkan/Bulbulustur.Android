package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateDeedStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateDeedStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateDeedStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateDeedStatusRepository {
    suspend fun GetSystemDescRealestateDeedStatussAsync(): Result<List<SystemDescRealestateDeedStatusDTO>>
    suspend fun GetSystemDescRealestateDeedStatusByIdAsync(systemDescRealestateDeedStatusId: Int): Result<SystemDescRealestateDeedStatusUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateDeedStatusInsertModel): Result<SystemDescRealestateDeedStatusInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateDeedStatusUpdateModel): Result<SystemDescRealestateDeedStatusUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateDeedStatusId: Int): Result<Unit>
}