package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.B2BProductDataDTO
import com.bulbulustur.android.businesslayer.Core.DTO.B2BProductFilterDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleProductRepository {

    suspend fun GetProductDataAsync(
        filters: B2BProductFilterDTO,
        page: Int = 1,
        pageSize: Int = 50
    ): Result<B2BProductDataDTO>

    suspend fun GetProductByIdExtendedAsync(
        languageId: Int,
        wholesaleProductId: Int
    ): Result<WholesaleProductDTO?>

    suspend fun GetProductRelatedsAsync(
        languageId: Int,
        wholesaleProductId: Int,
        count: Int = 10
    ): Result<List<WholesaleProductRelatedDTO>>

    suspend fun GetSearchingProductsAsync(
        companyId: Int = 0,
        key: String,
        page: Int = 1,
        pageSize: Int = 20,
        sortOrder: String = "Default_Asc"
    ): Result<PaginatedList<WholesaleProductDTO>>
}