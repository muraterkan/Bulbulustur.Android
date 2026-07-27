package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateRoomCountDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateRoomCountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateRoomCountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateRoomCountRepository {
    suspend fun GetSystemDescRealestateRoomCountsAsync(): Result<List<SystemDescRealestateRoomCountDTO>>
    suspend fun GetSystemDescRealestateRoomCountByIdAsync(systemDescRealestateRoomCountId: Int): Result<SystemDescRealestateRoomCountUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateRoomCountInsertModel): Result<SystemDescRealestateRoomCountInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateRoomCountUpdateModel): Result<SystemDescRealestateRoomCountUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateRoomCountId: Int): Result<Unit>
}