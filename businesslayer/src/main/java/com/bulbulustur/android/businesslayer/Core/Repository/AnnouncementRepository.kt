package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.AnnouncementDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAnnouncementRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AnnouncementInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AnnouncementUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AnnouncementRepository(
    private val apiClient: ApiClient = ApiClient
) : IAnnouncementRepository {

    override suspend fun GetAnnouncementsAsync(count: Int): Result<List<AnnouncementDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_ANNOUNCEMENT_BASE_URL,
            method = "GetAnnouncementsAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetAnnouncementByIdAsync(announcementId: Int): Result<AnnouncementUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_ANNOUNCEMENT_BASE_URL,
            method = "GetAnnouncementByIdAsync",
            query = "announcementId=$announcementId"
        )
    }

    override suspend fun GetAnnouncementByIdExtendedAsync(announcementId: Int): Result<AnnouncementDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_ANNOUNCEMENT_BASE_URL,
            method = "GetAnnouncementByIdExtendedAsync",
            query = "announcementId=$announcementId"
        )
    }

    override suspend fun InsertAsync(model: AnnouncementInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_ANNOUNCEMENT_BASE_URL,
            method = "AnnouncementInsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: AnnouncementUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_ANNOUNCEMENT_BASE_URL,
            method = "AnnouncementUpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(announcementId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.PURE_ANNOUNCEMENT_BASE_URL,
            method = "AnnouncementDelete",
            query = "announcementId=$announcementId"
        )
    }
}
