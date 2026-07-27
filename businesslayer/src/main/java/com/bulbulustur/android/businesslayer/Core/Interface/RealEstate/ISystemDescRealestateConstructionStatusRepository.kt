package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateConstructionStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateConstructionStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateConstructionStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateConstructionStatusRepository {
    suspend fun GetSystemDescRealestateConstructionStatussAsync(): Result<List<SystemDescRealestateConstructionStatusDTO>>
    suspend fun GetSystemDescRealestateConstructionStatusByIdAsync(systemDescRealestateConstructionStatusId: Int): Result<SystemDescRealestateConstructionStatusUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateConstructionStatusInsertModel): Result<SystemDescRealestateConstructionStatusInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateConstructionStatusUpdateModel): Result<SystemDescRealestateConstructionStatusUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateConstructionStatusId: Int): Result<Unit>
}