package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySupplierDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategorySupplierRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductCategorySupplierRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductCategorySupplierRepository {

    override suspend fun GetWholesaleProductCategorySuppliers(languageId: Int, productCategoryId: Int, count: Int): Result<List<WholesaleProductCategorySupplierDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_CATEGORY_SUPPLIER_BASE_URL,
            method = "GetWholesaleProductCategorySuppliers",
            query = "languageId=$languageId&productCategoryId=$productCategoryId&count=$count"
        )
    }
}
