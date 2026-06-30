package com.bulbulustur.android.businesslayer.Core.Util

data class PaginatedList<T>(
    val Items: List<T> = emptyList(),
    val PageNumber: Int = 1,
    val PageSize: Int = 0,
    val TotalItemCount: Int = 0,
    val TotalPageCount: Int = 0,
    val MetaData: Any? = null
) {

    val HasPreviousPage: Boolean
        get() = PageNumber > 1

    val HasNextPage: Boolean
        get() = PageNumber < TotalPageCount

    val IsFirstPage: Boolean
        get() = PageNumber <= 1

    val IsLastPage: Boolean
        get() = TotalPageCount == 0 || PageNumber >= TotalPageCount

    val IsEmpty: Boolean
        get() = Items.isEmpty()
}