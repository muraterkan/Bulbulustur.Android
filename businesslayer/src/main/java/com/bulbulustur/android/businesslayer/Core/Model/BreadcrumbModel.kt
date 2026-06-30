package com.bulbulustur.android.businesslayer.Core.Models

data class BreadcrumbModel(
    val ProductCategoryId: Int = 0,
    val CategoryName: String = "",
    val CategoryLevel: Int = 0
)

data class IdsModel(
    val ProductCategoryId: Int = 0
)