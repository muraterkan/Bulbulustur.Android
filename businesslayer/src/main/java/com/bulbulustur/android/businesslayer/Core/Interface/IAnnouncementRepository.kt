package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AnnouncementDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AnnouncementInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AnnouncementUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAnnouncementRepository {

    suspend fun GetAnnouncementsAsync(count: Int): Result<List<AnnouncementDTO>>

    suspend fun GetAnnouncementByIdAsync(announcementId: Int): Result<AnnouncementUpdateModel?>

    suspend fun GetAnnouncementByIdExtendedAsync(announcementId: Int): Result<AnnouncementDTO?>

    suspend fun InsertAsync(model: AnnouncementInsertModel): Result<Unit>

    suspend fun UpdateAsync(model: AnnouncementUpdateModel): Result<Unit>

    suspend fun DeleteAsync(announcementId: Int): Result<Unit>
}
