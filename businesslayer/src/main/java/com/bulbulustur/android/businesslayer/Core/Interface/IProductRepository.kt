package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductDataDTO
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductFilterDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList

interface IProductRepository {

    suspend fun GetProductDataAsync(
        filters: B2CProductFilterDTO,
        page: Int = 1,
        pageSize: Int = 50
    ): Result<B2CProductDataDTO>

    suspend fun GetProductByIdAsync(
        productId: Int
    ): Result<ProductUpdateModel?>

    suspend fun GetProductByIdExtendedAsync(
        languageId: Int,
        storeId: Int,
        productId: Int,
        variantId: Int = 0
    ): Result<ProductDTO?>

    suspend fun GetStoreProductDataAsync(
        storeId: Int,
        filters: B2CProductFilterDTO,
        page: Int = 1,
        pageSize: Int = 50
    ): Result<B2CProductDataDTO>

    suspend fun GetOtherStorePrices(
        languageId: Int,
        productId: Int,
        variantId: Int
    ): Result<List<ProductVariantDTO>>
    suspend fun GetDefaultProductVariantPicturesAsync(variantIds: List<Int>): Result<Map<String, String>>


    suspend fun GetSearchingProductsAsync(storeId: Int = 0, key: String, page: Int = 1, pageSize: Int = 20, sortOrder: String = "Default_Asc"): Result<PaginatedList<ProductDTO>>
}
