package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateUsageStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateUsageStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateUsageStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateUsageStatusRepository {
    suspend fun GetSystemDescRealestateUsageStatussAsync(): Result<List<SystemDescRealestateUsageStatusDTO>>
    suspend fun GetSystemDescRealestateUsageStatusByIdAsync(systemDescRealestateUsageStatusId: Int): Result<SystemDescRealestateUsageStatusUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateUsageStatusInsertModel): Result<SystemDescRealestateUsageStatusInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateUsageStatusUpdateModel): Result<SystemDescRealestateUsageStatusUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateUsageStatusId: Int): Result<Unit>
}