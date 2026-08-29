package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AdvertSponsoredInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AdvertSponsoredUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAdvertSponsoredRepository {
    suspend fun GetAdvertSponsoredListAsync(): Result<List<AdvertSponsoredDTO>>
    suspend fun GetAdvertSponsoredByIdAsync(advertSponsoredId: Int): Result<AdvertSponsoredUpdateModel?>
    suspend fun GetAdvertSponsoredByIdExtendedAsync(advertSponsoredId: Int): Result<AdvertSponsoredDTO?>
    suspend fun GetSponsoredAdvertsAsync(languageId: Int, productCategoryId: Int, count: Int = 8): Result<List<AdvertSponsoredDTO>>
    suspend fun GetSponsoredAdvertsByProductCategoryListAsync(languageId: Int, productCategoryIds: List<Int>, count: Int = 8): Result<List<AdvertSponsoredDTO>>
    suspend fun GetSponsoredAdvertsByProductCategoryListPagedAsync(languageId: Int, productCategoryIds: List<Int>, page: Int, pageSize: Int): Result<PaginatedList<AdvertSponsoredDTO>>
    suspend fun InsertAsync(model: AdvertSponsoredInsertModel): Result<Unit>
    suspend fun UpdateAsync(model: AdvertSponsoredUpdateModel): Result<Unit>
    suspend fun DeleteAsync(advertSponsoredId: Int): Result<Unit>
}
