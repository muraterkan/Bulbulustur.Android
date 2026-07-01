package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrowsingHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IProductBrowsingHistoryRepository {

    suspend fun GetProductBrowsingHistoriesAsync(
        memberId: Int,
        page: Int = 1,
        pageSize: Int = 20
    ): Result<PaginatedList<ProductBrowsingHistoryDTO>>

    suspend fun InsertProductBrowsingHistoryAsync(
        memberId: Int,
        model: ProductBrowsingHistoryInsertModel
    ): Result<Unit>

    suspend fun DeleteProductBrowsingHistoryAsync(
        memberId: Int,
        browsingHistoryId: Int
    ): Result<Unit>

    suspend fun DeleteAllProductBrowsingHistoriesAsync(
        memberId: Int
    ): Result<Unit>
}