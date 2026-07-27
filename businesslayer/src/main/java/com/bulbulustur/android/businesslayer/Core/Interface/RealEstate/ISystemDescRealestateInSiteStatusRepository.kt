package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateInSiteStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateInSiteStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateInSiteStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateInSiteStatusRepository {
    suspend fun GetSystemDescRealestateInSiteStatussAsync(): Result<List<SystemDescRealestateInSiteStatusDTO>>
    suspend fun GetSystemDescRealestateInSiteStatusByIdAsync(systemDescRealestateInSiteStatusId: Int): Result<SystemDescRealestateInSiteStatusUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateInSiteStatusInsertModel): Result<SystemDescRealestateInSiteStatusInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateInSiteStatusUpdateModel): Result<SystemDescRealestateInSiteStatusUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateInSiteStatusId: Int): Result<Unit>
}