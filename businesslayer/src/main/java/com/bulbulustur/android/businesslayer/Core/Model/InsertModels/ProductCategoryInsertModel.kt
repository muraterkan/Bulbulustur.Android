package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductCategoryInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Breadcrumb: String = "",
    val ParentId: Int = 0,
    val HaveChild: Boolean = false,
    val Picture: String = "",
    val Megamenu: Boolean = false,
    val MegamenuPicture: String = "",
    val MegamenuChild: Boolean = false,
    val CategoryLevel: Int = 0,
    val CategoryName: String = "",
    val CategoryDescriptionPicture: String = "",
    val EnglishTranslate: Boolean = false,
    val IconClass: String = ""
)
