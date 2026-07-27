package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateBalconyStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateBalconyStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateBalconyStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateBalconyStatusRepository {
    suspend fun GetSystemDescRealestateBalconyStatussAsync(): Result<List<SystemDescRealestateBalconyStatusDTO>>
    suspend fun GetSystemDescRealestateBalconyStatusByIdAsync(systemDescRealestateBalconyStatusId: Int): Result<SystemDescRealestateBalconyStatusUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateBalconyStatusInsertModel): Result<SystemDescRealestateBalconyStatusInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateBalconyStatusUpdateModel): Result<SystemDescRealestateBalconyStatusUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateBalconyStatusId: Int): Result<Unit>
}