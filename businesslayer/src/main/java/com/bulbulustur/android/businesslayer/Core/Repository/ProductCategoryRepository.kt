package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ProductCategoryRepository(
    private val apiClient: ApiClient = ApiClient
) : IProductCategoryRepository {

    override suspend fun GetProductCategoryListAsync():
            Result<List<ProductCategoryDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.PRODUCT_CATEGORY_PRODUCT_CATEGORIES_BASE_URL,
            method =
                "GetProductCategories",
            query =
                "languageId=1&count=30000"
        )
    }

    override suspend fun GetProductCategoryByIdAsync(
        productCategoryId: Int
    ): Result<ProductCategoryUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.PRODUCT_CATEGORY_PRODUCT_CATEGORIES_BASE_URL,
            method =
                "GetProductCategoryById",
            query =
                "languageId=1" +
                        "&productCategoryId=$productCategoryId"
        )
    }

    override suspend fun GetProductCategoryByIdExtendedAsync(
        productCategoryId: Int
    ): Result<ProductCategoryDTO?> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.PRODUCT_CATEGORY_PRODUCT_CATEGORIES_BASE_URL,
            method =
                "GetProductCategoryByIdExtended",
            query =
                "languageId=1" +
                        "&productCategoryId=$productCategoryId"
        )
    }

    override suspend fun GetProductChildCategoriesAsync(
        languageId: Int,
        productCategoryId: Int
    ): Result<List<ProductCategoryDTO>> {
        return apiClient.GetAsync(
            baseUrl =
                ApiRoutes.PRODUCT_CATEGORY_PRODUCT_CATEGORIES_BASE_URL,
            method =
                "GetProductChildCategories",
            query =
                "languageId=$languageId" +
                        "&productCategoryId=$productCategoryId"
        )
    }

    override suspend fun InsertAsync(
        model: ProductCategoryInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.PRODUCT_CATEGORY_PRODUCT_CATEGORIES_BASE_URL,
            method =
                "InsertAsync",
            data =
                model
        )
    }

    override suspend fun UpdateAsync(
        model: ProductCategoryUpdateModel
    ): Result<Unit> {
        return apiClient.PutAsync(
            baseUrl =
                ApiRoutes.PRODUCT_CATEGORY_PRODUCT_CATEGORIES_BASE_URL,
            method =
                "UpdateAsync",
            data =
                model
        )
    }

    override suspend fun DeleteAsync(
        productCategoryId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl =
                ApiRoutes.PRODUCT_CATEGORY_PRODUCT_CATEGORIES_BASE_URL,
            method =
                "DeleteAsync",
            query =
                "productCategoryId=$productCategoryId"
        )
    }
}