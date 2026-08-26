package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.B2BProductDataDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList

interface IWholesaleProductRepository {
    suspend fun GetProductDataAsync(languageId: Int, productCategoryId: Int = 0, page: Int = 1, pageSize: Int = 50, sortOrder: String = "Name_Desc", brandIds: String = ""): Result<B2BProductDataDTO>

    suspend fun GetProductByIdExtendedAsync(languageId: Int, wholesaleProductId: Int): Result<WholesaleProductDTO?>

    suspend fun GetProductRelatedsAsync(languageId: Int, wholesaleProductId: Int, count: Int = 10): Result<List<WholesaleProductRelatedDTO>>

    suspend fun GetSearchingProductsAsync(companyId: Int = 0, key: String, page: Int = 1, pageSize: Int = 20, sortOrder: String = "Default_Asc"): Result<PaginatedList<WholesaleProductDTO>>
}
