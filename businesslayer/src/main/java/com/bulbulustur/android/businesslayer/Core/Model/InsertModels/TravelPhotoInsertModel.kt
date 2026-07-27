package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelPhotoInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelId: Int,
    val photoUrl: String,
    val displayOrder: Int,
    val isCover: Boolean
)