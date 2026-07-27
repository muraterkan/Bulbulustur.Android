package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateSwapStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateSwapStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateSwapStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateSwapStatusRepository {
    suspend fun GetSystemDescRealestateSwapStatussAsync(): Result<List<SystemDescRealestateSwapStatusDTO>>
    suspend fun GetSystemDescRealestateSwapStatusByIdAsync(systemDescRealestateSwapStatusId: Int): Result<SystemDescRealestateSwapStatusUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateSwapStatusInsertModel): Result<SystemDescRealestateSwapStatusInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateSwapStatusUpdateModel): Result<SystemDescRealestateSwapStatusUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateSwapStatusId: Int): Result<Unit>
}